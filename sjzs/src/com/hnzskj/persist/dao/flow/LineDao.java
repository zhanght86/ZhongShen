/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-62012下午03:32:06
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.Line;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-6 下午03:32:06
 *修改人：郑辉
 *修改时间：
 */
public interface LineDao{

	/**
	 *创建人：郑辉
	 *描述： 	先把线路数据全部删除，然后再添加       bi
	 *创建时间：2012-4-7 上午10:18:26
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addLineInfo(Line  line);
	
	/**
	 *创建人：郑辉
	 *描述：	通过模版获取连接线的集合 	
	 *创建时间：2012-4-7 上午10:19:10
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Line>
	 */
	public List<Line> getLineByTempId(int tempno);
	
	/**
	 * 类描述：<br/>
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-8 上午09:07:23 
	 * 修改人：
	 * 修改时间：2012-8-8 上午09:07:23  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int delete(int tmepno);
	
	/**
	 * 类描述：
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-8 上午09:07:20 
	 * 修改人：
	 * 修改时间：2012-8-8 上午09:07:20  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public List<Line> getDotTacheList(int templateNO,int tache_id);
	
	/**
	 * 类描述：获取连接线的基本信息，主要使用来判断线路设置的条件
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-8 上午08:42:42 
	 * 修改人：
	 * 修改时间：2012-8-8 上午08:42:42  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Line getLineInfo(String templateId,String beforeTacheId,String endTacheId);
	
	/**
	 * 类描述：通过连接线的编号和模板获取对象
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-9 下午02:22:05 
	 * 修改人：
	 * 修改时间：2012-8-9 下午02:22:05  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Line getLineInfoByLineId(String templateId,String lineId);
}
