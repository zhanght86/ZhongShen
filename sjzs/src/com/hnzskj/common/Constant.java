/*
 * @项目名称: htglxt
 * @文件名称: EmployeeDao.java
 * @日期: 2011-5-23
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import org.springframework.context.ApplicationContext;

/**
 * 
 * 类名称：Constant <br/>
 * 类描述：系统共用常量<br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class Constant {

	/**
	 * 项目根目录（在InitServlet中初始化）
	 */
	public static String webRoot = "";

	/**
	 * 数据库配置文件路径（在InitServlet中初始化）
	 */
	public static String config_database_path = "";

	/**
	 * 系统配置参数路径（在InitServlet中初始化）
	 */
	public static String config_parameter_path = "";

	/**
	 * 初始化Spring容器的上下文对象
	 */
	public static ApplicationContext context = null;

	/**
	 * 表示性别男
	 */
	public static final String MAN = "1";

	/**
	 * 表示性别女
	 */
	public static final String WOMAN = "0";

	/**
	 * 员工未启用状态
	 */
	public static final String UNINIT = "0";

	/**
	 * 员工正常在职状态
	 */
	public static final String NORMAL = "1";

	/**
	 * 员工离职
	 */
	public static final String DIMISSION = "2";

	/**
	 * 员工帐号停用
	 */
	public static final String SUSPEND = "3";

	/**
	 * 个人电子签章
	 */
	public static final int EMPLSIGNET = 0;

	/**
	 * 部门电子签章
	 */
	public static final int ORGSIGNET = 1;

	/**
	 * 是否是短信功能 是 0
	 */
	public static final String TMESS = "0";
	/**
	 * 是否是短信功能 否1
	 */
	public static final String FMESS = "1";

	/**
	 * 判断是审批角色 ，还是业务角色，0 是审批角色，1是业务角色
	 */
	public static final int RTYPEY = 1;
	/**
	 * 判断是审批角色 ，还是业务角色，1 是审批角色，0是业务角色
	 */
	public static final int RTYPEN = 0;

	/** 机构类型－公司 */
	public static final int COMPANY = 0;

	/** 机构类型－部门 */
	public static final int DEPARTMENT = 1;

	/**
	 * 未删除
	 */
	public static final int NODEL = 1;

	/**
	 * 已删除
	 */
	public static final int YESDEl = 2;

	/**
	 * 草稿
	 */
	public static final int BACKUP = 3;

	/**
	 * 公开
	 */
	public static final int PUBLIC = 1;

	/**
	 * 不公开
	 */
	public static final int NOPUBLIC = 2;
	/**
	 * 未阅读
	 */
	public static final int NOREAD = 1;

	/**
	 * 已阅读
	 */
	public static final int READ = 2;

	/**
	 * 人员级别3 系统管理员
	 */
	public static int EMPLOYEE_ADMIN = 3;

	/**
	 * 人员级别2 部门领导
	 */
	public static int EMPLOYEE_LEADER = 2;

	/**
	 * 人员级别1 一般员工
	 */
	public static int EMPLOYEE_COMMONLY = 1;

	/**
	 * 模板类别－日报
	 */
	public static String DAILY = "1";

	/**
	 * 模板类型－周报
	 */
	public static String WEEKLY = "2";

	/**
	 *任务类型-开发项目
	 */
	public static String PROTASK = "3";

	/**
	 *任务类型-日常任务
	 */
	public static String DAYTASK = "4";

	/**
	 *任务类型-售前项目
	 */
	public static String SQTASK = "5";
	/**
	 *任务类型-实施项目
	 */
	public static String SSTASK = "6";
	/**
	 *任务类型-服务项目
	 */
	public static String FWTASK = "7";

	/**
	 *权限类型- 业务权限
	 */
	public static String POTTOE = "0";
	/**
	 *流程类型- 业务权限
	 */
	public static String FLTTOE = "1";

	/**
	 * 销售人员
	 */
	public static String ROLENAME = "销售人员";

	/**
	 * 超级管理员Id
	 */
	public static String ADMIN = "00000000";
	/**
	 * 编号
	 */
	public static String hNZSCODE = "HNZS";

	/**
	 * 父级字典类型是自己设置
	 */
	public static String RCODENOTSYS = "0";

	/**
	 * 父级字典类型是通过查询获取
	 */
	public static String RCODESYS = "1";
	/**
	 * 用户U盘使用期限为1年
	 */
	public static long userKeydeadLine = 365 * 24 * 60 * 60 * 1000;

	/* 下面常量主要用于前台，搜索类型 */
	/**
	 * @Fields ALL : 全部
	 */
	public static final String ALL = "All";
	/**
	 * @Fields SJFG : 审计法规
	 */
	public static final String SJFG = "FG";

	/**
	 * @Fields SJYJ :审计依据
	 */
	public static final String DXYJ = "YJ";

	/**
	 * @Fields FFAL : 方法案例
	 */
	public static final String FFAL = "AL";

	/**
	 * @Fields SJDH : 审计导航
	 */
	public static final String SJDH = "DH";

	/**
	 * @Fields SJSX : 审计事项
	 */
	public static final String SJSX = "SX";

	/**
	 * @Fields SJSS : 审计实施方案
	 */
	public static final String SJSS = "EI";
	
	/**
	 * 首页检索每页显示的数量
	 */
	public static  int INDEXPAGESIZE=15;
	
	/**
	 * 批量创建索引的数量。考虑到数据量大的话会内存溢出，所以分批次创建索引
	 */
	public static int BUILDINDEXSIZE=100;
	
	/**全文检索服务*/
	public static String SOLRSERVER="http://localhost:8983/solr";
	
	/**
	 * 待审核 
	 */
	public static final int CHECK_NO = 0;
	
	/**
	 * 审核中
	 */
	public static final int CHECK_ING = 1;
	
	/**
	 * 已审核
	 */
	public static final int CHECK_ED = 2;
	
	/**
	 * 已打回
	 */
	public static final int CHECK_BACK = 3;
	
	/**
	 * 查询所有状态
	 */
	public static final int CHECK_ALL = 4;
	
	/**
	 * 数据已迁移
	 */
	public static final Integer TRANSPORT_ED = 1;
	
	/**
	 * 数据未迁移
	 */
	public static final Integer TRANSPORT_NO = 0;
}
