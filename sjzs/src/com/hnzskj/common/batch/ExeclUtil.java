package com.hnzskj.common.batch;
/**
 * 封装Execl 解析的数据
 * arrayData  存放符合格式的数据
 * faileRowColum   不符合格式的数据的行号和列号
 * @author jacob
 *
 */
public class ExeclUtil {
	private  String[][] arrayData;
	private String  faileRowColum;
	
	
	public ExeclUtil(String[][] arrayData, String faileRowColum) {
		
		this.arrayData = arrayData;
		this.faileRowColum = faileRowColum;
	}
	public String[][] getArrayData() {
		return arrayData;
	}
	public void setArrayData(String[][] arrayData) {
		this.arrayData = arrayData;
	}
	public String getFaileRowColum() {
		return faileRowColum;
	}
	public void setFaileRowColum(String faileRowColum) {
		this.faileRowColum = faileRowColum;
	}
	
	

}
