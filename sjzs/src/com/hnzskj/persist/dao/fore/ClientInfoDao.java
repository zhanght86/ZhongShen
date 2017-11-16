package com.hnzskj.persist.dao.fore;

public interface ClientInfoDao {
	
	public int selectEmplInteg(String emplId);
	
	public boolean downClientInfo(String emplId ,int integel ,String clientId);
	
	
}
