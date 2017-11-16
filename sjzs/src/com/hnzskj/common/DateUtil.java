package com.hnzskj.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
	static final Logger log = Logger.getLogger(DateUtil.class);
	/**
	 * 年月日时分秒
	 */
	public static final String TIME_STRING = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 年月日
	 */
	public static final String TIME_YEEA_MONTH_DA_STRING = "yyyy-MM-dd";

	/**
	 * 获得当前时间
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_STRING);
		return dateFormat.format(new Date());
	}

	/**
	 * 
	 * 方法描述：获得传入字符串时间的函数</br>
	 * 创建人：zpl<br/>
	 * 创建时间：2012-4-18 上午11:23:07
	 * @return
	 * @version  1.0
	 */
	public static Date Str2Date(String date) {
		try {
			if (date.length() > 10)
				return Str2Date(date, "yyyy-MM-dd HH:mm:ss");
			else
				return Str2Date(date, "yyyy-MM-dd");
		} catch (Exception ex) {
			log.debug((new StringBuilder("Parse Date Error!")).append(
					ex.getMessage()).toString());
			return new Date(Calendar.getInstance().getTime().getTime());
		}
	}

	public static Date Str2Date(String date, String pattern) {
		java.util.Date d;
		try {
			SimpleDateFormat ft = new SimpleDateFormat(pattern);
			d = ft.parse(date);
			return new Date(d.getTime());
		} catch (Exception e) {
			log.debug((new StringBuilder("Parse Date Error!")).append(
					e.getMessage()).toString());
			return null;
		}
	}

	/**
	 * 获得指定天数的日期;i为1时表示获得当天的日期,如果获得最近n天,请直接传入n 方法描述：</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-9 下午01:01:51
	 * 
	 * @return
	 * @version 1.0
	 */
	public static String getAppointDate(Date date, int i) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, +i);
		Date d = cal.getTime();
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		return sp.format(d);// 获取指定天数的日期
	}

	/***
	 * 
	 * 方法描述：判断是否可以修改</br> 创建人：zpl<br/>
	 * 创建时间：2012-4-9 下午12:59:36
	 * 
	 * @return true 可以编辑 false 不可以编辑
	 * @version 1.0
	 */
	public static boolean isOrNotEdit(String date) {
		Date temp = Str2Date(date);
		Date now = new Date(Calendar.getInstance().getTime().getTime());
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = ft.format(now);// 获得当天的系统时间并格式化
		String strTemp = ft.format(temp);// 获得数据库文章创建时间并格式化
		String tomorrow = getAppointDate(temp, 1);// 获得数据库文章创建时间的后一天
		Date datetime = Str2Date((new StringBuilder(tomorrow)
				.append(" 08:30:00").toString()));// 时间点
		// System.out.println("当天时间:"+strToday);
		// System.out.println("第二天时间:"+tomorrow);
		// System.out.println("第二天临界值:"+Date2Str(datetime));
		// System.out.println("datetime:"+datetime.getTime());
		// System.out.println("now:"+now.getTime());
		if (strTemp.equals(strToday)) {
			return true;
		} else if (tomorrow.equals(strToday)
				&& (now.getTime() < datetime.getTime())) {
			return true;
		} else {
			return false;
		}
	}
}
