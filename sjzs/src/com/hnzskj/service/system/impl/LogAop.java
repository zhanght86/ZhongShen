/*
 * @项目名称: htglxt
 * @文件名称: LogAop.java
 * @日期: 2011-6-18
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;

import java.lang.reflect.Method;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;

import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.OperationLog;
import com.hnzskj.persist.bean.system.SysContent;
import com.hnzskj.persist.dao.system.OperationLogDao;

/**
 * 
 * 类名称：LogAop <br/>
 * 类描述：<br/>
 * 创建人：苏国庆 <br/>
 * 创建时间：2011-6-18 下午02:58:38 <br/>
 * 修改人：Administrator <br/>
 * 修改时间：2011-6-18 下午02:58:38 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class LogAop {
	
	private OperationLogDao operationLogDao;
	
	/**
	 * @return the operationLogDao
	 */
	public OperationLogDao getOperationLogDao() {
		return operationLogDao;
	}

	/**
	 * @param operationLogDao the operationLogDao to set
	 */
	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

	/**
	 * 方法描述：将用户的操作记录保存到数据库中<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-20 下午02:03:28<br/>         
	 * @param pj
	 * @return
	 * @version   1.0  
	 * @throws Throwable 
	 */
	public Object aroundTest(ProceedingJoinPoint pj) throws Throwable {
		HttpServletRequest request = SysContent.getRequest();
		HttpSession session = SysContent.getSession();
		Object obj = null;
		Employee empl = null;
		if (null != session) {
			empl = (Employee) session.getAttribute("loginEmpl");	
		}
		if (null != empl) {//如果session有用户信息
			StringBuilder operation = new StringBuilder();
			Method method = getMethod(pj);
			Description description = method.getAnnotation(Description.class);
			String value = description != null ? description.value() :  null;
			if (!containsStr(pj.getSignature().getName(),
					new String[]{"get","find","search","sel","list"})) {
				//获得目标类的信息
				operation.append("执行操作：" + value + "<br/>");
				operation.append("执行的类：" + pj.getTarget() + "<br/>");
				operation.append("执行方法：" +pj.getSignature().getName() + "<br/>");
				operation.append("方法参数：" + outArray(pj.getArgs()));
				
				
				//设置操作日志信息
				OperationLog oprLog = new OperationLog();
				oprLog.setOperator(empl.getOrgName() + "_" + empl.getEmplName() + "__" + empl.getEmplId());
				oprLog.setLoginIp(getIpAddr(request));
				oprLog.setOperation(operation.toString());
				
				//将日志信息保存数据库
				operationLogDao.save(oprLog);
			}
		}
		obj = pj.proceed();
		return obj;
	}
	
	/**
	 * 
	 * 方法描述：获得客户端真实的IP地址,用户在使用代理时也可获得真实IP地址<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-17 下午04:48:48<br/>         
	 * @return	当前用户的IP地址
	 * @version   1.0
	 */
	public String getIpAddr(HttpServletRequest request) {		
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 方法描述：获得执行的方法名<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-21 上午08:15:33<br/>         
	 * @param pj
	 * @return
	 * @version   1.0  
	 */
	private Method getMethod(ProceedingJoinPoint pj) {
		Method method = null;
		Class<?> clazz = pj.getTarget().getClass();
		Method[] ms = clazz.getMethods();
		for (Method m : ms) {
			if (m.getName().equals(pj.getSignature().getName())) {
				method = m;
				break;
			}
		}
		return method;
	}
	
	/**
	 * 方法描述：str字符串是否包含字符串数组中的某一元素<br/>
	 * 如果包含返回true,否则返回false,不区分大小写
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-21 下午03:09:16<br/>         
	 * @param str
	 * @param strArr
	 * @return
	 * @version   1.0  
	 */
	private boolean containsStr(String str, String[] strArr) {
		for (String str1 : strArr) {
			if (str.toLowerCase().contains(str1.toLowerCase())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 方法描述：将一个数组打印出来，数组中元素可能也是数组<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-27 下午05:07:07<br/>         
	 * @param obj
	 * @return
	 * @version   1.0  
	 */
	public String outArray(Object[] obj) {
		StringBuffer sb = new StringBuffer("[");
		if ( null != obj ) {
			for (Object temp : obj) {
				if ( temp instanceof Object[]) {//如果当前元素是一个数组
					sb.append(Arrays.toString((Object[])temp) + ",");
				} else {
					if ( null != temp ) {
						sb.append(temp.toString() + ",");
					} else {
						sb.append("null,");
					}
				}
			}
			sb.deleteCharAt(sb.length()-1);
		}
		sb.append("]");
		return sb.toString();
	}
}
