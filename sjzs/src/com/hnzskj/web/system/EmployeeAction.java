/*
 * @项目名称: htglxt
 * @文件名称: EmployeeAction.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web.system;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.hnzskj.common.Constant;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Encrypt;
import com.hnzskj.common.FileManager;
import com.hnzskj.common.JSONUtil;
import com.hnzskj.common.Page;
import com.hnzskj.common.SystemException;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.bean.system.Organization;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.service.system.EmployeeService;
import com.hnzskj.service.system.PowerService;
import com.hnzskj.service.system.RoleService;
import com.hnzskj.web.BaseAction;

/**        
 * 
 * 类名称：EmployeeAction     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午02:48:07   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午02:48:07   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class EmployeeAction extends BaseAction{
	private static final long serialVersionUID = -5960807926636155898L;
	
	private static Logger log = Logger.getLogger(EmployeeAction.class);
	
	private EmployeeService employeeService;
	
	private RoleService roleService;
	
	private PowerService powerService;
	
	private Page<Employee> page = new Page<Employee>();
	
	private Employee empl = new Employee();

	private List<Organization> orgList = new ArrayList<Organization>();
	private List<Role> roleList = new ArrayList<Role>();
	
	private List<Power> powerList = new ArrayList<Power>();
	private List<Employee> emplList = new ArrayList<Employee>();
	private String rcodes;
	/**
	 * 路径类型
	 */
	private String urlType;
	private String pcodes;
	
	private String closePage;
	
	private String refreshPage;	
	
	private File signet;
	
	private String signetFileName;
	
	private String signetContentType;
	
	private String flag;//添加查阅人时，传过来的参数，选择员工时，需要选择只是“在职”的员工
	
	/**电子签章类型部门和个人*/
	private int signetType;
	
	/**电子签章保存的相对当前应用的目录*/
	private String savePath;
	
	/***电子签章的图片类型*/
	private String imgTypes;
	
	


	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}

	/**
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}

	/**
	 * @return the emplList
	 */
	public List<Employee> getEmplList() {
		return emplList;
	}

	/**
	 * @param emplList the emplList to set
	 */
	public void setEmplList(List<Employee> emplList) {
		this.emplList = emplList;
	}

	/**
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
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
	 * @return the pcodes
	 */
	public String getPcodes() {
		return pcodes;
	}

	/**
	 * @param pcodes the pcodes to set
	 */
	public void setPcodes(String pcodes) {
		this.pcodes = pcodes;
	}

	/**
	 * @return the rcodes
	 */
	public String getRcodes() {
		return rcodes;
	}

	/**
	 * @return the urlType
	 */
	public String getUrlType() {
		return urlType;
	}

	/**
	 * @param urlType the urlType to set
	 */
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}

	/**
	 * @param rcodes the rcodes to set
	 */
	public void setRcodes(String rcodes) {
		this.rcodes = rcodes;
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

	/**
	 * @return the roleList
	 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	/**
	 * @return the orgList
	 */
	public List<Organization> getOrgList() {
		return orgList;
	}

	/**
	 * @param orgList the orgList to set
	 */
	public void setOrgList(List<Organization> orgList) {
		this.orgList = orgList;
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
	 * @return the page
	 */
	public Page<Employee> getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Page<Employee> page) {
		this.page = page;
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
	

	/**
	 * @return the singnet
	 */
	public File getSignet() {
		return signet;
	}

	/**
	 * @param singnet the singnet to set
	 */
	public void setSignet(File signet) {
		this.signet = signet;
	}

	/**
	 * @return the singnetFileName
	 */
	public String getSignetFileName() {
		return signetFileName;
	}

	/**
	 * @param singnetFileName the singnetFileName to set
	 */
	public void setSignetFileName(String signetFileName) {
		this.signetFileName = signetFileName;
	}

	/**
	 * @return the singnetContentType
	 */
	public String getSignetContentType() {
		return signetContentType;
	}

	/**
	 * @param singnetContentType the singnetContentType to set
	 */
	public void setSingnetContentType(String signetContentType) {
		this.signetContentType = signetContentType;
	}

	/**
	 * @return the signetType
	 */
	public int getSignetType() {
		return signetType;
	}

	/**
	 * @param signetType the signetType to set
	 */
	public void setSignetType(int signetType) {
		this.signetType = signetType;
	}

	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	/**
	 * @param signetContentType the signetContentType to set
	 */
	public void setSignetContentType(String signetContentType) {
		this.signetContentType = signetContentType;
	}

	/**
	 * @return the imgTypes
	 */
	public String getImgTypes() {
		return imgTypes;
	}

	/**
	 * @param imgTypes the imgTypes to set
	 */
	public void setImgTypes(String imgTypes) {
		this.imgTypes = imgTypes;
	}

	/**
	 * 
	 * 方法描述：进入员工添加页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:50:09<br/>         
	 * @return
	 * @version   1.0
	 */
	public String goAddPage() {
		return ADDPAGE;
	}
	
	/**
	 * 
	 * 方法描述：添加员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:51:48<br/>         
	 * @return
	 * @version   1.0
	 */
	public String addEmpl() {
		if(employeeService.isHaveEmpl(empl)){
			getRequest().setAttribute("errorMess", "用户账号已经存在！");
			return ADDPAGE;
		}
		boolean result = this.employeeService.addEmployee(empl);
		if ( result ) {
			this.addActionMessage("添加员工信息成功！");
			return ADDSUC;
		}
		this.addActionError("添加员工信息失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：删除员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:52:27<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String delEmpl() {
		boolean result = this.employeeService.deleteEmployeeById( empl.getEmplId());
		if (result) {
			return DELSUC;
		}
		this.addActionError("删除员工信息失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：将员工标识为离职<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-24 上午10:24:42<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String leaveOff() {
		boolean result = this.employeeService.leaveOffice(empl);
		if ( result ) {
			return "updateStatuSuc";
		}
		this.addActionError("将员工标志为注销失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：停用员工帐号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-24 上午10:24:59<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String suspend() {
		boolean result = this.employeeService.suspend(empl);
		if ( result ) {
			return "updateStatuSuc";
		}
		this.addActionError("停用员工帐号失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：启用员工帐号<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-24 上午10:59:26<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String enabledAccount() {
		boolean result = this.employeeService.enabledAccount(empl);
		if ( result ) {
			return "updateStatuSuc";
		}
		this.addActionError("停用员工帐号失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：进入修改员工信息页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:53:01<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String goUpdatePage() {
		empl = this.employeeService.findEmployeeById(empl);
		return UPDATEPAGE;
	}
	
	/**
	 * 方法描述：修改员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午02:58:23<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String updateEmpl() {
		boolean result = this.employeeService.updateEmployee(empl);
		if (result) {
			return UPDATESUC;
		}
		this.addActionError("修改员工信息失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：查找员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午03:01:53<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String searchEmpl() {
		page = this.employeeService.searchEmpl(empl, page);
		return LISTPAGE;
	}
	
	/**
	 * 方法描述：查询所有机构，用户在jsp页面生成机构树，用于查询机构员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-31 上午11:20:07<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String orgTree() {
		//orgList = this.organizationService.findAllOrganization("jmcode,jmssjg,jmname").getList();
		return "orgTree";
	}
	
	/**
	 * 方法描述：进入用户选择主界面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-27 下午03:01:53<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectMain() {
		if("1".equals(flag)){
			return "selectConaccess";
		}else{
			return "selectMain";
		}
	}
	/**
	 * 方法描述：进入选择审批角色主界面<br/>
	 * 创建人：丁艳伟   <br/>
	 * 创建时间：2012年2月27日19:38:55<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectPower(){

		return "selectPower";
	}
	/**
	 * 方法描述：按照部门查询在职员工信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 上午10:32:17<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String selectEmpl() {
		page = this.employeeService.searchEmplByOrgAndStauts(empl, page);
		return "selectPage";
	}
	
	/**
	 * 方法描述：查询所有机构，用户在jsp页面生成机构树，用于查询机构员工<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-5-31 上午11:20:07<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String orgTreeEmpl() {
//		orgList = this.organizationService.findAllOrganization("jmcode,jmssjg,jmname").getList();
//		if("3".equals(flag)){
//			return "orgTreeConaccess";
//		}else{
//		return "orgTreeEmpl";
//	}
		return "";
	}
	
	/**
	 *方法描述：  查询所有机构，用于查询机构员工<br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-5上午09:41:34
	 *@return 
	 *@version 1.0
	 */
	public String orgTreeEmple() {
		//orgList = this.organizationService.findAllOrganization("jmcode,jmssjg,jmname").getList();
		return "orgtreeEmple";
		
	}
	/**
	 *方法描述： 根据机构编号获得该机构下所有的在职员工 <br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-5上午11:30:54
	 *@return 
	 *@version 1.0
	 */
	public String getEmpls(){
		page = this.employeeService.searchEmplByOrgAndStauts(empl, page);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		String str = null;   
		PrintWriter out = null;
		try {
			out = response.getWriter();
            str = JSONObject.quote(JSONUtil.toJSONString(page.getList()));
            out.print(str);   
		} catch (IOException e1) {
			e1.printStackTrace();
		}  catch (JSONException e) {   
            log.error(DataUtil.getStackTraceAsString(e));;   
        } finally {
        	out.flush();   
        	out.close();   
        }
		return null;
	}
	

	/**
	 *创建人：郑辉
	 *描述： 用于获取所有的员工
	 *创建时间：2012-4-19 下午02:20:30
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String getEmpAll(){
		page = this.employeeService.searchEmplAll(page);
		return "workflowEmp";
	}
	
	/**
	 * 方法描述：进入角色添加页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午05:25:49<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String goAddRole() {
		rcodes = Arrays.toString(employeeService.getRoleCodes(empl));

		roleList = this.roleService.searchRole("roleId,roleName,roleType").getList();
		return "addRolePage";
	}
	
	/**
	 * 方法描述：添加用户角色信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-3 下午01:49:54<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String addRole() {
		boolean result = this.employeeService.addRole(empl);
		if (result) {
			return "addRoleSuc";
		}
		return FAIL;
	}
	
	/**
	 * 方法描述：修改密码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 下午02:28:20<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String changePassword() {
		String[] password = empl.getEmplPassword().split(", ");
		String oldPass1 = getEmplFromSession().getEmplPassword();
		String oldPass2 = Encrypt.digest(password[0]);
		boolean result = oldPass1.equals(oldPass2);
		if ( !result ) {
			throw new SystemException("您输入的旧密码不正确！");
		} 
		result = employeeService.updateEmployee(new String[]{"emplPassword"}, 
				new Object[]{Encrypt.digest(password[1]),getEmplFromSession().getEmplId()});
		if (result) {
			getEmplFromSession().setEmplPassword(Encrypt.digest(password[1]));
			addActionMessage("修改密码成功！");
			return "changeSuc";
		}
		addActionError("修改密码失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：进入密码修改页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-24 下午02:52:11<br/>    
	 * @return
	 * @version   1.0  
	 */
	public String goChangePage() {
		return "changePage";
	}
	
	/**
	 * 方法描述：重置员工密码<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-14 上午09:06:52<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String resetPassword() {
		boolean result = false;
		result = employeeService.resetPassword(empl);
		if (result) {
			return "resetSuc";
		}
		addActionError("重置密码失败！");
		return FAIL;
	}
	
	/**
	 * 方法描述：进入上传电子签章页面<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-16 下午02:44:05<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String goUpload() {
		Employee employee=(Employee)ServletActionContext.getRequest().getSession().getAttribute("loginEmpl");
		empl = this.employeeService.findEmployeeById(employee);
		return "uploadPage";
	}
	
	/**
	 * 方法描述：上传电子签章<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-15 下午05:40:54<br/>         
	 * @return
	 * @version   1.0  
	 */
	@SuppressWarnings("static-access")
	public String uploadImage() {
		if (null == signet) {
			throw new SystemException("您没有选择上传的电子签章图片！");
		}
		if (!imgTypes.contains(signetContentType)) { 
			throw new SystemException("图片格式错误，请上传.png,.gif格式的图片！");
		}
		File file1=null; //获得图片
		FileManager fileManager=new FileManager();
		String typeImg=fileManager.getFileExt(signetFileName); 
		typeImg=typeImg.substring(1,typeImg.length());//获得图片的格式（png,gif）
		boolean result = false;
		if (Constant.EMPLSIGNET == signetType) {
			try {
				new ImageSizer().resize(signet,signet,115,typeImg);  //对图片进行缩放处理 ，设置图片的宽为120 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error(DataUtil.getStackTraceAsString(e));;
			}
			empl.setEmplSignet(savePath + signetFileName);
			//result = employeeService.uploadEmplSignet(empl);
			result = employeeService.addSignet(empl, signet);
		} else {
			empl.setOrgSignet(savePath + signetFileName);
			result = employeeService.uploadOrgSignet(empl);
		}
		if (result) {
			//保存成功后删除本地文件
			return "uploadsuc";
		}
		addActionError("添加电子签名失败");
		return FAIL;
	}

	
	/**
	 * 方法描述：删除电子签章<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-29 上午10:09:46<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String delSignet() {
		boolean result = false;
		String sigType = getParameter("sigtype");
		empl = getEmplFromSession();
		if ("empl".equals(sigType)) {
			result = employeeService.deleteEmplSignet(empl);
			if (result) {
				empl.setEmplSignet(null);
				return "delSignet";
			}
		} else {
			result = employeeService.deleteOrgSignet(empl);
			if (result) {
				empl.setOrgSignet(null);
				return "delSignet";
			}
		}
		return FAIL;
	}
	
	/**
	 * 类描述：获取部门下所有的员工
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-31 下午12:15:32 
	 * 修改人：
	 * 修改时间：2012-8-31 下午12:15:32  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String getEmplOrgAll(){
		
		emplList =employeeService.searchEmplByOrgs(empl);
		return "toCustTree";
	}
	
	public String getOrgAllById()
	{
//		List<Organization> orgs = new ArrayList<Organization>();
//		orgs = organizationService.findAllOrganization();
//		
//		Organization organization  =new Organization();
//		organization.setOrgId(empl.getOrgId());
//		organization=organizationService.findOrgById(organization);
//		orgList.add(organization);
//	
//		
//		
//		for(int i =0;i<orgs.size();i++){
//			
//			if(orgs.get(i).getOrgParent().equals("00000000"))
//			{
//				orgList.add(orgs.get(i));			
//			}
//			if(orgs.get(i).getOrgParent().equals(organization.getOrgId())){
//				orgList.add(orgs.get(i));
//			}	
//		}
		return "toOrgTree";
	}
}
