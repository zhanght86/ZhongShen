package com.hnzskj.service.fore;

import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ContentMap;

public interface ShowInfoService {
	public List<ContentMap> getContentMaps();

	public ContentMap getContentSjfgByField(String field, int num);

	public ContentMap getContentDxyjByField(String field, int num);

	public ContentMap getContentFfalByField(String field, int num);

	public ContentMap getContentSjdhByField(String field, int num);
	
	public ContentMap getContentSjsxByField(String field, int num);
	
	public ContentMap getContentSsfaByField(String field, int num);
	
	/**
	 * 
	 * 方法描述: 查询各项最新的n条数据<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-23 上午09:39:09
	 * @param
	 *
	 */
	@SuppressWarnings("unchecked")
	public Page getLatestMethods(String type, Object entity, Page page);
}
