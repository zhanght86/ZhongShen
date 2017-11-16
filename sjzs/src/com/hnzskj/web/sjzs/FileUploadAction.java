package com.hnzskj.web.sjzs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import org.apache.struts2.ServletActionContext;


import com.hnzskj.web.BaseAction;

public class FileUploadAction extends BaseAction {
	private static final long serialVersionUID = 572146812454l;
	private static final int BUFFER_SIZE = 15728640;
	private File file;
	private String fileContentType;
	private String fileFileName;
	private String imageFileName;
	private String name;
	private boolean flag;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		if (fileContentType.contains("msword")) {
			this.fileContentType = ".doc";
		} else if (fileContentType.contains("excel")) {
			this.fileContentType = ".xls";
		} else if (fileContentType.contains("pdf")) {
			this.fileContentType = ".pdf";
		} else if (fileContentType.contains("powerpoint")) {
			this.fileContentType = ".ppt";
		} else {
			this.fileContentType = ".doc";
		}
	}

	private static void copyFile(File src, File dir) {
		try {
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				output = new BufferedOutputStream(new FileOutputStream(dir),BUFFER_SIZE);
				byte[] buffer = new byte[1024];
				while (input.read(buffer) > 0) {
					output.write(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (null != input) {
					input.close();
				}
				if (null != output) {
					output.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String test() throws IOException {
		
		imageFileName = String.valueOf(new Date().getTime()) + name;
		 File dirFile = new File(ServletActionContext.getServletContext().getRealPath("/sjzs")+"/"+imageFileName);
//		File dirFile = new File("C:\\struts\\document\\" + imageFileName + this.getFileContentType());
		dirFile.createNewFile();
		copyFile(file, dirFile);
		int fileSize = 0;
		try {
			fileSize = new FileInputStream(file).available() / 1024;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		flag = FlashPaper.converter(imageFileName, imageFileName + this.getFileContentType(), fileSize,"");
		System.out.println("%%%%%%%%%%%%%%%%%" + flag);
		return "suc";
	}
}
