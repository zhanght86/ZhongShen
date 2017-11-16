package com.hnzskj.persist.dao.fore.impl;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.BaseDao;
import com.hnzskj.persist.bean.fore.ClientInfoDTO;
import com.hnzskj.persist.bean.fore.SimpleMenuDTO;
import com.hnzskj.persist.dao.fore.WdzsDao;

public class WdzsDaoImpl extends BaseDao implements WdzsDao {

	@Override
	public List<SimpleMenuDTO> initMenu() {
		String sql="select menuId,menuName from sjzs_menutree where menuParent='00000000' and menuId<>'44444444' and menuId<>'22222222' and menuId<>'55555555'";
		List<Object> listObj = this.getList(sql, SimpleMenuDTO.class, null);
		List<SimpleMenuDTO> listMenu = new ArrayList<SimpleMenuDTO>();
		for(Object obj:listObj){
			listMenu.add((SimpleMenuDTO)obj);
		}
		if(listMenu.size() >0){
			return listMenu;
		}else{
			return null;
		}
	}

	@Override
	public List<SimpleMenuDTO> getMenuByParent(String menuParentId) {
		String sql = "select menuId,menuName from sjzs_menutree where menuParent=? ";
		Object[] params = new Object[]{menuParentId};
		List<Object> listObj = this.getList(sql, SimpleMenuDTO.class, params);
		List<SimpleMenuDTO> listMenu = new ArrayList<SimpleMenuDTO>();
		for(Object obj:listObj){
			listMenu.add((SimpleMenuDTO)obj);
		}
		if(listMenu.size() >0){
			return listMenu;
		}else{
			return null;
		}
	}

	@Override
	public ClientInfoDTO getClientById(String clientId) {
		String sql ="select id,clientId,integral ,uploadNum,downNum,title,rank,headIcon,note1,note2,note3,note4,note5 from sjzs_client_info where clientId=? ";
		Object[] params=new Object[]{clientId};
		return (ClientInfoDTO)this.get(sql, ClientInfoDTO.class, params);
	}

}
