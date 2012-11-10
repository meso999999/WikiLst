import java.util.HashMap;
import java.util.List;

import jp.gr.java_conf.hakusai.android.test.Book;
import jp.gr.java_conf.hakusai.util.ApiUtils;
import jp.gr.java_conf.hakusai.wiki.WikiAccess;
import jp.gr.java_conf.hakusai.wiki.WikiEdit;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;


public class A2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "ガラス";
		System.out.println("aaaaa");
        if(!StringUtils.isEmpty(s.toString())) {

    		WikiAccess wAcs = new WikiAccess();
    		Element root;
    		try {
    			root = wAcs.getWikiElement("テスト");
    			WikiEdit wEdit = new WikiEdit();
    			List<HashMap<String,String>> hMapLst = wEdit.getContentsLst(root);

    			for(HashMap<String,String> hMap : hMapLst ) {
    			    // ListViewに表示するデータを作成
    				System.out.println(hMap.get("body"));
    			}

    		    // ListViewにアダプタを設定
//    		    mLstBooks.setAdapter(adapter);
    		} catch (InterruptedException e) {
    			// TODO 自動生成された catch ブロック
    			e.printStackTrace();
    		}
        }
    }
}

