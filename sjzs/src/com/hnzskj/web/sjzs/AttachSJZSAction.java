package com.hnzskj.web.sjzs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.web.BaseAction;

@SuppressWarnings("serial")
public class AttachSJZSAction extends BaseAction {
	private static final int BUFFER_SIZE = 15728640;
	private String newFlag = "";
	protected static final Logger log = Logger.getLogger(AttachSJZSAction.class);

	private ArrayList<Attach> attachList = new ArrayList<Attach>();

	private Page<Attach> page = new Page<Attach>();

	private Attach attach = new Attach();
	private static  boolean isPross = false;

	private File file;
	private String fileContentType;
	private String fileFileName;
	private String imageFileName;
	private String name;
	private boolean flag;
	private String type;
	private String prossInitPath;


	
	private String path;
	//关闭的标签页
	private String closePage;
	//刷新的标签页
	private String refreshPage;
	private static String updateProjectPath;
	private static String updateDataPath;
	
	private AttachSJZSService attachSJZSService = null;
	
	
	/**
	 * @return the updateProjectPath
	 */
	public static String getUpdateProjectPath() {
		return updateProjectPath;
	}



	/**
	 * @param updateProjectPath the updateProjectPath to set
	 */
	public static void setUpdateProjectPath(String updateProjectPath) {
		AttachSJZSAction.updateProjectPath = updateProjectPath;
	}



	/**
	 * @return the updateDataPath
	 */
	public static String getUpdateDataPath() {
		return updateDataPath;
	}



	/**
	 * @param updateDataPath the updateDataPath to set
	 */
	public static void setUpdateDataPath(String updateDataPath) {
		AttachSJZSAction.updateDataPath = updateDataPath;
	}



	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the fileContentType
	 */
	public String getFileContentType() {
		return fileContentType;
	}

