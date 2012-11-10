import java.util.HashMap;
import java.util.List;

import org.jdom.Element;
import org.junit.Test;

import jp.gr.java_conf.hakusai.wiki.WikiAccess;
import jp.gr.java_conf.hakusai.wiki.WikiEdit;
import junit.framework.TestCase;


public class TestWiki2 extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	@Test
	public void testWiki() {
		System.out.println("aaaaa");
		WikiAccess wAcs = new WikiAccess();
		Element root;
		try {
			root = wAcs.getWikiElement("PARAM");
			WikiEdit wEdit = new WikiEdit();
			List<HashMap<String,String>> hMapLst = wEdit.getContentsLst(root);

			for(HashMap<String,String> hMap : hMapLst ) {
				System.out.println("　　" + "ID：");
				System.out.println("　　　" + hMap.get("id"));
				System.out.println("　　" + "URL：");
				System.out.println("　　　" + hMap.get("url"));
				System.out.println("　　" + "TITLE：");
				System.out.println("　　　" + hMap.get("title"));
				System.out.println("　　" + "BODY：");
				System.out.println("　　　" + hMap.get("body"));
				System.out.println("　　" + "LENGTH：");
				System.out.println("　　　" + hMap.get("length"));
				System.out.println("　　" + "REDIRECT：");
				System.out.println("　　　" + hMap.get("redirect"));
				System.out.println("　　" + "STRICT：");
				System.out.println("　　　" + hMap.get("strict"));
				System.out.println("　　" + "DATETIME：");
				System.out.println("　　　" + hMap.get("datetime"));
			}
		} catch (InterruptedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
