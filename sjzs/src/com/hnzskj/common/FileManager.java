package com.hnzskj.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.hnzskj.common.init.SysParamUtil;

/**
 * 方法描述：对指定文件的操作
 * @author Administrator
 * @version  1.0
 */
public class FileManager {
	
	private static Logger log = Logger.getLogger( FileManager.class.getName() );
	
	/**
	 * 
	 * 方法描述：根据指定的文件名和目录,把文件写入<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-16 下午03:55:40<br/>         
	 * @param folder	保存文件的文件夹 如 "myfiles"或者"myfiles/image"
	 * @param fileName	保存文件的真实文件名
	 * @param upfile	上传的文件
	 * @return	返回保存到服务器上的文件的名称，如果保存失败返回NULL
	 * @throws FileNotFoundException
	 * @version   1.0
	 */
	public static String copyFile (String folder, String fileName, File upfile) {
		//设置保存文件名
		String saveName = UUID.randomUUID().toString() + getFileExt(fileName);
		//设置文件保存路径
		String saveDir = new SysParamUtil().getInitCommon().getFilePath() + "/" +folder;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		File fileDir =  new File(saveDir);
		if ( !fileDir.exists() ) {//如果目录不存在
			fileDir.mkdirs();
		}
		try {
			fos = new FileOutputStream(new StringBuilder(saveDir).append("/").append(saveName).toString());
		} catch (FileNotFoundException e) {
			log.error("文件写入目录不正确!");
			log.error(DataUtil.getStackTraceAsString(e));;
		}

		try {
			fis = new FileInputStream(upfile);
		} catch (FileNotFoundException e1) {
			log.error("要保存的文件不存在！");
			saveName = null;
			e1.printStackTrace();
		}
		int len = 0;
		byte buffer[] = new byte[16 * 1024];
		try {
			while ((len = fis.read(buffer)) > 0)  {
				fos.write(buffer, 0, len);
			}
			log.info("文件保存成功：" + new StringBuilder(saveDir).append("/").append(saveName).toString());
		} catch (IOException e) {
			log.error("读取写入文件出错!");
			saveName = null;
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return saveName;
	}
	
	/**
	 * 方法描述：删除指定路径下的文件
	 * 创建人：高容翔
	 * 创建时间：2011-6-8
	 * @param pathname 指定的文件路径
	 * @return
	 * @version  1.0
	 */
	public static boolean delete( String pathname ){
		//文件存放路径
		pathname = pathname.trim();
		String delDir = new SysParamUtil().getInitCommon().getFilePath()+"/"+pathname;
		File file = new File(delDir.trim());
		if( file.isFile() && file.exists() ){
			file.delete();
			log.info("已经成功的删除了文件！ " + pathname);
			return true;
		}else{
			log.info("您要删除的文件不存在！！" + pathname);
			return false;
		}
	} 
	
	public static boolean deleteFile( String pathname ){			
		File file = new File(pathname.trim());
		if( file.isFile() && file.exists() ){
			file.delete();
			log.info("已经成功的删除了文件！ " + pathname);
			return true;
		}else{
			log.info("您要删除的文件不存在！！" + pathname);
			return false;
		}
	} 
	/**
	 * 
	 * 方法描述：获得文件后缀名,如果传入的文件名不合法,则返回NULL<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-16 下午03:55:20<br/>         
	 * @param fileName 文件的命名,如fileName.txt
	 * @return	返回后缀名不包含"."
	 * @version   1.0
	 */
	public static String getFileExt(String fileName) {
		if (fileName != null && !"".equals(fileName)) {
			return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
		}
		return null;
	}
	
	/**
	 * 
	 * 方法描述：判断文件是否是允许的类型<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-16 下午03:54:21<br/>         
	 * @param fileContentType
	 * @param exts
	 * @return 是 返回TRUE,不是返回FALSE
	 * @version   1.0
	 */
	public static boolean isAllowedType(String fileContentType, String exts[]) {
		if ( null != fileContentType ) {
			for (int i = 0; i < exts.length; i++) {
				String contentType = exts[i];
				if (fileContentType.equals(contentType)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：根据指定的文件名和目录,把文件写入,此方法提供目录的必须是全路径<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-16 下午03:55:40<br/>         
	 * @param folder	保存文件的文件夹 如 "d:/myfiles"或者"d:/myfiles/image"
	 * @param fileName	保存文件的真实文件名
	 * @param upfile	上传的文件
	 * @return	返回保存到服务器上的文件的名称，如果保存失败返回NULL
	 * @throws FileNotFoundException
	 * @version   1.0
	 */
	public static String saveFile (String folder, String fileName, File upfile) {
		//设置保存文件名
		String saveName = UUID.randomUUID().toString() + getFileExt(fileName);
		//设置文件保存路径
		String saveDir = folder;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		File fileDir =  new File(saveDir);
		if ( !fileDir.exists() ) {//如果目录不存在
			fileDir.mkdirs();
		}
		try {
			fos = new FileOutputStream(new StringBuilder(saveDir).append("/").append(saveName).toString());
		} catch (FileNotFoundException e) {
			log.error("文件写入目录不正确!");
			log.error(DataUtil.getStackTraceAsString(e));;
		}

		try {
			fis = new FileInputStream(upfile);
		} catch (FileNotFoundException e1) {
			log.error("要保存的文件不存在！");
			saveName = null;
			e1.printStackTrace();
		}
		int len = 0;
		byte buffer[] = new byte[16 * 1024];
		try {
			while ((len = fis.read(buffer)) > 0)  {
				fos.write(buffer, 0, len);
			}
			log.info("文件保存成功：" + new StringBuilder(saveDir).append("/").append(saveName).toString());
		} catch (IOException e) {
			log.error("读取写入文件出错!");
			saveName = null;
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		return saveName;
	}

}
