package com.hnzskj.web.upload;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.DataUtil;
import com.hnzskj.common.WordUtils;
import com.opensymphony.xwork2.ActionSupport;


public class ExportWordAction extends ActionSupport {
	private static Logger log = Logger.getLogger(ExportWordAction.class);
	private static final long serialVersionUID = 1L;
	private String filedataFileName;
	private File filedata;
	private String content = null;  
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFiledataFileName() {
		return filedataFileName;
	}
	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}
	public File getFiledata() {
		return filedata;
	}
	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
/**
 * 方法描述：上传word文件， 然后让word文件能在网页上能以原格式显式
 * @author 柴茂森
 * @param wordPath  上传的word文件的路径
 * @param htmlPath  根据word文件生成的html文件的路径
 * @param path      word上传以后的文件的根路径
 * @param content   经过处理后的word文档的内容
 */
  public String uploadWord(){
           
	    String htmlPath = null;     
	    String wordPath = null; 
	    
	    //获得当前项目下已存在的一个目录 用来存放上传的word文档
		String path = ServletActionContext.getServletContext().getRealPath("\\word");
		if (filedata != null) {
			File savefile = new File(new File(path), filedataFileName);
			if (!savefile.getParentFile().exists()) {
				savefile.getParentFile().mkdirs();
			}
			try {
				FileUtils.copyFile(filedata, savefile);
			} catch (IOException e) {
				log.error(DataUtil.getStackTraceAsString(e));;
			}
			     
            String fileName = filedataFileName.substring(0,filedataFileName.lastIndexOf("."));  
            String wordHtmlFileName = fileName + ".html";  
            
            wordPath = path +"\\"+ filedataFileName;
  
	        htmlPath = path +"\\"+ wordHtmlFileName;  
	        String path1 = ServletActionContext.getServletContext().getRealPath("\\WEB-INF\\lib");
	        WordUtils.addpath(path1);
            WordUtils.word2Html(wordPath, htmlPath);    
            
            String wordHtmlContent = WordUtils.getHtmlCode(htmlPath);   
            
            String styleCode = WordUtils.performStyleCode(wordHtmlContent);     
  
            String bodyCode = WordUtils.performBodyCode(wordHtmlContent);     
  
            content = styleCode + bodyCode;   
            styleCode = null;     
            bodyCode = null;     
            
            
         // 删除因上传word文件而在目录下产生的所有文件和子目录
            File path2 = new File(path);
            try {
				WordUtils.delAll(path2);
			} catch (IOException e) {
				log.error(DataUtil.getStackTraceAsString(e));;
			}
            
		}
		return "htmlContent";
	}

}
