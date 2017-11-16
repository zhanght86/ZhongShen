package com.hnzskj.persist.dao.flow;

import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.flow.Template;

public interface TemplateDao {

	/**
	 *创建人：郑辉 描述： 获取最大的模版编号 创建时间：2012-4-1 上午11:29:29 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int getTempldateNoMax() ;

	/**
	 *创建人：郑辉
	 *描述： 	插入数据，先判断是否存在，如果不存在，插入；如果存在，则修改
	 *创建时间：2012-4-7 上午09:21:02
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： int
	 */
	public int addTemplateInfo(Template template) ;
	
	/**
	 *创建人：郑辉
	 *描述： 该方法 通过一个模版编号获取到一个模版实例
	 *创建时间：2012-4-7 上午09:20:24
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Template
	 */
	public Template getTemplateInfoById(int no);
	
	public Page<Template> searchTemplate(Page<Template> page, Template template);
	
	public int delete(int template_no);
}
