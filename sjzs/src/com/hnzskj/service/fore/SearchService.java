/*@目名称：lwsj
 *@文件名：SearchService.java
 *@期：下午04:11:27
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.service.fore;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.QueryResult;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;


/**
 *类名称:SearchService
 *类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:18:42<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:18:42<br/>
 *修改备注: <br/>
 *@version 1.0
 *@param <E>
 */
public interface SearchService {
	public Page<QueryResult> searchAllByKey(String key, Page<QueryResult> page );
	
	public Page<QueryResult> searchSjfgByKey(String key, Page<QueryResult> page );
	
	public Page<QueryResult> searchDxyjByKey(String key, Page<QueryResult> page );
	
	public Page<QueryResult> searchFfalByKey(String key, Page<QueryResult> page );
	
	public Page<QueryResult> searchSjdhByKey(String key, Page<QueryResult> page );

	public DxyjLaw getDxyjLaw(DxyjLaw dxyjLaw);
	
	public Law getSjfgLaw(Law law);
	
	public FangFaAnLi getFangFaAnLi(FangFaAnLi fangFaAnLi);
	
	public SjdhMethod getSjdhMethod(SjdhMethod sjdhMethod);
	
	public DataDicDTO getSjsxMethod(DataDicDTO sjsxMethod);
	
	public ShiShiFangAnDTO getSsfaMethod(ShiShiFangAnDTO ssfaMethod);
}
