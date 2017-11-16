package com.hnzskj.persist.bean.sjzs;

import com.hnzskj.persist.bean.attach.Attach;

/**
 * 
 * 类名称：FangFaAnLi <br/>
 * 类描述：审计助手<br/>
 * 创建人：wxz <br/>
 * 创建时间：2013-2-19 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2013-2-19 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class FangFaAnLi {
	private String id;// 方法案例标题
	private String title;// 方法案例标题
	private String author;// 撰写人
	private String department;// 撰写单位
	private String ffalDateTime;// 撰写日期
	private String awards;// 获奖情况
	private String attachId;//附件ID
	private Attach attach;
	private String sort;
	private String content;
	private Integer uploadFlag;
	private String note1;
	private String note2;
	
	public FangFaAnLi() {
	}

	public FangFaAnLi(String id, String title, String author,
			String department, String ffalDateTime, String awards,
			String attachId, String sort, String content,
			Integer uploadFlag) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.department = department;
		this.ffalDateTime = ffalDateTime;
		this.awards = awards;
		this.attachId = attachId;
		this.sort = sort;
		this.content = content;
		this.uploadFlag = uploadFlag;
	}

	public Integer getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(Integer uploadFlag) {
		this.uploadFlag = uploadFlag;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}


	/**
	 * @return the ffalDateTime
	 */
	public String getFfalDateTime() {
		return ffalDateTime;
	}

	/**
	 * @param ffalDateTime the ffalDateTime to set
	 */
	public void setFfalDateTime(String ffalDateTime) {
		this.ffalDateTime = ffalDateTime;
	}

	/**
	 * @return the awards
	 */
	public String getAwards() {
		return awards;
	}

	/**
	 * @param awards
	 *            the awards to set
	 */
	public void setAwards(String awards) {
		this.awards = awards;
	}

	/**
	 * @return the attachId
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachId the attachId to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	
	
}
