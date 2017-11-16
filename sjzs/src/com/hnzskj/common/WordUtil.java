package com.hnzskj.common;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.hnzskj.common.init.SysParamUtil;



public class WordUtil {
	private static Logger log = Logger.getLogger(RandomNumber.class);

	private static String ROOTURL="";
	static {
		ROOTURL= new SysParamUtil().getInitCommon().getFilePath();
	}

	/**
	 * 
	 * 方法描述：根据数据往指定模板的变量中填充动态数据，并把填充后的数据生成一个信息的Word保存的指定的路径<br/>
	 * 创建人：余鹏飞 <br/>
	 * 创建时间：2011-6-7 下午02:07:41<br/>
	 * 
	 * @param sourceFile
	 *            模板的名称
	 * @param saveFile
	 *            填充数据后生成Word的名称
	 * @param dataMap
	 *            填充变量的动态数据
	 * @throws Exception
	 * @version 1.0
	 */
	public static int FillModel(String sourceFileName, String saveFileName,
			Map<String, Object> dataMap) throws Exception {
		String sourceFile=ROOTURL+"model/"+sourceFileName + ".doc";
		String saveFile=ROOTURL+"hetong/"+saveFileName + ".doc";
		String pathname = ROOTURL+"hetong";
		File pathfile = new File(pathname);
	    if(!pathfile.exists()){
	    	pathfile.mkdirs();
	    }
		File file = new File(saveFile);
		if(file.exists()){
			return 1;
		}
		replaceModel(sourceFile, saveFile, dataMap);
		return 1;
	}

	/**
	 * 使动态数据填充到模板变量,并生成新的document到指定位置 创建人：余鹏飞 创建时间：2011-5-27
	 * 
	 * @param modelPath
	 *            document模板的Path
	 * @param saveFile
	 *            生成新的document的路径+名称
	 * @param dataMap
	 *            从数据库中读取的动态数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public  static int replaceModel(String modelPath, String saveFile, Map dataMap)
			throws Exception {
		// 获取文档内的文本内容
		String targetContent = readDocument(modelPath);
		// 开始填充word模板文件
		String oldText;
		Object newValue;
		// 创建一个迭代器来遍历Map集合中的数据
		Iterator iterator = dataMap.keySet().iterator();
		while (iterator.hasNext()) {
			oldText = (String) iterator.next();
			newValue = (Object) dataMap.get(oldText);
			String newText = (String) (newValue + ""); // 把dataMap中的数据全部转换为字符串，其实就是把以前Student中的数据全部以字符串的样式进行显示到Word模板中
			// 用newText替换sourceContent中的模板数据oldText，并暂存到targetContent中
			targetContent = replaceValue(targetContent, oldText, newText);
		}
		// 填充Word模板文件结束
			writeWordFile(saveFile,targetContent);
			return 1;
	}


	/**
	 * 完成对字符串的替换和修正 创建人：余鹏飞 创建时间：2011-5-27
	 * 
	 * @param resourceContent
	 *            模板中的文本内容
	 * @param oldText
	 *            要被替换的变量值
	 * @param newValue
	 *            从数据库中获取的数据用来替换变量的文本
	 */

	public  static String replaceValue(String resourceContent, String oldText,
			String newValue) throws Exception {
		oldText = "$" + oldText + "$"; // $是模板中的特殊字符串，用来表示模板中要替换的内容
		//进行字符串替换，用 replaceContent来替换markContent
		String targetContent = resourceContent.replace(oldText, newValue);
		return targetContent;
	}

	/**
	 * 
	 * 方法描述：获取document模板中的文本内容<br/>
	 * 创建人：余鹏飞 <br/>
	 * 创建时间：2011-6-7 下午02:16:03<br/>
	 * 
	 * @param modelPath
	 *            模板的路径
	 * @return resourceContent 模板中的文本内容
	 * @throws Exception
	 * @version 1.0
	 */

	public  static String readDocument(String modelPath) throws Exception {
		File htmlFile = new File(modelPath);
		String docText = "";
		if(htmlFile.exists()){
			FileInputStream fileInput = new FileInputStream(htmlFile);
			POIFSFileSystem fileSystem = new POIFSFileSystem(fileInput);
			DocumentInputStream docinput  =fileSystem.createDocumentInputStream("WordDocument");
			byte[] buffer = new byte[1024];
			@SuppressWarnings("unused")
			int leng;
			while ((leng = docinput.read(buffer)) > 0) {
				docText += new String(buffer);
			}
			System.out.println(docText);
		}
		String resourceContent =URLDecoder.decode(docText,"UTF-8");
			// 对Document文件进行提取所有文本内容
		return resourceContent;

	}
	
	public static boolean writeWordFile(String docName,String content) {
		   boolean w = false;
		   try {
		      // 生成临时文件名称
			  String fileName = null;
			  if(docName.contains(ROOTURL)){
				  fileName = docName;
			  }else{
				  fileName = ROOTURL+docName;
			  }
		      byte b[] = content.getBytes("UTF-8");
		      ByteArrayInputStream bais = new ByteArrayInputStream(b);
		      POIFSFileSystem poifs = new POIFSFileSystem();
		      DirectoryEntry directory = poifs.getRoot();
			  @SuppressWarnings("unused")
			DocumentEntry documentEntry = directory.createDocument("WordDocument", bais);
		      FileOutputStream ostream = new FileOutputStream(fileName);
		      poifs.writeFilesystem(ostream);
		      w= true;
		      bais.close();
		      ostream.close();
		   } catch (IOException e) {
		    log.error(DataUtil.getStackTraceAsString(e));;
		   }
		   return w;
		}
}
