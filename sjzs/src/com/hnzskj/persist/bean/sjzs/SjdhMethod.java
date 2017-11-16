package com.hnzskj.persist.bean.sjzs;

/**
 * 审计导航的审计方法
 *
 */
public class SjdhMethod {
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 方法名称
	 */
	private String name;
	/**
	 * 分类编号
	 */
	private String typeNo;
	/**
	 * 流程实例号码
	 */
	private String template_no;
	/**
	 * 创建时间
	 */
	private String createTime;
	
	private String context;
	/**
	 * 排序字段
	 */
	private String orderNum;
	/**
	 * 流程实例
	 */
	private WorkFlowDTO workFlowDTO;
	
	private String note1;
	
	private String note2;
	
	
	/**
	 * @return the workFlowDTO
	 */
	public WorkFlowDTO getWorkFlowDTO() {
		return workFlowDTO;
	}
	/**
	 * @param workFlowDTO the workFlowDTO to set
	 */
	public void setWorkFlowDTO(WorkFlowDTO workFlowDTO) {
		this.workFlowDTO = workFlowDTO;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(String typeNo) {
		this.typeNo = typeNo;
	}
	
	
	public String getTemplate_no() {
		return template_no;
	}
	public void setTemplate_no(String templateNo) {
		template_no = templateNo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
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
