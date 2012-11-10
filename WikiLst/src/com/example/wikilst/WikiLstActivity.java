package com.example.wikilst;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.gr.java_conf.hakusai.android.test.Book;
import jp.gr.java_conf.hakusai.util.ApiUtils;
import jp.gr.java_conf.hakusai.wiki.WikiAccess;
import jp.gr.java_conf.hakusai.wiki.WikiEdit;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class WikiLstActivity extends Activity implements TextWatcher{

	  // ListView
	  private ListView mLstBooks;
	  private EditText m_editText;

	  // ListViewに表示するデータ
	  private ArrayList<Book> mBooks = new ArrayList<Book>();

	  ArrayAdapter<Book> adapter = null;

	private final String wikiTopUrl = "http://wikipedia.simpleapi.net/api?keyword=";
	private final String wikiTopFoot = "&output=xml";
	private final String wikiIncHead = "http://ja.wikipedia.org/w/api.php?format=json&action=opensearch&search=";
	private final String wikiIncFoot = "&namespace=0&suggest=";

	private final int    bodyLen = 100;

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d("MyAppDoc:", "beforeTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " count:" + String.valueOf(count) +
                   " after:" + String.valueOf(after));
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("MyAppDoc:", "onTextChanged() s:" + s.toString() + " start:" + String.valueOf(start) + " before:" + String.valueOf(before) +
                   " count:" + String.valueOf(count));

        if(!StringUtils.isEmpty(s.toString())) {

        	adapter.clear();
    		WikiAccess wAcs = new WikiAccess();
    		Element root;
    		try {
    			root = wAcs.getWikiElement(s.toString());
    			WikiEdit wEdit = new WikiEdit();
    			List<HashMap<String,String>> hMapLst = wEdit.getContentsLst(root);

    		    count = 1;
    			for(HashMap<String,String> hMap : hMapLst ) {
    			    // ListViewに表示するデータを作成
    				adapter.add(new Book(count++, ApiUtils.apiSubstring(hMap.get("body"),100),hMap.get("url")));
    				System.out.println(hMap.get("body"));
    			}

    		    // ListViewにアダプタを設定
    		    adapter.notifyDataSetChanged();
//    		    mLstBooks.setAdapter(adapter);
    		} catch (InterruptedException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        Log.d("MyAppDoc:", "afterTextChanged()");
    }

	  /** Called when the activity is first created. */
	  @TargetApi(9)
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_wiki_lst);

	    m_editText = (EditText)findViewById(R.id.srchText);

	    m_editText.addTextChangedListener(this);

	    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());

	    // Viewを取得
	    mLstBooks = (ListView)this.findViewById(R.id.lstBooks);

	    // イベントハンドラを設定
	    mLstBooks.setOnItemClickListener(
	    new AdapterView.OnItemClickListener() {
	      @Override
	      public void onItemClick(AdapterView<?> parent,
	        View view, int position, long id) {
	        // 選択されたデータを取得
	        ListView listView = (ListView)parent;
	        Book book = (Book)listView.getItemAtPosition(position);

	        Uri uri = Uri.parse(book.getLink());
	        	Intent i = new Intent(Intent.ACTION_VIEW,uri);
	        	startActivity(i);
	      }
	    });

	    // ListViewにアダプタを設定
	    adapter = new ArrayAdapter<Book>(
	      this,
	      R.layout.row_book, // 1行分のレイアウトファイル
	      R.id.txtBookName,  // 上記レイアウト内のテキスト表示箇所のId
	      mBooks             // 表示対象のデータ
	      );
	    mLstBooks.setAdapter(adapter);

	  }
}
