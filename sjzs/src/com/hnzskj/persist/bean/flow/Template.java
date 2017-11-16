package com.hnzskj.persist.bean.flow;

public class Template {
	private Integer template_no; // 模版编号
	private String template_name; // 模版名称
	private String description_info; // 模版描述
	private String create_time; // 创建时间
	private Integer order_code; // 模版类型编号
	private String flowmain; // 描述

	public Template() {
	
		// TODO Auto-generated constructor stub
	}

	public String getTemplate_name() {
		return template_name;
	}

	public void setTemplate_name(String templateName) {
		template_name = templateName;
	}

	public String getDescription_info() {
		return description_info;
	}

	public void setDescription_info(String descriptionInfo) {
		description_info = descriptionInfo;
	}

	public String getCreate_time() {
		if((create_time!=null)&&(!"".equals(create_time))){
			return create_time.substring(0,19);
		}
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public String getFlowmain() {
		return flowmain;
	}

	public void setFlowmain(String flowmain) {
		this.flowmain = flowmain;
	}

	/**
	 * @return the template_no
	 */
	public Integer getTemplate_no() {
		return template_no;
	}

	/**
	 * @param templateNo the template_no to set
	 */
	public void setTemplate_no(Integer templateNo) {
		template_no = templateNo;
	}

	/**
	 * @return the order_code
	 */
	public Integer getOrder_code() {
		return order_code;
	}

	/**
	 * @param orderCode the order_code to set
	 */
	public void setOrder_code(Integer orderCode) {
		order_code = orderCode;
	}
}
