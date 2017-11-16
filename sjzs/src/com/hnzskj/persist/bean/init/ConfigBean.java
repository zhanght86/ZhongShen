package com.hnzskj.persist.bean.init;

import java.util.ArrayList;

public class ConfigBean {
	/**
	 * 初始页面登录账户信息
	 */
	private InitAccountBean initAccount;
	/**
	 * 公共类别
	 */
	private InitCommon initCommon;
	/**
	 * 客户类别
	 */
	private ArrayList<InitCustLbBean>  initCustLbBean;
	/**
	 * 客户状态
	 */
	private ArrayList<InitKhzt>  initKhzt;
	/**
	 * 客户级别
	 */
	private ArrayList<InitKhjb>  initKhjb;
	/**
	 * 客户信用等级
	 */
	private ArrayList<InitKhxydj>  initKhxydj;
	
	/**
	 * 供应商类别
	 */
	private ArrayList<InitGyslb>  initGyslb;
	/**
	 * 供应商信用等级
	 */
	private ArrayList<InitGysxydj>  initGysxydj;
	/**
	 * 合同类别
	 */
	private ArrayList<InithtLbBean>  inithtLbBean;
	/**
	 * 资金性质类别
	 */
	private ArrayList<InitZjxzBean>  initZjxzBean;
	/**
	 * 合同附件类别
	 */
	private ArrayList<InitHtfjlbBean>  initHtfjlbBean;
	/**
	 * 
	 * 方法描述：临时流类别
	 * 创建人： 丁艳伟
	 * 创建时间：2011-6-14下午04:18:12
	 * @return
	 * @return
	 */
	private ArrayList<InitLslBean> initLslBean;
	
	/**
	 * 临时流状态
	 */
	private ArrayList<InitLslztBean> initLslztBean;
	/**
	 * 流程类别
	 */
	private ArrayList<InitLclbBean> initLclbBean;
	/**
	 * 流程状态
	 */
	private ArrayList<InitLcztBean> initLcztBean;
	/**
	 * 对象状态
	 */
	private ArrayList<InitDxztBean> initDxztBean;
	/**
	 * 流程审批 预期默认
	 */
	private ArrayList<InitLcspBean> initLcspBean;
	/**
	 * 钱币种类
	 */
	private ArrayList<InitQbzlBean> initQbzlBean;
	/**
	 * 项目附件类别
	 */
	private ArrayList<InitXmfjlbBean> initXmfjlbBean;
	/**
	 * 模板类别
	 */
	private ArrayList<InitMblbBean> initMblbBean;
	/**
	 * 合同文本类别
	 */
	public ArrayList<InitHtwblbBean> initHtwblbBean;

	/**
	 * 结算方式
	 */
	public ArrayList<InitJsfsBean> initJsfsBean;
	/**
	 * 合同状态
	 */
	public ArrayList<InitHtztBean> initHtztBean;
	
	/**
	 * 用户的初始密码
	 */
	public DefaultPassword defaultPassword;
	/**
	 * 加密
	 */
	public InitET99Bean initET99Bean;
	/**
	 * 合同数据库 ,合同编号首字母，合同附件路径，是否选择流程，是否启用短信功能
	 */
	public InitGgbmBean initGgbmBean;
	
	/**
	 * 项目编码生成规则
	 */
	public ArrayList<InitCodeTemp> initCodeTemp;
	
	
	/**
	 * 客户查看范围
	 */
	private InitKuckfw initKuckfw;
	
	/**
	 * 供应商查看范围
	 */
	private InitGysckfw initGysckfw;
	
	/**
	 * 项目查看范围
	 */
	private InitXmckfw initXmckfw;
	
	/**
	 * 项目查看范围
	 */
	private InitContractQueryScope initContractQueryScope;
	
	
	/**
	 * 项目编码生成规则
	 * @return initCodeTemp
	 */
	public ArrayList<InitCodeTemp> getInitCodeTemp() {
		return initCodeTemp;
	}
	
