package com.hnzskj.common.init;


import java.util.ArrayList;

import com.hnzskj.persist.bean.init.ConfigBean;
import com.hnzskj.persist.bean.init.Configure;
import com.hnzskj.persist.bean.init.DefaultPassword;
import com.hnzskj.persist.bean.init.InitAccountBean;
import com.hnzskj.persist.bean.init.InitCodeTemp;
import com.hnzskj.persist.bean.init.InitCommon;
import com.hnzskj.persist.bean.init.InitContractQueryScope;
import com.hnzskj.persist.bean.init.InitCustLbBean;
import com.hnzskj.persist.bean.init.InitDxztBean;
import com.hnzskj.persist.bean.init.InitET99Bean;
import com.hnzskj.persist.bean.init.InitGgbmBean;
import com.hnzskj.persist.bean.init.InitGysckfw;
import com.hnzskj.persist.bean.init.InitGyslb;
import com.hnzskj.persist.bean.init.InitGysxydj;
import com.hnzskj.persist.bean.init.InitHtfjlbBean;
import com.hnzskj.persist.bean.init.InitHtwblbBean;
import com.hnzskj.persist.bean.init.InitHtztBean;
import com.hnzskj.persist.bean.init.InitJsfsBean;
import com.hnzskj.persist.bean.init.InitKhjb;
import com.hnzskj.persist.bean.init.InitKhxydj;
import com.hnzskj.persist.bean.init.InitKhzt;
import com.hnzskj.persist.bean.init.InitKuckfw;
import com.hnzskj.persist.bean.init.InitLclbBean;
import com.hnzskj.persist.bean.init.InitLcspBean;
import com.hnzskj.persist.bean.init.InitLcztBean;
import com.hnzskj.persist.bean.init.InitLslztBean;
import com.hnzskj.persist.bean.init.InitMblbBean;
import com.hnzskj.persist.bean.init.InitQbzlBean;
import com.hnzskj.persist.bean.init.InitXmckfw;
import com.hnzskj.persist.bean.init.InitXmfjlbBean;
import com.hnzskj.persist.bean.init.InitZjxzBean;
import com.hnzskj.persist.bean.init.InithtLbBean;


/**
 * 系统参数提供类，通过实例化该类，调用它的方法获取系统参数。
 * 状态：创建 日期：2011-2-25
 * @version v1.0
 * @author 常利召
 *
 */
public class SysParamUtil {
	/**
	 * 系统参数保存对象
	 */
	private Configure config = Configure.getInstance();
	/**
	 * 系统参数
	 */
	private ConfigBean cb = config.getBean();

	public SysParamUtil() {
	}
	/**
	 * 获取初始化配置页面登录账号信息
	 * 
	 * @return InitAccountBean对象
	 */
	public InitAccountBean getInitAccount() {
		return cb.getInitAccount();
	}
	/**
	 * 方法描述：获得加密信息
	 * 创建人： 丁艳伟
	 * 创建时间：2011-8-9下午04:15:29
	 */
	public InitET99Bean getET99Bean(){
		return cb.getET99Bean();
		
	}
	/**
	 * 方法描述： 合同数据库 ,合同编号首字母，合同附件路径，是否选择流程
	 * 创建人： 丁艳伟
	 * 创建时间：2011-8-10下午02:05:52
	 */
	public InitGgbmBean getGgbmBean(){
		return cb.getInitGgbmBean();
	}
	/**
	 * 获取用户的初始密码
	 * 
	 * @return InitAccountBean对象
	 */
	public DefaultPassword getDefaultPassword() {
		return cb.getDefaultPassword();
	}
	/**
	 * 获得公共类
	 * 
	 * @return InitDatabase对象
	 */
	public InitCommon getInitCommon(){
		return cb.getInitCommon();
	}
	
