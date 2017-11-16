/*
 * @项目名称: OA
 * @文件名称: SoftWareItemsdAO.java
 * @日期: 2012-7-16
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.wflow;

import java.util.List;

import com.hnzskj.persist.bean.wflow.SoftWareItems;

  /**        
 * 
 * 类名称：SoftWareItemsdAO
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-16 下午02:29:17 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public interface SoftWareItemsDao {
		/**
		 * 类描述：添加一条
		 * 创建人：郑辉  <br/>
		 * 创建时间：2012-7-12 下午04:43:20 
		 * 修改人：
		 * 修改时间：2012-7-12 下午04:43:20  
		 * 修改备注：   
		 * @version   1.0     
		 */
		public int addInfo(SoftWareItems items);
		
		/**
		 * 类描述：批量插入多条
		 * 创建人：郑辉  <br/>
		 * 创建时间：2012-7-12 下午04:43:20 
		 * 修改人：
		 * 修改时间：2012-7-12 下午04:43:20  
		 * 修改备注：   
		 * @version   1.0     
		 */
		public int addBatchInfo(Object [][] params);
		
		/**
		 * 类描述：删除
		 * 创建人：郑辉  <br/>
		 * 创建时间：2012-7-12 下午04:43:32 
		 * 修改人：
		 * 修改时间：2012-7-12 下午04:43:32  
		 * 修改备注：   
		 * @version   1.0     
		 */
		public int deleteInfo(String id);
		
		
		/**
		 * 类描述：获取去到对象  通过实例号
		 * 创建人：郑辉  <br/>
		 * 创建时间：2012-7-13 上午08:57:40 
		 * 修改人：
		 * 修改时间：2012-7-13 上午08:57:40  
		 * 修改备注：   
		 * @version   1.0     
		 */
		public List<SoftWareItems> getInfo(String id);
}
