/**
 * 递归遍历文件夹的文件返回一个文件集合
 */
package com.hnzskj.common.batch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TraverseFile {
	private static List<String> filelist = new ArrayList<String>();
	private static List<String> filelisttemp = new ArrayList<String>();
	private static List<String> yasuo = new ArrayList<String>();
	private static List<String> failfile =new ArrayList<String>();

	

	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	public static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFiles(file.getAbsolutePath());
				//filelist.add(file.getAbsolutePath());
				// System.out.println(file.getAbsolutePath());
			//	filelisttemp.add(file.getAbsolutePath());
			} else {
				// System.out.println(file.getAbsolutePath());
				filelisttemp.add(file.getAbsolutePath());
			}
		}

	}

	
	
	/*
	 * 通过递归得到某一路径下所有的目录及其文件
	 */
	public static void getFilesrar(String filePathrarzip) {
		File root = new File(filePathrarzip);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFilesrar(file.getAbsolutePath());
				String filename = file.getAbsolutePath();
			
				String type = filename.substring(filename.lastIndexOf(".",filename.length()));
				if (type.equals(".rar")||type.equals(".zip")) {
				yasuo.add(file.getAbsolutePath());
				
				}
				
			} else {
				String filename = file.getAbsolutePath();
				String type = filename.substring(filename.lastIndexOf(".",filename.length()));
				if (type.equals(".rar")||type.equals(".zip")) {
					yasuo.add(file.getAbsolutePath());
					
				}
			}
		}

	}
	
	public static List<String> listfile() {
		return filelisttemp;

	}

	
	public static List<String> listfileyasuo() {
		return yasuo;

	}
	public  Map<String, String> getDocSWF(List<String> list) {
		
		for (int i = 0; i < list.size(); i++) {
			failfile.add((list.get(i)));
		}
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			String tempstr = list.get(i);
			
			if (tempstr.substring(tempstr.lastIndexOf("."), tempstr.length())
					.equals(".swf")) {
				for (int j = 0; j < list.size(); j++) {

					String tempstr2 = list.get(j);
				
					if (tempstr.substring(0, tempstr.lastIndexOf(".")).equals(
							tempstr2)) {
						map.put(tempstr, tempstr2);
						failfile.remove(tempstr);
						failfile.remove(tempstr2);
						break;
					}
				}
			}

		}

		return map;

	}

	public static void deleteNode(File node) {

		if (node.isDirectory()) {

			if (node.list().length == 0) {

				node.delete();

			

			} else {

				// list all the directory contents

				String files[] = node.list();

				for (String temp : files) {

					// construct the file structure

					File fileDelete = new File(node, temp);

					// recursive delete

					deleteNode(fileDelete);

				}

				// check the dir again , if empty then delete

				if (node.list().length == 0) {

					node.delete();

					

				}

			}

		} else {

			// if file then delete it

			node.delete();

		

		}

	}

	public static void deletebyName(File file,String filename[]) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file2 : files) {
				deletebyName(file2 ,filename);
			}
		} else {
			String name = file.getName();
			for (int i = 0; i < filename.length; i++) {
				if (filename[i].equals(name)) {
				
					file.delete();
				}
			}
			
		}
	}

	public static List<String> getFailfile() {
		return failfile;
	}

	public static void setFailfile(List<String> failfile) {
		TraverseFile.failfile = failfile;
	}
	
}
