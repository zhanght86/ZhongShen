package com.hnzskj.web.sjzs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.hnzskj.common.DataUtil;

public class FlashPaper extends Thread {
	private static Logger log = Logger.getLogger(FlashPaper.class);
	private static final int BUFFER_SIZE = 15728640;
	static boolean flag = false;
	static File file;

//	public static void main(String args[]) {
//		converter("1","1","d:\\","D:\\ProgramFiles\\Print2Flash3","");
//	}

	public synchronized static boolean converter(String swfName, String documentName, String path, String systemPath, long length) {
		boolean st_return = false;
		Runtime pro = Runtime.getRuntime();
		long time = 3000;
		String typeString = documentName.substring(documentName.lastIndexOf(".") + 1,documentName.length()).toLowerCase();
		String flashPaperPathString = getFlashPaperPath(systemPath);
		if(typeString.equals("pdf")){
			time = 15*1000;
		}
		Process p;
		try {
			String converter = flashPaperPathString + "/p2fServer.exe  " + path + "/doc/" + documentName+ " " + path + "/swf/"
					+ swfName + ".swf   ";
			
			p = pro.exec(converter);
			p.waitFor();
		//	Thread.sleep(time);
			p.destroy();
			st_return = true;
		} catch (Exception e) {
			System.out.println("执行失败");
			log.error(DataUtil.getStackTraceAsString(e));
		}
		return st_return;

	}

	public static final String getFlashPaperPath(String systempath) {
		String progressPath = "";
		File file = new File(systempath);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(file);
			progressPath = doc.selectSingleNode("//system//flashPaperPath")
					.getText();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return progressPath;
	}
	
	public static void copyFile(InputStream in, File dir) throws IOException {

		try {
			InputStream input = null;
			OutputStream output = null;
			try {
				input = new BufferedInputStream(in);
				output = new BufferedOutputStream(new FileOutputStream(dir));
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

	// 删除指定文件夹下所有文件

	// param path 文件夹完整绝对路径

	public static boolean delAllFile(String path) {

		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}

		String[] tempList = file.list();
		File temp = null;

		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
				flag = true;
			}

			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * 
	 * 描述：<br/>
	 * 判断某个路径下是否有文件 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-6 下午02:56:38 <br/>
	 * 
	 * @version 1.0
	 */

	public static boolean isFullFolder(String path) {
		boolean flag = false;
		if (path == null) {
			return false;
		}
		File file = new File(path);
		if (file.isDirectory()) {
			if (file.list().length > 0) {
				flag = true;
			}
		}
		return flag;
	}

	// 删除文件夹

	// param folderPath 文件夹完整绝对路径

	public static void delFolder(String folderPath) {

		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /**
	// * 删除单个文件
	// *
	// * @param sPath
	// * 被删除文件的文件名
	// * @return 单个文件删除成功返回true，否则返回false
	// */
	// private static boolean deleteFile(String sPath) {
	// flag = false;
	// file = new File(sPath);
	// // 路径为文件且不为空则进行删除
	// if (file.isFile() && file.exists()) {
	// file.delete();
	// flag = true;
	// }
	// return flag;
	// }
	//
	// /**
	// * 删除目录（文件夹）以及目录下的文件
	// *
	// * @param sPath
	// * 被删除目录的文件路径
	// * @return 目录删除成功返回true，否则返回false
	// */
	// private static boolean deleteDirectory(String sPath) {
	// // 如果sPath不以文件分隔符结尾，自动添加文件分隔符
	// if (!sPath.endsWith(File.separator)) {
	// sPath = sPath + File.separator;
	// }
	// File dirFile = new File(sPath);
	// // 如果dir对应的文件不存在，或者不是一个目录，则退出
	// if (!dirFile.exists() || !dirFile.isDirectory()) {
	// return false;
	// }
	// flag = true;
	// // 删除文件夹下的所有文件(包括子目录)
	// File[] files = dirFile.listFiles();
	// for (int i = 0; i < files.length; i++) {
	// // 删除子文件
	// if (files[i].isFile()) {
	// flag = deleteFile(files[i].getAbsolutePath());
	// if (!flag)
	// break;
	// } // 删除子目录
	// else {
	// flag = deleteDirectory(files[i].getAbsolutePath());
	// if (!flag)
	// break;
	// }
	// }
	// if (!flag)
	// return false;
	// // 删除当前目录
	// if (dirFile.delete()) {
	// return true;
	// } else {
	// return false;
	// }
	// }

}
