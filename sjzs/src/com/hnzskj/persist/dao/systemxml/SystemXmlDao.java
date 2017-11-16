package com.hnzskj.persist.dao.systemxml;

import java.util.List;

import org.dom4j.DocumentException;

import com.hnzskj.persist.bean.init.InitCodeTemp;

/**
 * 描述：对系统配置文件system.xml的操作
 * @author 田玉江
 * 创建日期： 2011-6-15
 *
 */
public interface SystemXmlDao {
	/**
	 * 读取system.xml中的信息
	 * @return
	 * @throws DocumentException 
	 */
	public List<Object> listSystemXml() throws DocumentException;
	public boolean add(String nodeName, String name);
	public boolean del(String nodeName, String name) throws DocumentException;
	public boolean update(String nodeName, String code, String replaceName);
   
	/**
     * 方法描述：系统的短信功能是否处于开启状态<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-9-14 上午08:22:39<br/>         
     * @return
     * @version   1.0
     */
	public boolean isOpenSMS();
	
	/**
	 * 方法描述：获得短信猫的modem.com信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-16 上午09:18:08<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String getModemCom();
	
	/**
	 * 方法描述：获得短信猫COM信息<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-16 上午09:18:11<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String getCOM();
	
	/**
	 * 
	 * 方法描述：根据节点名称和组织机构级别删除系统配置文件的项目编码信息。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-19 下午06:20:07<br/>         
	 * @param nodeName 节点名称<br/>
	 * @param grade 组织机构节点级别<br/>   
	 * @return boolean true成功false失败<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delByCodeTemp(String nodeName, String grade) throws DocumentException;
	
	/**
	 * 
	 * 方法描述：根据组织结构级别修改组织机构信息。组织机构级别唯一。修改前要判断当前组织机构级别是否存在。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-19 下午06:51:04<br/>         
	 * @param nodeName 父节点<br/>
	 * @param code 需要修改的节点唯一标识<br/> 
	 * @param codeTemp 项目编码实体<br/>    
	 * @return <br/>   
	 * @version   1.0<br/>
	 */
	public boolean updateByCodeTemp(String nodeName, String code, InitCodeTemp codeTemp);
	
	/**
	 * 
	 * 方法描述：添加项目节点编码节点信息。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2012-3-20 上午11:25:07<br/>         
	 * @param nodeName 父节点<br/>
	 * @param codeTemp 项目编码实体<br/>   
	 * @return boolean true成功false失败<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addCodeTemp(String nodeName, InitCodeTemp codeTemp);
	/**
	 *方法描述： 修改查看范围<br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-7下午02:22:48
	 *@return 
	 *@version 1.0
	 */
	public boolean updateScope(String nodeName, String code);
}