	/**
	 * 获得客户类别
	 * 
	 * @return ArrayList<InitCustLbBean>对象
	 */
	public ArrayList<InitCustLbBean> getInitCustLb(){
		return cb.getInitCustLbBean();
	}
	/**
	 * 获得客户状态
	 * 
	 * @return ArrayList<InitKhzt>对象
	 */
	public ArrayList<InitKhzt> getInitKhzt(){
		return cb.getInitKhzt();
	}
	/**
	 * 获得客户级别
	 * 
	 * @return ArrayList<InitKhjb>对象
	 */
	public ArrayList<InitKhjb> getInitKhjb(){
		return cb.getInitKhjb();
	}
	/**
	 * 获得客户信用等级
	 * 
	 * @return ArrayList<InitKhxydj>对象
	 */
	public ArrayList<InitKhxydj> getInitKhxydj(){
		return cb.getInitKhxydj();
	}
	/**
	 * 获得供应商类别
	 * 
	 * @return ArrayList<InitKhxydjBean>对象
	 */
	public ArrayList<InitGyslb> getInitGyslb(){
		return cb.getInitGyslb();
	}
	/**
	 * 获得供应商信用等级
	 * 
	 * @return ArrayList<InitGysxydjBean>对象
	 */
	public ArrayList<InitGysxydj> getInitGysxydj(){
		return cb.getInitGysxydj();
	}
	/**
	 * 获得合同类别
	 * 
	 * @return ArrayList<InithtLbBean>对象
	 */
	public ArrayList<InithtLbBean> getInithtLb(){
		return cb.getInithtLbBean();
	}
	/**
	 * 获得资金性质
	 * 
	 * @return ArrayList<InitZjxzBean>对象
	 */
	public ArrayList<InitZjxzBean> getInitZjxz(){
		return cb.getInitZjxzBean();
	}
	/**
	 * 获得合同附件类别
	 * 
	 * @return ArrayList<InitHtfjlbBean>对象
	 */
	public ArrayList<InitHtfjlbBean> getInitHtfjlb(){
		return cb.getInitHtfjlbBean();
	}
	/**
	 * 获得临时流状态
	 * @return ArrayList<InitLslztBean>对象
	 */
	public ArrayList<InitLslztBean> getLsList() {
		return cb.getInitLslztBean();
	}
	/**
	 * 获得流程状态
	 * @return ArrayList<InitLcztBean>对象
	 */
	public ArrayList<InitLcztBean> getInitLczt() {
		return cb.getInitLcztBean();
	}
	/**
	 * 获得流程类别
	 * @return ArrayList<InitLclvBean>对象 
	 */
	public ArrayList<InitLclbBean> getInitLclb() {
		return cb.getInitLclbBean();
	}
	/**
	 * 获得对象状态
	 * @return ArrayList<InitDxztBean>对象
	 */
	public ArrayList<InitDxztBean> getInitDxzt() {
		return cb.getInitDxztBean();
	}
	/**
	 * 获得流程审批 预期默认
	 * @return ArrayList<InitLcspBean>对象
	 */
	public ArrayList<InitLcspBean> getInitLcsp() {
		return cb.getInitLcspBean();
	}
	/**
	 * 获得钱币种类
	 * @return ArrayList<InitQbzlBean>对象
	 */
	public ArrayList<InitQbzlBean> getInitQbzl() {
		return cb.getInitQbzlBean();
	}
	/**
	 * 获得项目附件类别
	 * @return ArrayList<InitXmfjlbBean>对象
	 */
	public ArrayList<InitXmfjlbBean> getInitXmfjlb() {
		return cb.getInitXmfjlbBean();
	}
	/**
	 * 获得模板类别
	 * @return ArrayList<InitMblbBean>对象
	 */
	public ArrayList<InitMblbBean> getInitMblb() {
		return cb.getInitMblbBean();
	}
	/**
	 * 获得合同文本类别
	 * @return ArrayList<InitHtwblbBean>对象
	 */
	public ArrayList<InitHtwblbBean> getInitHtwblb() {
		return cb.getInitHtwblbBean();
	}
	/**
	 * 获得结算方式
	 * @return ArrayList<InitJsfsBean>对象
	 */
	public ArrayList<InitJsfsBean> getInitJsfs() {
		return cb.getInitJsfsBean();
	}
	/**
	 * 获得合同状态
	 * @return ArrayList<InitHtztBean>对象
	 */
	public ArrayList<InitHtztBean> getInitHtzt() {
		return cb.getInitHtztBean();
	}
	
	/**
	 * 项目编码生成规则
	 * @return ArrayList<InitCodeTemp>
	 */
	public ArrayList<InitCodeTemp> getInitCodeTemp(){
		return cb.getInitCodeTemp();		
	}
	
	/**
	 *方法描述：  客户查看范围<br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-7上午10:34:23
	 *@return 
	 *@version 1.0
	 */
	public InitKuckfw getInitKuckfw() {
		return cb.getInitKuckfw();
		
	}
	/**
	 *方法描述： 供应商查看范围 <br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-7下午03:22:31
	 *@return 
	 *@version 1.0
	 */
	public InitGysckfw getInitGysckfw() {
		return cb.getInitGysckfw();
	}
	/**
	 *方法描述： 项目查看范围 <br/>
	 *创建人：晁飞 <br/>
	 *创建时间：2012-4-7下午03:22:46
	 *@return 
	 *@version 1.0
	 */
	public InitXmckfw getInitXmckfw() {
		return cb.getInitXmckfw();
	}
	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-16 上午11:37:17<br/>         
	 * @return
	 * @version   1.0  
	 */
	
	public InitContractQueryScope getInitCtractQueryScope() {
		return cb.getInitContractQueryScope();
	}
}
