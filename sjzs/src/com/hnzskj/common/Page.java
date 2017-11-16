package com.hnzskj.common;

import java.util.ArrayList;

import java.util.List;

/**
 * 
 * 项目名称：htglxt     <br/>
 * 类名称：Page     <br/>
 * 类描述：分页功能辅助类，此类封装了分页中常用到的功能：当前页curPage,总页数totalPage,总记录数totalRecords,<br/>
 * 每页显示的记录数maxResult,每页显示页面索引个数maxIndex,开始索引beginIndex,结束索引endIndex,封装结果集的List<E><br/>
 * 此类使用泛型Page<E>,此泛型指定了结果集List<E>所装载的对象的类型，使用此辅助类时需要注意，每页页面默认显示的总记录数为20，<br/>
 * 如有需要可重新指定<br/>
 * <b>类的使用方法：<b/>
 * <ul>
 * <li>指定当前页curPage</li>
 * <li>指定每页的记录maxResult，如果采用默认的20条记录，便不用重新指定，此属性一定要在指定totalRecords属性之前</li>
 * <li>指定总页面显示的索引数maxIndex，默认为6</li>
 * <li>指定总记录数totalRecords</li>
 * <li>指定封装的结果集</li>
 * </ul>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-3-7 上午09:14:55   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-3-7 上午09:14:55   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
public class Page<E> {
	/**
	 * 当前页面的索引
	 */
	private int curPage = 1;
	
	/**
	 * 总页数
	 */
	private int totalPage;
	
	/**
	 * 总记录数
	 */
	private int totalRecords;
	
	/**
	 * 每页显示的记录数
	 */
	private int maxResult = 15;
	/**
	 * 显示的索引个数
	 */
	private int maxIndex = 6;
	
	/**
	 * 开始索引
	 */
	private int beginIndex;
	
	/**
	 * 结束索引
	 */
	private int endIndex;
	/**
	 * 金额
	 */
	private String moneyStr;
	
	/**
	 * 封装结果集的集合
	 */
	private List<E> list = new ArrayList<E>();

	public int getCurPage() {
		return curPage;
	}

	/**
	 * 
	 * 方法描述：设定当前页<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-7 上午09:32:17<br/>         
	 * @param curPage
	 * @version   1.0
	 */
	/*
	public void setCurPage(int curPage) {
		System.out.println(curPage);
		if ( curPage < 1 && curPage != -1) {
			this.curPage = 1;
		} else {
			this.curPage = curPage;
		}
	}
	*/
	/**
	 *方法描述：解决原来方法下首页如果设置为空或者没有设置的情况下出现的问题  <br/>
	 *问题：如果两个方法同时存在的话，还是会有问题，为什么不会自己去找这个方法呢？！<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-19下午02:34:19
	 *@return void
	 *@version 1.0
	 */
	public void setCurPage(String pageCur) {
		if(pageCur==null || "".equals(pageCur)){
			curPage=1;
		}else{
			try {
				curPage = Integer.parseInt(pageCur);
				if ( curPage < 1 && curPage != -1) {
					curPage = 1;
				}
			} catch (NumberFormatException e) {
				curPage = 1;
			}
			
		}

	}
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	/**
	 * 
	 * 方法描述：设定总记录数，此方法还可完成对总页数，开始索引，结束索引的设定<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-7 上午09:33:35<br/>         
	 * @param totalRecords
	 * @version   1.0
	 */
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
		this.totalPage = (0 == this.totalRecords % this.maxResult)  
							? this.totalRecords / this.maxResult 
							: this.totalRecords / this.maxResult + 1;
		if (curPage > totalPage ) {
			curPage = totalPage;
		}
		if (curPage < 1) {
			curPage = 1;
		}
		this.setBeginIndex();
		this.setEndIndex();
	}
	public List<E> getList() {
		return list;
	}

	public void setList(List<E> list) {
		this.list = list;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	/**
	 * 
	 * 方法描述：设置开始索引<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-8 下午02:19:11<br/>         
	 * @version   1.0
	 */
	public void setBeginIndex() {
		this.beginIndex = this.curPage - ( this.maxIndex - 1 ) / 2;
	}

	public int getEndIndex() {
		return endIndex;
	}

	/**
	 * 
	 * 方法描述：设定结束索引，此方法处理了以下情况<br/>
	 * <ol>
	 * 	<li>当前页 -(最在索引数－1)/2小于0时，开始索引置为1，此时如果结束索引endIndex > 总页数totalPage,结束索引置为totalPage</li>
	 * 	<li>当前页 -(最在索引数－1)/2大于0时
	 * 		<ol>
	 * 			<li>结束索引endIndex小于totalPage时,endIndex为endIndex</li>
	 * 			<li>结束索引endIndex大于totalPage时，endIndex为totalPage,如果开始索引＝结束索引－最大索引数+1大于0，
	 * 				则开始索引为此值，否则开始索引为1</li>
	 * 		<ol>
	 * <ol>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-3-7 上午09:39:46<br/>         
	 * @version   1.0
	 */
	public void setEndIndex() {
		if ( 0 >= this.curPage - (this.maxIndex - 1) / 2  ) {
			this.beginIndex = 1;
			if ( maxIndex > totalPage ) {
				this.endIndex = this.totalPage;
			} else {
				this.endIndex = this.maxIndex;
			}
		} else {
			this.endIndex = this.curPage + ( 0 == ( this.maxIndex - 1 ) / 2 
											? ( this.maxIndex - 1 ) / 2 
											: ( this.maxIndex - 1 ) / 2 + 1 );
			if ( this.endIndex > this.totalPage ) {
				this.endIndex = this.totalPage;
				this.beginIndex = this.endIndex - this.maxIndex + 1;
				if ( 0 >= this.beginIndex ) {
					this.beginIndex = 1;
				}
			}
		}
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void setMaxIndex(int maxIndex) {
		this.maxIndex = maxIndex;
	}

	public String getMoneyStr() {
		return moneyStr;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}
}