	/**
	 * 项目编码生成规则
	 * @param initCodeTemp the initCodeTemp to set
	 */
	public void setInitCodeTemp(ArrayList<InitCodeTemp> initCodeTemp) {
		this.initCodeTemp = initCodeTemp;
	}

	/**
	 * 合同数据库 ,合同编号首字母，合同附件路径，是否选择流程，是否启用短信功能
	 */
	public InitGgbmBean getInitGgbmBean() {
		return initGgbmBean;
	}

	public void setInitGgbmBean(InitGgbmBean initGgbmBean) {
		this.initGgbmBean = initGgbmBean;
	}

	public InitET99Bean getET99Bean() {
		return initET99Bean;
	}

	public void setAddPassWordBean(InitET99Bean addPassWordBean) {
		this.initET99Bean = addPassWordBean;
	}

	/**
	 * @return the defaultPassword
	 */
	public DefaultPassword getDefaultPassword() {
		return defaultPassword;
	}

	/**
	 * @param defaultPassword the defaultPassword to set
	 */
	public void setDefaultPassword(DefaultPassword defaultPassword) {
		this.defaultPassword = defaultPassword;
	}

	public ArrayList<InitGyslb> getInitGyslb() {
		return initGyslb;
	}

	public void setInitGyslb(ArrayList<InitGyslb> initGyslb) {
		this.initGyslb = initGyslb;
	}

	public ArrayList<InitKhxydj> getInitKhxydj() {
		return initKhxydj;
	}

	public void setInitKhxydj(ArrayList<InitKhxydj> initKhxydj) {
		this.initKhxydj = initKhxydj;
	}

	public ArrayList<InitKhjb> getInitKhjb() {
		return initKhjb;
	}

	public void setInitKhjb(ArrayList<InitKhjb> initKhjb) {
		this.initKhjb = initKhjb;
	}

	public ArrayList<InitHtztBean> getInitHtztBean() {
		return initHtztBean;
	}

	public void setInitHtztBean(ArrayList<InitHtztBean> initHtztBean) {
		this.initHtztBean = initHtztBean;
	}

	public ArrayList<InitJsfsBean> getInitJsfsBean() {
		return initJsfsBean;
	}

	public void setInitJsfsBean(ArrayList<InitJsfsBean> initJsfsBean) {
		this.initJsfsBean = initJsfsBean;
	}

	public ArrayList<InitLslBean> getInitLslBean() {
		return initLslBean;
	}

	public void setInitLslBean(ArrayList<InitLslBean> initLslBean) {
		this.initLslBean = initLslBean;
	}

	

	public ArrayList<InitHtwblbBean> getInitHtwblbBean() {
		return initHtwblbBean;
	}

	public void setInitHtwblbBean(ArrayList<InitHtwblbBean> initHtwblbBean) {
		this.initHtwblbBean = initHtwblbBean;
	}

	public ArrayList<InitLcspBean> getInitLcspBean() {
		return initLcspBean;
	}

	public void setInitLcspBean(ArrayList<InitLcspBean> initLcspBean) {
		this.initLcspBean = initLcspBean;
	}

	public ArrayList<InitKhzt> getInitKhzt() {
		return initKhzt;
	}

	public void setInitKhzt(ArrayList<InitKhzt> initKhzt) {
		this.initKhzt = initKhzt;
	}

	public ArrayList<InitDxztBean> getInitDxztBean() {
		return initDxztBean;
	}

	public void setInitDxztBean(ArrayList<InitDxztBean> initDxztBean) {
		this.initDxztBean = initDxztBean;
	}

	public ArrayList<InitLcztBean> getInitLcztBean() {
		return initLcztBean;
	}

	public void setInitLcztBean(ArrayList<InitLcztBean> initLcztBean) {
		this.initLcztBean = initLcztBean;
	}

	public ArrayList<InitCustLbBean> getInitCustLbBean() {
		return initCustLbBean;
	}

	public void setInitCustLbBean(ArrayList<InitCustLbBean> initCustLbBean) {
		this.initCustLbBean = initCustLbBean;
	}
	public InitCommon getInitCommon() {
		return initCommon;
	}

