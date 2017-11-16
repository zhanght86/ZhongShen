package com.hnzskj.persist.util;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.SystemException;

public class MyDataGenerator extends BaseDao {

	// system.xml文件路径
	private static String systemPath = Constant.webRoot + "WEB-INF\\config\\system.xml";
	// 导出数据压缩包路径
	private static String filePath = Constant.webRoot + "plugins\\update\\data";
	// 需要导出的表的properties文件路径
	private static String tablePropUrl = Constant.webRoot + "WEB-INF\\config\\clientTables.properties";
	
	private static final Logger LOGGER = Logger.getLogger(MyDataGenerator.class);
	
	private static Properties props = new Properties();
	
	static {
		System.out.println(tablePropUrl);
		try {
			InputStream in = new FileInputStream(tablePropUrl);	
			props.load(in);
		} catch (IOException e) {
			LOGGER.error("读取 导出数据表名 配置文件出错！");
			e.printStackTrace();
		}
	}

	/**
	 * 私有的导出数据的方法;
	 * 
	 * @param url
	 * @param user
	 * @param password
	 * @param sql
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
//	public boolean executeWork(String whereString, String file, String urlpath,
//			String systempath) throws Exception {
	public boolean executeWork(String whereString, String fileName) throws Exception {
		boolean flag = false;
        /**
         * 把sjzs_attach数据复制到sjzs_attachClient中
         */
		this.attachDataTrans(whereString);
		
		// whereString += " and deleteflag!=-1 ";
		String tableName = (String) this.getProperties("tablename");// 获得tableName

		File file1 = new File(filePath + "\\" + fileName);
		String filename = file1.getName();
		String parentpath = file1.getParent();

		String tempfile = parentpath + "\\temp_" + filename;
		/*
		 * 创建临时文件存储mysqldump命令导出的文件
		 */
		// cmd /c  
		File temp = new File(tempfile);
		String path = "\"" + this.getDataStructurePath().trim();
		String cammonds = path 
				+ "\\mysqldump\" --skip-opt -u root -p --password=root -c --default-character-set=utf8 --no-create-db=TRUE --add-drop-table=FALSE --no-create-info --skip-lock-tables --hex-blob --where=\""
				+ whereString + " and deleteflag!=-1 \" sjzs " + tableName;
//				+ " > \"" + tempfile + "\"";
		System.out.println(cammonds);
		Process p;
		Runtime pro = Runtime.getRuntime();

		p = pro.exec(cammonds);
		
		/**
		 * 将命令执行的结果写入文件
		 */
		InputStream is = p.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		OutputStream fos = new FileOutputStream(temp);
		PrintWriter pw = new PrintWriter(fos);
		String str = "";
		while((str=reader.readLine()) != null){
			pw.write(str + "\r\n");
			pw.flush();
		}
		pw.close();
		fos.close();
		reader.close();
		is.close();

		/**
		 * 删除attachClient表中的数据
		 */
		this.delattachClient();
		
//		/*
//		 * 获取进程Process的InputStream和ErrorStream 开辟两个新的进程对InputStream和ErrorStream
//		 * 在控制台上打印 ，防止缓冲区没及时清除溢出，程序阻塞
//		 */
//		final InputStream in = p.getInputStream();
//		final InputStream error = p.getErrorStream();
//
//		new Thread() {
//
//			public void run() {
//				BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
//				try {
//					String line1 = null;
//					while ((line1 = br1.readLine()) != null) {
//						if (line1 != null) {
//							System.out.println(line1);
//						}
//					}
//
//				} catch (Exception e) {
//					// TODO: handle exception
//				} finally {
//					try {
//						br1.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//		}.start();
//
//		new Thread() {
//
//			public void run() {
//				BufferedReader br1 = new BufferedReader(new InputStreamReader(
//						error));
//				try {
//					String line1 = null;
//					while ((line1 = br1.readLine()) != null) {
//						if (line1 != null) {
//							System.out.println(line1);
//						}
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//				} finally {
//					try {
//						br1.close();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}.start();
//		p.waitFor();
//		p.destroy();

		// 判断文件是否存在!

		File file2 = new File(tempfile);
		if (file2.exists() && file2.length() > 0) {
			/*
			 * filewrite 文件中先放入需要删除的sql记录 后放入通过mysqldump命令备份的sql记录
			 */

			FileWriter filewrite = new FileWriter(file1, true);
			/*
			 * 读取需要删除的sql记录
			 */
			filewrite.write(this.delsql(whereString) + "\r\n");

			try {
				InputStream fis = new FileInputStream(file2);
				// 用一个读输出流类去读
				InputStreamReader isr = new InputStreamReader(fis, "utf-8");
				// 用缓冲器读行
				BufferedReader br = new BufferedReader(isr);
				String line = null;
				// 直到读完为止
				int i = 0;
				while ((line = br.readLine()) != null) {
					/*
					 * 通过mysqldump命令备份的sql记录
					 */
					filewrite.write(line + "\r\n");
				}
				br.close();
				isr.close();
				fis.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			filewrite.flush();
			filewrite.close();
			getZIP(file1);

			flag = true;
		}else{
			throw new SystemException("导出数据失败！"); 
		}
		file1.delete();// 删除文件
		file2.delete();// 删除文件
		return flag;
	}

