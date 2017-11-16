package com.hnzskj.common;

import java.io.FileNotFoundException;


import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

public class ExcelReport {
	//Sheet实例对象
	private static HSSFFont font;
	private static HSSFWorkbook workbook = new HSSFWorkbook();
	private static HSSFCellStyle titletyle = setTitleStyle();
	private static HSSFCellStyle cellStyle = setCellStyle();
	private static HSSFCellStyle tabTitleStyle = setTabTitleStyle();


	/**
	 * 
	 * 方法描述：把中文格式化显示<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:00:15<br/>         
	 * @param cellFont
	 * @return
	 * @version   1.0
	 */
	public  static String setEncoding(Object cellFont){
		HSSFRichTextString textString = new HSSFRichTextString((String)cellFont);
		String valueString = textString.toString()+"";
		return valueString;
	}
	
	/**
	 * 方法描述：创建一个Excel文档的Sheet实例对象<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:00:38<br/>         
	 * @param documentName
	 * @param rowNames	生成表头的名称
	 * @return
	 * @version   1.0
	 */
	public static HSSFSheet getSheet(String documentName,String[] rowNames){
		HSSFSheet sheet = workbook.createSheet(documentName +".xls"); //建立新的sheet对象
		for (int i = 0; i < rowNames.length; i++) {
			sheet.setColumnWidth((short)i,(short)3500);
		}
		return sheet;
	}
	
	/**
	 * 
	 * 方法描述： 设置每一Cell显示文本的样式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:01:13<br/>         
	 * @return	Font实例对象
	 * @version   1.0
	 */
	public static HSSFFont setFont(){
		font=workbook.createFont();
	    font.setFontName("宋体");  //字体的样式
	    font.setBoldweight((short) 100);//字体的粗细
	    font.setFontHeight((short) 300);//字体的大小
	    font.setColor(HSSFColor.BLUE.index);//字体的颜色
		return font;
	}
	
	
	/**
	 * 
	 * 方法描述：设置标题文本的样式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:01:46<br/>         
	 * @return	HSSFCELLStyle 对象
	 * @version   1.0
	 */
	public static HSSFFont setTitleFont(){
		font=workbook.createFont();
	    font.setFontName("黑体");  //字体的样式
	    font.setBoldweight((short) 200);//字体的粗细
	    font.setFontHeight((short) 350);//字体的大小
	    font.setColor(HSSFColor.BLACK.index);//字体的颜色
		return font;
	}
	
	
	/**
	 * 
	 * 方法描述：设置表头的样式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:02:09<br/>         
	 * @return	HSSFCELLStyle 对象
	 * @version   1.0
	 */
	public static HSSFCellStyle setTabTitleStyle(){
		HSSFCellStyle tbtitletyle = workbook.createCellStyle();
		tbtitletyle.setBorderBottom((short)4);
		tbtitletyle.setBottomBorderColor((short)2);  //2是红虚线3是绿虚线4是；蓝虚线5是黄虚线6是
		tbtitletyle.setFillForegroundColor((short)29); //1是白色 17是深绿色18蓝色19是土绿色 20,2是红色29浅红色 15是黄色
		tbtitletyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		tbtitletyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		tbtitletyle.setBorderBottom((short)4);
		tbtitletyle.setBottomBorderColor((short)7);
		tbtitletyle.setFont(setTitleFont());
		return tbtitletyle;
	}
	
