/*@目名称：sjzs
 *@文件名：LoginAction.java
 *@期：上午11:11:31
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.web.fore;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Encrypt;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.fore.RegLoginService;
import com.hnzskj.web.BaseAction;

/**
 *类名称:LoginAction 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:19:01<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:19:01<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */


public class LoginAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String INDEX = "index";
	private final String LOGIN = "login";
	private final String REG = "reg";
	private final String LOGSUC = "logSuc";
	private final String REGSUC = "regSuc";
	private final String IMAGE = "image";
	private String oldPass = null;
	private String newPass = null;
	private String reNewPass = null;
	private String yanzh = null;
	private Employee employee;
	private RegLoginService regLoginService;

	private String userKeyId;

	/**
	 * @return the yanzh
	 */
	public String getYanzh() {
		return yanzh;
	}

	/**
	 * @param yanzh
	 *            the yanzh to set
	 */
	public void setYanzh(String yanzh) {
		this.yanzh = yanzh;
	}

	/**
	 * @return the oldPass
	 */
	public String getOldPass() {
		return oldPass;
	}

	/**
	 * @param oldPass
	 *            the oldPass to set
	 */
	public void setOldPass(String oldPass) {
		this.oldPass = Encrypt.digest(oldPass);
	}

	/**
	 * @return the newPass
	 */
	public String getNewPass() {
		return newPass;
	}

	/**
	 * @param newPass
	 *            the newPass to set
	 */
	public void setNewPass(String newPass) {
		this.newPass = Encrypt.digest(newPass);
	}

	/**
	 * @return the reNewPass
	 */
	public String getReNewPass() {
		return reNewPass;
	}

	/**
	 * @param reNewPass
	 *            the reNewPass to set
	 */
	public void setReNewPass(String reNewPass) {
		this.reNewPass = Encrypt.digest(reNewPass);
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee
	 *            the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the regLoginService
	 */
	public RegLoginService getRegLoginService() {
		return regLoginService;
	}

	/**
	 * @param regLoginService
	 *            the regLoginService to set
	 */
	public void setRegLoginService(RegLoginService regLoginService) {
		this.regLoginService = regLoginService;
	}

	/**
	 * @return the userKeyId
	 */
	public String getUserKeyId() {
		return userKeyId;
	}

	/**
	 * @param userKeyId
	 *            the userKeyId to set
	 */
	public void setUserKeyId(String userKeyId) {
		this.userKeyId = userKeyId;
	}

	/**
	 *方法描述：跳转到首页 ，如果是u盘用户直接提示完善信息，如果是其他用户则跳转到首页 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午08:54:20
	 * 
	 * @return
	 *@version 1.0
	 */
	public String index() {
		if (checkIsU()) {
			if (checkHasInfo()) { // 判断是否首次登陆
				return "indexPage";
			} else {
				getRequest().setAttribute("showMessage",
						"您好，您是U盘用户，首次登陆网站需要完善您的个人信息！");
				return REG;
			}
		} else {
			if (getSession().getAttribute("employee") == null) {
				return INDEX;
			} else {
				return "indexPage";
			}
		}
	}	
	
	/**
	 *方法描述：判断是否是 U盘用户 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-14上午09:41:48
	 * 
	 * @return
	 *@version 1.0
	 */
	public boolean checkIsU() {
		boolean result = false;
		if ("".equals(acceptU())) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	/**
	 *方法描述：判断U盘用户基本信息是否存在 -根据userKeyId<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-14上午10:14:50
	 * 
	 * @return
	 *@version 1.0
	 */
	public boolean checkHasInfo() {
		boolean result = false;
		Employee employee = new Employee();
		employee = regLoginService.checkEmplInfo(userKeyId);
		if ((employee != null)
				&& (employee.getEmplAccount() != null && !"".equals(employee
						.getEmplAccount().trim()))
				&& (employee.getEmplPassword() != null && !"".equals(employee
						.getEmplPassword()))) {
			setSessionAttr("employee", employee);
			result = true;
		} else {
			getRequest().setAttribute("userKeyId", userKeyId);
			result = false;
		}
		return result;
	}

	/**
	 *方法描述：接受传递过来的U盘key <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-14下午02:27:52
	 * 
	 * @return
	 *@version 1.0
	 */
	public String acceptU() {
		if (this.userKeyId != null && !this.userKeyId.trim().equals("")) {
			return userKeyId.trim();
		} else {
			return "";
		}
	}

	/**
	 *方法描述：跳转到登录页面 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午08:54:24
	 * 
	 * @return
	 *@version 1.0
	 */
	public String login() {
		if (checkIsU()) {
			if (checkHasInfo()) {
				return "indexPage";
			} else {
				getRequest().setAttribute("showMessage",
						"您好，您是U盘用户，首次登陆网站需要完善您的个人信息！");
				return REG;
			}
		} else {
			return LOGIN;
		}
	}

	/**
	 *方法描述：用户登录 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午08:54:28
	 * 
	 *@return
	 *@version 1.0
	 */
	public String loginEmpl() {
		String loginIp = this.getIpAddr();
		employee.setEmplPassword(Encrypt.digest(employee.getEmplPassword()));
		employee = regLoginService.loginEmployee(employee, loginIp);
		if (employee == null) {
			getRequest().setAttribute("errorMess", "登录失败！");
			return LOGIN;
		} else {
			this.setSessionAttr("employee", employee);
			return LOGSUC;
		}

	}

	/**
	 *方法描述： 用户注册<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午08:54:31
	 * 
	 * @return
	 *@version 1.0
	 */
	public String regEmpl() {
		employee.setEmplId(new BaseDao().getGUID());
		employee.setTimeOrder(new java.sql.Date(new java.util.Date().getTime())
				.toString());
		employee.setEmplPassword(Encrypt.digest(employee.getEmplPassword()));
		
		if (regLoginService.addEmployee(employee)) {
			this.setSessionAttr("employee", employee);
			return REGSUC;
		} else {
			getRequest().setAttribute("errorMess", "注册失败！");
			return REG;
		}

	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午08:54:34
	 * 
	 * @return
	 *@version 1.0
	 */
	public String reg() {
		return REG;
	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-5上午10:52:32
	 * 
	 * @return
	 *@version 1.0
	 */
	public String image() {
		return IMAGE;
	}
	
	
	
	
	
	/**
	 *方法描述：显示个人主页（个人资料页面） <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:31
	 * 
	 * @return
	 *@version 1.0
	 */
	public String showHomePage() {
		if (getSession().getAttribute("employee") == null) {
			return "homePageError";
		} else {
			return "homePage";
		}
	}

	/**
	 *方法描述：跳转到信息修改页面 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:37
	 * 
	 * @return
	 *@version 1.0
	 */
	public String updateInfo() {
		if (getSession().getAttribute("employee") == null) {
			return INDEX;
		} else {
			return "updateInfo";
		}
	}

	/**
	 *方法描述：跳转到密码修改页面 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:40
	 * 
	 * @return
	 *@version 1.0
	 */
	public String updatePass() {
		if (getSession().getAttribute("employee") == null) {
			return INDEX;
		} else {
			return "updatePass";
		}
	}

	/**
	 *方法描述：用户信息修改，根据用户ID把可以修改的信息修改 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:43
	 * 
	 * @return
	 *@version 1.0
	 */
	public String infoUpdate() {
		if (getSession().getAttribute("employee") == null) {
			return "exit";
		} else {
			this.employee.setEmplId(((Employee) getSession().getAttribute(
					"employee")).getEmplId());
			employee = regLoginService.updateEmployee(employee);
			if (employee != null) {
				getSession().setAttribute("employee", employee);
				getRequest().setAttribute("updateMess", "信息修改成功！");
				return "infoUpdate";
			} else {
				return FAIL;
			}
		}
	}

	/**
	 *方法描述：用户修改密码时，对于一些密码，验证码信息的验证 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:47
	 * 
	 * @return
	 *@version 1.0
	 */
	public String passUpdate() {
		Object rand = getSessoinAttr("rand");
		if (getSession().getAttribute("employee") == null) {
			return INDEX;
		}  else if (!newPass.equals(reNewPass)) {
			this.getRequest().setAttribute("errorMess", "两次新密码输入不一致！");
			return "updatePass";
		} else if (rand == null) {
			this.getRequest().setAttribute("errorMess", "获取验证码失败！");
			return "updatePass";
		} else if (!yanzh.equals(rand.toString())) {
			this.getRequest().setAttribute("errorMess", "验证码输入错误！");
			return "updatePass";
		} else {
			this.employee = (Employee) getSession().getAttribute("employee");
			this.employee.setEmplId(((Employee) getSession().getAttribute(
					"employee")).getEmplId());
			this.employee.setEmplPassword((oldPass));
			if (regLoginService.loginEmployee(employee, getIpAddr()) != null) {
				employee = regLoginService.updatePass(employee, newPass);
				if (employee != null) {
					getSession().setAttribute("employee", employee);
					getRequest().setAttribute("updateMess", "密码修改成功！");
					return "passUpdate";
				} else {
					return FAIL;
				}
			} else {
				this.getRequest().setAttribute("errorMess", "原始密码错误！");
				return "updatePass";
			}
		}
	}

	/**
	 *方法描述：用户退出时对于session中的一些信息的清除 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午03:59:51
	 * 
	 * @return
	 *@version 1.0
	 */
	public String exit() {
		setSessionAttr("employee", null);
		setSessionAttr("key", null);
		setSessionAttr("type", null);
		return "exit";
	}

}
