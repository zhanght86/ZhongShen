package com.hnzskj.persist.dao.fore.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadAttachDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ClientUploadDXYJDTO;
import com.hnzskj.persist.bean.fore.ClientUploadFFALDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSJFGDTO;
import com.hnzskj.persist.bean.fore.ClientUploadSSFNDTO;
import com.hnzskj.persist.dao.fore.ClientUploadDao;


public class ClientUploadDaoImpl extends BaseDao implements ClientUploadDao {

	@Override
	public boolean addUpload(ClientUploadDTO clientUpload) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into ").append(this.getTableName(clientUpload.getType())).append("( ")
		   .append(" id, caption, content, parentId, checkFlag,")
		   .append(" uploadDate, integral, type, attachId, clientId,")
		   .append(" clientName, isOpen, curCheckUserId, curCheckUserName, isTransport");
		String other = this.buildInsertSql(clientUpload.getType());
		sql.append(other).append(")");
		sql.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
		String[] temp = other.split(",");
		for(int i=0;i<temp.length-1;i++){
			sql.append(",?");
		}
		sql.append(")");
		List<Object> params = new ArrayList<Object>();
		params.add(this.getGUID());
		params.add(clientUpload.getCaption());
		params.add(clientUpload.getContent());
		params.add(clientUpload.getParentId());
		params.add(clientUpload.getCheckFlag());
		params.add(new Timestamp(System.currentTimeMillis()));
		params.add(clientUpload.getIntegral());
		params.add(clientUpload.getType());
		params.add(clientUpload.getAttachId());
		params.add(clientUpload.getClientId());
		params.add(clientUpload.getClientName());
		params.add(clientUpload.getIsOpen());
		params.add(clientUpload.getCurCheckUserId());
		params.add(clientUpload.getCurCheckUserName());
		params.add(clientUpload.getIsTransport());
		addParams(clientUpload,params);
		return this.update(sql.toString(), params.toArray())>0;
	}
	
	/**
	 * 
	 * 方法描述: 拼接插入时各个类的SQL语句<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午02:17:19
	 * @param
	 *
	 */
	private String buildInsertSql(String type){
		String sql = ",";
		if(Constant.DXYJ.equals(type)){
			sql += " lawNo, tiao, kuan ";
		}else if(Constant.FFAL.equals(type)){
			sql += " author, department, ffalDateTime, awards ";
		}else if(Constant.SJFG.equals(type)){
			sql += " lawNumber, lawOrg, lawDate, lawTrade, lawCategory ";
		}else if(Constant.SJSS.equals(type)){
			sql += " writedate, industry, keyword, author ";
		}
		return sql;
	}

