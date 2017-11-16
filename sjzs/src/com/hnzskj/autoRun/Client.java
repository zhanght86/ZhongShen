package com.hnzskj.autoRun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Client {
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL(
					"http://192.168.0.125:8090/sjzs/servlet/CheckUpdateServlet?projectV=V1.0&dataV=V2.0");
			URLConnection urlConnection = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));

			String readLine = "";
			readLine = br.readLine();
			readLine = new String(readLine.getBytes(), "utf-8");
			System.out.println(readLine);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
