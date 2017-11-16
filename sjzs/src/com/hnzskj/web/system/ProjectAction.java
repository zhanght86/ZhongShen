/*
 * @项目名称: OA
 * @文件名称: ProjectAction.java
 * @日期: 2012-8-9 下午02:03:22  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.web.system;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.system.Project;
import com.hnzskj.service.system.ProjectService;
import com.hnzskj.web.BaseAction;

/**    
 * 项目名称：OA   <br/>
 * 类名称：ProjectAction.java   <br/>
 * 类描述：  项目信息维护逻辑层 <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 下午02:03:22   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 下午02:03:22   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class ProjectAction extends BaseAction{

	private static final long serialVersionUID = 1741631891367868895L;
	/**
	 * 项目信息
	 */
	private Project project = new Project();
	
	/**
	 * 项目信息分页
	 */
	private Page<Project> page = new Page<Project>();
	
	/**
	 * 项目信息业务层注入
	 */
	private ProjectService projectService = null;
	
	/**
	 * 关闭的页签
	 */
	private String closePage;
	
	/**
	 * 刷新的页签
	 */
	private String refreshPage;	
	
	/**
	 * 标记字段
	 */
	private String type;

	/**
	 * 标记字段
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * 标记字段
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 关闭的页签
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}

	/**
	 * 关闭的页签
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * 刷新的页签
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}

	/**
	 * 刷新的页签
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
	}

	/**
	 * 项目信息
	 * @return the project
	 */
	public Project getProject() {
		return project;
	}

	/**
	 * 项目信息
	 * @param project the project to set
	 */
	public void setProject(Project project) {
		this.project = project;
	}

	/**
	 * 项目信息分页
	 * @return the page
	 */
	public Page<Project> getPage() {
		return page;
	}

	/**
	 * 项目信息分页
	 * @param page the page to set
	 */
	public void setPage(Page<Project> page) {
		this.page = page;
	}

	/**
	 * 项目信息业务层注入
	 * @param projectService the projectService to set
	 */
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	/**
	 * 
	 * 方法描述：查询项目列表信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午02:28:14<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String searchProject(){
		page = projectService.searchProject(page, project);
		return LISTPAGE;
	}
	
	/**
	 * 
	 * 方法描述：进入项目添加页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午03:26:01<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String goAddPage(){
		return ADDPAGE;
	}
	
	/**
	 * 
	 * 方法描述：添加项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午03:48:37<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String addProject(){
		boolean result = projectService.addProject(project);
		if ( result ) {
			this.addActionMessage("添加项目信息成功！");
			return ADDSUC;
		}
		this.addActionError("添加项目信息失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：进入项目详细信息页面<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午04:18:40<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String goUpdatePage(){
		project = projectService.getProject(project.getProId());
		return SHOWPAGE;
	}
	
	/**
	 * 
	 * 方法描述：修改项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午04:43:37<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String updProject(){
		boolean result = projectService.updProject(project);
		if ( result ) {
			this.addActionMessage("修改项目信息成功！");
			return UPDATESUC;
		}
		this.addActionError("修改项目信息失败！");
		return FAIL;
	}
	
	/**
	 * 
	 * 方法描述：删除项目信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-8-9 下午04:45:54<br/>         
	 * @param <br/>   
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public String delProject(){
		boolean result = projectService.delProject(project.getProId());
		if ( result ) {
			this.addActionMessage("删除项目信息成功！");
			if(null!=type&&"listPage".equals(type)){
				return DELSUC;
			}else{
				return "delSucByShowPage";
			}
		}
		this.addActionError("删除项目信息失败！");
		return FAIL;
	}
	
}
