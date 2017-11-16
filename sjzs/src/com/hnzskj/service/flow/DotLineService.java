package com.hnzskj.service.flow;

import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.*;

/**
 *创建人：郑辉 描述： 创建时间：2012-4-1 上午11:21:36 修改人：郑辉 修改时间：
 */
public interface DotLineService {

	/**
	 *创建人：郑辉 描述：用来获取最大的模版编号 创建时间：2012-4-1 上午11:22:17 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int getTemplateMax() ;

	/**
	 *创建人：郑辉 描述： 用来保存模版，环节，连接线 创建时间：2012-4-6 上午11:24:06 修改人：郑辉 修改时间： 返回类型： void
	 */
	public void save(Template template, List<Tache> tacheList,
			List<Line> lineList) ;

	/**
	 *创建人：郑辉 描述： 获取模版的分页 创建时间：2012-4-7 上午08:32:00 修改人：郑辉 修改时间： 返回类型：
	 * Page<Template>
	 */
	public Page<Template> getAllTemplate(Template template) ;

	/**
	 *创建人：郑辉 描述 ： 通过工作流模版编号，初始化工作流环节，等属性属性 创建时间：2012-4-7 上午09:07:42 修改人：郑辉
	 * 修改时间： 返回类型： WorkFlow
	 */
	public WorkFlow getWorkFlowInfo(Template template) ;
	
	public void tacheToWorkFlow(WorkFlow workflow,Template template);
	public void lineToWorkFlow(WorkFlow workflow,Template template);
	
	/**
	 *创建人：郑辉
	 *描述： 	获取模板环节的路径
	 *创建时间：2012-4-12 下午05:36:17
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	public List<Tache> getDotTacheList(int templateno,int tachtId);
	
	public Tache getTache(int templateNO,int tacheId);
	
	/**
	 * 类描述：删除功能
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-8 上午08:41:30 
	 * 修改人：
	 * 修改时间：2012-8-8 上午08:41:30  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int delteTemplateAndTacheAndLine(int template_no);
	
	
	/**
	 * 类描述：获取连接线的基本信息，主要使用来判断线路设置的条件
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-8 上午08:42:42 
	 * 修改人：
	 * 修改时间：2012-8-8 上午08:42:42  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public Line getLineInfo(String templateId,String beforeTacheId,String endTacheId);
	
	public int updateAttatch(String template_no,String tacheId,String attachId);
}