	/**
	 * @param fileContentType
	 *            the fileContentType to set
	 */
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}

	/**
	 * @param fileFileName
	 *            the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * @param imageFileName
	 *            the imageFileName to set
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the flag
	 */
	public boolean isFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	/**
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * @param newFlag
	 *            the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	/**
	 * @return the attachList
	 */
	public ArrayList<Attach> getAttachList() {
		return attachList;
	}

	/**
	 * @param attachList
	 *            the attachList to set
	 */
	public void setAttachList(ArrayList<Attach> attachList) {
		this.attachList = attachList;
	}

	/**
	 * @return the page
	 */
	public Page<Attach> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<Attach> page) {
		this.page = page;
	}

	/**
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach
	 *            the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the attachSJZSService
	 */
	public AttachSJZSService getAttachSJZSService() {
		return attachSJZSService;
	}

	/**
	 * @param attachSJZSService
	 *            the attachSJZSService to set
	 */
	public void setAttachSJZSService(AttachSJZSService attachSJZSService) {
		this.attachSJZSService = attachSJZSService;
	}

	public String selectFile() {
		return "fileSelect";
	}

	public String selectFileAttach() {
		return "fileSelectAttach";
	}
	public String selectUpdateDataFile() {
		return "selectUpdateDataFile";
	}
	
	public String upFilePage() {
		return "upPage";
	}
	
	/**
	 * 创建流程图时文件上传页面
	 * @return
	 */
	public String upSingleFilePage() {
		return "upSinglePage";
	}
	
	public String upSingleFilePageAttach() {
		return "upSinglePageattach";
	}
	
	public String updateDateUpFilePage(){
		return "updateDateUpFilePage";
	}

	/**
	 * @return the closePage
	 */
	public String getClosePage() {
		return closePage;
	}

	/**
	 * @param closePage the closePage to set
	 */
	public void setClosePage(String closePage) {
		this.closePage = closePage;
	}

	/**
	 * @return the refreshPage
	 */
	public String getRefreshPage() {
		return refreshPage;
	}

	/**
	 * @param refreshPage the refreshPage to set
	 */
	public void setRefreshPage(String refreshPage) {
		this.refreshPage = refreshPage;
	}

	public String searchMessage() {

		this.attachList = (ArrayList<Attach>) attachSJZSService.findAttachs(this.attach.getAttachId());// 根据菜单id查询相应的方法案例
		if (this.attachList != null && this.attachList.size() > 0) {
			this.attach = this.attachList.get(0);
		}
		return "showFF";
	}

  public String getUpdateDatas(){
	     updateProjectPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/project/");
		 updateDataPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/data/");
	  
		if(null!=type && type.trim().equals("1")){//数据或程序更新上传，不需要保存到数据库中
			this.attachList = this.getFiles(attachList, updateDataPath);
		}else{
			this.attachList = this.getFiles(attachList, updateProjectPath);
		}
	  
	  return "showFiles";
  }
	
  
  
  public ArrayList<Attach> getFiles(List<Attach> attachList, String path) {  
      File file = new File(path);   
      File[] files = file.listFiles();    
      Attach tempAttach = null;
//      String tempPathString = "";
      if (files == null) {   
      } else {  
    	  attachList = new ArrayList<Attach>();
          for (int i = 0; i < files.length; i++) {   
        	  
              // 判断是否是文件夹   
              if (files[i].isDirectory()) {   
                  // 递归调用getFiles方法，得到所有的文件   
                  getFiles(attachList, files[i].getAbsolutePath());   //getAvailablePath
              } else {// 只处理fileSuffix后缀的文档   
            	  tempAttach = new Attach();
            	  tempAttach.setAttachName(files[i].getName());
//            	  tempPathString = files[i].getAbsolutePath();
//            	  tempPathString = tempPathString.replace(":", "*");
//            	  tempPathString = tempPathString.replace("\\", "@");
//            	  tempAttach.setPathString(tempPathString);
            	  attachList.add(tempAttach);
//                  fileList.add(files[i]);// 添加到文件集合中   
              }   
         }  
      }
	return (ArrayList<Attach>) attachList;   
  }

  public String deleteFile(){
	  if(path!=null && path!=null){
		  String fileName = new String(path);
		  
		  updateProjectPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/project/");
		  updateDataPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/data/");
//		  path = path.replace("*", ":");
//		  path = path.replace("@", "\\");
		  if(null != type && type.trim().equals("0")){//程序
			  path = this.updateProjectPath +"\\"+path; 
		  }
		  if(null != type && type.trim().equals("1")){//数据
			  path = this.updateDataPath +"\\"+path; 
		  }
		  File temp = new File(path);
		  boolean flag =  temp.delete();
		  if(flag){//删除数据库中的记录
			deleteUpdateLogByFileName(fileName);
		  }
	  }
	 return "deleteSuc";  
  }
  
	  /**
	   * 在删除更新包时关联删除数据库记录
	   * 描述：<br/>
	   * 创建人：wenxuanzhen <br/>
	   * 创建时间：2013-3-15 上午10:21:42 <br/>  
	   * @version   1.0
	   */
	  private int deleteUpdateLogByFileName(String fileName){
		    BaseDao baseDao = new BaseDao();
			String sql = "delete from updateDataLog where fileName = '"+ fileName + "' ";
			return baseDao.update(sql, null);
	  }
  
  
	public void downFile() throws IOException {
		String nameString = null;
		if (path != null && !path.trim().equals("")) {
			 updateProjectPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/project/");
			 updateDataPath = ServletActionContext.getServletContext().getRealPath("/plugins/update/data/");
			  if(null != type && type.trim().equals("0")){//程序
				  path = this.updateProjectPath +"\\"+path; 
			  }
			  if(null != type && type.trim().equals("1")){//数据
				  path = this.updateDataPath +"\\"+path; 
			  }
			File fileTempFile = new File(path);
			InputStream in = null;
			OutputStream os = null;
			try {
				nameString = java.net.URLEncoder.encode(fileTempFile.getName(), "utf-8");
				this.getResponse().setHeader("Content-Disposition", "attachment;filename=" + nameString);
				os = this.getResponse().getOutputStream();
				in = new FileInputStream(fileTempFile);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = in.read(b)) > 0) {
					os.write(b, 0, i);
				}
				this.getResponse().setContentType("application/x-download;chartset=utf-8");
				this.getRequest().setAttribute("name", nameString);
			} catch (Exception e) {
				log.error(DataUtil.getStackTraceAsString(new Exception("下载失败！")));
				PrintWriter printWriter = this.getResponse().getWriter();
				this.getResponse().setContentType("text/html");
				printWriter.print("<script language='javascript'>  alert('附件不存在！');history.go(-1);</script>");
			}
		}
	}
  
	
	public String showAtttachInfo() {
		this.attachList = (ArrayList<Attach>) attachSJZSService.findAttachs(this.attach.getAttachId());// 根据菜单id查询相应的方法案例
		if (this.attachList != null && this.attachList.size() > 0) {
			this.attach = this.attachList.get(0);
		}else{
			log.error("没有附件信息！");
			if(type!=null && type.trim().equals("0")){
				this.closePage = "查看方法案例";
			}
			if(type!=null && type.trim().equals("1")){
				this.closePage = "查看法规";
			}
			if(type!=null && type.trim().equals("2")){
				this.closePage = "查看方案";
			}
			return "suc";
		}

		String path = ServletActionContext.getServletContext().getRealPath("/files/sjzs/");
		imageFileName = String.valueOf(new Date().getTime());
		File dirFile = new File(path + "/swf/" +imageFileName + ".swf");
		// File dirFile = new File("C:\\struts\\document\\" + imageFileName+"."
		// +attach.getAttachType());
		try {
			dirFile.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//删除临时文件
//		FlashPaper.delAllFile(path + "/doc/");
		FlashPaper.delAllFile(path + "/swf/");
		try {
			FlashPaper.copyFile(attach.getAttachContentSwf(), dirFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		int fileSize = 0;
//		prossInitPath = ServletActionContext.getServletContext().getRealPath("/plugins/FlashPaper2.2/");
//		if(!isPross){
//			initProgress();
//		}
//		flag = FlashPaper.converter(imageFileName, imageFileName + "."
//				+ attach.getAttachType(), fileSize, path,prossInitPath);
		return "suc";
	}

	public void initProgress() {
		isPross = true;
		Runtime pro = Runtime.getRuntime();
		Process ps;
	    prossInitPath = ServletActionContext.getServletContext().getRealPath("/plugins/FlashPaper2.2/");
		String initString= prossInitPath+"/init/init.bat"; 
		try {
			ps = pro.exec(initString);
			ps.waitFor();
			ps.destroy();
		} catch (Exception e) {
			try {
				isPross = false;
				throw new Exception("文件转换工具初始化异常！请刷新页面");
			} catch (Exception e1) {
				isPross = false;
				e1.printStackTrace();
			}
		} 
	}

	

	public String deleteTempFiles(){
		String path = ServletActionContext.getServletContext().getRealPath("/files/sjzs/");
		//删除临时文件
		FlashPaper.delAllFile(path + "/doc/");
		FlashPaper.delAllFile(path + "/swf/");
		PrintWriter out;
		try {
			out = getResponse().getWriter();
			out.print("KO");
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public String deleteAttachById() {

		Boolean result = attachSJZSService.delAttach(attach.getAttachId());
		if (result) {
			return DELSUC;
		} else {
			return FAIL;
		}
	}



	
	
}
