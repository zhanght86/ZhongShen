/*
 * @项目名称: htglxt
 * @文件名称: PowerServiceImpl.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.system.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.common.SystemException;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.system.Power;
import com.hnzskj.persist.bean.system.Role;
import com.hnzskj.persist.dao.system.EmployeeDao;
import com.hnzskj.persist.dao.system.PowerDao;
import com.hnzskj.persist.dao.system.RoleDao;
import com.hnzskj.service.system.PowerService;

/**        
 * 
 * 类名称：PowerServiceImpl     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 下午02:16:40   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 下午02:16:40   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class PowerServiceImpl implements PowerService{
	private PowerDao powerDao;
	
	private RoleDao roleDao;
	
	private EmployeeDao employeeDao;
	
	/**
	 * @return the employeeDao
	 */
	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	/**
	 * @param employeeDao the employeeDao to set
	 */
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

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

	/**
	 * @return the powerDao
	 */
	public PowerDao getPowerDao() {
		return powerDao;
	}

	/**
	 * @param powerDao the powerDao to set
	 */
	public void setPowerDao(PowerDao powerDao) {
		this.powerDao = powerDao;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#addPower(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("添加功能信息")
	public boolean addPower(Power power) {
		if (!"#".equals(power.getPowerUrl())) {
			String countSql = "select count(powerUrl) from power where powerUrl=?";
			Integer records = Integer.parseInt( new BaseDao().getSingleValue(countSql, new Object[]{power.getPowerUrl()}).toString());
			if ( records > 0) {
				throw new SystemException("请选择其他的权限url，数据库中已经存在当前url！");
			}
		}
		int result = 0;
		result = powerDao.addPower(power);
		return result > 0 ? true : false;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#deletePower(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("删除功能信息")
	public boolean deletePowers(Serializable... powerIds) {
		int result = 0;
		result = powerDao.deletePowerByPowerIds(powerIds);
		return result >= powerIds.length ? true : false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#updatePower(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("更新功能信息")
	public boolean updatePower(Power power) {
		Power temp = this.powerDao.findById(power.getPowerId());
		if ( !power.getPowerUrl().equals( temp.getPowerUrl() ) && !"#".equals(power.getPowerUrl())) {
			String countSql = "select count(powerUrl) from power where powerUrl=? and powerId != '" + power.getPowerId() + "'";
			Integer records = Integer.valueOf(new BaseDao().getSingleValue(countSql, new Object[]{power.getPowerUrl()}).toString());
			if ( records > 0) {
				throw new SystemException("请选择其他的权限url，数据库中已经存在当前url！");
			}
		}
		int result = 0;
		result = powerDao.updatePower(power);
		return result == 1 ? true : false;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#findByPcode(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("根据功能代码查询权限信息")
	public Power findById(Power power) {
		power = powerDao.findById(power.getPowerId());
		if (power != null && power.getPowerParent().equals("00000000")) {
			Power root = new Power();
			root.setPowerName("系统功能");
			power.setParentNode(root);
		} else {
			Power parentNode = powerDao.findPowerNameById(power.getPowerParent());
			power.setParentNode(parentNode);
		}
		return power;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#searchPower(java.lang.String)
	 */
	@Override
	@Description("查询功能信息")
	public Page<Power> searchPower(String fields) {
		Page<Power> page = new Page<Power>();
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("powerOrder", "asc");
		page = powerDao.searchPower(null, fields, orderBy);
		return page;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#searchPower(java.lang.String, com.hnzskj.persist.bean.system.Power, com.hnzskj.common.Page)
	 */
	@Override
	@Description("根据条件查询所有的权限")
	public Page<Power> searchPower(String fields, Power power, Page<Power> page) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");
		List<Object> queryParams = new ArrayList<Object>();
		if (!"".equals(power.getPowerName()) && null != power.getPowerName()) {
			sb.append(" and powerName like ? ");
			queryParams.add("%" + power.getPowerName() + "%");
		}
		if (!"".equals(power.getPowerId()) && null != power.getPowerId()) {
			sb.append(" and powerParent = ?");
			queryParams.add(power.getPowerId());
		}
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("powerOrder", "asc");
		page = powerDao.searchPower(page, fields, sb.toString(), queryParams.toArray(), orderBy);
		return page;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#findChildren(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("查找当前功能的子功能")
	public Page<Power> findChildren(Power power, String fields) {
		Page<Power> page = new Page<Power>();
		String sqlCondition = " where powerParent=?";
		Object[] queryParams = {power.getPowerId()};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("powerOrder", "asc");
		page = powerDao.searchPower(page, fields, sqlCondition, queryParams, orderBy);
		return page;
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#buildXMLForRole(com.hnzskj.persist.bean.system.Role)
	 */
	@Override
	@Description("创建为角色分配权限的xml文档")
	public String buildXMLForRole(Role role) {
		//查询角色拥有的权限
		List<Power> rpowers = roleDao.getPowers(role.getRoleId());
		
		String sqlCondition = " where powerSuperPower=?";
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("powerOrder", "asc");
		//查询所有的权限
		List<Power> powers = powerDao.searchPower(null,"powerId,powerName,powerParent,powerType",sqlCondition,new Object[]{false}, orderby).getList();
		StringBuffer strBuffer = new StringBuffer("<?xml version='1.0' encoding='utf-8'?><tree id=\"0\">");
//		strBuffer.append("<item text=\"系统功能\" id=\"1\" >");
		Power power = new Power("00000000","-1","系统功能");
		createTreeXML(power, powers, strBuffer, rpowers ,role);
//		strBuffer.append("</item></tree>");
		strBuffer.append("</tree>");
		return strBuffer.toString();
	}
	
	@Override
	@Description("创建为角色分配权限的xml文档")
	public String buildXMLForOneRole(Role role) {
		//查询角色拥有的权限
		List<Power> rpowers = roleDao.getPowers(role.getRoleId());
		StringBuffer strBuffer = new StringBuffer("<?xml version='1.0' encoding='utf-8'?><tree id=\"0\">");
		Power power = new Power("00000000","-1","系统功能");
		createTreeXML(power, rpowers, strBuffer, rpowers ,role);
		strBuffer.append("</tree>");
		return strBuffer.toString();
	}
	
	/**
	 * 构建dhtmlxtree的xml文档
	 * @param sysMenuList
	 * @param strBuffer
	 */
	@Description("构建dhtmlxtree的xml文档")
	private void createTreeXML(Power power, List<Power> powers, StringBuffer strBuffer,List<Power> rpowers,Role role) {
		List<Power> tempList = findChild(power, powers);//获得此菜单的子菜单
		for (Power power2 : tempList) {
			String powerType=power2.getPowerType(); if(null == powerType) powerType="";
			String roleType=role.getRoleType();  if(null == roleType) roleType="";
			if(powerType.equals(roleType)){  //业务角色对应业务权限，审批角色对应流程权限
				if (rpowers.contains(power2)) {//如果当前角色包含菜单,则显示时为选中状态
					strBuffer.append( "<item text=\"" + power2.getPowerName() 
							+ "\" open=\"1\" id=\"" + power2.getPowerId() + "\" checked=\"1\">");
				} else {
					strBuffer.append( "<item text=\"" + power2.getPowerName() 
							+ "\" open=\"1\" id=\"" + power2.getPowerId() + "\"> ");
				}
			}else{
				continue;
			}
			createTreeXML(power2, powers, strBuffer, rpowers ,role);//递归调用
			strBuffer.append("</item>");
		}
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
	@Description("从结果集中查找当前指定权限的子权限")
	private List<Power> findChild(Power power, List<Power> powers) {
		List<Power> childPowers = new ArrayList<Power>();		
		for (int i = 0; i < powers.size(); i++) {
			Power tempPower = powers.get(i);
			if ( tempPower.getPowerParent().equals( power.getPowerId() )) {//如果当前权限是power的子节点
				childPowers.add(tempPower);
			}
		}		
		return childPowers;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#findParentCode(java.lang.String)
	 */
	@Override
	@Description("查询功能的父级功能编号")
	public String findParentCode(String powerId) {
		String parentCode = powerDao.findParentCode(powerId);		
		return parentCode;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#findDirectChild(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("查询直接子功能信息")
	public List<Power> findDirectChild(Power power) {
		String sqlCondition = " where powerParent = ?";
		Object[] param = new Object[]{power.getPowerId()};
		LinkedHashMap<String, String> orderBy = new LinkedHashMap<String, String>();
		orderBy.put("powerOrder", "asc");
		List<Power> list = powerDao.searchPower(null, " powerId, powerName, powerUrl ", sqlCondition, param, orderBy).getList();
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.service.system.PowerService#getPowerStr(com.hnzskj.persist.bean.system.Power)
	 */
	@Override
	@Description("生成功能菜单字符串")
	public String getPowerStr(Power power, List<Power> powers) {
		Collections.sort(powers);
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
	@Description("构建功能菜单树")
	private static String createTree(Power power, List<Power> powers) {
		List<Power> temp = findChildPower(power, powers);//获得此菜单的子菜单
		StringBuffer sb = new StringBuffer();
		boolean haschild = false;
		if (!temp.isEmpty()) {
			for (Power child1 : temp) {
				List<Power> temp2 = findChildPower(child1, powers);//获得此菜单的子菜单
				if (!temp2.isEmpty()) {
					sb.append("{title:\"").append(child1.getPowerName()).append("\",html:\"");
					haschild = true;
					for (Power child2 : temp2) {
						sb.append("<a class='menu' href='").append(child2.getPowerUrl()).append("' target='rightFrame'>");
						sb.append("<div class='menudiv' onmouseover=this.className='onmouseover' onmouseout=this.className='menudiv'>");
						sb.append(child2.getPowerName()).append("</div></a>");
					}
					sb.append("\"},");
				} else {
					sb.append("<a class='menu' href='").append(child1.getPowerUrl()).append("' target='rightFrame'>");
					sb.append("<div class='menudiv' onmouseover=this.className='onmouseover' onmouseout=this.className='menudiv'>");
					sb.append(child1.getPowerName()).append("</div></a>");
				}
			}
			if (!haschild) {
				//sb.append("{title:\"").append(power.getPowerName()).append("\",html:\"");
				sb.insert(0, "\",html:\"");
				sb.insert(0, power.getPowerName());
				sb.insert(0, "{title:\"");
				sb.append("\"},");
			}
		}
		if (sb.length() >= 1) {
			sb.deleteCharAt(sb.length()-1);
		}
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
	@Description("从结果集中查找当前指定权限的子权限")
	private static List<Power> findChildPower(Power power, List<Power> powers) {
		List<Power> childPowers = new ArrayList<Power>();		
		for (int i = 0; i < powers.size(); i++) {
			Power tempPower = powers.get(i);
			if ( tempPower.getPowerParent().equals( power.getPowerId() )) {//如果当前权限是power的子节点
				childPowers.add(tempPower);
			}
		}
		return childPowers;
	}
}
