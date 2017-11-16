package com.hnzskj.autoRun;

import java.net.*;
import java.io.*;



import com.hnzskj.common.Constant;
import com.hnzskj.web.sjzs.FlashPaper;

public class ServerApp {
	public static void main(String args[]) {
		try {
			Socket clientSocket = null;
			String inputLine;

			ServerSocket sSocket = new ServerSocket(11000);
			System.out.println("Server starting listen on:" + sSocket.getLocalPort());

				clientSocket = sSocket.accept();
				BufferedReader is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				PrintWriter os = new PrintWriter(clientSocket.getOutputStream());

				while (true) {
					inputLine = is.readLine();					// 当客户端输入stop的时候服务器程序运行终止！
					System.err.println("客户端请求=="+inputLine);
					 if (inputLine.equals("isHave")) {
						System.out.println(inputLine);
						// todo 检测是否有更新数据
//						if (isHaveUpdateData()) {
//							os.println("YES");
//						} else {
//							os.println("NO");
//						}

					}else{
						os.println("NO");
					}

					os.println("您的请求、是="+inputLine);
					os.flush();
				}
		} catch (Exception e) {
			System.out.println("Exception :" + e.getMessage());
		}
	}

	private static String isHaveUpdateData() {
		String flag = "";
		return flag;
	}

}