/*
 * @项目名称: OA
 * @文件名称: ProjectServiceImpl.java
 * @日期: 2012-8-9 上午11:35:29  
 * @版权: 2012 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.service.system.impl;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.system.Project;
import com.hnzskj.persist.dao.system.ProjectDao;
import com.hnzskj.service.system.ProjectService;

/**    
 * 项目名称：OA   <br/>
 * 类名称：ProjectServiceImpl.java   <br/>
 * 类描述：  项目信息维护业务层实现类 <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2012-8-9 上午11:35:29   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2012-8-9 上午11:35:29   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class ProjectServiceImpl implements ProjectService {

	/**
	 * 项目实体业务注入
	 */
	private ProjectDao projectDao = null;
	/**
	 * 项目实体业务注入
	 * @param projectDao the projectDao to set
	 */
	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.ProjectService#addProject(com.hnzskj.persist.bean.system.Project)
	 */
	@Override
	@Description("添加一条项目信息")
	public boolean addProject(Project project) {
		int result = projectDao.addProject(project);
		return result >0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.ProjectService#delProject(java.lang.String)
	 */
	@Override
	@Description("删除一条项目信息")
	public boolean delProject(String proId) {
		int result = projectDao.delProject(proId);
		return result >0?true:false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.ProjectService#getProject(java.lang.String)
	 */
	@Override
	@Description("获取一条项目信息")
	public Project getProject(String proId) {
		Project project = projectDao.getProject(proId);
		return project;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.ProjectService#searchProject(com.hnzskj.common.Page, com.hnzskj.persist.bean.system.Project)
	 */
	@Override
	@Description("查询项目信息列表")
	public Page<Project> searchProject(Page<Project> page, Project project) {
		String fields=" PROID,PRONO,PRONAME,PROORDER,PRODATETIME,NOTE ";
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		if(null!=project){
			//项目名称
			if(null!=project.getProName()&&!"".equals(project.getProName())){
				sb.append(" AND PRONAME like '%").append(project.getProName().trim()).append("%'");
			}
			//项目编号
			if(null!=project.getProNo()&&!"".equals(project.getProNo())){
				sb.append(" AND PRONO like '%").append(project.getProNo()).append("%'");
			}
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("PROORDER", "ASC");
		page = this.projectDao.searchProject(page, fields, sb.toString(), null, orderby);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.ProjectService#updProject(com.hnzskj.persist.bean.system.Project)
	 */
	@Override
	@Description("修改一条项目信息")
	public boolean updProject(Project project) {
		int result = projectDao.updProject(project);
		return result >0?true:false;
	}

}
