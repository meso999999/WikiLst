package jp.gr.java_conf.hakusai.android.test;

public class Book {
	  private int mId;
	  private String mName;
	  private String link;

	  public Book(int id, String name,String link) {
	    mId = id;
	    mName = name;
	    this.link = link;
	  }

	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String toString() {
	    return mName;
	  }

	  public void setName(String name) {
	    mName = name;
	  }

	  public String getName() {
	    return mName;
	  }

	  public void setId(int id) {
	    mId = id;
	  }

	  public int getId() {
	    return mId;
	  }
	}