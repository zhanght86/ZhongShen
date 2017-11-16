/*@目名称：lwsj
 *@文件名：SearchDaoImpl.java
 *@期：下午04:12:46
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.persist.dao.fore.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.QueryResult;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.dao.fore.SearchDao;
import com.hnzskj.persist.dao.sjzs.DataDicDao;
import com.hnzskj.persist.dao.sjzs.DingXingYiJuDao;
import com.hnzskj.persist.dao.sjzs.FangFaAnLiDao;
import com.hnzskj.persist.dao.sjzs.ShenJiFaGuiDao;
import com.hnzskj.persist.dao.sjzs.ShiShiFangAnDao;
import com.hnzskj.persist.dao.sjzs.SjdhMethodDao;

/**
 *类名称:SearchDaoImpl 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:16:58<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:16:58<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public class SearchDaoImpl extends BaseDao implements SearchDao {
	private DingXingYiJuDao dingXingYiJuDao;
	private ShenJiFaGuiDao shenJiFaGuiDao;
	private FangFaAnLiDao fangFaAnLiDao;
	private SjdhMethodDao sjdhMethodDao;
	private DataDicDao dataDicDao;
	private ShiShiFangAnDao shiShiFangAnDao;
	/**
	 * @return the shenJiFaGuiDao
	 */
	public ShenJiFaGuiDao getShenJiFaGuiDao() {
		return shenJiFaGuiDao;
	}

	/**
	 * @param shenJiFaGuiDao
	 *            the shenJiFaGuiDao to set
	 */
	public void setShenJiFaGuiDao(ShenJiFaGuiDao shenJiFaGuiDao) {
		this.shenJiFaGuiDao = shenJiFaGuiDao;
	}

	/**
	 * @return the fangFaAnLiDao
	 */
	public FangFaAnLiDao getFangFaAnLiDao() {
		return fangFaAnLiDao;
	}

	/**
	 * @param fangFaAnLiDao
	 *            the fangFaAnLiDao to set
	 */
	public void setFangFaAnLiDao(FangFaAnLiDao fangFaAnLiDao) {
		this.fangFaAnLiDao = fangFaAnLiDao;
	}

	/**
	 * @return the sjdhMethodDao
	 */
	public SjdhMethodDao getSjdhMethodDao() {
		return sjdhMethodDao;
	}

	/**
	 * @param sjdhMethodDao
	 *            the sjdhMethodDao to set
	 */
	public void setSjdhMethodDao(SjdhMethodDao sjdhMethodDao) {
		this.sjdhMethodDao = sjdhMethodDao;
	}

	/**
	 * @return the dingXingYiJuDao
	 */
	public DingXingYiJuDao getDingXingYiJuDao() {
		return dingXingYiJuDao;
	}

	/**
	 * @param dingXingYiJuDao
	 *            the dingXingYiJuDao to set
	 */
	public void setDingXingYiJuDao(DingXingYiJuDao dingXingYiJuDao) {
		this.dingXingYiJuDao = dingXingYiJuDao;
	}
	
	
	
	public DataDicDao getDataDicDao() {
		return dataDicDao;
	}

	public void setDataDicDao(DataDicDao dataDicDao) {
		this.dataDicDao = dataDicDao;
	}

	public ShiShiFangAnDao getShiShiFangAnDao() {
		return shiShiFangAnDao;
	}

	public void setShiShiFangAnDao(ShiShiFangAnDao shiShiFangAnDao) {
		this.shiShiFangAnDao = shiShiFangAnDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#searchAllByKey(com.hnzskj.common
	 * .Page, java.lang.String)
	 */
	@SuppressWarnings( { "unchecked" })
	@Override
	public Page<QueryResult> searchAllByKey(Page<QueryResult> page, String key,
			LinkedHashMap<String, String> orderby) {
		StringBuilder sql = new StringBuilder();
		List<QueryResult> resultList = new ArrayList<QueryResult>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String countSql = "";// 查询总记录数的sql语句
		String newSql = "";// 查询的sql语句
		String sqlFour = "(select j.id as id ,j.caption as title , j.lawContent as content ,"
				+ "'Dxyj' as type, j.writeDate as date from sjzs_dxyj j "
				+ "union all "
				+ "select l.id as id ,l.title as title , l.content as content ,"
				+ "'Ffal' as type, l.ffalDateTime as date from sjzs_ffal l "
				+ "union all "
				+ "select g.lawId as id ,g.lawName as title , g.lawContent as content ,"
				+ "'Sjfg' as type, g.lawDate as date from sjzs_sjfg g "
				+ "union all "
				+ "select h.id as id ,h.name as title , h.context as content ,"
				+ "'Sjdh' as type, h.createTime as date from sjzs_sjdh h )as empl";

		String mainSql = "( empl.title like '%" + key
				+ "%' or empl.content like '%" + key + "%') ";
		countSql = sql.toString() + "select count(empl.id) from " + sqlFour
				+ " where " + mainSql;
		// 查询总记录数
		totalRecords = Integer.parseInt(this.getSingleValue(countSql, null)
				.toString());
		if (page != null) {// 如果需要分页
			// 先执行分页子句的查询结果
			String tempSql = sql.toString() + "select id from " + sqlFour
					+ " where  " + mainSql + " limit 0, "
					+ (page.getCurPage() - 1) * page.getMaxResult();
			List<String> idsList = this.getListSingleValue(tempSql, null);
			StringBuffer idBuffer = new StringBuffer();
			if (idsList != null && idsList.size() > 0) {
				for (int i = 0; i < idsList.size(); i++) {
					if (i == 0) {
						idBuffer.append("'" + idsList.get(i) + "'");
					} else {
						idBuffer.append(",'" + idsList.get(i) + "'");
					}
				}
			} else {
				idBuffer.append("''");
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()
					+ "select empl.id, empl.title ,  empl.content, empl.type, empl.date from "
					+ sqlFour + " where " + mainSql;
			newSql += " and empl.id not in (" + idBuffer.toString() + ") "
					+ BaseDao.buildOrderBy(orderby) + " limit 0,"
					+ page.getMaxResult();
			resultList = this.query(newSql, QueryResult.class, null);
		} else {// 如果不需要分页
			newSql = sql.toString()
					+ "select empl.id, empl.title, empl.content, empl.type, empl.date from "
					+ sqlFour + " where  " + mainSql;
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		}

		// 设置总记录数
		page.setList(resultList);
		// 设置结果集
		page.setTotalRecords(totalRecords);
		page = chengColor(page, key);
		if (page.getList().isEmpty()) {
			// page = null;
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#searchDxyjByKey(com.hnzskj.common
	 * .Page, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryResult> searchDxyjByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby) {

		StringBuilder sql = new StringBuilder();
		List<QueryResult> resultList = new ArrayList<QueryResult>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String countSql = "";// 查询总记录数的sql语句
		String newSql = "";// 查询的sql语句
		String mainSql = " caption like '%" + key + "%' or lawContent like '%"
				+ key + "%' ";
		countSql = sql.toString() + "select count(id) from sjzs_dxyj where "
				+ mainSql;
		// 查询总记录数
		totalRecords = Integer.parseInt(this.getSingleValue(countSql, null)
				.toString());
		if (page != null) {// 如果需要分页
			// 先执行分页子句的查询结果
			String tempSql = sql.toString()
					+ "select id from sjzs_dxyj where  " + mainSql
					+ BaseDao.buildOrderBy(orderby) + " limit 0, "
					+ (page.getCurPage() - 1) * page.getMaxResult();
			List<String> idsList = this.getListSingleValue(tempSql, null);
			StringBuffer idBuffer = new StringBuffer();
			if (idsList != null && idsList.size() > 0) {
				for (int i = 0; i < idsList.size(); i++) {
					if (i == 0) {
						idBuffer.append("'" + idsList.get(i) + "'");
					} else {
						idBuffer.append(",'" + idsList.get(i) + "'");
					}
				}
			} else {
				idBuffer.append("''");
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()
					+ "select id as id ,caption as title , lawContent as content ,'Dxyj' as type, writeDate as date from sjzs_dxyj where "
					+ mainSql;
			newSql += " and id not in (" + idBuffer.toString() + ") "
					+ BaseDao.buildOrderBy(orderby) + " limit 0,"
					+ page.getMaxResult();
			resultList = this.query(newSql, QueryResult.class, null);
		} else {// 如果不需要分页
			newSql = sql.toString()
					+ "select id as id ,caption as title , lawContent as content ,'Dxyj' as type,writeDate as date from sjzs_dxyj where  "
					+ mainSql;
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		}

		// 设置总记录数
		page.setList(resultList);
		// 设置结果集
		page.setTotalRecords(totalRecords);
		page = chengColor(page, key);
		if (page.getList().isEmpty()) {
			// page = null;
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#searchFfalByKey(com.hnzskj.common
	 * .Page, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryResult> searchFfalByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby) {
		StringBuilder sql = new StringBuilder();
		List<QueryResult> resultList = new ArrayList<QueryResult>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String countSql = "";// 查询总记录数的sql语句
		String newSql = "";// 查询的sql语句
		String mainSql = " title like '%" + key + "%' or content like '%" + key
				+ "%' ";
		countSql = sql.toString() + "select count(id) from sjzs_ffal where "
				+ mainSql;
		// 查询总记录数
		totalRecords = Integer.parseInt(this.getSingleValue(countSql, null)
				.toString());
		if (page != null) {// 如果需要分页
			// 先执行分页子句的查询结果
			String tempSql = sql.toString()
					+ "select id from sjzs_ffal where  " + mainSql
					+ BaseDao.buildOrderBy(orderby) + " limit 0, "
					+ (page.getCurPage() - 1) * page.getMaxResult();
			List<String> idsList = this.getListSingleValue(tempSql, null);
			StringBuffer idBuffer = new StringBuffer();
			if (idsList != null && idsList.size() > 0) {
				for (int i = 0; i < idsList.size(); i++) {
					if (i == 0) {
						idBuffer.append("'" + idsList.get(i) + "'");
					} else {
						idBuffer.append(",'" + idsList.get(i) + "'");
					}
				}
			} else {
				idBuffer.append("''");
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()
					+ "select id as id ,title as title , content as content ,'Ffal' as type,ffalDateTime as date from sjzs_ffal where "
					+ mainSql;
			newSql += " and id not in (" + idBuffer.toString() + ") "
					+ BaseDao.buildOrderBy(orderby) + " limit 0,"
					+ page.getMaxResult();
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		} else {// 如果不需要分页
			newSql = sql.toString()
					+ "select id as id ,title as title , content as content ,'Ffal' as type,ffalDateTime as date from sjzs_ffal where  "
					+ mainSql;
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		}

		// 设置总记录数
		page.setList(resultList);
		// 设置结果集
		page.setTotalRecords(totalRecords);
		page = chengColor(page, key);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#searchSjdhByKey(com.hnzskj.common
	 * .Page, java.lang.String)
	 */
	@SuppressWarnings( "unchecked")
	@Override
	public Page<QueryResult> searchSjdhByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby) {
		StringBuilder sql = new StringBuilder();
		List<QueryResult> resultList = new ArrayList<QueryResult>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String countSql = "";// 查询总记录数的sql语句
		String newSql = "";// 查询的sql语句
		String mainSql = " name like '%" + key + "%' or context like '%" + key
				+ "%' ";
		countSql = sql.toString() + "select count(id) from sjzs_sjdh where "
				+ mainSql;
		System.out.println(countSql);
		// 查询总记录数
		totalRecords = Integer.parseInt(this.getSingleValue(countSql, null)
				.toString());
		if (page != null) {// 如果需要分页
			// 先执行分页子句的查询结果
			String tempSql = sql.toString()
					+ "select id from sjzs_sjdh where  " + mainSql
					+ BaseDao.buildOrderBy(orderby) + " limit 0, "
					+ (page.getCurPage() - 1) * page.getMaxResult();
			List<String> idsList = this.getListSingleValue(tempSql, null);
			StringBuffer idBuffer = new StringBuffer();
			if (idsList != null && idsList.size() > 0) {
				for (int i = 0; i < idsList.size(); i++) {
					if (i == 0) {
						idBuffer.append("'" + idsList.get(i) + "'");
					} else {
						idBuffer.append(",'" + idsList.get(i) + "'");
					}
				}
			} else {
				idBuffer.append("''");
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()
					+ "select id as id ,name as title , context as content ,'Sjdh' as type,createTime as date from sjzs_sjdh where "
					+ mainSql;
			newSql += " and id not in (" + idBuffer.toString() + ") "
					+ BaseDao.buildOrderBy(orderby) + " limit 0,"
					+ page.getMaxResult();
			// 查询结果集
			System.out.println(newSql);
			resultList = this.query(newSql, QueryResult.class, null);
		} else {// 如果不需要分页
			newSql = sql.toString()
					+ "select id as id ,name as title , context as content ,'Sjdh' as type,createTime as date from sjzs_sjdh where  "
					+ mainSql;
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		}

		// 设置总记录数
		page.setList(resultList);
		// 设置结果集
		page.setTotalRecords(totalRecords);
		page = chengColor(page, key);
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#searchSjfgByKey(com.hnzskj.common
	 * .Page, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<QueryResult> searchSjfgByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby) {
		StringBuilder sql = new StringBuilder();
		List<QueryResult> resultList = new ArrayList<QueryResult>();// 封装查询结果集
		Integer totalRecords = 0;// 记录查询的总记录数
		String countSql = "";// 查询总记录数的sql语句
		String newSql = "";// 查询的sql语句
		String mainSql = " lawName like '%" + key + "%' or lawContent like '%"
				+ key + "%' ";
		countSql = sql.toString() + "select count(lawId) from sjzs_sjfg where "
				+ mainSql;
		// 查询总记录数
		totalRecords = Integer.parseInt(this.getSingleValue(countSql, null)
				.toString());
		if (page != null) {// 如果需要分页
			// 先执行分页子句的查询结果
			String tempSql = sql.toString()
					+ "select lawId from sjzs_sjfg where  " + mainSql
					+ BaseDao.buildOrderBy(orderby) + " limit 0, "
					+ (page.getCurPage() - 1) * page.getMaxResult();
			List<String> idsList = this.getListSingleValue(tempSql, null);
			StringBuffer idBuffer = new StringBuffer();
			if (idsList != null && idsList.size() > 0) {
				for (int i = 0; i < idsList.size(); i++) {
					if (i == 0) {
						idBuffer.append("'" + idsList.get(i) + "'");
					} else {
						idBuffer.append(",'" + idsList.get(i) + "'");
					}
				}
			} else {
				idBuffer.append("''");
			}
			// 如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
			// 如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
			newSql = sql.toString()
					+ "select lawId as id ,lawName as title , lawContent as content ,'Sjfg' as type,lawDate as date from sjzs_sjfg where "
					+ mainSql;
			newSql += " and lawId not in (" + idBuffer.toString() + ")"
					+ BaseDao.buildOrderBy(orderby) + "  limit 0,"
					+ page.getMaxResult();
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		} else {// 如果不需要分页
			newSql = sql.toString()
					+ "select lawId as id ,lawName as title , lawContent as content ,'Sjfg' as type,lawDate as date from sjzs_sjfg where  "
					+ mainSql;
			// 查询结果集
			resultList = this.query(newSql, QueryResult.class, null);
		}

		// 设置总记录数
		page.setList(resultList);
		// 设置结果集
		page.setTotalRecords(totalRecords);
		page = chengColor(page, key);
		return page;
	}

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6下午02:14:45
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> chengColor(Page<QueryResult> page, String key) {
		List<QueryResult> list = page.getList();
		Iterator<QueryResult> iterator = list.iterator();
		while (iterator.hasNext()) {
			QueryResult result = iterator.next();
			String content = result.getContent();
			
			String newContent = "";
			if (content != null) {
				if (content.length() > 150) {
					int first = content.indexOf(key);
					if (first > 150) {
						int end=first+75;
						if( end>content.length() ){
							end=content.length();
						}
						newContent = content.substring(first - 75, end);//
					} else {
						newContent = content.substring(0, 140);
					}
				} else {
					newContent = content;
				}
			}

			String newDate = "";
			if (result.getDate() != null) {
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-DD");
				try {
					newDate =sFormat.format( sFormat.parse(result.getDate()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			result.setContent(newContent);
			result.setDate(newDate);
		}
		return page;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#getDxyjLaw(com.hnzskj.persist.bean
	 * .sjzs.DxyjLaw)
	 */
	@Override
	public DxyjLaw getDxyjLaw(DxyjLaw dxyjLaw) {
		String dxyjId = dxyjLaw.getId();
		if (dxyjId == null) {
			return null;
		} else {
			return dingXingYiJuDao.getLawById(dxyjId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#getFfal(com.hnzskj.persist.bean
	 * .sjzs.FangFaAnLi)
	 */
	@Override
	public FangFaAnLi getFfal(FangFaAnLi fangFaAnLi) {
		String ffalId = fangFaAnLi.getId();
		if (ffalId == null) {
			return null;
		} else {
			return fangFaAnLiDao.getFfalById(ffalId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#getSjdh(com.hnzskj.persist.bean
	 * .sjzs.SjdhMethod)
	 */
	@Override
	public SjdhMethod getSjdh(SjdhMethod sjdh) {
		String sjdhId = sjdh.getId();
		if (sjdhId == null) {
			return null;
		} else {
			return sjdhMethodDao.findById(sjdhId);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.hnzskj.persist.dao.fore.SearchDao#getSjfgLaw(com.hnzskj.persist.bean
	 * .sjzs.Law)
	 */
	@Override
	public Law getSjfgLaw(Law law) {
		String sjfgId = law.getLawId();
		if (sjfgId == null) {
			return null;
		} else {
			return shenJiFaGuiDao.getLawById(sjfgId);
		}
	}

	@Override
	public DataDicDTO getSjsx(DataDicDTO sjsxMethod) {
		String sjsxId = sjsxMethod.getDicId();
		if(sjsxId == null){
			return null;
		}else{
			return dataDicDao.getDataDicById(sjsxId);
		}
	}

	@Override
	public ShiShiFangAnDTO getSsfa(ShiShiFangAnDTO ssfaMethod) {
		String ssfaId = ssfaMethod.getId();
		if(ssfaId == null){
			return null;
		}else{
			return shiShiFangAnDao.getSSFAById(ssfaId);
		}
	}
}
