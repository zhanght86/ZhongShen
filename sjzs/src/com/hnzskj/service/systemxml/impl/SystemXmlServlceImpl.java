/*
 * @项目名称: htglxt
 * @文件名称: SystemXmlServlceImpl.java
 * @日期: 2011-6-15
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.systemxml.impl;

import java.util.List;

import org.dom4j.DocumentException;

import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.init.InitCodeTemp;
import com.hnzskj.persist.dao.systemxml.SystemXmlDao;
import com.hnzskj.service.systemxml.SystemXmlService;

/**        
 * 
 * 类名称：EmployeeServiceImpl     <br/>
 * 类描述：<br/>
 * 创建人：田玉江   <br/>
 * 创建时间：2011-6-15   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-15 <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */

public class SystemXmlServlceImpl implements SystemXmlService {

	private SystemXmlDao systemXmlDao;
	public SystemXmlDao getSystemXmlDao() {
		return systemXmlDao;
	}
	public void setSystemXmlDao(SystemXmlDao systemXmlDao) {
		this.systemXmlDao = systemXmlDao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#listSystemXml()
	 */
	@Override
	public List<Object> listSystemXml() throws DocumentException {
		// TODO Auto-generated method stub
		return systemXmlDao.listSystemXml();
	}
	/*
	 * (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#add(java.lang.String, java.lang.String)
	 */
	@Description("为配置信息添加元素")
	@Override
	public boolean add(String nodeName, String name) {
		// TODO Auto-generated method stub
		return systemXmlDao.add(nodeName, name);
	}
	/*
	 * (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#del(java.lang.String, java.lang.String)
	 */
	@Description("删除配置信息的某个节点")
	@Override
	public boolean del(String nodeName, String code) throws DocumentException {
		// TODO Auto-generated method stub
		return systemXmlDao.del(nodeName, code);
	}
	/*
	 * (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#update(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Description("修改配置信息")
	@Override
	public boolean update(String nodeName, String code, String replaceName) {
		// TODO Auto-generated method stub
		return systemXmlDao.update(nodeName, code, replaceName);
	}
	
	@Description("删除项目编码配置信息")
	@Override
	public boolean delByCodeTemp(String nodeName, String code) throws DocumentException {
		// TODO Auto-generated method stub
		return systemXmlDao.delByCodeTemp(nodeName, code);
	}
	
	/* (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#updateByCodeTemp(java.lang.String, java.lang.String, com.hnzskj.persist.bean.init.InitCodeTemp)
	 */
	@Override
	@Description("修改项目编码信息")
	public boolean updateByCodeTemp(String nodeName, String grade, InitCodeTemp codeTemp){
		return systemXmlDao.updateByCodeTemp(nodeName, grade, codeTemp);
	}
	
	@Override
	@Description("添加项目编码节点信息")
	public boolean addCodeTemp(String nodeName,InitCodeTemp codeTemp){
		return systemXmlDao.addCodeTemp(nodeName, codeTemp);
	}
	/* (non-Javadoc)
	 * @see com.hnzskj.service.systemxml.SystemXmlService#updateScope()
	 */
	@Override
	public boolean updateScope(String nodeName, String code) {
		return systemXmlDao.updateScope(nodeName,code);
	}

}
