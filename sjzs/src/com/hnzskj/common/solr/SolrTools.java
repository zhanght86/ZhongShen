/*
 * @项目名称: sjzs
 * @文件名称: SolrTools.java
 * @日期: 2013-4-8 上午08:09:56  
 * @版权: 2013 河南中审科技有限公司
 * @开发公司或单位：河南中审科技有限公司研发部
 */
package com.hnzskj.common.solr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;

import com.hnzskj.common.Constant;
import com.hnzskj.common.tag.FunctionUtils;
import com.hnzskj.persist.bean.fore.QueryResult;


/**    
 * 项目名称：sjzs   <br/>
 * 类名称：SolrTools.java   <br/>
 * 类描述：  全文检索工具类。包含一些创建索引、修改索引、删除索引以及查询索引的方法 <br/>
 * 创建人：王亲臣   <br/>
 * 创建时间：2013-4-8 上午08:09:56   <br/>
 * 修改人：王亲臣   <br/>
 * 修改时间：2013-4-8 上午08:09:56   <br/>
 * 修改备注：    <br/>
 * @version  1.0  
 */
public class SolrTools{
	
	private HttpSolrServer server = null;
	
	public SolrTools(){
			String solrServerUrl=Constant.SOLRSERVER;
			server =new HttpSolrServer(solrServerUrl);
	}

	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param qResult 参数实体<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addIndex(QueryResult qResult,File file){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str= null!=file?tika.parseToString(file):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", qResult.getId());
		doc.addField("title",qResult.getTitle());
		//doc.addField("content", qResult.getContent());
		doc.addField("updatedate", qResult.getDate());
		doc.addField("attach", qResult.getTitle()+FunctionUtils.htmltoText(qResult.getContent()).trim()+str+qResult.getDate());
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param uuid uuid，主键，唯一，不能为null<br/>
	 * @param id 业务主键<br/>
	 * @param title 标题<br/>
	 * @param content 说明<br/>
	 * @param updatedate 日期<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addIndex(String uuid,String id,String title,String content,String updatedate,File file){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=file?tika.parseToString(file):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("title",title);
		//doc.addField("content", content);
		doc.addField("updatedate", updatedate);
		doc.addField("attach", title+FunctionUtils.htmltoText(content)+str+updatedate);
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param uuid uuid，主键，唯一，不能为null<br/>
	 * @param id 业务主键<br/>
	 * @param title 标题<br/>
	 * @param content 说明<br/>
	 * @param updatedate 日期<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addIndex(String uuid,String id,String title,String content,String updatedate,InputStream inputStream){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=inputStream?tika.parseToString(inputStream):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("title",title);
		//doc.addField("content", content);
		doc.addField("updatedate", updatedate);
		doc.addField("attach", title+FunctionUtils.htmltoText(content)+str+updatedate);
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param qResult 参数实体<br/>
	 * @param inputStream 附件流<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean addIndex(QueryResult qResult,InputStream inputStream){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=inputStream?tika.parseToString(inputStream):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", qResult.getId());
		doc.addField("title",qResult.getTitle());
		//doc.addField("content", qResult.getContent());
		doc.addField("updatedate", qResult.getDate());
		doc.addField("attach",qResult.getTitle()+FunctionUtils.htmltoText(qResult.getContent())+str+qResult.getDate());
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：修改索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param qResult 参数实体<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updIndex(QueryResult qResult,File file){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=file?tika.parseToString(file):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", qResult.getId());
		doc.addField("title",qResult.getTitle());
		//doc.addField("content", qResult.getContent());
		doc.addField("updatedate", qResult.getDate());
		doc.addField("attach", qResult.getTitle()+FunctionUtils.htmltoText(qResult.getContent())+str+qResult.getDate());
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：修改索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param qResult 参数实体<br/>
	 * @param inputStream 附件流<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updIndex(QueryResult qResult,InputStream inputStream){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=inputStream?tika.parseToString(inputStream):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", qResult.getId());
		doc.addField("title",qResult.getTitle());
		//doc.addField("content", qResult.getContent());
		doc.addField("updatedate", qResult.getDate());
		doc.addField("attach", qResult.getTitle()+FunctionUtils.htmltoText(qResult.getContent())+str+qResult.getDate());
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param uuid uuid，主键，唯一，不能为null<br/>
	 * @param id 业务主键<br/>
	 * @param title 标题<br/>
	 * @param content 说明<br/>
	 * @param updatedate 日期<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updIndex(String uuid,String id,String title,String content,String updatedate,InputStream inputStream){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=inputStream?tika.parseToString(inputStream):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("title",title);
		//doc.addField("content", content);
		doc.addField("updatedate", updatedate);
		doc.addField("attach", title+FunctionUtils.htmltoText(content)+str+updatedate);
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:17:55<br/>         
	 * @param uuid uuid，主键，唯一，不能为null<br/>
	 * @param id 业务主键<br/>
	 * @param title 标题<br/>
	 * @param content 说明<br/>
	 * @param updatedate 日期<br/>
	 * @param file 附件<br/>   
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean updIndex(String uuid,String id,String title,String content,String updatedate,File file){
		boolean result = false;
		Tika tika =new Tika();
		String str = "";
		try {
			str=null!=file?tika.parseToString(file):"";
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		}
		SolrInputDocument doc=new SolrInputDocument();
		doc.addField("id", id);
		doc.addField("title",title);
		doc.addField("content", content);
		doc.addField("updatedate", updatedate);
		doc.addField("attach", title+FunctionUtils.htmltoText(content)+str+updatedate);
		try {
			server.add(doc);
			server.commit();
			result=true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
		
	/**
	 * 
	 * 方法描述：删除索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:25:19<br/>         
	 * @param qResult 参数实体<br/>
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delIndex(QueryResult qResult){
		try {
			server.deleteById(qResult.getId());
			server.commit();
			return true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：删除索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:25:19<br/>         
	 * @param qResult 参数实体<br/>
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delIndex(List<QueryResult> list){
		try {
			if(null!=list&&list.size()>0){
				QueryResult queryResult = null;
				for (int i = 0; i < list.size(); i++) {
					queryResult = (QueryResult)list.get(i);
					server.deleteById(queryResult.getId());
				}
			}
			server.commit();
			return true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：删除索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 上午08:25:19<br/>         
	 * @param uuId 索引唯一标识Id<br/>
	 * @return boolean<br/>   
	 * @version   1.0<br/>
	 */
	public boolean delIndex(String uuId){
		try {
			server.deleteById(uuId);
			server.commit();
			return true;
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * 方法描述：批量创建索引<br/>
	 * 创建人：王亲臣   <br/>
	 * 创建时间：2013-4-8 下午05:48:17<br/>         
	 * @param flag =false  <br/>   
	 * @return void<br/>   
	 * @version   1.0<br/>
	 */
	public void buildIndex(boolean flag){
		SolrCommon solrCommon = new SolrCommon();
		String[] tableViews=new String []{"sjzs_dxyjview","sjzs_sjfgview","sjzs_ffalview","sjzs_sjdhview","sjzs_datadicview","sjzs_ssfnview"};
		for (int j = 0; j < tableViews.length; j++) {
			int count = solrCommon.searchQueryResutTotal(tableViews[j]);
			if (count>0) {
				int pageSize =count%Constant.BUILDINDEXSIZE==0?count/Constant.BUILDINDEXSIZE:count/Constant.BUILDINDEXSIZE+1;
				Collection<SolrInputDocument> docs = null;
				if (pageSize>0) {
					try {
						for (int i =1; i <= pageSize; i++) {
							docs = solrCommon.searchQueryResults((i-1)*Constant.BUILDINDEXSIZE,tableViews[j]);
							if (docs !=null&&docs.size()>0) {
								server.add(docs);
								server.commit();
							}
							System.out.println("成功！");
						}
					} catch (SolrServerException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		SolrTools solrTools=new SolrTools();
			Tika tika =new Tika();
			String str = "";
			try {
				str=tika.parseToString(new File("c:\\1.doc"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}
			System.out.println(str);
			SolrInputDocument doc=null;
			Collection<SolrInputDocument> docs = new ArrayList<SolrInputDocument>();
			doc = new SolrInputDocument();
		//	doc.addField("uuid", "11111111112");
			doc.addField("id", "111111111");
			doc.addField("title","标题阿斯蒂芬啊");
			doc.addField("content", "主要内容阿斯利康放假阿萨科罚金阿拉山口发");
			doc.addField("updatedate", "2012-01-10");
			doc.addField("attach", str);
			docs.add(doc);
			
			try {
				solrTools.server.add(docs);
				solrTools.server.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//solrTools.delIndex("111");
			try {
				solrTools.server.deleteById("11111111114");
				solrTools.server.deleteById("11111111113");
				solrTools.server.deleteById("11111111112");
				solrTools.server.commit();
			} catch (SolrServerException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("success");
		
	}
}
