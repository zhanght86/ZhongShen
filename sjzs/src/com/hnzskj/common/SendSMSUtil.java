/*
 * @项目名称: htglxt
 * @文件名称: SendSMSUtil.java
 * @日期: 2011-9-8
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**        
 * 
 * 类名称：SendSMSUtil     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-9-8 上午08:21:38   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-9-8 上午08:21:38   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SendSMSUtil {
	private static Logger log = Logger.getLogger(SendSMSUtil.class);
	
	/**
	 * 方法描述：<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-9-16 下午04:50:37<br/>         
	 * @param smses
	 * @param allowedResend
	 * @version   1.0  
	 */
	public static void sendSMS(String smsSet, boolean allowedResend) {
		String str = smsSet.replace("[", "").replace("]", "").trim();
		if ("".equals(str)) {
			return;
		}
		//方法1：直接使用类路径下面的WSDL文件
		String wsdl = "SendSMSService.wsdl"; // 对应的WSDL文件		
		Resource resource = new ClassPathResource(wsdl);
		Client client = null;
		try {
			client = new Client(resource.getInputStream(), null);
		} catch (IOException e) {
			log.error("获得WebService客户端出错：" + DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		} catch (Exception e) {
			log.error("获得WebService客户端出错：" + DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		} 
		
		// 根据WSDL创建客户实例
		Object[] objArray = new Object[2];
		objArray[0] = smsSet.toString();
		objArray[1] = allowedResend;
		
		// 调用特定的Web Service方法
		Object[] results = null;
		
		try {
			results = client.invoke("sendSMS", objArray);
		} catch (Exception e) {
			log.error("调用WebService服务器端出错：" + DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		
		try {
			log.info("成功发送短信条数：" + results[0]);
		} catch (Exception e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
	}
}
