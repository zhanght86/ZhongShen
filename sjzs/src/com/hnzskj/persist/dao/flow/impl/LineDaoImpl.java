/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-62012下午03:32:06
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow.impl;

import java.sql.Timestamp;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.flow.Line;
import com.hnzskj.persist.dao.flow.LineDao;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-6 下午03:32:06
 *修改人：郑辉
 *修改时间：
 */
public class LineDaoImpl extends BaseDao implements LineDao{

	/**
	 *创建人：郑辉
	 *描述： 	先把线路数据全部删除，然后再添加  
	 *创建时间：2012-4-7 上午10:18:26
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	@SuppressWarnings("unchecked")
	public int addLineInfo(Line  line){
		String sql ="select TEMPLATE_ID from Sys_line where TEMPLATE_ID=? and line_id =?";
		Object [] param ={line.getTemplate_id(),line.getLine_id()};
		List list =this.query(sql, Line.class, param);
		if(list.size()!=0){
			sql ="delete sys_line  where TEMPLATE_ID=? and line_id =?";
			this.update(sql, param);
		}
		sql ="insert into SYS_LINE (LINE_ID,TEMPLATE_ID,TACHE_ID,END_TACHE_ID,LINE_TYPE,LINK_PATH,lineLink_role,lineLink_props,updatedate) values(?,?,?,?,?,?,?,?,?)";
		Object [] params ={line.getLine_id(),
				line.getTemplate_id(),
				line.getTache_id(),
				line.getEnd_tache_id(),
				line.getLine_type(),
				line.getLink_path(),
				line.getLineLink_role(),
				line.getLineLink_props(),
				new Timestamp(System.currentTimeMillis())
			};
		int num =this.update(sql, params);
		if(0!= num) return num;
		return 0;
	}
	
	/**
	 *创建人：郑辉
	 *描述：	通过模版获取连接线的集合 	
	 *创建时间：2012-4-7 上午10:19:10
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： List<Line>
	 */
	@SuppressWarnings("unchecked")
	public List<Line> getLineByTempId(int tempno){
		List<Line> lineList =null;
		String sql ="select line_id ,template_id,tache_Id,end_tache_id,line_type,link_path,lineLink_role,lineLink_props  from SYS_LINE where template_id=? order by LINE_ID asc";
		Object [] params={tempno};
		lineList =this.query(sql, Line.class, params);
		if(lineList.size()!=0)return lineList;
		return null;
	}
	
	public int delete(int tmepno){
		String sql ="delete from sys_line  where TEMPLATE_ID=?";
		Object [] param ={tmepno};
		int num =this.update(sql, param);
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public List<Line> getDotTacheList(int templateNO,int tache_id){
		List<Line> lineList =null;
		String sql ="select * from sys_line  where TEMPLATE_ID=? and tache_id=?";
		Object [] param ={templateNO,tache_id};
		lineList =this.query(sql,Line.class, param);
		return lineList;
	}

	/* (non-Javadoc)
	 * @see com.hnzskj.persist.dao.flow.LineDao#getLineInfo(java.lang.String, java.lang.String)
	 */
	@Override
	public Line getLineInfo(String templateId, String beforeTacheId,String endTacheId) {
		// TODO Auto-generated method stub
		String sql ="select line_id ,template_id,tache_Id,end_tache_id,line_type," +
				"link_path,lineLink_role,lineLink_props  from SYS_LINE " +
				"where template_id=? and tache_Id=? and end_tache_id =? order by LINE_ID asc";
		Object [] params={templateId,beforeTacheId,endTacheId};
		Line line =(Line)this.get(sql, Line.class, params);
		return line;
	}
	
	/**
	 * 类描述：通过连接线的编号和模板获取对象
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-9 下午02:22:05 
	 * 修改人：
	 * 修改时间：2012-8-9 下午02:22:05  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Line getLineInfoByLineId(String templateId,String lineId){
		String sql ="select line_id ,template_id,tache_Id,end_tache_id,line_type," +
		"link_path,lineLink_role,lineLink_props  from SYS_LINE " +
		"where template_id=? and lineId=? order by LINE_ID asc";
		Object [] params={templateId,lineId};
		Line line =(Line)this.get(sql, Line.class, params);
		return line;
	}
}
