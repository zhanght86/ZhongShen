/*
 * @项目名称: OA
 * @文件名称: HolidayFlowDao.java
 * @日期: 2012-7-12
 * @版权: 2012 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.persist.dao.sjzs.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.dao.sjzs.SjdhMethodDao;

  /**        
 * 
 * 类名称：HolidayFlowDao
 * 类描述：<br/>
 * 创建人：郑辉  <br/>
 * 创建时间：2012-7-12 下午04:26:06 
 * 修改人：Administrator
 * 修改时间：
 * 修改备注：   
 * @version   1.0     
 */

public class SjdhMethodDaoImpl extends BaseDao implements SjdhMethodDao{

	@Override
	public int addInfo(SjdhMethod sjdh) {
		// TODO Auto-generated method stub
		String uuid =this.getGUID();
		String sql ="insert into sjzs_sjdh (id,name,typeNo,template_no,createTime,orderNum,context,updatedate,deleteflag,note1,note2 ) values (?,?,?,?,?,?,?,?,?,?,?)";
		Object [] params ={
				uuid,
				sjdh.getName(),
				sjdh.getTypeNo(),
				sjdh.getTemplate_no(),
				sjdh.getCreateTime(),
				sjdh.getOrderNum(),
				sjdh.getContext(),
				new Timestamp(System.currentTimeMillis()),
				0,
				sjdh.getNote1(),
				sjdh.getNote2()
		};
		int result = this.update(sql, params);
		return result;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		id=id.substring(0,id.length()-1);
//		String sql ="delete from sjzs_sjdh where id in ("+id+")";
		String sql ="update sjzs_sjdh set deleteflag = -1 ,updatedate=? where id in ("+id+")";
		Object[] params = new Object[]{new Timestamp(System.currentTimeMillis())};
		return this.update(sql, params);
	}

	@Override
	public int update(SjdhMethod sjdh) {
		// TODO Auto-generated method stub
		String sql ="update sjzs_sjdh set name = ? , orderNum=?,context=?,updatedate =?,deleteflag =? ,note1 =? ,note2 =? where id = ?";
		Object [] params = {sjdh.getName(),sjdh.getOrderNum(),sjdh.getContext(),new Timestamp(System.currentTimeMillis()),1,sjdh.getNote1(),
				sjdh.getNote2(),sjdh.getId()};
		return this.update(sql, params);
	}
	
	public int updateTempNO(String Id,int template_no){
		String sql ="update sjzs_sjdh set template_no=?,updatedate=?,deleteflag =? where id = ?";
		Object [] params ={template_no,	new Timestamp(System.currentTimeMillis()),1,Id};
		return this.update(sql, params);
	}

