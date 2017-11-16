package com.hnzskj.persist.bean.fore;

public class ClientUploadFFALDTO  extends ClientUploadDTO{
	
	private String author;
	
	private String department;
	
	private String ffalDateTime;
	
	private String awards;

	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getFfalDateTime() {
		return ffalDateTime;
	}

	public void setFfalDateTime(String ffalDateTime) {
		this.ffalDateTime = ffalDateTime;
	}

	public String getAwards() {
		return awards;
	}

	public void setAwards(String awards) {
		this.awards = awards;
	}

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

	@Override
	public String toString() {
		return "ClientUploadFFALDTO [author=" + author + ", awards=" + awards
				+ ", department=" + department + ", ffalDateTime="
				+ ffalDateTime + ", note1=" + note1 + ", note2=" + note2
				+ ", note3=" + note3 + ", note4=" + note4 + ", note5=" + note5
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
}
