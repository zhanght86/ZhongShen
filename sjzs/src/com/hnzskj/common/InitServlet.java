package com.hnzskj.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.inti.ParamGetManager;
import com.hnzskj.persist.dao.inti.impl.ParamGetManagerImpl;


public class InitServlet extends HttpServlet {
	
	private static final long serialVersionUID = 3471744266439819256L;
	private static Logger log = Logger.getLogger(InitServlet.class);
	private static String prePath;
	public void init() {
		String strPrefix = getServletContext().getRealPath("/");
		prePath = strPrefix;
		// 初始化项目根目录
		Constant.webRoot = strPrefix;
		// 初始化log4j
		initLog4j(Constant.webRoot);
		log.info("项目工程路径：" + Constant.webRoot);
		// 初始化数据库配置文件路径
		Constant.config_database_path = Constant.webRoot+ getInitParameter("databasePath").substring(1);
		log.debug("数据库配置文件路径：" + Constant.config_database_path);
		// 初始化系统参数配置文件路径
		Constant.config_parameter_path = Constant.webRoot
				+ getInitParameter("systemParameter").substring(1);
		log.debug("系统参数配置文件路径：" + Constant.config_parameter_path);
		// 初始化Spring容器的上下文对象
		Constant.context = WebApplicationContextUtils
				.getWebApplicationContext(this.getServletContext());
		// 读取系统参数
		ParamGetManager paramGM = new ParamGetManagerImpl();
		paramGM.saveParams(Constant.config_parameter_path);
	
		//设置不经过检查的权限功能
		getServletContext().setAttribute("uncheck", getUncheckList());
		//创建空白的List<Employee>,List<String>用于封装登陆过的用户的ip和登陆的用户
		initEmplsAndIps();
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) {
	}
	private void initLog4j(String strPrefix) {
		System.setProperty("htglxt.root", strPrefix);
		String strFile = getInitParameter("log4j");
		if (strFile != null)
			PropertyConfigurator
					.configure(strPrefix + File.separator + strFile);
		else {
			System.exit(-1);
		}
		//用于处理定期删除日志文件
		String path = this.getServletContext().getRealPath("WEB-INF\\logs");
		String pathSJZS = this.getServletContext().getRealPath("/files/sjzs/");
		String urlpath =  this.getServletContext().getRealPath("WEB-INF\\config\\clientTables.properties");
		String systempath = this.getServletContext() .getRealPath("WEB-INF\\config\\system.xml");
		String serverTemp = this.getUploadTempPath();
		
		FileDeleter deleter = new FileDeleter();
		deleter.setTargetDirectory(path);
		deleter.start();
//		
		NFDFlightDataTimerTask sjzsTimer = new NFDFlightDataTimerTask();
		sjzsTimer.setUploadTempDir(pathSJZS);//删除文件转换的临时文件
		sjzsTimer.setServerTempDir(serverTemp);//删除文件上传的临时文件
		sjzsTimer.setPropertiesURL(urlpath);
		sjzsTimer.setSystempath(systempath);
		sjzsTimer.start();
/*		String springXMLLocation = strPrefix + "/WEB-INF/config/applicationContext.xml";
		SendSMSTimer sendSMSTimer = SendSMSTimer.getInstance(springXMLLocation);
		sendSMSTimer.start();*/
	}
	public static String getPrePath() {
		return prePath;
	}
	
	/**
	 * 方法描述：获得不需要检查的功能列表<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-6-25 上午09:17:14<br/>         
	 * @return
	 * @version   1.0  
	 */
	public String getUncheckList() {
		//取得不经过检查功能配置文件路径
		String path = this.getServletContext().getRealPath("WEB-INF\\config\\uncheck.txt");
		File file = new File(path);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			log.info("找不到WEB-INF\\config\\uncheck.txt文件！");
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		InputStreamReader in = new InputStreamReader(is);;
		BufferedReader reader = new BufferedReader(in );
		StringBuilder content = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {//每次读取一行数据，行与行之间以逗号分隔
				if (line.trim() != null && line.trim().length() > 0) {
					if (!line.startsWith("##")) {
						content.append(line).append(",");
					}
				}
			}
		} catch (IOException e) {
			log.info("读取WEB-INF\\config\\uncheck.txt文件出错！");
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return content.toString();
	}
	/**
	 * 
	 * 描述：获取tomcat logs文件<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-8 上午09:33:22 <br/>  
	 * @version   1.0
	 */
	private String getUploadTempPath() {
//		String systempath = this.getServletContext() .getRealPath("WEB-INF\\config\\system.xml");
//		String serverTempPath = "";
//		File file = new File(systempath);
//		SAXReader reader = new SAXReader();
//		Document doc = null;
//		try {
//			doc = reader.read(file);
//			serverTempPath = doc.selectSingleNode("//system//serverTemp").getText();
//		} catch (DocumentException e) {
//			e.printStackTrace();
//		}
		String tomcatPath=System.getProperty("catalina.home")+"/temp/";
		tomcatPath=tomcatPath.replace("/", File.separator);
		return tomcatPath;
	}

	
	/**
	 * 方法描述：初始化登陆的用户列表和登陆的ip列表<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-12 上午09:13:05<br/>         
	 * @version   1.0  
	 */
	private void initEmplsAndIps() {
		ServletContext context = getServletContext();
		List<Employee> empls = new ArrayList<Employee>();
		
		List<String> ips = new ArrayList<String>();
		context.setAttribute("ips", ips);
		context.setAttribute("empls", empls);
	}
}
