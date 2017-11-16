/*@目名称：lwsj
 *@文件名：SearchDao.java
 *@期：下午04:12:08
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.persist.dao.fore;

import java.util.LinkedHashMap;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.QueryResult;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;

/**
 *类名称:SearchDao 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:17:53<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:17:53<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
public interface SearchDao {
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午10:13:09
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> searchAllByKey(Page<QueryResult> page, String key,
			LinkedHashMap<String, String> orderby);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午10:13:16
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> searchSjfgByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午10:13:18
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> searchDxyjByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午10:13:21
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> searchFfalByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午10:13:25
	 * 
	 * @return
	 *@version 1.0
	 */
	public Page<QueryResult> searchSjdhByKey(Page<QueryResult> page,
			String key, LinkedHashMap<String, String> orderby);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-7上午09:59:44
	 * 
	 * @return
	 *@version 1.0
	 */
	public DxyjLaw getDxyjLaw(DxyjLaw dxyjLaw);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-7上午09:59:48
	 * 
	 * @return
	 *@version 1.0
	 */
	public Law getSjfgLaw(Law law);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-7上午09:59:52
	 * 
	 * @return
	 *@version 1.0
	 */
	public FangFaAnLi getFfal(FangFaAnLi fangFaAnLi);

	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-7上午09:59:57
	 * 
	 * @return
	 *@version 1.0
	 */
	public SjdhMethod getSjdh(SjdhMethod sjdh);
	
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 下午03:19:16
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public DataDicDTO getSjsx(DataDicDTO sjsxMethod);
	
	/**
	 *方法描述： <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 下午03:19:19
	 * 
	 *@return
	 *@version 1.0
	 */ 
	public ShiShiFangAnDTO getSsfa(ShiShiFangAnDTO ssfaMethod);

}
