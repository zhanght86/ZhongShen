package com.hnzskj.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

public class WordUtils {
	private static Logger log = Logger.getLogger(RandomNumber.class);
	/**
	 * 方法描述：将上传的word文档转换成html文件
	 * 
	 * @author 柴茂森
	 * @param wordPath   上传后的word文档所在的路径
	 * @param htmlPath   根据word文档转换成的html文件的路径
	 */
	public static synchronized void word2Html(String wordPath, String htmlPath) {
		ActiveXComponent app = null;
		try {
			app = new ActiveXComponent("Word.Application");// 启动word

			app.setProperty("Visible", new Variant(false));

			// 设置word不可见
			Dispatch docs = (Dispatch) app.getProperty("Documents")
					.toDispatch();

			Dispatch doc = Dispatch.invoke(
					docs,
					"Open",
					Dispatch.Method,
					new Object[] { wordPath, new Variant(false),
							new Variant(true) }, new int[1]).toDispatch();
			// 打开word文件 8转为 html 9转为 mht
			Dispatch.invoke(doc, "SaveAs", Dispatch.Method, new Object[] {
					htmlPath, new Variant(8) }, new int[1]);

			Variant f = new Variant(false);

			Dispatch.call(doc, "Close", f);

		} catch (Exception e) {
			System.out.println("启动Word控件出错.......");
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			// 注意这里一定 要关闭否则服务器端会有很多winword.exe进程
			if (null != app) {
				app.invoke("Quit", new Variant[] {});
				app = null;
			}
		}

	}

	/**
	 * 方法描述：根据html文件的真实路径读取出html代码
	 * @author 柴茂森
	 * @param fileName   html文件的真实路径
	 * @return  result  html代码
	 */
	public static synchronized String getHtmlCode(String fileName) {

		InputStream in = null;
		String result = null;
		try {
			in = new FileInputStream(fileName);
			result = IOUtils.toString(in, "gb2312");
		} catch (Exception e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		} finally {
			IOUtils.closeQuietly(in);
		}
		return result;
	}

	/**
	 * 方法描述：处理Style标签中的内容
	 * @author 柴茂森
	 * @param htmlCode  html代码
	 * @return result 返回Style的代码
	 */
	public static synchronized String performStyleCode(String htmlCode) {
		String result = "";

		int index = 0;

		int styleStartIndex = 0;

		int styleEndIndex = 0;

		// 截取<style>标签中开始部分的坐标

		while (index < htmlCode.length()) {
			int styleIndexStartTemp = htmlCode.indexOf("<style>", index);

			if (styleIndexStartTemp == -1) {
				break;
			}
			int styleContentStartIndex = htmlCode.indexOf("<!--",
					styleIndexStartTemp);

			if (styleContentStartIndex - styleIndexStartTemp == 9) {
				styleStartIndex = styleIndexStartTemp;
				break;
			}
			index = styleIndexStartTemp + 7;
		}

		// 截取style标签中后面部分的坐标
		index = 0;
		while (index < htmlCode.length()) {
			int styleContentEndIndex = htmlCode.indexOf("-->", index);

			if (styleContentEndIndex == -1) {
				break;
			}
			int styleEndIndexTemp = htmlCode.indexOf("</style>",
					styleContentEndIndex);

			if (styleEndIndexTemp - styleContentEndIndex == 5) {
				styleEndIndex = styleEndIndexTemp;
				break;
			}
			index = styleContentEndIndex + 4;
		}

		result = htmlCode.substring(styleStartIndex, styleEndIndex + 8);

		return result;
	}

	/**
	 * 方法描述：截取body内容
	 * @author 柴茂森
	 * @param htmlCode html代码
	 * @return bodyCode   body中的内容
	 */
	public static synchronized String performBodyCode(String htmlCode) {
		String bodyCode = "";
		// 处理body
		int bodyIndex = htmlCode.indexOf("<body");

		int bodyEndIndex = htmlCode.indexOf("</html>");

		if (bodyIndex != -1 && bodyEndIndex != -1) {
			htmlCode = htmlCode.substring(bodyIndex, bodyEndIndex);
			bodyCode = htmlCode;
		}
		htmlCode = null;
		return bodyCode;
	}
	
	/**
	 * 方法描述：递归删除文件指定目录下的全部文件和子目录
	 * @author 柴茂森
	 * @param f 指定的目录
	 */
	public static void delAll(File f) throws IOException {
		
		//如果文件夹不存在
	    if(!f.exists())
	    throw new IOException("指定目录不存在:"+f.getName());

	    
	    //先尝试直接删除， 若文件夹非空。枚举、递归删除里面内容
	    if(!f.delete()){
	      File subs[] = f.listFiles();
	      for (int i = 0; i <= subs.length - 1; i++) {
	        if (subs[i].isDirectory()){
	        //递归删除子文件夹内容
	          delAll(subs[i]);
	        }
	        subs[i].delete();
	      }
	    }
	  }
	/**
	 * 方法描述：通过反射修改加载jacob.dll组件的路径
	 * @author 柴茂森
	 * @param s  jacob.dll所在的路径
	 */
	public static void addpath(String s){
		try {           
			Field field = ClassLoader.class.getDeclaredField("usr_paths");          
			field.setAccessible(true); 
			String[] paths = (String[])field.get(null); 
			for (int i = 0; i < paths.length; i++) {    
				if (s.equals(paths[i])) {
					break;               
				}           
			}     
			String[] tmp = new String[paths.length+1];           
			System.arraycopy(paths,0,tmp,0,paths.length);           
			tmp[paths.length] = s;
			field.set(null,tmp);  
		} catch (Exception e) {           
			log.error(DataUtil.getStackTraceAsString(e));;
		}  

	}
	
	

}

