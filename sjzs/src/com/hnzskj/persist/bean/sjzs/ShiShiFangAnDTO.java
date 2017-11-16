package com.hnzskj.persist.bean.sjzs;

import com.hnzskj.persist.bean.attach.Attach;

public class ShiShiFangAnDTO {
	
	private String id;//主键ID
	
	private String name;//实施方案名称
	
	private String industry;//所属行业
	
	private String attachId;//附件ID
	
	private String keyWord;//关键字
	
	private String writeDate;//撰写日期
	
	private String updatedate;//更新日期
	
	private Attach attach;
	
	private Integer uploadFlag;
	
	private String sort;
	
	private String note1;
	
	private String note2;

	private String author;
	
	public ShiShiFangAnDTO() {
	}
	
	public ShiShiFangAnDTO(String id, String name, String industry,
			String attachId, String keyWord, String writeDate,
			String sort, String author, Integer uploadFlag) {
		super();
		this.id = id;
		this.name = name;
		this.industry = industry;
		this.attachId = attachId;
		this.keyWord = keyWord;
		this.writeDate = writeDate;
		this.uploadFlag = uploadFlag;
		this.sort = sort;
		this.author = author;
	}

	/**
	 * @return the note1
	 */
	public String getNote1() {
		return note1;
	}

	/**
	 * @param note1 the note1 to set
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
	 * @param note2 the note2 to set
	 */
	public void setNote2(String note2) {
		this.note2 = note2;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the attachid
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachid the attachid to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	/**
	 * @return the keyword
	 */
	public String getKeyWord() {
		return keyWord;
	}

	/**
	 * @param keyword the keyword to set
	 */
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	/**
	 * @return the writeDate
	 */
	public String getWriteDate() {
		return writeDate;
	}

	/**
	 * @param writeDate the writeDate to set
	 */
	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	/**
	 * @return the updatedate
	 */
	public String getUpdatedate() {
		return updatedate;
	}

	/**
	 * @param updatedate the updatedate to set
	 */
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	/**
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(Integer uploadFlag) {
		this.uploadFlag = uploadFlag;
	}
	
}
