package com.hnzskj.persist.dao.fore.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.Content;
import com.hnzskj.persist.dao.fore.ShowInfoDao;
import com.hnzskj.persist.dao.sjzs.DataDicDao;

public class ShowInfoDaoImpl extends BaseDao implements ShowInfoDao {
	
	private DataDicDao dataDicDao ;
	
	
	public DataDicDao getDataDicDao() {
		return dataDicDao;
	}

	public void setDataDicDao(DataDicDao dataDicDao) {
		this.dataDicDao = dataDicDao;
	}

	@Override
	public List<Content> searchDxyjByField(int num) {
		String sql = "select id as id,caption as title,lawContent as content,SUBSTRING(writeDate,1,10) as date from sjzs_dxyj "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchByField(sql, params);
	}

	@Override
	public List<Content> searchFfalByField(int num) {
		String sql = "select id as id,title as title,content as content,SUBSTRING(ffalDateTime,1,10) as date from sjzs_ffal "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchByField(sql, params);
	}

	@Override
	public List<Content> searchSjdhByField(int num) {
		String sql = "select id as id,name as title,context as content,SUBSTRING(createTime,1,10) as date from sjzs_sjdh "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchByField(sql, params);
	}

	@Override
	public List<Content> searchSjfgByField(int num) {
		String sql = "select lawId as id,lawName as title,lawContent as content,SUBSTRING(lawDate,1,10) as date from sjzs_sjfg "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchByField(sql, params);
	}

	@Override
	public List<Content> searchSjsxByField(int num) {
		String sql = "select dicid as id ,dicName as title ,dicMemo as content,SUBSTRING(updateDate,1,10) as date from sjzs_datadic "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchSjsxContent(searchByField(sql, params));
	}

	@Override
	public List<Content> searchSsfaByField(int num) {
		String sql = "select id as id ,name as title ,keyword as content,SUBSTRING(writedate,1,10) as date from sjzs_ssfn "
				+ "where deleteflag <> -1 order by date desc limit 0,?";
		Object[] params = new Object[] { num };
		return searchByField(sql, params);
	}

	/**
	 *方法描述：通用的，根据给定的sql语句，返回列表结果 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-7 下午04:23:03
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public List<Content> searchByField(String sql, Object[] params) {
		List<Object> objectList = getList(sql, Content.class, params);
		List<Content> contentList = new ArrayList<Content>();
		Content content = new Content();
		if (objectList != null) {
			for (int i = 0; i < objectList.size(); i++) {
				content = (Content) objectList.get(i);
				contentList.add(content);
			}
		} else {
			content = null;
		}
		return contentList;
	}
	
	/**
	 *方法描述：根据查询的审计事项的基本信息，得到审计事项的详细信息（主要是后来对主页面的修改需要） <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-7 下午04:24:53
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public List<Content> searchSjsxContent(List<Content> sjsxCont){
		for (Content content : sjsxCont) {
			content.setObj(dataDicDao.getDataDicById(content.getId()));
		}
		return sjsxCont;
	}
	
	@SuppressWarnings("unchecked")
	public Page getLatestMethods(Class clazz, String fields, String table, String condition,
			 Object[] params, LinkedHashMap<String, String> orderBy, Page page){
		Page tpage;
		if(page==null){
			tpage = new Page();
		}else{
			tpage = page;
		}
		condition = condition == null ? "" : condition; 
		String sql = "select " + fields + " from " + table + " where deleteflag <> -1 " +
					 condition + BaseDao.buildOrderBy(orderBy) +
					 " limit " + (page.getCurPage() - 1) * page.getMaxResult() + "," + page.getMaxResult();
		List list = this.query(sql, clazz, params);
		tpage.setList(list);
		return page;
	}
}