	/**
	 * 
	 * 方法描述：设置表头的样式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:02:30<br/>         
	 * @return	HSSFCELLStyle 对象
	 * @version   1.0
	 */
	public static HSSFCellStyle setTitleStyle(){
		HSSFCellStyle titletyle = workbook.createCellStyle();
		titletyle.setBorderBottom((short)4);
		titletyle.setBottomBorderColor((short)2);  //2是红虚线3是绿虚线4是；蓝虚线5是黄虚线6是
		titletyle.setFillForegroundColor((short)19); //1是白色 17是深绿色18蓝色19是土绿色 20,2是红色29浅红色 15是黄色
		titletyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titletyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titletyle.setBorderBottom((short)4);
		titletyle.setBottomBorderColor((short)7);
		titletyle.setFont(setFont());
		return titletyle;
	}
	
	
	/**
	 * 
	 * 方法描述：设置表中Cell的文本显示样式<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:02:54<br/>         
	 * @return	HSSFCELLStyle 对象
	 * @version   1.0
	 */
	public static HSSFCellStyle setCellStyle(){
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBottomBorderColor((short)2);  //2是红虚线3是绿虚线4是；蓝虚线5是黄虚线6是
		cellStyle.setFillForegroundColor((short)29); //1是白色 17是深绿色18蓝色19是土绿色 20,2是红色29浅红色 15是黄色
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setBorderBottom((short)4);
		cellStyle.setBottomBorderColor((short)7);
		cellStyle.setFont(setFont());
		return cellStyle;
	}
	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：余鹏飞   <br/>
	 * 创建时间：2011-7-14 下午06:03:18<br/>         
	 * @param documentName 文档的名字
	 * @param titleName 生成表格的表头说明性文本
	 * @param rowNames 文本标题的集合
	 * @param cellValues 向Excel中填充的数据列表
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @version   1.0
	 */
	public static HSSFWorkbook createExcel(String documentName,String titleName ,String[] rowNames,List<Object[]> cellValues)throws FileNotFoundException,IOException{
		workbook = new HSSFWorkbook();
		//设置表头样式
		setTabTitleStyle();
		//创建表头显示的文本标题
		HSSFSheet sheet = getSheet(documentName,rowNames);
		HSSFRow tabTitle = sheet.createRow(0);
		tabTitle.setHeight((short)500);
		@SuppressWarnings("unused")
		HSSFCell titleCell = null;
		titleCell =tabTitle.createCell((short)0);
		createCell(tabTitle, 0,tabTitleStyle, HSSFCell.CELL_TYPE_STRING,titleName);
		HSSFRow row = sheet.createRow(1);
		for (int i = 0; i < rowNames.length; i++) {
			if(i<(rowNames.length-1)){
			titleCell = tabTitle.createCell((short)(i+1));}
			HSSFCell cell = row.createCell((short)i);
			cell.setCellStyle(titletyle);
	        createCell(row, i,titletyle, HSSFCell.CELL_TYPE_STRING,rowNames[i]);
		}
		
		//创建Excel表格并填充数据
		for (int i = 0; i <cellValues.size(); i++) {
			HSSFRow rowInfo = sheet.createRow(i+2);  //建立新的row对象
			Object[] values = cellValues.get(i); 
			
			for (int j = 0; j < values.length; j++) {
				 createCell(rowInfo, j ,cellStyle, HSSFCell.CELL_TYPE_STRING,values[j]);
			}
		}
		//添加窗口冻结的效果(列数，行数)
		sheet.createFreezePane(3,2);
		//合并单元格的效果
		sheet.addMergedRegion(new Region(0,(short)0,0,(short)(rowNames.length-1)));
		return workbook;
	}
	
	// 创建Excel单元格   
	   private static void createCell(HSSFRow row, int column, HSSFCellStyle style,   
	           int cellType,Object value) {   
	           HSSFCell cell = row.createCell((short) column);   
	          // cell.setEncoding(HSSFCell.ENCODING_UTF_16);   
	           if (style != null) {   
	              cell.setCellStyle(style);   
	          }    
	          switch (cellType) {   
	              case HSSFCell.CELL_TYPE_BLANK: {   
	       }   
	           break;   
	       case HSSFCell.CELL_TYPE_STRING: {   
	           cell.setCellValue(value.toString());   
	            }   
	           break;   
	       case HSSFCell.CELL_TYPE_NUMERIC: {   
	           cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);   
	           // DecimalFormat format = new DecimalFormat("###,##0.00");   
	           // cell.setCellValue(Float.parseFloat(value.toString()));   
	           cell.setCellValue(Double.parseDouble(value.toString()));   
	       }   
	           break;   
	       default:   
	           break;   
	       }   
	   }    
	 
}