	@Override
	public boolean deleteUpload(ClientUploadDTO clientUpload) {
		String table= getTableName(clientUpload.getType());
		String sql ="delete "+table+", sjzs_clientuploadattach from "+table+", sjzs_clientuploadattach "
		+ " where "+table+".attachId = sjzs_clientuploadattach.attachId and "+table+".id = ?";
		
		System.out.println(sql);
		Object[] params = new Object[]{clientUpload.getId()};
		int value = this.update(sql, params);
		if(value  >0){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public List<ClientUploadDTO> selectUploadByClient(String ClientId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientUploadDTO selectUploadById(String uploadId, String type) {
		String sql = "select * from " + this.getTableName(type) + " where id = '" + uploadId + "'";
		if(Constant.DXYJ.equals(type)){
			return (ClientUploadDXYJDTO)this.get(sql, ClientUploadDXYJDTO.class, null);
		}else if(Constant.FFAL.equals(type)){
			return (ClientUploadFFALDTO)this.get(sql, ClientUploadFFALDTO.class, null);
		}else if(Constant.SJFG.equals(type)){
			return (ClientUploadSJFGDTO)this.get(sql, ClientUploadSJFGDTO.class, null);
		}else if(Constant.SJSS.equals(type)){
			return (ClientUploadSSFNDTO)this.get(sql, ClientUploadSSFNDTO.class, null);
		}
		return null;
	}

	@Override
	public boolean updateUpload(ClientUploadDTO clientUpload) {
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(getTableName(clientUpload.getType())).append(" set ")
		   .append("caption = ?, content = ?, parentId = ?, checkFlag = ?, integral = ?, ")
		   .append("type = ?, attachId = ?, clientId = ?, clientName = ?, isOpen = ?, curCheckUserId = ?, ")
		   .append("curCheckUserName = ?, isTransport = ? ").append(buildUpdateSql(clientUpload.getType()))
		   .append("where id = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(clientUpload.getCaption());
		params.add(clientUpload.getContent());
		params.add(clientUpload.getParentId());
		params.add(clientUpload.getCheckFlag());
//		params.add(clientUpload.getUploadDate());
		params.add(clientUpload.getIntegral());
		params.add(clientUpload.getType());
		params.add(clientUpload.getAttachId());
		params.add(clientUpload.getClientId());
		params.add(clientUpload.getClientName());
		params.add(clientUpload.getIsOpen());
		params.add(clientUpload.getCurCheckUserId());
		params.add(clientUpload.getCurCheckUserName());
		params.add(clientUpload.getIsTransport());
		addParams(clientUpload,params);
		params.add(clientUpload.getId());
		return this.update(sql.toString(), params.toArray())>0;
	}
	
	/**
	 * 
	 * 方法描述: 拼接更新时各个类的SQL语句<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午02:18:20
	 * @param
	 *
	 */
	private String buildUpdateSql(String type){
		String sql = ",";
		if(Constant.DXYJ.equals(type)){
			sql += " lawNo = ?, tiao = ?, kuan = ? ";
		}else if(Constant.FFAL.equals(type)){
			sql += " author = ?, department = ?, ffalDateTime = ?, awards = ? ";
		}else if(Constant.SJFG.equals(type)){
			sql += " lawNumber = ?, lawOrg = ?, lawDate = ?, lawTrade = ?, lawCategory = ? ";
		}else if(Constant.SJSS.equals(type)){
			sql += " writedate = ?, industry = ?, keyword = ?, author = ? ";
		}
		return sql;
	}
	
	/**
	 * 
	 * 方法描述: 为每个类特有属性添加对就的参数<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-27 下午02:18:55
	 * @param
	 *
	 */
	private List<Object> addParams(ClientUploadDTO clientUpload, List<Object> params){
		String type = clientUpload.getType();
		if(Constant.DXYJ.equals(type)){
			ClientUploadDXYJDTO dxyj = (ClientUploadDXYJDTO)clientUpload;
			params.add(dxyj.getLawNo());
			params.add(dxyj.getTiao());
			params.add(dxyj.getKuan());
		}else if(Constant.FFAL.equals(type)){
			ClientUploadFFALDTO ffal = (ClientUploadFFALDTO)clientUpload;
			params.add(ffal.getAuthor());
			params.add(ffal.getDepartment());
			params.add(ffal.getFfalDateTime());
			params.add(ffal.getAwards());
		}else if(Constant.SJFG.equals(type)){
			ClientUploadSJFGDTO sjfg = (ClientUploadSJFGDTO)clientUpload;
			params.add(sjfg.getLawNumber());
			params.add(sjfg.getLawOrg());
			params.add(sjfg.getLawDate());
			params.add(sjfg.getLawTrade());
			params.add(sjfg.getLawCategory());
		}else if(Constant.SJSS.equals(type)){
			ClientUploadSSFNDTO ssfn = (ClientUploadSSFNDTO)clientUpload;
			params.add(ssfn.getWriteDate());
			params.add(ssfn.getIndustry());
			params.add(ssfn.getKeyWord());
			params.add(ssfn.getAuthor());
		}
		return params;
	}
	
	/**
	 * 
	 * 方法描述: 根据类型获得相应的表名<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-26 下午02:12:48
	 * @param
	 *
	 */
	private String getTableName(String type){
		String tableName = "";
		if(Constant.DXYJ.equals(type)){
			tableName = "sjzs_clientupload_dxyj";
		}else if(Constant.FFAL.equals(type)){
			tableName = "sjzs_clientupload_ffal";
		}else if(Constant.SJFG.equals(type)){
			tableName = "sjzs_clientupload_sjfg";
		}else {
			tableName = "sjzs_clientupload_ssfn";
		}
		return tableName;
	}
	
	/**
	 * 
	 * 方法描述: 查询总记录数<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-26 下午02:13:18
	 * @param
	 *
	 */
	private Integer searchCount(String type, ClientUploadDTO clientUpload){
		StringBuffer sqlCountStr = new StringBuffer("select sum(tid) from (");
		if(type == null || "".equals(type) || "ALL".equals(type)){
			sqlCountStr.append(" (select count(id) as tid from sjzs_clientupload_dxyj").append(buildCondition(clientUpload)).append(")");
			sqlCountStr.append(" union all ").append("(select count(id) as tid from sjzs_clientupload_ffal").append(buildCondition(clientUpload)).append(")");
			sqlCountStr.append(" union all ").append("(select count(id) as tid from sjzs_clientupload_sjfg").append(buildCondition(clientUpload)).append(")");
			sqlCountStr.append(" union all ").append("(select count(id) as tid from sjzs_clientupload_ssfn").append(buildCondition(clientUpload)).append(")");
		}else{
			sqlCountStr.append(" select count(id) as tid from ").append(getTableName(type)).append(buildCondition(clientUpload));
		}
		sqlCountStr.append(") as temp ");
		return Integer.valueOf(getSingleValue(sqlCountStr.toString(), null).toString());
	}
	
	/**
	 * 
	 * 方法描述: 拼接查询条件语句<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-26 下午02:21:39
	 * @param
	 *
	 */
	private String buildCondition(ClientUploadDTO clientUpload){
		StringBuffer sqlCondition = new StringBuffer(" where 1 = 1");
		if(clientUpload.getCaption()!=null&&!"".equals(clientUpload.getCaption())){
			sqlCondition.append(" and caption like '%").append(clientUpload.getCaption()).append("%'");
		}
		if(Constant.CHECK_ALL!=clientUpload.getCheckFlag()){
			sqlCondition.append(" and checkFlag = ").append(clientUpload.getCheckFlag());
		}
		String beginDate = clientUpload.getBeginDate();
		String endDate = clientUpload.getEndDate();
		if(beginDate!=null&&!"".equals(beginDate)&&endDate!=null&&!"".equals(endDate)){
			sqlCondition.append(" and uploaddate between ").append(beginDate).append(" and ").append(endDate);
		}else if(beginDate!=null&&!"".equals(beginDate)&&(endDate==null||"".equals(endDate))){
			sqlCondition.append(" and uploaddate > '").append(beginDate).append("'");
		}else if((beginDate==null||"".equals(beginDate))&&endDate!=null&&!"".equals(endDate)){
			sqlCondition.append(" and uploaddate < '").append(endDate).append("'");
		}
		if(clientUpload.getClientId()!=null&&!"".equals(clientUpload.getClientId())){
			sqlCondition.append(" and clientId = '").append(clientUpload.getClientId()).append("'");
		}
		if(clientUpload.getCurCheckUserId()!=null&&!"".equals(clientUpload.getCurCheckUserId())&&!"ALL".equals(clientUpload.getCurCheckUserId())){
			sqlCondition.append(" and curCheckUserId = '").append(clientUpload.getCurCheckUserId()).append("'");
		}
		if(clientUpload.getParentId()!=null&&!"".equals(clientUpload.getParentId())){
			String childListStr = this.getChileList(clientUpload.getParentId());
			sqlCondition.append(" and FIND_IN_SET(parentId, '" + childListStr + "')");
		}
		return sqlCondition.toString();
	}

	/**
	 * 
	 * 方法描述: 拼接主查询语句<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-26 下午02:22:20
	 * @param
	 *
	 */
	private String buildMianSql(String fields, String type, Page<ClientUploadDTO> page,ClientUploadDTO clientUpload, LinkedHashMap<String, String> orderby){
		String newFileds = "";
		if("*".equals(fields.trim())){
			newFileds += " temp.*";
		}else{
			String[] temp = fields.split(",");
			for(int i=0;i<temp.length;i++){
				newFileds += " temp." + temp[i] + ",";
			}
		}
		newFileds = newFileds.substring(0,newFileds.length()-1);
		StringBuffer sqlSearch = new StringBuffer("select ").append(newFileds).append(" from (");
		if(type == null || "".equals(type) || "ALL".equals(type)){
			sqlSearch.append(" (select ").append(fields).append(" from sjzs_clientupload_dxyj").append(buildCondition(clientUpload)).append(")");
			sqlSearch.append(" union all ").append(" (select ").append(fields).append(" from sjzs_clientupload_ffal").append(buildCondition(clientUpload)).append(")");
			sqlSearch.append(" union all ").append(" (select ").append(fields).append(" from sjzs_clientupload_sjfg").append(buildCondition(clientUpload)).append(")");
			sqlSearch.append(" union all ").append(" (select ").append(fields).append(" from sjzs_clientupload_ssfn").append(buildCondition(clientUpload)).append(")");
		}else{
			sqlSearch.append(" select ").append(fields).append(" from ").append(getTableName(type)).append(buildCondition(clientUpload));
		}
		sqlSearch.append(") as temp ").append(" order by temp.uploaddate desc ");
		if(page!=null){
			sqlSearch.append(" limit " + (page.getCurPage()-1)*page.getMaxResult() + "," + page.getMaxResult()) ;
		}
		return sqlSearch.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ClientUploadDTO> searchByCondition(Page<ClientUploadDTO> page,
			String fields, ClientUploadDTO clientUpload, LinkedHashMap<String, String> orderby) {
		List<ClientUploadDTO> clientUploadList = new ArrayList<ClientUploadDTO>();//封装结果集
		Page<ClientUploadDTO> ppage = new Page<ClientUploadDTO>();//如果page为null时使用
		String type = clientUpload.getType();
		
		Integer totalRecords = searchCount(type,clientUpload);//查询的总记录数
		
		//查询的sql语句
		String sql = buildMianSql(fields,type,page,clientUpload,orderby);//查询的sql语句
		System.out.println("~~~~~~~~" + sql);
		clientUploadList = query(sql, ClientUploadDTO.class, null);
		
//		if ( page != null ) {//如果是分页查询
//			sql = "select " + fields + " from "+ "" + "" + BaseDao.buildOrderBy(orderby)+
//			"limit "+(page.getCurPage() - 1) * page.getMaxResult() + "," + page.getMaxResult() ;
//		
//			ppage = page;			
//			clientUploadList = query(sql, ClientUploadDTO.class, null);
//		} else {//如果不需要分页查询 
//			sql = "select " + fields + " from sjzs_clientupload " + "" + buildOrderBy(orderby);
//			clientUploadList = query(sql, ClientUploadDTO.class, null);
//		}
		ppage = page;
		ppage.setTotalRecords(totalRecords);
		ppage.setList(clientUploadList);		
		return ppage;
	}

	@Override
	public int changeCheckFlag(ClientUploadDTO clientUpload) {
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(getTableName(clientUpload.getType())).append(" set checkflag = ")
		   .append(clientUpload.getCheckFlag()).append(" where id = ").append(clientUpload.getId());
		return this.update(sql.toString(), null);
	}

	@Override
	public String getCurCheckUser(ClientUploadDTO clientUpload) {
		StringBuffer sql = new StringBuffer();
		sql.append("select curCheckUserId ").append(getTableName(clientUpload.getType())).append(" where id = ")
		   .append(clientUpload.getId());
		return (String)this.getSingleValue(sql.toString(), null);
	}


	@SuppressWarnings("unchecked")
	@Override
	public Page<ClientUploadFFALDTO> searchFFAN(Page<ClientUploadFFALDTO> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderby) {
		List<ClientUploadFFALDTO> clientUpload = new ArrayList<ClientUploadFFALDTO>();//封装结果集
		Page<ClientUploadFFALDTO> ppage = new Page<ClientUploadFFALDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(params[0].toString());
		countSql = "select count(id) from sjzs_clientupload_ffal " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_clientupload_ffal " +sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
			ppage = page;			
			clientUpload = query(sql, ClientUploadFFALDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_clientupload_ffal " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ buildOrderBy(orderby);
			clientUpload = query(sql, ClientUploadFFALDTO.class, null);
		}
		ppage.setTotalRecords(totalRecords);
		ppage.setList(clientUpload);
		return ppage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ClientUploadSJFGDTO> searchSJFG(Page<ClientUploadSJFGDTO> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderby) {
		List<ClientUploadSJFGDTO> clientUpload = new ArrayList<ClientUploadSJFGDTO>();//封装结果集
		Page<ClientUploadSJFGDTO> ppage = new Page<ClientUploadSJFGDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(params[0].toString());
		countSql = "select count(id) from sjzs_clientupload_sjfg " + sqlCondition + " and FIND_IN_SET(parentId, '" + childListStr + "') ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_clientupload_sjfg " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+"," + page.getMaxResult() ;
			ppage = page;			
			clientUpload = query(sql, ClientUploadSJFGDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_clientupload_sjfg " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ buildOrderBy(orderby);
			clientUpload = query(sql, ClientUploadSJFGDTO.class, null);
		}
		ppage.setTotalRecords(totalRecords);
		ppage.setList(clientUpload);
		return ppage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<ClientUploadSSFNDTO> searchSSFN(Page<ClientUploadSSFNDTO> page,
			String fields, String sqlCondition, Object[] params,
			LinkedHashMap<String, String> orderby) {
		List<ClientUploadSSFNDTO> clientUpload = new ArrayList<ClientUploadSSFNDTO>();//封装结果集
		Page<ClientUploadSSFNDTO> ppage = new Page<ClientUploadSSFNDTO>();//如果page为null时使用
		Integer totalRecords = 0;//查询的总记录数
		String sql = "";//查询的sql语句
		String countSql = "";//查询总记录数的sql语句
		String childListStr = this.getChileList(params[0].toString());
		countSql = "select count(id) from sjzs_clientupload_ssfn " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') ";
		totalRecords = Integer.valueOf(getSingleValue(countSql, null).toString());
		if ( page != null ) {//如果是分页查询
			sql = "select " + fields + " from sjzs_clientupload_ssfn " +sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ BaseDao.buildOrderBy(orderby)+
			"limit "+(page.getCurPage() - 1) * page.getMaxResult()+","+page.getMaxResult() ;
			ppage = page;			
			clientUpload = query(sql, ClientUploadSSFNDTO.class, null);
		} else {//如果不需要分页查询
			sql = "select " + fields + " from sjzs_clientupload_ssfn " + sqlCondition +" and FIND_IN_SET(parentId, '" + childListStr + "') "+ buildOrderBy(orderby);
			clientUpload = query(sql, ClientUploadSSFNDTO.class, null);
		}
		ppage.setTotalRecords(totalRecords);
		ppage.setList(clientUpload);
		return ppage;
	}


	@Override
	public int updateClientUpNum(String clientId) {
		String sql ="update sjzs_client_info set uploadNum=uploadNum+1 where clientId=? ";
		Object[] params = new Object[]{clientId};
		return this.update(sql, params);
	}
	
	@Override
	public ClientUploadAttachDTO findAttachs(String attachId) {
		String sql = "select attachId ,attachName,attachContentDoc,attachContentSwf,uploadDate from sjzs_clientuploadattach where  attachId = '"+attachId+"'";
		conn = getConnection();
		pstmt = null;
		rs = null;
		ClientUploadAttachDTO attach = null;
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				attach = new ClientUploadAttachDTO();
				attach.setAttachId(rs.getString("attachId"));
				attach.setAttachName(rs.getString("attachName"));
//				attach.setAttachType(rs.getString("attachType"));
				attach.setAttachContentDoc(rs.getBinaryStream("attachContentDoc"));
				attach.setAttachContentSwf(rs.getBinaryStream("attachContentSwf"));
				attach.setUploadDate(rs.getDate("uploadDate").toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			close(rs, pstmt, conn);
		}
		return attach;
	}

	@Override
	public void downNatlByCheck(String clientId) {
		String sql ="update sjzs_client_info set integral= integral-1 where clientId=?　";
		Object[] params = new Object[]{clientId};
		this.update(sql, params);
	}

	@Override
	public boolean batchMoveData(String ids, String type) {
		String tableName = "";
		String newIds = "";
		String[] temp = ids.split(",");
		for(int i=0;i<temp.length;i++){
			newIds += "'" + temp[i] + "',";
		}
		newIds = newIds.substring(0,newIds.length()-1);
		String sql = "";
		if(Constant.DXYJ.equals(type)){
			tableName = "sjzs_clientupload_dxyj";
		}else if(Constant.FFAL.equals(type)){
			tableName = "sjzs_clientupload_ffal";  //,updatedate,deleteflag,note1,note2
//			sql = "insert into sjzs_ffal (id,title,author,department,ffalDateTime,awards,attachId,sort,content)";
//			sql += 				"select id,caption,author,department,ffalDateTime,awards,attachId,parentId,content from sjzs_clientupload_ffal where id in(" + newIds + ")";
			sql = "select * from sjzs_clientupload_ffal where id in( " + newIds + " )";
			List<ClientUploadFFALDTO> ffals = this.query(sql, ClientUploadFFALDTO.class, null);
			for(ClientUploadFFALDTO ffal : ffals){
				sql = "insert into sjzs_ffal (id,title,author,department,ffalDateTime,awards,content,attachId,sort,updatedate,deleteflag,uploadFlag)";
			}
		}else if(Constant.SJFG.equals(type)){
			tableName = "sjzs_clientupload_sjfg";
		}else {
			tableName = "sjzs_clientupload_ssfn";
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientUploadFFALDTO> selectFFALUploads(String ids) {
		String sql = "select * from sjzs_clientupload_ffal where id in (" + this.buildIds(ids) + ")";
		return this.query(sql, ClientUploadFFALDTO.class, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientUploadSJFGDTO> selectSJFGUploads(String ids) {
		String sql = "select * from sjzs_clientupload_sjfg where id in (" + this.buildIds(ids) + ")";
		return this.query(sql, ClientUploadSJFGDTO.class, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientUploadSSFNDTO> selectSSFNUploads(String ids) {
		String sql = "select * from sjzs_clientupload_ssfn where id in (" + this.buildIds(ids) + ")";
		return this.query(sql, ClientUploadSSFNDTO.class, null);
	}

	private String buildIds(String ids){
		String newIds = "";
		if(ids!=null&&!"".equals(ids)){
			String[] temp = ids.split(",");
			for(int i=0;i<temp.length;i++){
				newIds += "'" + temp[i] + "', ";
			}
			newIds = newIds.substring(0,newIds.length()-2);
		}
		System.out.println("~~~~~newIds:" + newIds);
		return newIds;
	}

	@Override
	public int updateTransportFlag(String ids, Integer flag, String type) {
		String sql = "update " + this.getTableName(type) + " set isTransport = " + flag + " where id in (" + this.buildIds(ids) + ")";
		return this.update(sql, null);
	}
}
