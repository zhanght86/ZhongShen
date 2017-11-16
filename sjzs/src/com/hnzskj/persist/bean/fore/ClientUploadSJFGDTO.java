package com.hnzskj.persist.bean.fore;

public class ClientUploadSJFGDTO  extends ClientUploadDTO{
	
	private String lawNumber;
	
	private String lawOrg;
	
	private String lawDate;
	
	private String lawGrade;
	
	private String lawCategory;
	
	private String lawTrade;
	
	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	public String getLawNumber() {
		return lawNumber;
	}

	public void setLawNumber(String lawNumber) {
		this.lawNumber = lawNumber;
	}

	public String getLawOrg() {
		return lawOrg;
	}

	public void setLawOrg(String lawOrg) {
		this.lawOrg = lawOrg;
	}

	public String getLawDate() {
		return lawDate;
	}

	public void setLawDate(String lawDate) {
		this.lawDate = lawDate;
	}

	public String getLawGrade() {
		return lawGrade;
	}

	public void setLawGrade(String lawGrade) {
		this.lawGrade = lawGrade;
	}

	public String getLawCategory() {
		return lawCategory;
	}

	public void setLawCategory(String lawCategory) {
		this.lawCategory = lawCategory;
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

	public String getLawTrade() {
		return lawTrade;
	}

	public void setLawTrade(String lawTrade) {
		this.lawTrade = lawTrade;
	}

	@Override
	public String toString() {
		return "ClientUploadSJFGDTO [lawCategory=" + lawCategory + ", lawDate="
				+ lawDate + ", lawGrade=" + lawGrade + ", lawNumber="
				+ lawNumber + ", lawOrg=" + lawOrg + ", note1=" + note1
				+ ", note2=" + note2 + ", note3=" + note3 + ", note4=" + note4
				+ ", note5=" + note5 + ", toString()=" + super.toString() + "]";
	}

	
	
}