	/**
	 * 
	 * 方法描述: 删除attachClient中的数据<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-24 下午01:27:29
	 * @param
	 *
	 */
	private boolean delattachClient(){
		String sql = "delete from sjzs_attachclient";
		return this.update(sql, null) > 0;
	}
	/**
	 * 
	 * 描述：该方法实现把sjzs_attach中的字段除attachContentDoc之外的其他字段复制到sjzs_attachClient中<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-11 下午03:01:56 <br/>
	 * 
	 * @version 1.0
	 */
	private boolean attachDataTrans(String whereCondition) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO sjzs_attachclient (");
		buffer.append("	attachId,");
		buffer.append("	attachName,");
//		buffer.append("	attachContentDoc,");
		buffer.append("	attachContentSwf,");
		buffer.append("	note1,");
		buffer.append("	note2,");
		buffer.append("	uploadDate,");
		buffer.append("	updatedate,");
		buffer.append("	deleteflag");
		buffer.append("	) SELECT");
		buffer.append("	a.attachId,");
		buffer.append("	a.attachName,");
//		buffer.append("	a.attachContentDoc,");
		buffer.append("	a.attachContentSwf,");
		buffer.append("	a.note1,");
		buffer.append(" a.note2,");
		buffer.append(" a.uploadDate,");
		buffer.append(" a.updatedate,");
		buffer.append(" a.deleteflag ");
		buffer.append(" FROM");
		buffer.append("	sjzs_attach a where ");
		buffer.append(whereCondition);
		return this.update(buffer.toString(), null) > 0;
	}
	
	/**
	 * 
	 * 描述：获取mysql的安装目录<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-11 下午03:01:01 <br/>  
	 * @version   1.0
	 */
	public static final String getDataStructurePath() {
		String dataStructurePath = "";
		File file = new File(systemPath);
		SAXReader reader = new SAXReader();
		Document doc = null;
		try {
			doc = reader.read(file);
			dataStructurePath = doc.selectSingleNode("//system//mysqlPath").getText();
		} catch (DocumentException e) {
			e.printStackTrace();
			dataStructurePath = "tomcat\\webapps\\sjzs\\WEB-INF\\data_structure.sql";
		}
		return dataStructurePath;
	}

	// sql
	// SELECT a.attachId FROM sjzs_attach a where a.deleteflag IN (-1,1); //
	// sjzs_attach 表
	// SELECT d.id FROM sjzs_dxyj d WHERE d.deleteflag IN (1,-1);
	// SELECT f.Id FROM sjzs_ffal f where f.deleteflag IN (-1,1);
	// SELECT m.menuId FROM sjzs_menutree m where m.deleteflag IN (-1,1);
	// SELECT d.id FROM sjzs_sjdh d where d.deleteflag IN (-1,1);
	// SELECT g.lawId FROM sjzs_sjfg g where g.deleteflag IN (-1,1);
	/*
	 * 根据传入的sql,tableName获得应删除的记录的id集合， 把获得集合转成字符串sql语句 返回应删除的sql语句
	 */
	@SuppressWarnings("unchecked")
	private String delsql(String whereString) {
		List<String> list = new ArrayList<String>();
		Map<String, String> map = (Map<String, String>) this.getProperties("map");
		String deletesqlid = "";
		// map.put("sjzs_attach", "attachId");
		// map.put("sjzs_dxyj", "id");
		// map.put("sjzs_ffal", "id");
		// map.put("sjzs_menutree", "menuId");
		// map.put("sjzs_sjdh", "id");
		// map.put("sjzs_sjfg", "lawId");

		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {

			Object object = iterator.next();
			String sqlselectid = "SELECT " + map.get(object) + " FROM "
					+ object + " where deleteflag IN (-1,1) and " + whereString;
			
//			System.out.println(sqlselectid);
			list = this.getListSingleValue(sqlselectid, null);
			if (list.size() == 0) {
				continue;
			}
			// System.out.println(list.toArray().toString());

			StringBuffer buffer = new StringBuffer();
			for (String str : list) {
//				System.out.println("我执行了");
//				System.out.println(str);
				buffer.append("'" + str + "',");
			}

			deletesqlid += "DELETE  FROM "
					+ object
					+ " WHERE "
					+ map.get(object)
					+ "  IN ("
					+ buffer.toString().substring(0,
							buffer.toString().lastIndexOf(",")) + ");\r\n";
		}
		return deletesqlid;
	}

	/*
	 * 文件压缩zip格式
	 */
	private void getZIP(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		String fileName = file.getName();
		String zipFileName = fileName.substring(0, fileName.lastIndexOf('.'))+ ".zip";
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file.getParent() + "\\" + zipFileName));
		ZipOutputStream zos = new ZipOutputStream(out);
		ZipEntry entry = new ZipEntry(file.getName());
		zos.putNextEntry(entry);

		byte[] data = new byte[1024];
		int len;
		while ((len = in.read(data)) != -1) {
			zos.write(data, 0, len);
		}
		zos.close();
		out.close();
		in.close();
	}
	
	/*
	 * clientTables.properties 文件key值为表名，value值为表的id;
	 * 读取clientTables.properties,parameter为tablename和map;
	 * tablename时,方法返回要操作的表名，及文件key的字符串拼接; map时 方法返回要操作的表， 表名与id之间的对应关系
	 * ，返回一个map对象
	 */
	public Object getProperties(String parameter){
		String tableName = "";
		Map<String, String> map = new HashMap<String, String>();
		Set<Object> set = props.keySet();
		Iterator<Object> iterator = set.iterator();
		/*
		 * 获取表名的字符串拼接
		 */
		if ("tablename".equals(parameter)) {
			while (iterator.hasNext()) {
				String object = (String) iterator.next();
				tableName += object + " ";
			}
			return tableName;
		}
		/*
		 * 获取表名与id之间对应的map
		 */
		if ("map".equals(parameter)) {
			while (iterator.hasNext()) {
				String object = (String) iterator.next();
				map.put(object, (String) props.get(object));
			}
			return map;
		}
		return null;
	}

}
