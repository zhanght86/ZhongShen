/*
 * @项目名称: htglxt
 * @文件名称: ELFunUtils.java
 * @日期: 2011-5-30
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common.tag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.DateUtil;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.init.InithtLbBean;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Organization;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Project;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.bean.system.SysContent;

/**
 * 
 * 类名称：ELFunUtils <br/>
 * 类描述：jsp页面自定义函数<br/>
 * 创建人：苏国庆 <br/>
 * 创建时间：2011-5-30 下午01:46:07 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-5-30 下午01:46:07 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class FunctionUtils {
	private static final Logger LOGGER = Logger.getLogger(FunctionUtils.class);

	/**
	 * 
	 * 方法描述：判断当前用户Employee是否拥有当前指定purl的访问权限<br/>
	 * 如果拥有返回true,否则返回flase 创建人：苏国庆 <br/>
	 * 创建时间：2011-5-30 下午02:19:31<br/>
	 * 
	 * @param employee
	 * @param purl
	 * @return
	 * @version 1.0
	 */
	public static boolean check(String purl) {
		// 获得当前登陆用户
		Employee employee = (Employee) SysContent.getRequest().getSession()
				.getAttribute("loginEmpl");
	
		
		if (null != employee) {
			
			if(employee.getEmplName().equals("超级管理员"))
				return true;
			
			
			Set<Power> powers = employee.getPowers();// 当前用户所拥有的所有的权限
			for (Power power : powers) {

				/*
				 * 判断用户是否拥有当前purl的访问权限
				 */
				if (power.getPowerUrl().contains(purl)) {// 如果拥有返回true
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * 方法描述：根据用户的id获得用户的姓名<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-7-11 下午02:18:26<br/>
	 * 
	 * @param uid
	 * @return
	 * @version 1.0
	 */
	public static String getEmplNameById(int uid) {
		String sql = "select emplName from employee where emplId = ?";
		String uname = "";
		uname = (String) new BaseDao()
				.getSingleValue(sql, new Object[] { uid });
		uname = (uname == null) ? "" : uname;
		return uname;
	}
	/**
	 * 
	 * 描述：<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-2-26 上午09:47:44 <br/>  
	 * @version   1.0
	 */
	public static String getMenuNameByCode(String jmcode) {
		String jmname = "";
		String sql = "select menuName from sjzs_menuTree where menuId = ?";
		jmname = (String) new BaseDao().getSingleValue(sql,
				new Object[] { jmcode });
		jmname = (jmname == null) ? "" : jmname;
		return jmname;
	}
	
	/**
	 * 方法描述：根据部门编号查询部门名称<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-7-11 下午05:48:46<br/>
	 * 
	 * @param jmcode
	 * @return
	 * @version 1.0
	 */
	public static String getOrgNameByCode(String jmcode) {
		String jmname = "";
		if ("00000000".equals(jmcode)) {
			return "组织机构树";
		}
		String sql = "select orgName from org where orgId = ?";
		jmname = (String) new BaseDao().getSingleValue(sql,
				new Object[] { jmcode });
		jmname = (jmname == null) ? "" : jmname;
		return jmname;
	}

	/**
	 * 
	 * 方法描述：通过jgcode查询出机构名称(没有用到)<br/>
	 * 创建人：王亲臣 <br/>
	 * 创建时间：2012-3-14 上午08:56:03<br/>
	 * 
	 * @param jmcode
	 *            机构code<br/>
	 * @return String 机构名称<br/>
	 * @version 1.0<br/>
	 */
	public static String getOrgNameByJmcode(String jmcode) {
		String name = "";
		SysParamUtil sp = new SysParamUtil();
		List<InithtLbBean> listhtlb = sp.getInithtLb();
		for (InithtLbBean inithtLbBean : listhtlb) {
			if (inithtLbBean.getCode().equals(jmcode)) {
				name = inithtLbBean.getName();
			}
		}
		if (name == "") {
			String sql = "select orgName from org where orgName = ?";
			name = (String) new BaseDao().getSingleValue(sql,
					new Object[] { jmcode });
		}
		return name;
	}

	private static List<Organization> orgList = new ArrayList<Organization>();

	/**
	 * 
	 * 方法描述：判断当前用户Employee是否拥有当前指定purl的访问权限<br/>
	 * 如果拥有返回true,否则返回flase 创建人：苏国庆 <br/>
	 * 创建时间：2011-5-30 下午02:19:31<br/>
	 * 
	 * @param employee
	 * @param purl
	 * @return
	 * @version 1.0
	 */
	// public static boolean check(String purl) {
	// // 获得当前登陆用户
	// // Employee employee = (Employee) SysContent.getRequest().getSession()
	// // .getAttribute("loginEmpl");
	// //
	// // if ( null != employee ) { Set<Power> powers =
	// // employee.getPowers();//当前用户所拥有的所有的权限 for (Power power : powers) {
	// //
	// //
	// // //判断用户是否拥有当前purl的访问权限
	// //
	// // if (power.getPowerUrl().contains(purl)) {//如果拥有返回true return true; }
	// // }
	//		 
	// return false;
	// }

	/**
	 * 方法描述：根据部门编号查询部门名称<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-7-11 下午05:48:46<br/>
	 * 
	 * @param orgId
	 * @return
	 * @version 1.0
	 */
	public static String getOrgNameByOrgId(String orgId) {
		String orgName = "";
		if ("00000000-0000-0000-0000-000000000000".equals(orgId)) {
			return "组织机构树";
		}
		String sql = "select orgName from organization where orgId = ?";
		orgName = (String) new BaseDao().getSingleValue(sql,
				new Object[] { orgId });
		orgName = (orgName == null) ? "" : orgName;
		return orgName;
	}

	/**
	 * 
	 * 
	 * 方法描述：通过部门id获得部门名称<br/>
	 * 创建人：王亲臣 <br/>
	 * 创建时间：2011-12-31 下午03:38:17<br/>
	 * 
	 * @param personUUID
	 *            人员UUID<br/>
	 * @return <br/>
	 * @version 1.0<br/>
	 */
	public static String getOrgNameByID(String uid) {
		String sql = "select orgName from organization where orgId in (?)";
		String orgName = (String) new BaseDao().getSingleValue(sql,
				new Object[] { uid });
		return orgName;
	}

	/**
	 * 方法描述：通过用户Id查询出用户姓名<br/>
	 * 创建人：王亲臣 <br/>
	 * 创建时间：2012-1-5 下午06:38:56<br/>
	 * 
	 * @param emplId
	 *            用户id<br/>
	 * @return String 用户姓名<br/>
	 * @version 1.0<br/>
	 */
	public static String getEmplNameByEmplId(String emplId) {
		if(emplId.equals(Constant.ADMIN)){
			return "超级管理员";
		}
		String sql = "SELECT EMPLNAME FROM EMPLOYEE WHERE EMPLID = ? ";
		String emplName = (String) (new BaseDao().getSingleValue(sql,
				new Object[] { emplId }));
		return emplName;
	}

	/**
	 * 方法描述：查询所有的子部门信息<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2012-3-16 下午05:42:04<br/>
	 * 
	 * @param orgs
	 * @return
	 * @version 1.0
	 */
	public static List<Organization> getAllChildOrg(List<Organization> orgs) {
		orgList.addAll(orgs);
		List<Organization> list = new ArrayList<Organization>();
		// for (Organization org : orgs) {
		// getChildOrg(org.getOrgId());
		// }
		// Set<Organization> set = new HashSet<Organization>();
		// set.addAll(orgList);
		// list.addAll(set);
		// Collections.sort(list, new Comparator<Organization>() {
		// @Override
		// public int compare(Organization o1, Organization o2) {
		// Date t1 = DataUtil.getDate(o1.getStoreTime());
		// Date t2 = DataUtil.getDate(o2.getStoreTime());
		// return t1.compareTo(t2);
		// }
		// });
		return list;
	}

	@SuppressWarnings( { "unchecked", "unused" })
	private static void getChildOrg(String jmcode) {
		List<Organization> list = null;
		String sql = "select orgId, orgName, storeTime from organization where orgParent = ?";
		list = new BaseDao().query(sql, Organization.class,
				new Object[] { jmcode });
		for (Organization org : list) {// 采用递归调用的方式直到没有子部门信息是结束调用
			orgList.add(org);
			getChildOrg(org.getOrgId());
		}
	}
	
	/**
	 * 类描述：通过角色获取角色名称
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-7 下午03:45:23 
	 * 修改人：
	 * 修改时间：2012-8-7 下午03:45:23  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public static String getRoleNamesByCode(String roleId){
		String roleName="";
		if(roleId.equals("")) return "";
		if(roleId.split("_").length==0){
			String sql ="select roleName from role where roleId=?";
			Object[] params={roleId};
			roleName =(String)new BaseDao().getSingleValue(sql, params);
		}else{
			String [] rolesId =roleId.split("_");
			for(int i =0;i<rolesId.length;i++){
				String sql ="select roleName from role where roleId=?";
				Object[] params={rolesId[i]};
				roleName +=(String)new BaseDao().getSingleValue(sql, params)+"_";
			}
		}
		return roleName;
	}
	

	/**
	 * 方法描述：根据用户Id查询出其所在部门信息<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2012-3-17 上午09:38:42<br/>
	 * 
	 * @param emplId
	 * @return
	 * @version 1.0
	 */
	@SuppressWarnings("unchecked")
	public static String getOrgNameByEmplIds(String emplId) {
		StringBuffer orgNames = new StringBuffer("");
		String sql = "select org.orgName from employee inner join org on org.orgId = employee.orgId where emplId = ?";
		List<Organization> listOrg = new BaseDao().query(sql,
				Organization.class, new Object[] { emplId });
		for (Organization organization : listOrg) {
			orgNames.append(",").append(organization.getOrgName());
		}
		return orgNames.toString().replaceFirst(",", "");
	}

	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2012-3-17 上午10:31:58<br/>
	 * 
	 * @param jourId
	 * @return
	 * @version 1.0
	 */
	public static Integer getReplyCount(String jourId) {
		Integer count = 0;
		String sql = "select count(replyId) from reply where journalId = ? and status = 1";
		count = (Integer) new BaseDao().getSingleValue(sql,
				new Object[] { jourId });
		return count;
	}

	public static boolean validate(String id) throws Exception {
		String sql = "select storeDate from journal where journalID = ? limit 1";
		String storeTime = new BaseDao().getSingleValue(sql,
				new Object[] { id }).toString();
		if (DateUtil.isOrNotEdit(storeTime)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * 方法描述：判断日志是否按时提交=0说明没有按时提交</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-5 下午01:57:28
	 * 
	 * @return
	 * @version 1.0
	 */
	public static boolean validateTimeOfOut(String id) {
		String sql = "select COUNT(*) from journal where DATEPART(DD,journalDate)=DATEPART(DD,storeDate) "
				+ "and DATEPART(MM,journalDate)=DATEPART(MM,storeDate)  and  journalID= ? ";
		int result = (Integer) new BaseDao().getSingleValue(sql,
				new Object[] { id });
		return result == 0 ? true : false;
	}

	/**
	 * 
	 * 方法描述：判断日志是否按时提交=0说明没有按时提交</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-5 下午01:57:28
	 * 
	 * @return
	 * @version 1.0
	 */
	public static boolean validateTimeOut(String journalDate, String storeDate) {
		boolean result = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 日志所属日期的第二天的8点30
			long limit = sdf.parse(journalDate).getTime()
					+ ((24 + 8) * 60 + 30) * 60 * 1000;
			long store = sdf.parse(storeDate).getTime();
			long value = limit - store;
			if (value < 0) {
				result = true;
			}
		} catch (ParseException e) {
			LOGGER.error("转换时间出错！");
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
		}
		return result;
	}

	/**
	 * 
	 * 方法描述：登录用户是否的部门和所有的部门进行匹配</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-6 下午06:18:55
	 * 
	 * @return
	 * @version 1.0
	 */
	public static boolean getOranization(String id, String orgParent) {
		Employee employee = (Employee) ServletActionContext.getRequest()
				.getSession().getAttribute("loginEmpl");
		if (employee == null)
			return false;
		for (int i = 0; i < employee.getManageOrgs().size(); i++) {
			if (employee.getManageOrgs().get(i).getOrgId().equals(id)
					|| employee.getManageOrgs().get(i).getOrgId().equals(
							orgParent)) {
				return true;
			}
		}
		return false;
	}

	

	/**
	 * 
	 * 方法描述：根据日志 id查询日志标题</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-17 上午08:59:48
	 * 
	 * @return
	 * @version 1.0
	 */
	public static String getTitleByJournalId(String id) {
		String sql = "select journalTitle from journal where journalID = ? limit 1";
		String journalTitle = (String) new BaseDao().getSingleValue(sql,
				new Object[] { id });
		return journalTitle;
	}

	/**
	 * 
	 * 方法描述：判断批复时间是否在转发时间内</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-18 上午11:24:09
	 * 
	 * @return
	 * @version 1.0
	 */
	public static boolean isNotShow(String notId, String journalId) {
//		// 首先根据转发通知的id查询出转发的时间
//		Date sendTime = null;
//		Date ReplyTime = null;
//		try {
//			String sqlSendTime = "select notSendTime from noticeReceive where notId = ?";
//			sendTime = (Date) new BaseDao().getSingleValue(sqlSendTime,
//					new Object[] { notId });
//			// 然后根据日志id查询出转发的批复信息的时间，
//			String sqlReplyTimes = "select replyTime from reply where replyId = ?";
//			ReplyTime = (Date) new BaseDao().getSingleValue(sqlReplyTimes,
//					new Object[] { journalId });
//		} catch (Exception e) {
//			LOGGER.info("判断批复时间是否在转发时间内");
//			LOGGER.error(DataUtil.getStackTraceAsString(e));;
//		}
//		// 最后批复时间小于转发时间返回true,否则false
//		if (ReplyTime.getTime() <= sendTime.getTime()) {
//			return true;
//		}
		return false;
	}

	/**
	 * 方法描述：根据类型id查询类型名称<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2012-4-27 下午12:11:44<br/>
	 * 
	 * @param typeId
	 * @return
	 * @version 1.0
	 */
	public static String getTemplateTypeName(String typeId) {
		String sql = "select typeName from templateType where typeId = ?";
		String typeName = null;
		typeName = (String) new BaseDao().getSingleValue(sql,
				new Object[] { typeId });
		return typeName;
	}

	/**
	 *毛俊玲 方法描述：根据用','号隔开的用户id获取用','号隔开的用户名称字符串 2012-5-9
	 * 
	 * @param emplIds
	 *            用','号隔开的用户id字符串
	 * @return 用','号隔开的用户名称字符串
	 */
	public static String getEmplNamesByEmplIds(String emplIds) {
			String emplNames = "";
			if (emplIds.indexOf(",") != -1) {
				String[] ids = emplIds.split(",");
				StringBuilder names = new StringBuilder();
				for (String sqlParame : ids) {
					String sql = "select emplName from employee where emplId = ' limit 1"
						+ sqlParame + "'";
					String name = (String) new BaseDao().getSingleValue(sql, null);
					names.append("," + name);
				}
//				for (int i = 0; i < ids.length; i++) {
//					String sql = "select emplName from employee where emplId = '"
//						+ ids[i] + "'";
//					String name = (String) new BaseDao().getSingleValue(sql, null);
//					names.append("," + name);
//				}
				emplNames = names.toString();
				emplNames = emplNames.substring(1, emplNames.length());
			} else {
				String sql = "select emplName from employee where emplId = ' limit 1"
					+ emplIds + "'";
				emplNames = (String) new BaseDao().getSingleValue(sql, null);
			}
		return emplNames;
	}

	/**
	 *毛俊玲 方法描述：根据任务id获得任务名称 2012-5-9
	 * 
	 * @param taskId
	 * @return
	 */
	public static String getTaskNameById(String taskId) {
		String sql = "select taskName from task where taskId = '" + taskId
				+ "'";
		String name = (String) new BaseDao().getSingleValue(sql, null);
		return name;
	}

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			@SuppressWarnings("unused")
			long limit = sdf.parse("2012-05-02 00:00:00.000").getTime()
					+ ((24 + 8) * 60 + 30) * 60 * 1000;
			@SuppressWarnings("unused")
			long store = sdf.parse("2012-05-03 08:31:00.000").getTime();
		} catch (ParseException e) {
			LOGGER.error("转换时间出错！");
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
		}
	}

	public static String getAddOne(String num) {
		int idx = 0;
		idx = Integer.parseInt(num) + 1;
		return idx + "";
	}

	/**
	 * 根据类型查询是否归还
	 * 
	 * @param items_input_type
	 * @return
	 */
	public static String getItems_is_return(String items_input_type) {
		String isReturn = "否";
		// 参数不为空并且不是空格的时候，执行查询
		if ((items_input_type != null) && (!"".equals(items_input_type))) { 
			String sql = "SELECT items_is_return FROM stock where items_type=?";
			isReturn = (String) new BaseDao().getSingleValue(sql, new Object[]{ items_input_type });
		}
		return isReturn;
	}

	/**
	 * 根据会议id获得会议的参会人员Id
	 *毛俊玲
	 *方法描述：
	 *2012-5-31
	 * @param cfrId 会议id
	 * @param status 0：获得预定的参会人员      1：获得实际的参会人员
	 * @return
	 */
	public static String getIds(String cfrId,String status){
		List<String> ids =  new ArrayList<String>();
		@SuppressWarnings("unused")
		List<String> names = new ArrayList<String>();
		if((cfrId != null)&&(!"".equals(cfrId))){
			String sql = "SELECT emplId From conference_empl WHERE cfrId=? and empType=? ";
			ids =(List<String>)new BaseDao().getListSingleValue(sql, new Object[]{cfrId,status});
			sql = "SELECT emplName From conference_empl WHERE cfrId=? and empType=? ";
			names =(List<String>)new BaseDao().getListSingleValue(sql, new Object[]{cfrId,status});
		}
		String empIds ="";
		if(ids.size() !=0){
			for(int i=0;i<ids.size();i++){
				empIds+=ids.get(i).toString()+",";
			}
		}else{
			return "";
		}
		return empIds.substring(0,empIds.lastIndexOf(","));
	}
	
	/**
	 * 根据会议id获得会议的参会人员名称
	 *毛俊玲
	 *方法描述：
	 *2012-5-31
	 * @param cfrId 会议id
	 * @param status 0：获得预定的参会人员      1：获得实际的参会人员
	 * @return
	 */
	public static String getNames(String cfrId,String status){
	
		List<String> ids = new ArrayList<String>();
		List<String> names = new ArrayList<String>();
		if((cfrId != null)&&(!"".equals(cfrId))){
			String sql = "SELECT emplId From conference_empl WHERE cfrId=? and empType=? ";
			ids =(List<String>)new BaseDao().getListSingleValue(sql, new Object[]{cfrId,status});
			sql = "SELECT emplName From conference_empl WHERE cfrId=? and empType=? ";
			names =(List<String>)new BaseDao().getListSingleValue(sql, new Object[]{cfrId,status});
		}
		String ns="";
		if(ids.size()!=0){
			for(int i=0;i<ids.size();i++){			
				ns+=names.get(i).toString()+",";
			}		
		}else{
			return "";
		}
		return ns.substring(0,ns.lastIndexOf(","));
	}
	
	/**
	 *毛俊玲
	 *方法描述：获得查看通知的人员姓名情况
	 *包括，哪些人员已查看通知，哪些人员未查看通知
	 *2012-6-16
	 * @param notId
	 * @return
	 */
	public static String getNamesOfReadNotice(String notId){
		StringBuffer sBuffer = new StringBuffer();
		/*
		sBuffer.append("<br/><b>未查看通知的人员：</b>");
		//获得所有未读通知的人员名称
		String sql = "select notReceiveName from noticeReceive where isRead = "+Constant.NOREAD+" and sendId = '"+notId+"'";
		List<String> names1 =(List<String>)new BaseDao().getListSingleValue(sql,null);
		for(int i=0;i<names1.size();i++){
			sBuffer.append(names1.get(i)+"  ");
		}
		sBuffer.append("<br/><b>已查看通知的人员：</b>");
		String sql2 = "select notReceiveName from noticeReceive where isRead = "+Constant.READ+" and sendId = '"+notId+"'";
		List<String> names2 =(List<String>)new BaseDao().getListSingleValue(sql2,null);
		for(int i=0;i<names2.size();i++){
			sBuffer.append(names2.get(i)+"  ");
		}
		*/
		return sBuffer.toString();
	}
	
	/**
	 *创建人：郑辉
	 *描述：用于截取任务管理中添加修改操作中文本域中br 	
	 *创建时间：2012-7-5 上午10:58:27
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public static String getCutInfo(String info){
		String cutInfo="";
		cutInfo=info.replaceAll(" ", "<br/>").replaceAll("。", "<br/>").replaceAll("，", "<br/>").replaceAll(",", "<br/>");
		if(info!=null&&!info.equals("")){
			info=info.trim().replaceAll("<br/>", ",");
			if(info.lastIndexOf("<br/>")!=-1){
				info=info.substring(0,info.lastIndexOf(","))+"。";
			}
			cutInfo=info;
		}
		return cutInfo;
	}
	
	public static String dateFormat(String time){
		String date="";
	    date=time.replaceAll(" ", "");
	    date=date.substring(0,date.lastIndexOf("-"))+" "+date.substring(date.lastIndexOf("-")+1)+"时";
		return date;
	}
	
	/**
	 * 销售漏斗
	 * 客户列表中客户所属项目数据处理
	 * 将   ‘项目名_跟踪单id’格式处理成 <a href="url?id">项目名</a>
	 *毛俊玲
	 *方法描述：
	 *2012-8-13
	 * @param proNames
	 * @return
	 */
	public static String getProHref(String proNames){
		StringBuilder returnName = new StringBuilder();
		if(proNames!=null&&!"".equals(proNames)){
			if(proNames.indexOf(",")!=-1){
				String[] array1 = proNames.split(",");
				for(int i=0;i<array1.length;i++){
					int ind = array1[i].indexOf("_");
					String trackid = array1[i].substring(ind+1,array1[i].length());
					String proId = array1[i].substring(0,ind);
					String name = getProjectNameById(proId);
					String taskId = getTaskId(trackid);
					
					if(i==array1.length-1){
						returnName.append("<a href=\"javascript:void(0)\" title=\"项目跟进查询\" onclick=\"self.parent.parent.addTab('项目跟进查询','saleProject!searchSaleProjectInfo.action?track_no="+trackid+"&taskDetail.taskId="+taskId+"')\">"+name+"</a>");
					}else{
						returnName.append("<a href=\"javascript:void(0)\" title=\"项目跟进查询\" onclick=\"self.parent.parent.addTab('项目跟进查询','saleProject!searchSaleProjectInfo.action?track_no="+trackid+"&taskDetail.taskId="+taskId+"')\">"+name+"</a>");
						returnName.append("</br>");
					}
					
				}
			}else {
				int ind = proNames.indexOf("_");
				String trackId = proNames.substring(ind+1,proNames.length());
				String proId = proNames.substring(0,ind);
				String name = getProjectNameById(proId);
				String taskId = getTaskId(trackId);
				returnName.append("<a href=saleProject!searchSaleProjectInfo.action?track_no="+trackId+"&taskDetail.taskId="+taskId+">"+name+"</a>");
			}
			return returnName.toString();
		}else {
			return "";
		}
	}
	
	public static String getTaskId(String trackNo){
		String sql = "select task_id from project_track where track_no = ?";
		String id = String.valueOf(new BaseDao().getSingleValue(sql, new Object[]{trackNo}));
		if(id!=null&&!"".equals(id)){
			return id;
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * 方法描述：通过当前登录用户获取到当前用户的角色<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-24 上午09:57:06<br/>         
	 * @param employee 当前登录用户<br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	private static String getRoleNameByEmplId(Employee employee){
		List<Role> list = getRoleNameByEmplId(employee.getEmplId());
		if(null!=list&&list.size()>0){
			for (Role role : list) {
				if(role.getRoleName().equals(Constant.ROLENAME)){
					return "AND C.CUSTOMER_CREATE_PERSON = '"+employee.getEmplId()+"'";
				}
			}
		}
		return "";
	
	}
	/**
	 * 
	 * 方法描述：通过当前登录用户获取到用户角色集合<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-24 上午10:22:32<br/>         
	 * @param emplId 当前登录用户Id<br/>   
	 * @return List<Role><br/>   
	 * @version   1.0<br/>
	 */
	@SuppressWarnings("unchecked")
	private static List<Role> getRoleNameByEmplId(String emplId){
		//查询授权用户的审批角色
		String sql = "select roleId,roleName,roleRemark,roleType from role where roleId in (select distinct(roleId) from user_role where emplId = ? ) and roleType = 0 ";
		List<Role> roles = new BaseDao().query(sql, Role.class, new Object[]{emplId});
		return roles;
	}
	
	
	
	
	/**
	 * 
	 * 方法描述：通过项目编号获取项目名称<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-24 上午09:55:16<br/>         
	 * @param <br/>   
	 * @return String<br/>   
	 * @version   1.0<br/>
	 */
	public static String getProjectNameById(String proId){
		String sql="SELECT PRONAME FROM PROJECT WHERE PROID = ? ";
		Project project = (Project)new BaseDao().get(sql, Project.class, new Object[]{proId});
		if(null!=project&&!"".equals(project.getProName())){
			return project.getProName();
		}
		return "";
	}
	
	/**
	 *毛俊玲
	 *方法描述：根据比例id获得客户成交比例
	 *2012-8-15
	 * @return
	 */
	public static String getRateByRateNo(String id){
		if(id==null||id.equals("")){
			return "";
		}
		String sql = "select lcText from latentCuster where lcId = ?";
		String text = String.valueOf(new BaseDao().getSingleValue(sql, new Object[]{id}));
		if(null!=text&&!"".equals(text)){
			return text;
		}else{
			return "";
		}
	}
	
	/**
	 * 方法描述：设置文件的搜索关键字重点显示<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2012-1-12 下午03:49:40<br/>
	 * 
	 * @param keyWords
	 * @param Content
	 * @return
	 * @version 1.0
	 */
	public static String setKeyWords(String keyWords, String content) {
		if (null == content) {
			return "";
		}
		content = htmltoText(content).replaceAll("&nbsp;", "").replaceAll("\t", "").replaceAll("　", "");
		
		int keyIndex=content.indexOf(keyWords);//第一个关键字出现的位置
		
		if(keyIndex<100){
			if (content.length() > 300) {
				content = content.substring(0, 300);						
			}
		}else{
			content=content.substring(keyIndex-keyWords.length()-20,content.length());
			if (content.length() > 400) {
				content = content.substring(0, 300);						
			}else{
				content = content.substring(0, content.length());
			}
		}
//		content = content.substring(content.indexOf(keyWords)-keyWords.length(), 300);
//		
//		if (content.length() > 300) {
//			content = content.substring(0, 300);						
//		}
		content = content.replaceAll(keyWords, "<font color=\"red\">" + keyWords + "</font>");
		return content;
	}
	
	/**
	 * 方法描述：去除指定字符串中html代码<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-4-20 上午09:52:45<br/>
	 * 
	 * @param inputString
	 *            要去除HTML标签的字符串
	 * @return 返回去除掉HTML标签的字符串
	 * @version 1.0
	 */
	public static String htmltoText(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_ba;
		java.util.regex.Matcher m_ba;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
			String patternStr = "\\s+";

			p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_ba = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE);
			m_ba = p_ba.matcher(htmlStr);
			htmlStr = m_ba.replaceAll(""); // 过滤空格

			textStr = htmlStr;

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}
		textStr = textStr.replaceAll("&nbsp;", "").replaceAll("\t", "").replaceAll("　", "").replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		return textStr;// 返回文本字符串
	}
	
	
}