	@Override
	public Page<SjdhMethod> searchSjdhMethodParentId(Page<SjdhMethod> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderBy) {
		// TODO Auto-generated method stub
		
//		List<SjdhMethod> empls = new ArrayList<SjdhMethod>();//封装查询结果集
//		Integer totalRecords = 0;//记录查询的总记录数
//		String sql = "";//查询的sql语句
//		String countSql = "";//查询总记录数的sql语句
//		Page<SjdhMethod> epage = new Page<SjdhMethod>();//临时变量，如果在page为null的情况下使用
//		
//		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
//		countSql = "select count(id) from sjzs_sjdh " + sqlCondition + " and deleteflag in (0,1) ";
//		//查询总记录数
//		totalRecords = Integer.valueOf(getSingleValue(countSql, params).toString());
//		
//		if ( page != null) {//如果需要分页
//			sql = "select " + fields + "  from sjzs_sjdh " +sqlCondition + " and deleteflag in (0,1) " + BaseDao.buildOrderBy(orderBy) +
//				"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
//			
//		
//			epage = page;
//			
//			empls = query(sql, SjdhMethod.class, params);
//		} else {//如果不需要分页
//			sql = "select " +fields + " from sjzs_sjdh " + sqlCondition + " and deleteflag in (0,1) " + buildOrderBy(orderBy);
//			//查询结果集
//			empls = query(sql, SjdhMethod.class, params);
//		}
//		//设置总记录数
//		epage.setList(empls);
//		//设置结果集
//		epage.setTotalRecords(totalRecords);
//
//		return epage;
		
//		StringBuilder sql = new StringBuilder();
//		List<SjdhMethod> laws = new ArrayList<SjdhMethod>();//封装查询结果集
//		Integer totalRecords = 0;//记录查询的总记录数
//		String countSql = "";//查询总记录数的sql语句
//		String newSql = "";//查询的sql语句
//		Page<SjdhMethod> epage = new Page<SjdhMethod>();//临时变量，如果在page为null的情况下使用
//		sqlCondition =  ("".equals(sqlCondition) || null == sqlCondition) ? " " : sqlCondition;
//		countSql =sql.toString()+ "select count(id) from sjzs_sjdh where deleteflag in (0,1) and FIND_IN_SET(typeNo, getChildList(?)) " + sqlCondition;
//		//查询总记录数
//		totalRecords = Integer.parseInt( this.getSingleValue(countSql, params).toString());
//		if ( page != null) {//如果需要分页
//			//先执行分页子句的查询结果
//			String tempSql = sql.toString()+"select id from sjzs_sjdh where deleteflag in (0,1) and FIND_IN_SET(typeNo, getChildList(?)) " + sqlCondition +BaseDao.buildOrderBy(orderBy)+" limit 0,"+ (page.getCurPage() - 1) * page.getMaxResult() ;
//			List<String> idsList = this.getListSingleValue(tempSql, params);
//			StringBuffer idBuffer = new StringBuffer();
//			if(idsList!=null&&idsList.size()>0){
//				for(int i =0;i<idsList.size();i++){
//					if(i==0){
//						idBuffer.append("'"+idsList.get(i)+"'");
//					}else{
//						idBuffer.append(",'"+idsList.get(i)+"'");
//					}
//				}
//			}
//			else{
//				idBuffer.append("''");
//			}
//			//如果不执行模糊查询则构建where语句使用DBUtil.buildOrderBy（）的方法
//			//如果执行模糊查询则构建where语句使用PolicyDao的buildSQLWhereFuzzy（）方法
//			newSql = sql.toString()+ "select * from sjzs_sjdh where deleteflag in (0,1) and FIND_IN_SET(typeNo, getChildList(?)) ";
//				newSql += " and id not in ("+idBuffer.toString()+")" + sqlCondition + BaseDao.buildOrderBy(orderBy) +"  limit 0," + page.getMaxResult() ;
//			epage = page;
//			//查询结果集
//			laws =this.query(newSql, SjdhMethod.class, params);
//		} else {//如果不需要分页
//			newSql = sql.toString()+"select * from sjzs_sjdh where deleteflag in (0,1) and FIND_IN_SET(typeNo, getChildList(?)) "+ sqlCondition + BaseDao.buildOrderBy(orderBy);
//			//查询结果集
//			laws = this.query(newSql, SjdhMethod.class, params);
//		}
//		
//		//设置总记录数
//		epage.setList(laws);
//		//设置结果集
//		epage.setTotalRecords(totalRecords);
//		return epage;
		
		List<SjdhMethod> sjdhMethod = new ArrayList<SjdhMethod>();//封装结果集
		Page<SjdhMethod> ppage = new Page<SjdhMethod>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(params[0].toString());
		sqlCondition = ("".equals(sqlCondition) || null == sqlCondition) ? "" : sqlCondition;
		countSql = "select count(id) from sjzs_sjdh " + sqlCondition +" and FIND_IN_SET(typeNo, '" + childListStr + "') and deleteflag in (0,1) ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_sjdh " +sqlCondition +" and FIND_IN_SET(typeNo, '" + childListStr + "')  and deleteflag in (0,1) "+ BaseDao.buildOrderBy(orderBy)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
		
			ppage = page;			
			sjdhMethod = query(sql, SjdhMethod.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_sjdh " + sqlCondition +" and FIND_IN_SET(typeNo, '" + childListStr + "')  and deleteflag in (0,1) "+ buildOrderBy(orderBy);
			sjdhMethod = query(sql, SjdhMethod.class, null);
		}
		
		ppage.setTotalRecords(totalRecords);
		ppage.setList(sjdhMethod);		
		return ppage;
	}
	
	public SjdhMethod findById(String Id){
		String sql ="select * from sjzs_sjdh where id =?";
		Object [] params = {Id};
		return (SjdhMethod)this.get(sql, SjdhMethod.class, params);
	}

	@Override
	public int updateSJDHType(String id, String workFlowId) {
		String sql ="update sjzs_sjdh set template_no=?,updatedate=?,deleteflag=? where id = ?";
		Object [] params ={workFlowId,	new Timestamp(System.currentTimeMillis()),1,id};
		return this.update(sql, params);
	}
	
}