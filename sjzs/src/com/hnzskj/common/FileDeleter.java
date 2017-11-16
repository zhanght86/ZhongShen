package com.hnzskj.common;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class FileDeleter extends TimerTask {
    private String targetDirectory = "";
	private Timer timer = new Timer();
    private Logger logger = Logger.getLogger(FileDeleter.class);
    
    public String getTargetDirectory() {
		return targetDirectory;
	}

	public void setTargetDirectory(String targetDirectory) {
		this.targetDirectory = targetDirectory;
	}

	/**
     * 
     * 方法描述：删除指定目录下的符合规的文件<br/>
     * 创建人：苏国庆   <br/>
     * 创建时间：2011-3-9 下午02:42:17<br/>         
     * @param dir　指定的目录
     * @version   1.0
     */
    private void delFiles(String dir) {  
    	Date nowDate = new java.sql.Date(System.currentTimeMillis());
    	File f = new File(dir);
    	File[] files = null;
        if (f.exists() && f.isDirectory() ) {
            files = f.listFiles();
            for (int i = 0; i < files.length; i++) {
            	long between = nowDate.getTime() - files[i].lastModified();
            	if ( (between / (7 * 24 * 60 * 60 * 1000 ) )> 0 ) {
            		String fileName = files[i].getName();
            		boolean result = files[i].delete();
            		if ( result ) {
            			logger.info("Delete log files: " + fileName );
            		}
            	}
            }
        }
    }
    
    //启动计时任务
    public void start() {
    	//每隔指定的时间执行删除一次任务
        timer.scheduleAtFixedRate(this, new Date(), 24 * 60 * 60 * 1000);
    }
    
    //运行计时任务
    public void run() {        
        delFiles( targetDirectory );
    }
}
