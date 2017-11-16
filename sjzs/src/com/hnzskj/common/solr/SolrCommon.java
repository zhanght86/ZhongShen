/*
 * @项目名称: sjzs
 * @文件名称: SolrCommon.java
 * @日期: 2013-4-8 下午06:06:04  
 * @版权: 2013 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.solr;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;


import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.tag.FunctionUtils;

/**    
 * 项目名称：sjzs   <br/>
 * 类名称：SolrCommon.java   <br/>
 * 类描述：  创建索引需要用到 <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2013-4-8 下午06:06:04   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2013-4-8 下午06:06:04   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class SolrCommon extends BaseDao {

	
	/**
	 * 
	 * 方法描述：<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 下午06:11:10<br/>         
	 * @param start 从第几条开始查询数据<br/>
	 * @param flag true 重置索引  false 增量索引 <br/>      
	 * @return List<QueryResult><br/>   
	 * @version   1.0<br/>
	 */
	public Collection<SolrInputDocument> searchQueryResults(int start,String tableName){
		String sql="SELECT ID,TITLE,CONTENT,TYPE,UPDATEDATE,ATTACH FROM "+tableName+" LIMIT " +
				""+start+","+Constant.BUILDINDEXSIZE ;
		conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection<SolrInputDocument> docs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			SolrInputDocument doc=null;
			docs = new ArrayList<SolrInputDocument>();
			
			StringBuffer ids = new StringBuffer();
			while(rs.next()){
				Tika tika = new Tika();
				doc = new SolrInputDocument();
				doc.addField("id", rs.getString("ID"));
				doc.addField("title", rs.getString("TITLE"));
				if (rs.getString("CONTENT")==null) {
					doc.addField("content","");
				}else {
					doc.addField("content",rs.getString("CONTENT"));
				}
				doc.addField("updatedate", rs.getString("UPDATEDATE"));
				doc.addField("type", rs.getString("type"));
				//doc.addField("attach", );
				String str=FunctionUtils.htmltoText(rs.getBinaryStream("ATTACH")==null?"":tika.parseToString(rs.getBinaryStream("ATTACH")));
				doc.addField("attach", rs.getString("TITLE")+str+rs.getString("updatedate"));
				ids.append(",'"+doc.getFieldValue("id")+"'");
			
				docs.add(doc);
			}
			
			if (ids.toString().length()>5) {
				tableName=tableName.replace("view", "");
				String idName="id";
				if (tableName.equals("sjzs_datadic")) {
					idName="dicID";
				}else if (tableName.equals("sjzs_sjfg")) {
					idName="lawId";
				}
				updateStatus(ids.toString(), tableName, idName);//审计方案
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
			
		}
		
		return docs;
	}
	
	/**
	 * 
	 * 方法描述：查询总的记录数。<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 下午06:11:33<br/>         
	 * @param <br/>   
	 * @return int<br/>   
	 * @version   1.0<br/>
	 */
	public int searchQueryResutTotal(String tableName){
		String sql="SELECT COUNT(id) AS COUNT FROM "+tableName;
		Object count=getSingleValue(sql, null);
		if (null!=count&&!("").equals(count)) {
			return Integer.parseInt(count.toString());
		}else{
			return 0;
		}
	}
	/**
	 * 
	 * 描述：批量修改是否创建索引的状态
	 * 创建人：丁艳伟
	 * 2013-4-17下午04:30:10
	 */
	public  int updateStatus(String ids,String tableName,String idName){
		int num=0;
		ids=ids.replaceFirst(",","");
		String sql="update "+tableName+" set note1 = -1 where "+idName+" in ("+ids+")";
		num=update(sql,null);
		return num;
	}
}
