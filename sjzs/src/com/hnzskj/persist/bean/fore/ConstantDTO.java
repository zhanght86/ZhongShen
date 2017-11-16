package com.hnzskj.persist.bean.fore;

public class ConstantDTO {
	// 主键ID
	private String id;
	// 常量类型
	private String type;
	// 常量值
	private String typeValue;
	// 备用
	private String note1;
	// 备用
	private String note2;
	// 备用
	private String note3;
	// 说明
	private String explanation;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the typeValue
	 */
	public String getTypeValue() {
		return typeValue;
	}

	/**
	 * @param typeValue
	 *            the typeValue to set
	 */
	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	/**
	 * @return the note1
	 */
	public String getNote1() {
		return note1;
	}

	/**
	 * @param note1
	 *            the note1 to set
	 */
	public void setNote1(String note1) {
		this.note1 = note1;
	}

	/**
	 * @return the note2
	 */
	public String getNote2() {
		return note2;
	}

	/**
	 * @param note2
	 *            the note2 to set
	 */
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	/**
	 * @return the note3
	 */
	public String getNote3() {
		return note3;
	}

	/**
	 * @param note3
	 *            the note3 to set
	 */
	public void setNote3(String note3) {
		this.note3 = note3;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the explanation
	 */
	public String getExplanation() {
		return explanation;
	}

	/**
	 * @param explanation
	 *            the explanation to set
	 */
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Override
	public String toString() {
		return "ConstantDTO [id=" + this.id + ", type=" + this.type
				+ ", typeValue=" + this.typeValue + ", explanation="
				+ this.explanation + ", note1=" + this.note1 + ", note2="
				+ this.note2 + ", note3=" + note3 + "]";
	}

}
