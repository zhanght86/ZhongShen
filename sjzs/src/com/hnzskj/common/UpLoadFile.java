/*
 * @项目名称: htglxt
 * @文件名称: UpFile.java
 * @日期: 2011-6-9
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**        
 * 
 * 类名称：UpFile 简单文件的上传和下载<br/>
 * 类描述：<br/>
 * 创建人：余鹏飞  <br/>
 * 创建时间：2011-6-9 下午03:00:08   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-9 下午03:00:08   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */

public class UpLoadFile {
	/**
	 * 
	 * 方法描述：上传文件到指定的项目路径<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午03:00:21<br/>         
	 * @param saveDir
	 * @param fileName
	 * @param upfile
	 * @return
	 * @throws FileNotFoundException
	 * @version   1.0
	 */
	private static String ROOTURL="";
	private static int timeMillis=0;
	static {
		String filePath = WordUtil.class.getClassLoader().getResource("").toString();
		//因为获得文件的协议为本地文件传输协议，所以要去掉URL中前面的"file:/" 
		//如果获得的URL中含有空格，则空格会被"%20"替换，但是dom4j识别不了"%20",所以要将"%20"重新转换为空格" "
		ROOTURL= filePath.replace("file:/", "").replace("%20", " ").replace("WEB-INF/classes/", "");
	}
	/**
	 * 
	 * 方法描述：判断源文件是否存在，避免用户随便输入路径<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午04:19:16<br/>         
	 * @param sourcePath
	 * @return
	 * @throws Exception
	 * @version   1.0
	 */
	public static boolean isExists(String sourcePath) throws Exception{
		boolean isFail = false;
	    File serviceFile =new File(sourcePath);
		if(serviceFile.exists()){
	        isFail = true;
		}
		return isFail;
	}
	
	/**
	 * 
	 * 方法描述：判断文件是否过大，如果过大就不能上传<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午04:35:09<br/>         
	 * @param sourcePath
	 * @return
	 * @version   1.0
	 */
	public static int getSize(String sourcePath){
		 int size = 0;
		 File serviceFile =new File(sourcePath);
		//获取文件内容大小
		    long fileSize = serviceFile.length();
		    //设置文件上传的最大内存为10M
		    long maxSize = 1024*10*10;
		    if(fileSize>maxSize){
		    	size = 1;
		    }
		    return size;
	}
	/**
	 * 
	 * 方法描述：上传文件的主要方法<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午03:26:24<br/>         
	 * @param sourcePath 源文件的路径
	 * @throws Exception
	 * @version   1.0
	 */
	
	public static int copyFile(String sourcePath) throws Exception{
	    	//文件从命名后的保存路径
	    	String savePath = ROOTURL + "proFile/"+reNameSaveFile(sourcePath);
	    	FileInputStream in = new FileInputStream(sourcePath);         // 来源文件            
	    	FileOutputStream out = new FileOutputStream(savePath);       // 目的文件
	    	BufferedInputStream bufferedIn = new BufferedInputStream(in);
	    	BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
	    	byte[] data = new byte[1];
	    	while (bufferedIn.read(data) != -1) {
	    		@SuppressWarnings("unused")
				String  content = new String(data);
	    		bufferedOut.write(data);                                     
	    	}
	    	bufferedOut.flush();												//将缓冲区中的数据全部写出    
	    	bufferedIn.close();													//关闭流        
	    	bufferedOut.close();
	    	return timeMillis;
	}
	
	/**
	 * 
	 * 方法描述：对上传的文件进行重新命名<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午03:30:22<br/>         
	 * @param sourceFileName 源文件名称
	 * @return 命名后的文件名称
	 * @version   1.0
	 */
	public static String reNameSaveFile(String sourceFileName){
		//获取系统毫秒数并转换成字符串形式
	   timeMillis=(int)System.currentTimeMillis();
		String temp[] = sourceFileName.replaceAll("\\\\","/").split("/");
		//获得文件名称和后缀名称
		if (temp.length > 1) {
			sourceFileName = temp[temp.length - 1];
		}
		//获得文件名中最后一个.的索引
		int lastIndex = sourceFileName.lastIndexOf(".");
		//获取最后一个.前面的部分(文件的名称)并加上当前毫秒数来组成文件的新的名称
		String preStr = sourceFileName.substring(0, lastIndex)+timeMillis+"";
		//获取最后一个.后面的部分(文件的后缀+.)
		String lastStr = sourceFileName.substring(lastIndex);
		//生成一个该文件新的名称的字符串
		String reNameFile =preStr+lastStr;
		return reNameFile;
	}
	
	/***
	 * 
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-9 下午04:40:50<br/>         
	 * @param sourcePath
	 * @return
	 * @throws Exception
	 * @version   1.0
	 */
	public static int UpFiles(String sourcePath) throws Exception{
		int resultNum = 0;
		if(!isExists(sourcePath)){
			resultNum = 0;      //说明上传文件不存在	
		    return resultNum;
		}if(getSize(sourcePath)==1){
			resultNum = 1;      //说明上传文件大于10M
			return resultNum;
		}
			resultNum = copyFile(sourcePath);
		    return resultNum;
	}
}