	public void setInitCommon(InitCommon initCommon) {
		this.initCommon = initCommon;
	}

	public InitAccountBean getInitAccount() {
		return initAccount;
	}

	public void setInitAccount(InitAccountBean initAccount) {
		this.initAccount = initAccount;
	}

	public ArrayList<InithtLbBean> getInithtLbBean() {
		return inithtLbBean;
	}

	public void setInithtLbBean(ArrayList<InithtLbBean> inithtLbBean) {
		this.inithtLbBean = inithtLbBean;
	}

	public ArrayList<InitZjxzBean> getInitZjxzBean() {
		return initZjxzBean;
	}

	public void setInitZjxzBean(ArrayList<InitZjxzBean> initZjxzBean) {
		this.initZjxzBean = initZjxzBean;
	}

	public ArrayList<InitHtfjlbBean> getInitHtfjlbBean() {
		return initHtfjlbBean;
	}

	public void setInitHtfjlbBean(ArrayList<InitHtfjlbBean> initHtfjlbBean) {
		this.initHtfjlbBean = initHtfjlbBean;
	}

	public void setInitLslztBean(ArrayList<InitLslztBean> initLslztBean) {
		this.initLslztBean = initLslztBean;
	}

	public ArrayList<InitLslztBean> getInitLslztBean() {
		return initLslztBean;
	}

	public void setInitLclbBean(ArrayList<InitLclbBean> initLclbBean) {
		this.initLclbBean = initLclbBean;
	}

	public ArrayList<InitLclbBean> getInitLclbBean() {
		return initLclbBean;
	}

	public void setInitQbzlBean(ArrayList<InitQbzlBean> initQbzlBean) {
		this.initQbzlBean = initQbzlBean;
	}

	public ArrayList<InitQbzlBean> getInitQbzlBean() {
		return initQbzlBean;
	}


	public ArrayList<InitXmfjlbBean> getInitXmfjlbBean() {
		return initXmfjlbBean;
	}

	public void setInitXmfjlbBean(ArrayList<InitXmfjlbBean> initXmfjlbBean) {
		this.initXmfjlbBean = initXmfjlbBean;
	}

	public ArrayList<InitMblbBean> getInitMblbBean() {
		return initMblbBean;
	}

	public void setInitMblbBean(ArrayList<InitMblbBean> initMblbBean) {
		this.initMblbBean = initMblbBean;
	}

	public ArrayList<InitGysxydj> getInitGysxydj() {
		return initGysxydj;
	}

	public void setInitGysxydj(ArrayList<InitGysxydj> initGysxydj) {
		this.initGysxydj = initGysxydj;
	}

	/**
	 * @return the initKuckfw
	 */
	public InitKuckfw getInitKuckfw() {
		return initKuckfw;
	}

	/**
	 * @param initKuckfw the initKuckfw to set
	 */
	public void setInitKuckfw(InitKuckfw initKuckfw) {
		this.initKuckfw = initKuckfw;
	}

	/**
	 * @return the initGysckfw
	 */
	public InitGysckfw getInitGysckfw() {
		return initGysckfw;
	}

	/**
	 * @param initGysckfw the initGysckfw to set
	 */
	public void setInitGysckfw(InitGysckfw initGysckfw) {
		this.initGysckfw = initGysckfw;
	}

	/**
	 * @return the initXmckfw
	 */
	public InitXmckfw getInitXmckfw() {
		return initXmckfw;
	}

	/**
	 * @param initXmckfw the initXmckfw to set
	 */
	public void setInitXmckfw(InitXmckfw initXmckfw) {
		this.initXmckfw = initXmckfw;
	}

	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2012-4-16 上午11:32:00<br/>         
	 * @param initQuery
	 * @version   1.0  
	 */
	
	public void setInitContractQueryScope(InitContractQueryScope initQuery) {
		this.initContractQueryScope = initQuery;
	}

	/**
	 * @return the initContractQueryScope
	 */
	public InitContractQueryScope getInitContractQueryScope() {
		return initContractQueryScope;
	}
	
}
