/*
 * @项目名称: htglxt
 * @文件名称: LoginAction.java
 * @日期: 2011-6-15
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Encrypt;
import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.flow.TaskList;
import com.hnzskj.persist.bean.init.InitAccountBean;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.service.flow.WorkFlowService;
import com.hnzskj.service.system.EmployeeService;
import com.hnzskj.service.system.PowerService;
import com.hnzskj.service.system.RoleService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：LoginAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-15 上午09:30:02   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-15 上午09:30:02   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 8853224995855600703L;

	private EmployeeService employeeService;
	
	private PowerService powerService;
	
	private RoleService roleService;
	
	private WorkFlowService workFlowService;
	
	
	private Employee empl = new Employee();
	
	private List<Power> powerList = new ArrayList<Power>();
	
	private String adminName;
	
	private String adminPass;
	
	private String menuStr;
	
	public List<TaskList> flowTaskList =new ArrayList<TaskList> ();
	public int rtnIdx=0;
	


	private Integer logicType=0;

	/**
	 * @return the menuStr
	 */
	public String getMenuStr() {
		return menuStr;
	}
	
	/**
	 * @param menuStr the menuStr to set
	 */
	public void setMenuStr(String menuStr) {
		this.menuStr = menuStr;
	}

	/**
	 * @return the powerList
	 */
	public List<Power> getPowerList() {
		return powerList;
	}

	/**
	 * @param powerList the powerList to set
	 */
	public void setPowerList(List<Power> powerList) {
		this.powerList = powerList;
	}

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * @return the powerService
	 */
	public PowerService getPowerService() {
		return powerService;
	}

	/**
	 * @param powerService the powerService to set
	 */
	public void setPowerService(PowerService powerService) {
		this.powerService = powerService;
	}
	/**
	 * @return the roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * @param roleService the roleService to set
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}


	public int getRtnIdx() {
		return rtnIdx;
	}

	public void setRtnIdx(int rtnIdx) {
		this.rtnIdx = rtnIdx;
	}

	/**
	 * @return the empl
	 */
	public Employee getEmpl() {
		return empl;
	}

	/**
	 * @param empl the empl to set
	 */
	public void setEmpl(Employee empl) {
		this.empl = empl;
	}

	public WorkFlowService getWorkFlowService() {
		return workFlowService;
	}

	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	

	/**
	 * @return the logicType
	 */
	public Integer getLogicType() {
		return logicType;
	}

	/**
	 * @param logicType the logicType to set
	 */
	public void setLogicType(Integer logicType) {
		this.logicType = logicType;
	}

	/**
	 * @return the flowTaskList
	 */
	public List<TaskList> getFlowTaskList() {
		return flowTaskList;
	}

	/**
	 * @param flowTaskList the flowTaskList to set
	 */
	public void setFlowTaskList(List<TaskList> flowTaskList) {
		this.flowTaskList = flowTaskList;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Override
	public String execute() throws Exception {	
		/*
		 * 如果当前请求中的session包含用户信息，则将用户信息从application中移除
		 * 解决用户刷新登陆页面时出现的用户重复登陆问题
		 */
		removeLoginInfo();
		
		//从配置文件中读取超级用户信息		
		SysParamUtil sysp = new SysParamUtil();
		InitAccountBean account = sysp.getInitAccount();
		adminName = account.getStrInitUser();
		adminPass = account.getStrInitPass();
		if (adminName.equals(empl.getEmplAccount()) 
				&& adminPass.equals(Encrypt.digest(empl.getEmplPassword()))) {//如果是系统超级管理员
			empl.setEmplId("00000000");
			empl.setEmplName("超级管理员");
			empl.setOrgId("00000000");
			empl.setOrgName("系统管理");
			//设置用户登陆IP
			empl.setLoginIp(getIpAddr());
			//查询出所有的功能
			powerList = this.powerService.searchPower("powerName,powerId,powerParent,powerUrl,powerImg,powerModule").getList();
			empl.getPowers().addAll(powerList);
		} else {//如果是系统用户
			empl = this.employeeService.login(empl);//根据用户和密码查询用户
			if ( null != empl ) {//如果用户密码正确
				if (Constant.DIMISSION.equals(empl.getEmplStatus())) {//如果已离职
					addActionError("您的账号已经被停用！");
					return "dimission";
				} else if (Constant.UNINIT.equals(empl.getEmplStatus())) {//如果尚未启用
					addActionError("您的账号尚未初始化！");
					return "uninit";
				}
				//设置用户登陆IP
				empl.setLoginIp(getIpAddr());			
				//获得用户的权限
				powerList.addAll(empl.getPowers());
				//对powerList进行排序，按原始顺序显示功能菜单
				Collections.sort(powerList);
			} else {
				addActionError("您输入的用户或密码有误！");
				return ERROR;
			}
			/*//完成一个用户同一时间只能登陆一次，一台机器同一时间只能登陆一个用户		
			String result = recordLogin(empl);
			if ("repeatEmpl".equals(result)) {//如果当前用户已经登陆
				this.addActionError("当前用户已经登陆系统，不可重复登陆！");
				return "repeatEmpl";
			}*/
		}

		setEmplToSession(empl);  //将登陆用户放入session
		menuStr = getPowers(powerList);   //权限
		
		//待办任务
		//flowTaskList=getNowTask();
		logicType=1;
		return SUCCESS;
	}
	
	/**
	 * 方法描述：用户退出登陆销毁session<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-29 上午11:14:55<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String loginout() {
		getSession().invalidate();
		getSession().setAttribute("null", "");
		return "loginout";
	}

	/**
	 * 方法描述：输出菜单结构树<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-26 上午10:09:19<br/>         
	 * @param powers
	 * @return
	 * @version   1.0  
	 */
	public static String getPowers(List<Power> powers) {
		Power power = new Power("00000000","系统功能","-1");
		List<Power> tempPower = new ArrayList<Power>();
		for (Power power2 : powers) {//只使用模块功能
			if (true == power2.isPowerModule()) {
				tempPower.add(power2);
			}
		}
		String str = createTree(power, tempPower);
		return str;
	}
	
	/**
	 * 方法描述：构建功能菜单树<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-26 上午10:11:21<br/>    
	 * @param power
	 * @param powers
	 * @param strBuffer
	 * @version   1.0  
	 */
	private static String createTree(Power power, List<Power> powers) {
		List<Power> temp = findChild(power, powers);//获得此菜单的子菜单
		StringBuffer sb = new StringBuffer();
		sb.append("{\"menus\":[\n");
		if (!temp.isEmpty()) {
			for (Power child1 : temp) {
				sb.append("{");
				sb.append("\"menuid\":").append("\"").append(child1.getPowerId()).append("\",");
				//sb.append("\"icon\":\"").append(child1.getPimg()).append("\",");
				sb.append("\"icon\":\"").append("icon-sys").append("\",");
				sb.append("\"menuname\":\"").append(child1.getPowerName()).append("\",\n");
				List<Power> temp2 = findChild(child1, powers);//获得此菜单的子菜单
				//此处的空格一定要保留，因为如果当前菜单没有子菜单时，下面执行删除字符时会将"[\n"给删除掉
				sb.append("\t\"menus\":[\n   ");
				for (Power child2 : temp2) {
					sb.append("\t\t{\"menuid\":\"").append(child2.getPowerId()).append("\",");
					sb.append("\"menuname\":\"").append(child2.getPowerName()).append("\",");
					sb.append("\"icon\":\"").append("icon-nav").append("\",");
					sb.append("\"url\":\"").append(child2.getPowerUrl()).append("\"},\n");
				}
				sb.delete(sb.length()-2, sb.length()-1);
				sb.append("\t]\n");
				sb.append("},\n");
			}
			sb.delete(sb.length() -2, sb.length() -1);
		}
		sb.append("]}");
		return sb.toString();
	}
	
	/**
	 * 
	 * 方法描述：从结果集中查找当前指定权限的子权限<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-10 下午07:10:39<br/>         
	 * @param power	当前权限
	 * @param powers	封装权限的集
	 * @return
	 * @version   1.0
	 */
	private static List<Power> findChild(Power power, List<Power> powers) {
		List<Power> childPowers = new ArrayList<Power>();		
		for (int i = 0; i < powers.size(); i++) {
			Power tempPower = powers.get(i);
			if ( tempPower.getPowerParent().equals( power.getPowerId() )) {//如果当前权限是power的子节点
				childPowers.add(tempPower);
			}
		}
		return childPowers;
	}
	
	/**
	 * 方法描述：<br/> 任意用户登录后就到节点附属表中进行查看是否有审批预期的数据，
	 * 并进行相应的操作
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-4 下午06:43:23<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String index() {
		return SUCCESS;
	}
	
	/**
	 * 方法描述：获得警告信息条数<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-4 下午07:10:15<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String warning(){
		SysParamUtil sysp = new SysParamUtil();
		InitAccountBean account = sysp.getInitAccount();
		adminName = account.getStrInitUser();
		adminPass = account.getStrInitPass();
		empl = getEmplFromSession();

		if (null != empl && !adminName.equals(empl.getEmplName()) 
				&& !adminPass.equals(Encrypt.digest(empl.getEmplPassword()))) {
		}
		return "warning";
	}
	
	/**
	 * 
	 *创建人：郑辉
	 *描述： 	通过任务处理人，获取当前未处理的任务列表
	 *创建时间：2012-4-10 上午08:50:40
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public  List<TaskList> getNowTask(){
		//直接从session中获取
		List<TaskList> list =new ArrayList<TaskList>();
		String enti_id =((Employee)getSession().getAttribute("loginEmpl")).getEmplId();
		list =workFlowService.getTaskListByEntiID(enti_id);
		return list;
	}
	


	public String updateIndexPage(){
		//待办任务
		//flowTaskList=getNowTask();
		
		//要归还的物品
		
		//rtnIdx=returnlist.size();
		return "warning";
	}
}
