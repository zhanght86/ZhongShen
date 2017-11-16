/*
 * @项目名称: htglxt
 * @文件名称: DateUtil.java
 * @日期: 2011-6-1
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

import com.hnzskj.common.init.SysParamUtil;

/**
 * 把时间类型转化为字符串，把字符串转换为时间类型数据.
 * 类名称：DateUtil     <br/>
 * 类描述：<br/>
 * 创建人：余鹏飞  <br/>
 * 创建时间：2011-6-1 上午10:20:52<br/>
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-1 上午10:20:52<br/>
 * 修改备注:<br/>
 * @version   1.0
 */

public class DataUtil {

    private static String[] fields;
    private static Object[] params;
	private static Logger log = Logger.getLogger(DataUtil.class);
    
    /**
	 * 要查询显示的数据列名称
	 */
	public static String[] getFields() {
		return fields;
	}
	/**
	 * 要查询显示的数据列名称.
	 */
	public static void setFields(String[] fields) {
		DataUtil.fields = fields;
	}
	/**
	 * 对应各个列的参数列表
	 */
	public static Object[] getParams() {
		return params;
	}
	/**
	 * 对应各个列的参数列表
	 */
	public static void setParams(Object[] params) {
		DataUtil.params = params;
	}
	/**
	 * 
	 * 方法描述：把字符串类型的数据转换为时间类型<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-5-30 下午05:45:09<br/>         
	 * @param data 字符串数据
	 * @return
	 * @version   1.0
	 */
	public static Date getDate(String data){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sqlDate = null;
			if(null!=data){
				try {
					sqlDate = format.parse(data);
				} catch (ParseException e) {
					log  .error(DataUtil.getStackTraceAsString(e));;
				}
			}
		return sqlDate;
	}
	/**
	 * 
	 * 方法描述：把时间类型的数据转换为字符串类型<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-5-30 下午05:45:13<br/>         
	 * @param date 时间数据
	 * @return
	 * @version   1.0
	 */
	public static String getString(Date date){
		String dateStr = null;
		if (null != date ) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateStr = format.format(date);			
		}
		return dateStr;
	}
	/**
	 * 用于向表中插入当前时间
	 * 
	 * 方法描述：获取当前时间并返回时间的字符串形式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-7 下午04:20:49<br/>         
	 * @return
	 * @version   1.0
	 */
	public  static String getNowTime(){
		java.util.Date date = new java.util.Date();
		//时间的显示格式是yyyy-MM-dd- HH:mm:ss
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String data = format.format(date);
		return data;
	}
	
	/**
	 * 类描述：<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-7-11 上午09:30:29 
	 * 修改人：
	 * 修改时间：2012-7-11 上午09:30:29  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public static String getNowDate(){
			java.util.Date date = new java.util.Date();
			//时间的显示格式是yyyy-MM-dd- HH:mm:ss
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String data = format.format(date);
			return data;
	}
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-11 上午10:36:53<br/>         
	 * @param dateStr 读取到实体后的日期格式的字符串 2011-10-20    或者   2011-10-20 00:00:00
	 * @param delPart 如果要得到2011-10-20 则(delPart="00:00:00:.0") 如果要得到 2011-10-20 00:00:00则(delPart=".0")
	 * @return
	 * @version   1.0
	 */
	public static String formateStr(String dateStr,String delPart){
		dateStr = dateStr.replace(delPart, "");
		return dateStr;
	}
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-11 上午10:36:53<br/>         
	 * @param dateStr 读取到实体后的日期格式的字符串 2011-10-20    或者   2011-10-20 00:00:00
	 * @param delPart 如果要得到2011-10-20 则(endIndex=10) 如果要得到 2011-10-20 00:00:00则(endIndex=19)
	 * @return
	 * @version   1.0
	 */
	public static String formateStr(String dateStr,int endIndex){
		String format = "";
		if(null==dateStr||"".equals(dateStr)){
			
			return format;
		}
		format = dateStr.substring(0, endIndex);
		return format;
	}
	/**
	 * 
	 * 方法描述：把一个String[] 数组的元素用,分解后变成一个字符串<br/>
	 * 比如 new String['2','9','1'] To String value ='2,9,1'
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-2 下午05:07:37<br/>         
	 * @param arrays
	 * @return String
	 * @version   1.0
	 */
	public static String getValueFromArray(String[] arrays){
		String value = "";
		if(null==arrays){
			return null;
		}
		for (String message : arrays) {
			value += message +",";
		}
		value = value.substring(0, value.length()-1);
		return  value;
	}
	
	/**
	 * 方法描述：将一个String类型的数组以字符中的形式输出<br/>
	 * 如：new String[]{"a","b","c"}输出为"'a','b','c'"
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-2 上午09:46:34<br/>         
	 * @param strArr
	 * @return	如果数组为NULL或者长度为0返回"";
	 * @version   1.0  
	 */
	public static String arrayToString(String[] strArr) {
		StringBuffer sb = new StringBuffer("");
		if (null != strArr && strArr.length > 0) {
			for (String str : strArr) {
				sb.append("'").append(str).append("'").append(",");
			}
			sb.deleteCharAt(sb.length() -1);
		}
		return sb.toString();
	}
	

	/**
	 * 
	 * 方法描述：获得一个指定长度的随机数字<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-13 下午03:59:45<br/>         
	 * @param length 获取随机数字的长度
	 * @return
	 * @version   1.0
	 */
	public static String GetRandom(int length) {
		String Elecode = getTime()+"-";
		String[] buffer = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				   "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
				   "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z"};
				Random random = new Random();
				for (int i = 0; i <length; i++) {
				       Elecode += buffer[random.nextInt(62)];
				}
		        return Elecode;
	}

	/**
	 * 获取当前的时间 ：年月日
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-13 下午03:59:40<br/>         
	 * @return
	 * @version   1.0
	 */
	
	public static String getTime(){
		java.util.Date date = new java.util.Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String data = format.format(date);
		return data;
	}
	
	/**
	 * 传递过来的时间 月又几天
	 * @param time
	 * @return
	 */
	public static int getMountDays(String time){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		 int days =01;
		    try {
		      Date date = format.parse(time+"-01");
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(date);
		      days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		    } catch (ParseException e) {
		      log.error(DataUtil.getStackTraceAsString(e));;
		    }
		    return days;
	}
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-6-3 下午02:58:28<br/>         
	 * @param sourceText  原始字符串如(getstuName,getstuNo)
	 * @param lower 需要变成大写的第一个字符如s 该字符必须小写
	 * @return 把一个字符变成大写后的该字符串 getStuName,getStuNo
	 * @version   1.0
	 */
	public  String replace(String sourceText){
		 sourceText =sourceText.substring(sourceText.lastIndexOf(".")+1);
		//获取get后的第一个字符
		 String place = "get" + sourceText.substring(0,1).toUpperCase();
		 //把get后第一个字符改变成大写
		 String newText=sourceText.replaceFirst(sourceText.substring(0,1), place);
		 return newText;
	}
	
	
	/**
     * 
     * 方法描述：构造Update的SQL语句，包括参数的传入<br/>
     * 创建人：余鹏飞   <br/>
     * 创建时间：2011-6-3 下午03:44
     * @param cla 一个类 比如  Student student = new Student(); student。getClass() 就是传入的参数
     * @param obj 要修改的实体对象 如 student
     * @param lower 要转换的一个小写字符
     * @throws Exception
     * @version   1.0
     */
	
	@SuppressWarnings("unchecked")
	public void  createUpdateSQL(Class cla,Object obj) throws Exception{
		String fieldsStr = "";
		String valuesStr = "";
		//获取一个类[比如stu.getClass()]的所有属性
		Field[] fieldes = cla.getDeclaredFields();
		/**
		 *遍历属性和属性值
		 *对属性和属性值组成字符串后分割成数组并存储
		 */
		for (Field field : fieldes) {
			String getproStr = field.toString();
			String property =  replace(getproStr);
			Method method  =   cla.getMethod(property);
			//对属性的数据类型进行判断后赋值组装成字符串形式
			if(null!=method.invoke(obj)){
				if(field.getType().toString().equals("class java.lang.String")){
					fieldsStr += field.getName()+",";
					String value = (String)method.invoke(obj);
					valuesStr += value+",";
				}if(field.getType().toString().equals("int")) {
					fieldsStr += field.getName()+",";
					Integer value = (Integer)method.invoke(obj);
					valuesStr += value+",";
				}if(field.getType().toString().equals("class java.sql.Date")){
					fieldsStr += field.getName()+",";
					Date value = (Date)method.invoke(obj);
					valuesStr += value+",";
				}if(field.getType().toString().equals("class java.lang.Double")){
					fieldsStr += field.getName()+",";
					Double value = (Double)method.invoke(obj);
					valuesStr += value+",";
				}
			}
	}
		if(null==fieldsStr||"".equals(fieldsStr)||null==valuesStr||"".equals(valuesStr)){
			return;
		}else{
			fieldsStr = fieldsStr.substring(0,(fieldsStr.length()-1));
			fields = fieldsStr.split(",");
			valuesStr = valuesStr.substring(0,(valuesStr.length()-1));
			params = new Object[valuesStr.split(",").length];
			Object[] objs = valuesStr.split(",");
			for (int i = 0; i < objs.length; i++) {
				params[i] = objs[i];
			}
		}
	}
	/**
     * 
     * 方法描述：自动生成合同编号<br/>
     * 创建人：常利召   <br/>
     * 创建时间：2011-6-3 下午03:44
     * @throws Exception
     * @version   1.0
     */
	public static String getContractCode(){
		//获得合同编号的头部
		SysParamUtil sp = new SysParamUtil();
		String strtou = sp.getInitCommon().getContractcode();
		//获得合同编号的年份
		//Calendar cal = Calendar.getInstance();
		//int yearInt = cal.get(Calendar.YEAR);
		//String year = String.valueOf(yearInt);
		//自动增加尾部信息
//		BaseDao bd = new BaseDao();
//		String sql = "select max(RIGHT(hbcode,8)) from htjbxx";
//		String code = bd.getSingleValue(sql);
//		if(null!=code){
//			code = String.valueOf(Integer.parseInt(code.substring(code.length()-4))+1);
//			if(1==code.length()){
//				code = "000"+code;
//			}else if(2==code.length()){
//				code = "00"+code;
//			}else if(3==code.length()){
//				code = "0"+code;
//			}
//			code = strtou+year+code;
//		}else{
//			code = strtou+year+"0001";
//		}
		
		//晁飞添加开始
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		String[]bStrings = format.format(new Date()).split("-");
		String date="";
		for (int i = 0; i < bStrings.length; i++) {
			date+=bStrings[i];
		}
		String code = strtou+date+(int)(Math.random()*1000);
		//晁飞添加结束
		return code;
	}

	/**
     * 
     * 方法描述：合同编号下一个编号<br/>
     * 创建人：常利召   <br/>
     * 创建时间：2011-6-3 下午03:44
     * @throws Exception
     * @version   1.0
     */
	public static String getContractCodeNext(String code){
		String tou = code.substring(0,2);
//		code = String.valueOf(Integer.parseInt(code.substring(code.length()-4))+1);
//		if(1==code.length()){
//			code = "000"+code;
//		}else if(2==code.length()){
//			code = "00"+code;
//		}else if(3==code.length()){
//			code = "0"+code;
//		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		String[]bStrings = format.format(new Date()).split("-");
		String date="";
		for (int i = 0; i < bStrings.length; i++) {
			date+=bStrings[i];
		}
		return tou+date+(int)(Math.random()*1000);
	}
	/**
     * 
     * 方法描述：合同编号上一个编号<br/>
     * 创建人：常利召   <br/>
     * 创建时间：2011-6-3 下午03:44
     * @throws Exception
     * @version   1.0
     */
	public static String getContractCodeGoNext(String code){
		String tou = code.substring(0,2);
//		code = String.valueOf(Integer.parseInt(code.substring(code.length()-4))-1);
//		if(1==code.length()){
//			code = "000"+code;
//		}else if(2==code.length()){
//			code = "00"+code;
//		}else if(3==code.length()){
//			code = "0"+code;
//		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		
		String[]bStrings = format.format(new Date()).split("-");
		String date="";
		for (int i = 0; i < bStrings.length; i++) {
			date+=bStrings[i];
		}
		return tou+date+(int)(Math.random()*1000);
	}

	/**
     * 
     * 方法描述：合同逾期天数方法<br/>
     * 创建人：常利召   <br/>
     * 创建时间：2011-6-3 下午03:44
     * @throws Exception
     * @param endDate 计划完成日期
     * @version   1.0
     */
	public static String getContractYqts(Date endDate){
		long divisor = 3600*1000*24;
		BigDecimal bigDivisor = new BigDecimal(divisor);
		Calendar calCurrent=Calendar.getInstance();
		Calendar endCurrent=Calendar.getInstance();
		if(null!=endDate){
			endCurrent.setTime(endDate);
		}else{
			endCurrent.setTime(new Date());
		}
		calCurrent.setTime(new Date());
		long millis = calCurrent.getTimeInMillis();
		long milliend = endCurrent.getTimeInMillis();
		BigDecimal bigMillis = new BigDecimal(millis);
		BigDecimal bigMilliend = new BigDecimal(milliend);
		BigDecimal dayNum = bigMillis.divide(bigDivisor,2);
		BigDecimal dayNumend = bigMilliend.divide(bigDivisor,2);
		String yqts = String.valueOf(dayNum.intValue()-dayNumend.intValue());
		return yqts;
	}
	
	/**
	 * 方法描述：获得当前字符str中包含字符str1的个数
	 * 创建人： 丁艳伟
	 * 创建时间：2011-7-8下午11:44:32
	 * @param str
	 * @param str1
	 * @return
	 * @return
	 */
	public static int getStrCount(String str, String str1) {
		String[] strarr = str.split("");
		int result = 0;
		for (int i = 0; i < strarr.length; i++) {
			boolean eresult = str1.equals(strarr[i]);
			if (eresult) {
				result++;
			}
		}
		return result;
	}

	
	public static String getTimeOut(String maxDate,String minDate){
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeOut=""; 
		try {
			 java.util.Date now = df.parse(maxDate);
			java.util.Date date=df.parse(minDate);
			long l=now.getTime()-date.getTime();
			long day=l/(24*60*60*1000);
			long hour=(l/(60*60*1000)-day*24);
			long min=((l/(60*1000))-day*24*60-hour*60);
			long s=(l/1000-day*24*60*60-hour*60*60-min*60);
			timeOut =day+"天"+hour+"小时"+min+"分"+s+"秒";
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			log.error(DataUtil.getStackTraceAsString(e));;

		}
		return timeOut;
	}
	
	/**
	 * 方法描述：<br/>.
	 * 创建人： <br/>
	 * 创建时间：2012-5-16 上午09:53:13<br/>
	 * @param e
	 * @return String
	 * @version   1.0
	 */
	public static String getStackTraceAsString(Exception e) {

		//转换成String，并返回该String
		StringBuffer error = null;
		StringWriter stringWriter = null;
		PrintWriter printWriter = null;		
		try {
			// StringWriter将包含堆栈信息
			stringWriter = new StringWriter();
			//必须将StringWriter封装成PrintWriter对象，
			//以满足printStackTrace的要求
			printWriter = new PrintWriter(stringWriter);
			//获取堆栈信息
			e.printStackTrace(printWriter);
			error = stringWriter.getBuffer();
		} catch (Exception ex1) {
			ex1.printStackTrace();
		} finally {
			try {
				stringWriter.close();
			} catch (IOException ex1) {
				ex1.printStackTrace();
			}
			printWriter.close();
		}
		return error.toString();
	}

	public static void main(String[] args) {
//		Calendar calCurrent=Calendar.getInstance();
//		Calendar calCurrent1=Calendar.getInstance();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		java.util.Date date = null;
//		try {
//			date = (java.util.Date) sdf.parseObject("2011-06-27");
//		} catch (ParseException e1) {
//			e1.printStackTrace();
//		}
//		calCurrent.setTime(new Date());
//		calCurrent1.setTime(date);
//		long millis = calCurrent.getTimeInMillis();
//		long millis1 = calCurrent1.getTimeInMillis();
//		BigDecimal bigMillis = new BigDecimal(millis);
//		BigDecimal bigMillis1 = new BigDecimal(millis1);
//		long divisor = 3600*1000*24;
//		BigDecimal bigDivisor = new BigDecimal(divisor);
//		BigDecimal dayNum = bigMillis.divide(bigDivisor,2);
//		BigDecimal dayNum1 = bigMillis1.divide(bigDivisor,2);
//		String a = String.valueOf(dayNum.intValue()-dayNum1.intValue());
//		System.out.println(dayNum.intValue()-dayNum1.intValue());
//		System.out.println(a);
/*		String a = "撒地方士大夫是是对0001";
		System.out.println(a.substring(a.length()-4));*/

//		String stringsString = "1122323121";
//		String strr = "1";
//		System.out.println(getStrCount(stringsString, strr));

	}

	
}
