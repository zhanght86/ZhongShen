package com.hnzskj.persist.bean.sjzs;

import java.util.Date;

public class UserKeyDTO {

	private String userKey;
	private String productionDate;
	private String saleDate;
	private String note1;
	private String note2;

	/**
	 * @return the userKey
	 */
	public String getUserKey() {
		return userKey;
	}

	/**
	 * @param userKey
	 *            the userKey to set
	 */
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	/**
	 * @return the productionDate
	 */
	public String getProductionDate() {
		return productionDate;
	}

	/**
	 * @param productionDate the productionDate to set
	 */
	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}

	/**
	 * @return the saleDate
	 */
	public String getSaleDate() {
		return saleDate;
	}

	/**
	 * @param saleDate the saleDate to set
	 */
	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
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

}
