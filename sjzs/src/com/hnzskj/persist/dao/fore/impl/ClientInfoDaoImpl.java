package com.hnzskj.persist.dao.fore.impl;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.dao.fore.ClientInfoDao;

public class ClientInfoDaoImpl extends BaseDao implements ClientInfoDao {

	@Override
	public boolean downClientInfo(String emplId, int integel,String clientId) {
		String sql="update sjzs_client_info set integral =integral-"+integel+" , downNum= downNum+1  where clientId=? ";
		Object[] params = new Object[]{emplId};
		int value = this.update(sql, params);
		if(clientId != null){
			String sql2 ="update sjzs_client_info set integral = integral+"+integel+"  where clientId=? ";
			Object[] params2 = new Object[]{clientId};
			this.update(sql2, params2);
		}
		
		if(value>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int selectEmplInteg(String emplId) {
		String sql ="select integral from sjzs_client_info where clientId=? ";
		Object[] params =new Object[]{emplId};
		int value = Integer.parseInt(this.getSingleValue(sql, params).toString());
		return value;
	}

}
