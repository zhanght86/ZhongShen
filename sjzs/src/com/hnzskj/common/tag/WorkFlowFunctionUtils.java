package com.hnzskj.common.tag;

import com.hnzskj.common.BaseDao;

public class WorkFlowFunctionUtils {

	/**
	 *创建人：郑辉
	 *描述： 通过模板号获取到模板的初始化路径	
	 *创建时间：2012-4-10 下午02:55:33
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public static String getTemplateTageByTempID(String id) {
		String url="";
		if(id!=null&&!"".equals(id)){
			String sqlString = "select DESCRIPTION_INFO from sys_template where TEMPLATE_NO = ?";
			Object [] params={id};
			url = (String)new BaseDao().getSingleValue(sqlString, params);
			url = (url == null) ? "" : url;
		}
		return url;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 	获取模板的名称
	 *创建时间：2012-4-16 下午02:34:07
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public static String getTemplateName(String id ){
		String name="";
		if(id!=null&&!"".equals(id)){
			String sqlString = "select template_name from sys_template where TEMPLATE_NO = ?";
			Object [] params={id};
			name = (String)new BaseDao().getSingleValue(sqlString, params);
			name = (name == null) ? "" : name;
		}
		return name;
	}
	
	/**
	 *创建人：郑辉
	 *描述： 	获取员工名称
	 *创建时间：2012-4-16 下午02:39:27
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public static String getEmployeeName(String id){
		String name="";
		if(id!=null&&!"".equals(id)){
			String sqlString = "select emplName from employee where emplID = ?";
			Object [] params={id};
			name = (String)new BaseDao().getSingleValue(sqlString, params);
			name = (name == null) ? "" : name;
		}
		return name;
	}
	
	public static String  setInstanceState(String num){
		String state ="";
		if(1==Integer.parseInt(num))
			state="正在进行中";
		else if(2==Integer.parseInt(num))
			state="已完成";
		else
			state="";
		return state;
	}
	
	public static String setTaskState(String val){
		String str ="";
		if(val.equals("E")){
			str="任务已处理";
		}else if(val.equals("B")){
			str="任务正在进行时";
		}else{
			str="任务未开始";
		}
		return str;
	}
	
	public static Integer getTemplateId(String instance_no){
		Integer id=0;
		if(instance_no!=null&&!"".equals(instance_no)){
			String sqlString = "select template_id from SYS_INSTANCE_LIST where instance_id = ?";
			Object [] params={instance_no};
			id = (Integer)new BaseDao().getSingleValue(sqlString, params);
		}
		return id;
	}
}

