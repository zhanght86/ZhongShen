package com.hnzskj.common;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.hnzskj.persist.dao.systemxml.SystemXmlDao;

public class SendSMSTimer extends TimerTask {
    private static String springXMLLocation = "";
    private static final SendSMSTimer SENDMESSAGE = new SendSMSTimer();
	private Timer timer = new Timer();
	private Logger log = Logger.getLogger(SendSMSTimer.class);
    
    private SendSMSTimer() {}
    
    /**
     * 方法描述：获得发送短信的定时器<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-9-5 下午02:59:57<br/>         
     * @param springXMLLocation	spring配置文件的存放路径
     * @return
     * @version   1.0  
     */
    public static SendSMSTimer getInstance(String springXMLLocation) {
    	SendSMSTimer.springXMLLocation = springXMLLocation;    	
    	return SENDMESSAGE;
    }
    
	/**
     * 
     * 方法描述：<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-3-9 下午02:42:17<br/>         
     * @param dir　指定的目录
     * @version   1.0
     */
    private void send() {
    	ApplicationContext ac = new FileSystemXmlApplicationContext(springXMLLocation);
    	//项目到期
    	SystemXmlDao systemXmlDao = (SystemXmlDao) ac.getBean("systemXmlDao");
    	
		if (systemXmlDao.isOpenSMS()) {//如果短信功能开启
    		log.info("开始查找到期的数据信息");
	    	log.info("查找到期的数据信息完毕");
    	}
    }
    
    //启动计时任务
    public void start() {
    	try {
    		/**
    		 * 休眠10秒钟解决开始执行对数据库信息进行统计时，数据库连接池尚未初始化完成的问题
    		 */
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			log.error(DataUtil.getStackTraceAsString(e));
			log.error(DataUtil.getStackTraceAsString(e));;
		}
    	log.info("定时器启动");
    	//每隔指定的时间执行一次任务
        timer.scheduleAtFixedRate(this, new Date(), 30 * 60 * 1000);
    }
    
    //运行计时任务
    public void run() {
		log.info("定时器开始执行任务");
		//开始执行扫描任务
        send();
    }
}
