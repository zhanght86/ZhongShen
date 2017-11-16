package com.hnzskj.persist.bean.fore;

public class ClientUploadDXYJDTO extends ClientUploadDTO{
	
	private String lawNo;
	
	private String tiao;
	
	private String kuan;
	
	private String writeDate;
	
	private String note1;

	private String note2;

	private String note3;

	private String note4;

	private String note5;

	public String getLawNo() {
		return lawNo;
	}

	public void setLawNo(String lawNo) {
		this.lawNo = lawNo;
	}

	public String getTiao() {
		return tiao;
	}

	public void setTiao(String tiao) {
		this.tiao = tiao;
	}

	public String getKuan() {
		return kuan;
	}

	public void setKuan(String kuan) {
		this.kuan = kuan;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
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
		return "ClientUploadDXYJDTO [kuan=" + kuan + ", lawNo=" + lawNo
				+ ", note1=" + note1 + ", note2=" + note2 + ", note3=" + note3
				+ ", note4=" + note4 + ", note5=" + note5 + ", tiao=" + tiao
				+ ", writeDate=" + writeDate + ", toString()="
				+ super.toString() + "]";
	}

	
	
}
