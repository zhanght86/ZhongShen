package com.hnzskj.common;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class DeCompressUtil {
	
	  /**  
	    * 解压zip格式的压缩文件到指定位置  
	     * @param zipFileName 压缩文件  
	     * @param extPlace 解压目录  
	     * @throws Exception  
	     */   
	   @SuppressWarnings("unchecked")   
	   public  static synchronized void unzip(String zipFileName, String extPlace) throws Exception {   
	        try {   
	            (new File(extPlace)).mkdirs();   
	            File f = new File(zipFileName);   
	            ZipFile zipFile = new ZipFile(zipFileName);   
	           if((!f.exists()) && (f.length() <= 0)) {   
	               throw new Exception("要解压的文件不存在!");   
	           }   
	            String strPath, gbkPath, strtemp;   
	           File tempFile = new File(extPlace);   
	           strPath = tempFile.getAbsolutePath();   
	            java.util.Enumeration e = zipFile.getEntries();   
	           while(e.hasMoreElements()){   
	                org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e.nextElement();   
	                gbkPath=zipEnt.getName();   
	               if(zipEnt.isDirectory()){   
	                    strtemp = strPath + File.separator + gbkPath;   
	                  File dir = new File(strtemp);   
	                   dir.mkdirs();   
	                   continue;   
	               } else {   
	                    //读写文件   
	                    InputStream is = zipFile.getInputStream(zipEnt);   
	                   BufferedInputStream bis = new BufferedInputStream(is);   
	                   gbkPath=zipEnt.getName();   
	                    strtemp = strPath + File.separator + gbkPath;   
	                    
	                   //建目录   
	                   String strsubdir = gbkPath;   
	                    for(int i = 0; i < strsubdir.length(); i++) {   
	                     if(strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {   
	                           String temp = strPath + File.separator + strsubdir.substring(0, i);   
	                            File subdir = new File(temp);   
	                            if(!subdir.exists())   
	                            subdir.mkdir();   
	                        }   
	                  }   
	                   FileOutputStream fos = new FileOutputStream(strtemp);   
	                 BufferedOutputStream bos = new BufferedOutputStream(fos);   
	                   int c;   
	                  while((c = bis.read()) != -1) {   
	                       bos.write((byte) c);   
	                  }   
	                   bos.close();   
	                    fos.close();   
	                }   
	          }   
	       } catch(Exception e) {   
	           e.printStackTrace();   
	            throw e;   
	       }   
	   }   
	
	 public static  synchronized void unzip(String zipFileName, String extPlace,boolean whether) throws Exception {   
	       try {   
	           (new File(extPlace)).mkdirs();   
	            File f = new File(zipFileName);   
	            ZipFile zipFile = new ZipFile(zipFileName);   
	          if((!f.exists()) && (f.length() <= 0)) {   
	              throw new Exception("要解压的文件不存在!");   
	          }   
	            String strPath, gbkPath, strtemp;   
	           File tempFile = new File(extPlace);   
	           strPath = tempFile.getAbsolutePath();   
	           java.util.Enumeration e = zipFile.getEntries();   
	           while(e.hasMoreElements()){   
	               org.apache.tools.zip.ZipEntry zipEnt = (ZipEntry) e.nextElement();   
	                gbkPath=zipEnt.getName();   
	                if(zipEnt.isDirectory()){   
	                  strtemp = strPath + File.separator + gbkPath;   
	                   File dir = new File(strtemp);   
	                   dir.mkdirs();   
	                   continue;   
	                } else {   
	                   //读写文件   
	                    InputStream is = zipFile.getInputStream(zipEnt);   
	                   BufferedInputStream bis = new BufferedInputStream(is);   
	                  gbkPath=zipEnt.getName();   
	                  strtemp = strPath + File.separator + gbkPath;   
	                   
	                  //建目录   
	                    String strsubdir = gbkPath;   
	                   for(int i = 0; i < strsubdir.length(); i++) {   
	                      if(strsubdir.substring(i, i + 1).equalsIgnoreCase("/")) {   
	                           String temp = strPath + File.separator + strsubdir.substring(0, i);   
	                            File subdir = new File(temp);   
	                           if(!subdir.exists())   
	                           subdir.mkdir();   
	                       }   
	                   }   
	                    FileOutputStream fos = new FileOutputStream(strtemp);   
	                   BufferedOutputStream bos = new BufferedOutputStream(fos);   
	                    int c;   
	                   while((c = bis.read()) != -1) {   
	                        bos.write((byte) c);   
	                    }   
	                    bos.close();   
	                   fos.close();   
	               }   
	            }   
	        } catch(Exception e) {   
	            e.printStackTrace();   
	           throw e;   
	       }   
	    }   
	    

	 /**
	     * 根据原始rar路径，解压到指定文件夹下.
	     * @param srcRarPath 原始rar路径
	     * @param dstDirectoryPath 解压到的文件夹
	     */
	    public static synchronized void unrar(String srcRarPath, String dstDirectoryPath) {
	            if (!srcRarPath.toLowerCase().endsWith(".rar")) {
	                System.out.println("非rar文件！");
	                return;
	            }
	            File dstDiretory = new File(dstDirectoryPath);
	            if (!dstDiretory.exists()) { //目标目录不存在时，创建该文件夹
	                dstDiretory.mkdirs();
	            }
	            Archive a = null;
	            try {
	                a = new Archive(new File(srcRarPath));
	                if (a != null) {
	                    a.getMainHeader().print(); //打印文件信息.
	                    FileHeader fh = a.nextFileHeader();
	                    while (fh != null) {
	                            if (fh.isDirectory()) { //文件夹
	                                File fol = new File(dstDirectoryPath + File.separator + fh.getFileNameString());
	                                fol.mkdirs();
	                            } else { //文件
	                                File out = new File(dstDirectoryPath + File.separator + fh.getFileNameString().trim());
	                                System.out.println(out.getAbsolutePath());
	                                try {//之所以这么写try，是因为万一这里面有了异常，不影响继续解压.
	                                    if (!out.exists()) {
	                                        if (!out.getParentFile().exists()) {//相对路径可能多级，可能需要创建父目录.
	                                            out.getParentFile().mkdirs(); 
	                                        }
	                                        out.createNewFile();
	                                    }
	                                    FileOutputStream os = new FileOutputStream(out);
	                                    a.extractFile(fh, os);
	                                    os.close();
	                                } catch (Exception ex) {
	                                    ex.printStackTrace();
	                                }
	                            }
	                        fh = a.nextFileHeader();
	                    }
	                  a.close();
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	    }


	    

	/**
	 * 解压缩
	 */
	public static void deCompress(String sourceFile, String destDir)
			throws Exception {
		// 保证文件夹路径最后是"/"或者"\"
		char lastChar = destDir.charAt(destDir.length() - 1);
		if (lastChar != '/' && lastChar != '\\') {
			destDir += File.separator;
		}
		// 根据类型，进行相应的解压缩
		String type = sourceFile.substring(sourceFile.lastIndexOf(".") + 1);
		if (type.equals("zip")) {
			DeCompressUtil.unzip(sourceFile, destDir);
		} else if (type.equals("rar")) {
			DeCompressUtil.unrar(sourceFile, destDir);
		} else {
			throw new Exception("只支持zip和rar格式的压缩包！");
		}
	}

	
	public static void main(String[] args) {
		
		
		try {
			
			DeCompressUtil.deCompress( "e:\\测试2.rar","e:\\eee2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
