/*
 * @项目名称: htglxt
 * @文件名称: RoleServiceImpl.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.dao.system.RoleDao;
import com.hnzskj.service.system.RoleService;

/**        
 * 
 * 类名称：RoleServiceImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午02:08:51   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午02:08:51   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#addRole(com.hnzskj.persist.bean.system.Role)
	 */
	/**
	 * @return the roleDao
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * @param roleDao the roleDao to set
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	@Description("添加角色信息")
	public boolean addRole(Role role) {
		int result = 0;
		result = roleDao.addRole(role);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#deleteRole(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	@Description("删除角色信息")
	public boolean deleteRole(Role role) {
		int result = 0;
		result = roleDao.deleteRoleById(role.getRoleId());
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#searchRole(java.lang.String)
	 */
	@Override
	@Description("查询角色信息")
	public Page<Role> searchRole(String fields) {
		Page<Role> page = new Page<Role>();
		page = roleDao.searchRole(fields);
		return page;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#searchRole(java.lang.String, com.hnzskj.common.Page)
	 */
	@Override
	@Description("分页查询角色信息")
	public Page<Role> searchRole(String fields, Role role, Page<Role> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(role.getRoleName()) && null != role.getRoleName()) {
			sb.append(" and roleName like ? ");
			queryParams.add("%" + role.getRoleName() + "%");
		}
		page = roleDao.searchRole(page, fields, sb.toString(), queryParams.toArray());
		
		return page;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#findByRcode(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	@Description("根据角色代码查询角色信息")
	public Role findById(Role role) {
		role = this.roleDao.findById(role.getRoleId());
		return role;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#updateRole(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	@Description("更新角色信息")
	public boolean updateRole(Role role) {
		int result = 0;
		result = roleDao.updateRole(role);
		return result == 1 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#authorize(com.hnzskj.persist.bean.system.Role, java.lang.String[])
	 */
	@Override
	@Description("给角色分配权限")
	public boolean authorize(Role role, String[] pcodes) {
		int result = roleDao.saveRolePower(role, pcodes);
		return result > 0 ? true : false;
	}

	@Override
	@Description("查询角色拥有的所有功能代码")
	public String[] getPcode(String rcode) {
		String[] pcodes = null;
		List<Power> powers = roleDao.getPowersCode(rcode);
		pcodes = new String[powers.size()];
		for (int i = 0; i < powers.size(); i++) {
			pcodes[i] = powers.get(i).getPowerId();
		}
		return pcodes;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#isExsit(int, java.lang.String)
	 */
	@Override
	@Description("判断角色名称是否存在")
	public boolean isExsit(String roleId, String rname) {
		boolean result = false;
		int count = 0;
		count = roleDao.getRoleCountByIdAndName(roleId, rname);
		result = count > 0 ? true : false;
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#checkRole(java.lang.String)
	 */
	@Override
	public boolean checkRole(String roleName) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.RoleService#getRoleNames(java.lang.String)
	 */
	@Override
	public String getRoleNames(String emplId){
		List<Role> list = roleDao.getRoleNameByUid(emplId);
		if(null!=list&&list.size()>0){
			StringBuilder sb = new StringBuilder();
			for (Role role : list) {
				sb.append(role.getRoleName()).append(",");
			}
			return sb.toString();
		}
		return "";
	}
}
