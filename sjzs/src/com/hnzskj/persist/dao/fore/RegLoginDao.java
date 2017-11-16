/*@目名称：lwsj
 *@文件名：RegLoginDao.java
 *@期：下午02:39:47
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.persist.dao.fore;

import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.system.Employee;

/**
 *类名称:RegLoginDao
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:17:32<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:17:32<br/>
 *修改备注: <br/>
 *@version 1.0
 *@param <E>
 */
public interface RegLoginDao {
	
	public int addEmployee(Employee employee);
	
	public String getPassByName(String emplName);
	
	public Employee loginEmployee(Employee employee, String loginIP);
	
	public Employee updateEmployee(Employee employee);
	
	public Employee updatePass(Employee employee ,String newPass);
	
	public Employee checkEmplInfo(String UserKeyId );
	
	public int addClientInfo(ClientInfoDTO clientInfo);
}
