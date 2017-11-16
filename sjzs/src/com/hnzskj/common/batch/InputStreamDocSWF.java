package com.hnzskj.common.batch;

import java.io.InputStream;

public class InputStreamDocSWF {
	private InputStream doc;
	private InputStream swf;
	private String name;
	private int doclentgh;
	private int 	swflentgh;
private 	String FileName;
	public InputStreamDocSWF(InputStream doc, InputStream swf, String name,
			int doclentgh, int swflentgh,String FileName) {
		super();
		this.doc = doc;
		this.swf = swf;
		this.name = name;
		this.doclentgh = doclentgh;
		this.swflentgh = swflentgh;
		this.FileName = FileName;
	}
	
	public InputStream getDoc() {
		return doc;
	}
	public void setDoc(InputStream doc) {
		this.doc = doc;
	}
	public InputStream getSwf() {
		return swf;
	}
	public void setSwf(InputStream swf) {
		this.swf = swf;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getDoclentgh() {
		return doclentgh;
	}

	public void setDoclentgh(int doclentgh) {
		this.doclentgh = doclentgh;
	}

	public int getSwflentgh() {
		return swflentgh;
	}

	public void setSwflentgh(int swflentgh) {
		this.swflentgh = swflentgh;
	}

	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	 

}
