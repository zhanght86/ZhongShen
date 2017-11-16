package com.hnzskj.common;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.hnzskj.common.solr.SolrTools;
import com.hnzskj.persist.util.MyDataGenerator;

public class NFDFlightDataTimerTask extends TimerTask {
	private String uploadTempDir = "";
	private String serverTempDir = "";
	// 时间间隔
	private long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private int hour ;
	private int min;
	
	private String systempath;
    private Logger logger = Logger.getLogger(NFDFlightDataTimerTask.class);
	private String PropertiesURL = ""; // 获取clientTables.properties 文件的路径

	
	
	/**
	 * @return the pERIOD_DAY
	 */
	public long getPERIOD_DAY() {
		return PERIOD_DAY;
	}


	/**
	 * @param pERIODDAY the pERIOD_DAY to set
	 */
	public void setPERIOD_DAY(long pERIODDAY) {
		PERIOD_DAY = pERIODDAY;
	}


	/**
	 * @return the hour
	 */
	public int getHour() {
		return hour;
	}


	/**
	 * @param hour the hour to set
	 */
	public void setHour(int hour) {
		this.hour = hour;
	}


	/**
	 * @return the min
	 */
	public int getMin() {
		return min;
	}


	/**
	 * @param min the min to set
	 */
	public void setMin(int min) {
		this.min = min;
	}


	/**
	 * @return the systempath
	 */
	public String getSystempath() {
		return systempath;
	}


	/**
	 * @param systempath the systempath to set
	 */
	public void setSystempath(String systempath) {
		this.systempath = systempath;
	}


	/**
	 * @return the propertiesURL
	 */
	public String getPropertiesURL() {
		return PropertiesURL;
	}


	/**
	 * @param propertiesURL the propertiesURL to set
	 */
	public void setPropertiesURL(String propertiesURL) {
		PropertiesURL = propertiesURL;
	}


	/**
	 * @return the uploadTempDir
	 */
	public String getUploadTempDir() {
		return uploadTempDir;
	}


	/**
	 * @param uploadTempDir the uploadTempDir to set
	 */
	public void setUploadTempDir(String uploadTempDir) {
		this.uploadTempDir = uploadTempDir;
	}

	/**
	 * @return the serverTempDir
	 */
	public String getServerTempDir() {
		return serverTempDir;
	}


	/**
	 * @param serverTempDir the serverTempDir to set
	 */
	public void setServerTempDir(String serverTempDir) {
		this.serverTempDir = serverTempDir;
	}

	public NFDFlightDataTimerTask() {
		
	}
	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	   //启动计时任务
    public void start() {
    	
    	File file = new File(systempath);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(file);
			this.hour = Integer.parseInt(doc.selectSingleNode("//system//hour").getText());
			this.min = Integer.parseInt(doc.selectSingleNode("//system//min").getText());
			this.PERIOD_DAY = Long.parseLong(doc.selectSingleNode("//system//PERIOD_DAY").getText());
		} catch (DocumentException e) {
			e.printStackTrace();
			
		}
    	
    	Calendar calendar = Calendar.getInstance();
		/*** 定制每日10:00执行方法 ***/
		calendar.set(Calendar.HOUR_OF_DAY, this.hour);
		calendar.set(Calendar.MINUTE, this.min);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime(); // 第一次执行定时任务的时间
		// 如果第一次执行定时任务的时间 小于 当前的时间
		// 此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。

		if (date.before(new Date())) {
			date = this.addDay(date, 1);
		}

		Timer timer = new Timer();
//		NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();
		// 安排指定的任务在指定的时间开始进行重复的固定延迟执行。
		timer.schedule(this, date, PERIOD_DAY);
    }
    
	@Override
	public void run() {
		try {
			// 在这里写你要执行的内容
			   sjzsDeleteUploadTemps(uploadTempDir+"/doc/");
			   sjzsDeleteUploadTemps(uploadTempDir+"/swf/");
		       sjzsDeleteUploadTemps(serverTempDir);
		       //updateIndexs();
		       deleteUnUserItems();
		       //更新索引
		   	new SolrTools().buildIndex(false);
		} catch (Exception e) {
			logger.info("-------------解析信息发生异常--------------");
		}
	}
	
	 /**
     * 
     * 描述： 定时删除文件上传转换临时文件doc 和swf   服务器中的临时文件<br/>
     * 创建人：wenxuanzhen <br/>
     * 创建时间：2013-4-8 上午08:44:09 <br/>  
     * @version   1.0
     */
    private void sjzsDeleteUploadTemps(String dir){
    	File f = new File(dir);
    	File[] files = null;
        if (f.exists() && f.isDirectory() ) {
            files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
            		String fileName = files[i].getName();
            		boolean result = files[i].delete();
            		if ( result ) {
            			logger.info("Delete log files: " + fileName );
            	}
            }
        }
    }
    /**
     * 
     * 描述：更新前台全文检索的索引<br/>
     * 创建人：wenxuanzhen <br/>
     * 创建时间：2013-4-8 上午08:45:07 <br/>  
     * @version   1.0
     */
    
    private void updateIndexs(){
    }
	
    
	private void deleteUnUserItems() {
		MyDataGenerator  dataGenerator = new MyDataGenerator();
		Map<String, String> map = (Map<String, String>) dataGenerator.getProperties("map");
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String deletesql = "";
			Object object = iterator.next();
			deletesql += "DELETE  FROM " + object + " WHERE deleteFlag = -1 " ;
            this.doDelete(deletesql);
		}
	}
	
	private int doDelete(String sql){
		BaseDao baseDao = new BaseDao();
		return baseDao.update(sql, null);
	}
}
