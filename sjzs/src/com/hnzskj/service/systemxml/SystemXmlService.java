/*
 * @项目名称: htglxt
 * @文件名称: SystemXmlServlceImpl.java
 * @日期: 2011-6-15
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.service.systemxml;

import java.util.List;

import org.dom4j.DocumentException;

import com.hnzskj.persist.bean.init.InitCodeTemp;

/**
 * 描述：对系统配置文件system.xml的操作服务接口
 * @author 田玉江
 * 创建日期： 2011-6-15
 */
public interface SystemXmlService {
	/**
	 * 方法描述：对system.xml的读取
	 * 创建人：田玉江
	 * @return  List<Object>
	 * @throws DocumentException 
	 */
	public List<Object> listSystemXml() throws DocumentException;
	/**
	 * 方法描述：根据节点名称和子节点的名称来创建一个子节点
	 * @param nodeName 父节点的名称
	 * @param name 子节点的名称
	 * @return boolean
	 */
	public boolean add(String nodeName, String name);
	/**
	 * 方法描述：根据节点名称和子节点的code值来删除一个子节点
	 * @param nodeName 父节点的名称
	 * @param code 子节点的code值
	 * @return boolean
	 * @throws DocumentException
	 */
	public boolean del(String nodeName, String code) throws DocumentException;
	/**
	 * 方法描述：根据节点名称、子节点的code值和新名称来更新一个子节点
	 * @param nodeName 父节点的名称
	 * @param code 子节点的值
	 * @param replaceName 新名称
	 * @return
	 */
	public boolean update(String nodeName, String code, String replaceName);
	
	/**
	 * 
	 * 方法描述：删除项目编码配置信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-19 下午06:14:09<br/>         
	 * @param nodeName 节点名称<br/>
	 * @param code code值<br/>   
	 * @return boolean true删除成功false删除失败<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delByCodeTemp(String nodeName, String code) throws DocumentException;
	
	/**
	 * 
	 * 方法描述：修改项目编码配置信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-19 下午07:20:04<br/>         
	 * @param nodeName 节点名称<br/>
	 * @param code 作为grade使用。部门级别<br/>
	 * @param codeTemp 项目编码实体<br/>      
	 * @return boolean true修改成功false修改失败<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updateByCodeTemp(String nodeName, String code, InitCodeTemp codeTemp);
	
	/**
	 * 
	 * 方法描述：添加项目编码节点信息<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-20 上午11:20:55<br/>         
	 * @param nodeName 父节点<br/>   
	 * @param codeTemp 项目节点实体 <br/>
	 * @return boolean true成功false失败 <br/>      
	 * @version   1.0<br/>
	 */
	public boolean addCodeTemp(String nodeName,InitCodeTemp codeTemp);
	
	/**
	 *方法描述： 修改查看范围<br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-7下午02:22:48
	 *@return 
	 *@version 1.0
	 */
	public boolean updateScope(String nodeName, String code);
}
