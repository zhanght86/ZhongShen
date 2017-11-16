/**
 * flow
 *com.hnzskj.dao.flow
 *2012-4-92012上午11:40:41
 *郑辉
 *
 */
package com.hnzskj.persist.dao.flow;

import java.util.List;

import com.hnzskj.persist.bean.flow.FlowMess;

/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-9 上午11:40:41
 *修改人：郑辉
 *修改时间：
 */
public interface FlowMessDao{
	public List<FlowMess> getFlowMessInfo(int insatnce_id);
	
	public int add(FlowMess fm);
	
	public List<FlowMess> getFlowMessByNoAndTache(int insatnce_no ,String LABEL);
}
