/*
 * @项目名称: htglxt
 * @文件名称: DownloadAction.java
 * @日期: 2011-6-11
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.DataUtil;
import com.hnzskj.common.init.SysParamUtil;

/**        
 * 
 * 类名称：DownloadAction     <br/>
 * 类描述：实现文件下载的工具类<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-11 下午02:03:59   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-11 下午02:03:59   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class DownloadAction extends BaseAction {
	private static final long serialVersionUID = -6003308627061839840L;
	private static final Logger LOGGER = Logger.getLogger(DownloadAction.class);
	//下载时客户端保存文件时的名称
	private String fileName;
	//文件在服务器上保存的位置,文件夹的名称和文件的名称，如"file/image/a.jpg"
	private String savePath;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		try {
			this.fileName = new String(fileName.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
		}
	}
	
	/**
	 * @return the savePath
	 */
	public String getSavePath() {
		return savePath;
	}

	/**
	 * @param savePath the savePath to set
	 */
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public String execute() throws Exception {
		//将文件名进行处理，解决文件名中文乱码的问题	
		fileName = java.net.URLEncoder.encode(fileName,"utf-8");	
		//获取文件的绝对路径
		String filePath = new SysParamUtil().getInitCommon().getFilePath() + savePath;
		//设置下载的返回客户端浏览器的头信息
		getResponse().setContentType("application/x-download;chartset=GBK");
		getResponse().setHeader("Content-Disposition", "attachment;filename=" + fileName );

		OutputStream os = ServletActionContext.getResponse().getOutputStream();
		FileInputStream fis = new FileInputStream( filePath );
		try {
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = fis.read(b)) > 0) {
			   os.write(b, 0, i);
			}
		} catch (IOException e) {
			LOGGER.error("下载文件出错....");
			LOGGER.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			fis.close();
			os.flush();
			os.close();
		}
		return null;
	}
}
