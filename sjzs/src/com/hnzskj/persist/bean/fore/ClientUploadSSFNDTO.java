package com.hnzskj.persist.bean.fore;

public class ClientUploadSSFNDTO  extends ClientUploadDTO{
	
	private String writeDate;
	
	private String industry;
	
	private String keyWord;
	
	private String author;
	
	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	public String getNote1() {
		return note1;
	}

	public void setNote1(String note1) {
		this.note1 = note1;
	}

	public String getNote2() {
		return note2;
	}

	public void setNote2(String note2) {
		this.note2 = note2;
	}

	public String getNote3() {
		return note3;
	}

	public void setNote3(String note3) {
		this.note3 = note3;
	}

	public String getNote4() {
		return note4;
	}

	public void setNote4(String note4) {
		this.note4 = note4;
	}

	public String getNote5() {
		return note5;
	}

	public void setNote5(String note5) {
		this.note5 = note5;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "ClientUploadSSFNDTO [note1=" + note1 + ", note2=" + note2
				+ ", note3=" + note3 + ", note4=" + note4 + ", note5=" + note5
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
