package com.hnzskj.autoRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import java.net.UnknownHostException;


public class AutoCheckUpdate {

	/**
	 * 描述：<br/>
	 * 此类在优盘插入到电脑上是就自动运行链接服务器检测是否有更新数据 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-6 下午02:17:44 <br/>
	 * 
	 * @version 1.0
	 */
	public static void main(String[] args) {

		try {
			
			
			
//		URL url = new URL("http://192.168.0.125:8090/sjzs/servlet/CheckUpdateServlet?version=V1.0");
//		URLConnection urlConnection = url.openConnection();
		
		
		
			Socket socket = new Socket("127.0.0.1", 11000);
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));

			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
			outWriter.write("isHave");
			String readLine = reader.readLine();
			String returnString = "";
			 //从系统标准输入读入一字符串
			while(!readLine.equals("bye")){
				 returnString = readLine;
			}
			System.err.println("服务器返回=="+returnString);			
			reader.close();
			outWriter.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
