/*@目名称：lwsj
 *@文件名：RegLoginService.java
 *@期：下午02:42:39
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.service.fore;

import com.hnzskj.persist.bean.system.Employee;

/**
 *类名称:RegLoginService 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:18:31<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:18:31<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public interface RegLoginService {
	public boolean addEmployee(Employee employee);

	public Employee loginEmployee(Employee employee, String loginIp);

	public Employee updateEmployee(Employee employee);
	
	public Employee updatePass(Employee employee,String newPass);
	
	public Employee checkEmplInfo(String userKeyId);
}
