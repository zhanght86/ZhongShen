/*
 * @项目名称: htglxt
 * @文件名称: OperatorET99.java
 * @日期: 2011-8-9
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import ET99jni.CET99;
import ET99jni.IET99;
import ET99jni.RTException;

import com.hnzskj.common.init.SysParamUtil;
import com.hnzskj.persist.bean.init.InitET99Bean;

/**        
 * 
 * 类名称：OperatorET99     <br/>
 * 类描述：<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-8-9 下午03:23:53   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-8-9 下午03:23:53   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class OperatorET99 {
	/**
	 * 当前应用的lib文件夹所在的目录
	 */
	private static String libURL;
	
	/**
	 * 加密锁信息
	 */
	private static InitET99Bean et99Bean;
	
	static {
		String filePath = WordUtil.class.getClassLoader().getResource("").toString();
		//因为获得文件的协议为本地文件传输协议，所以要去掉URL中前面的"file:/" 
		//如果获得的URL中含有空格，则空格会被"%20"替换，但是dom4j识别不了"%20",所以要将"%20"重新转换为空格" "
		libURL= filePath.replace("file:/", "").replace("%20", " ").replace("/classes/", "/lib/");
		et99Bean = new SysParamUtil().getET99Bean();
	}
	
	/**
	 * 方法描述：检测ET99是否存在<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-9 下午02:21:27<br/>         
	 * @return
	 * @version   1.0  
	 */
	public static boolean authentic() {
		WordUtils.addpath(libURL);
		IET99 et99 = new CET99();
		byte[] pid = et99Bean.getPid().getBytes();
		int[] tokenCount = new int[1];
		try {
			//根据指定的PID查找ET99，并将查找到的个数存储在tokenCount中
			et99.FindToken(pid, tokenCount);
		} catch (RTException e) {
			return false;
		}
		if ( 1 ==  tokenCount[0]) {
			return true;
		} 
		return false;
	}
	
	/**
	 * 方法描述：从ET99中读取数据,获得当前系统允许的最大人数<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-8-9 下午03:30:45<br/>         
	 * @version   1.0  
	 */
	public static int readCount(){
		int count = 0;
		byte[] pid = et99Bean.getPid().getBytes();
		byte[] userPin = et99Bean.getUserpin().getBytes();
		byte[] pdata = new byte[]{48, 48, 48, 48};
		WordUtils.addpath(libURL);
		IET99 et99 = new CET99();
		try {
			//打开ET99
			et99.OpenToken(pid, 1);
			//验证当前userpin
			et99.Verify(0, userPin);
			//从加密锁中读取数据,从偏移0向后读取4个字节的数据
			et99.Read(0, 4, pdata);
			//关闭ET99
			et99.CloseToken();
		} catch (RTException e) {
			throw new SystemException("请确认服务器端已经插上软件加密锁");
		}
		
		/*
		 * 将从加密锁中读取的数据转换成int
		 * 从加密锁中读取的数据是一个字节数组将其组织成字符串后的形式为"0050"
		 */		
		count = Integer.parseInt(new String(pdata));
		
		return count;
	}
}
