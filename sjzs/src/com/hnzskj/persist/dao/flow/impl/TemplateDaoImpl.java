package com.hnzskj.persist.dao.flow.impl;

import java.sql.Timestamp;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.Template;
import com.hnzskj.persist.dao.flow.TemplateDao;

public class TemplateDaoImpl extends BaseDao implements TemplateDao{

	/**
	 *创建人：郑辉 描述： 获取最大的模版编号 创建时间：2012-4-1 上午11:29:29 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int getTempldateNoMax() {
		String sqlStr ="select template_no,template_name from sys_template";
		Template template =(Template)this.get(sqlStr, Template.class, null);
		
		
		String sql = "SELECT auto_increment FROM information_schema.`TABLES` WHERE TABLE_SCHEMA='sjzs' AND TABLE_NAME='sys_template';";
		int startNum=0;
		Object obj = getSingleValue(sql, null);
		int maxNo = 0;
		if (null != obj) {
			startNum =Integer.parseInt(obj.toString());
			maxNo = Integer.parseInt(obj.toString());
		}
		
		
		if(null == template&&startNum==1000){
			maxNo=1000;
		}
		return maxNo;
	}

	/**
	 *创建人：郑辉
	 *描述： 	插入数据，先判断是否存在，如果不存在，插入；如果存在，则修改
	 *创建时间：2012-4-7 上午09:21:02
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addTemplateInfo(Template template) {
		String sql ="";
		//判断是否存在该模版
		Template temp =getTemplateInfoById(template.getTemplate_no());
		if (null != temp) {
			// 如果存在数据库，跟新数据
			sql = "update sys_template set template_name=?,DESCRIPTION_INFO=?,CREATE_TIME=?,ORDER_CODE=?,FLOWMAIN=?, updatedate=? where template_no =? ";
			Object[] params = { template.getTemplate_name(),
					template.getDescription_info(), template.getCreate_time(),
					template.getOrder_code(), template.getFlowmain(),
					new Timestamp(System.currentTimeMillis()),
					template.getTemplate_no() };
			this.update(sql, params);
		} else {
			sql = "insert into SYS_TEMPLATE (template_name,DESCRIPTION_INFO,CREATE_TIME,ORDER_CODE,FLOWMAIN ,updatedate) values(?,?,?,?,?,?)";
			Object[] params = { template.getTemplate_name(),
					template.getDescription_info(), template.getCreate_time(),
					template.getOrder_code(), template.getFlowmain(),new Timestamp(System.currentTimeMillis()) };
			int num = this.insertDataAndGetId(sql, params);
			if (0 != num)
				return num;
		}
		return 0;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 该方法 通过一个模版编号获取到一个模版实例
	 *创建时间：2012-4-7 上午09:20:24
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Template
	 */
	public Template getTemplateInfoById(int no){
		Template temp =null;
		String sql = "select template_no,template_name,flowmain,description_info,ORDER_CODE,CREATE_TIME from sys_template where template_no =?";
		Object[] parmas = { no };
		temp = (Template)this.get(sql, Template.class, parmas);
		if(null != temp) return temp;
		return temp;
	}
	
	@SuppressWarnings("unchecked")
	public Page<Template> searchTemplate(Page<Template> page, Template template) {
		// TODO Auto-generated method stub
		List<Template> list =null;
		String sql ="select count(*) from sys_template";
		String totalIndex = (this.getSingleValue(sql, null).toString());
	    sql ="select  template_no,template_name,create_time,ORDER_CODE from sys_template "+"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
	    
		list=this.query(sql, Template.class, null);
		page.setCurPage(totalIndex);
		page.setList(list);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.TemplateDao#delete(int)
	 */
	@Override
	public int delete(int templateNo) {
		// TODO Auto-generated method stub
		String sql ="delete from sys_template where template_no =?";
		Object [] params ={templateNo};
		int num =this.update(sql, params);
		return num;
	}
}
