package com.hnzskj.service.flow.impl;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.flow.*;
import com.hnzskj.persist.dao.flow.*;
import com.hnzskj.service.flow.DotLineService;

/**
 *创建人：郑辉 描述： 创建时间：2012-4-1 上午11:21:36 修改人：郑辉 修改时间：
 */
public class DotLineServiceImpl implements DotLineService{
	private TemplateDao tempDao;
	private TacheDao tacheDao;
	private LineDao lineDao;
	public TemplateDao getTempDao() {
		return tempDao;
	}

	public void setTempDao(TemplateDao tempDao) {
		this.tempDao = tempDao;
	}

	public TacheDao getTacheDao() {
		return tacheDao;
	}

	public void setTacheDao(TacheDao tacheDao) {
		this.tacheDao = tacheDao;
	}

	public LineDao getLineDao() {
		return lineDao;
	}

	public void setLineDao(LineDao lineDao) {
		this.lineDao = lineDao;
	}

	/**
	 *创建人：郑辉 描述：用来获取最大的模版编号 创建时间：2012-4-1 上午11:22:17 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int getTemplateMax() {
		int tempno = tempDao.getTempldateNoMax();
		return tempno;
	}

	@Override
	@Description("创建可视化流程")
	public void save(Template template, List<Tache> tacheList,
			List<Line> lineList) {
		// 添加模版
		if (null != template) {
			tempDao.addTemplateInfo(template);
		}
		// 添加环节
		if (null != tacheList && 0 != tacheList.size()) {
			//如果环节集合不为空，那么先删除所有的数据
			tacheDao.deleteTacheInfo(template.getTemplate_no());
			for (int i = 0; i < tacheList.size(); i++) {
				Tache tache = tacheList.get(i);
				tache.setTemplate_id(template.getTemplate_no()); // 为环节中的模版赋值
				 tacheDao.addTacheInfo(tache);
				
			}
		}
		
		
		// 添加连线
		if (null != lineList && 0 != lineList.size()) {
			//如果lineList不为空，先删除该模板下所有的数据，然后再插入，这样防止，原有的数据和新添加的数据不一致
			lineDao.delete(template.getTemplate_no());
			for (int i = 0; i < lineList.size(); i++) {
				Line line = lineList.get(i);
				line.setTemplate_id(template.getTemplate_no());
				lineDao.addLineInfo(line);
				
			}
		}
	}

	@Override
	@Description("获取模拟列表")
	public Page<Template> getAllTemplate(Template template) {
		Page<Template> page = new Page<Template>();
		page = tempDao.searchTemplate(page, template);
		return page;
	}

	@Override
	@Description("获取图形化模板的数据")
	public WorkFlow getWorkFlowInfo(Template template) {
		/*
		 * 1。获取模版信息，将数据构建到workflow实例的flowMain，flowName,flowNo
		 * 2。通过模版编号，获取环节的信息，将数据处理后构建在workflow中的dotProp
		 * 3。通过模版编号，获取连接线的信息，连接线的信息有通过有序索引
		 * ，按顺序排列，处理后存放在lineProp，lineDotList，lineLikeList中
		 */
		WorkFlow workflow = new WorkFlow();
		// 1.
		Template temp = tempDao.getTemplateInfoById(template
				.getTemplate_no());
		if (null != temp) {
			//1.初始化属于模版的三个参数值
			workflow.setFlowNo(temp.getTemplate_no() + "");
			workflow.setFlowMain(temp.getFlowmain());
			workflow.setFlowName(temp.getTemplate_name());

			//2. 初始化环节的参数值
			tacheToWorkFlow(workflow,temp);
			
			//3.获取连接线的属性
			lineToWorkFlow(workflow,temp);
		}
		return workflow;
	}
	
	public void tacheToWorkFlow(WorkFlow workflow,Template template){
		List<Tache> tacheList = tacheDao.getTacheByTempId(template.getTemplate_no());
		if (tacheList.size() != 0) {
			StringBuffer dotProp=new StringBuffer();
			for (int i = 0; i < tacheList.size(); i++) {

				Tache tache = tacheList.get(i);
				
				if(1==tache.getTache_id()||2==tache.getTache_id()){
					//构建节点属性
					dotProp.append(";");
					dotProp.append(tache.getTache_id()+",");
					dotProp.append(tache.getXposition()+",");
					dotProp.append(tache.getYposition()+",");
					dotProp.append(",");
					dotProp.append(tache.getTache_name()+",");
					dotProp.append(tache.getDotType()+",");
				}else{
					dotProp.append(";");
					dotProp.append(tache.getTache_id()+",");
					dotProp.append(tache.getXposition()+",");
					dotProp.append(tache.getYposition()+",");
					dotProp.append("*tache_id="+tache.getTache_id()+"*tache_type="+tache.getTache_type()+"*tache_name="+tache.getTache_name()+"*tache_desc="+tache.getTache_DESCRIPTION());
					dotProp.append("*emp_name="+tache.getEmp_names()+"*emp_ids="+tache.getGuid()+"*dottype="+tache.getDotType()+"*days="+tache.getDays()+"*attach="+tache.getAttach()+"*hours="+tache.getHours()+"*minutes="+tache.getMinutes());
					dotProp.append("*tag="+tache.getMemo()+"*is_back="+tache.getIs_back()+"*model="+tache.getNameModel()).append("*roles_id="+tache.getRoles_id()+"*roles_name="+tache.getRoles_name()+",");					
					dotProp.append(tache.getTache_name()+",");
					dotProp.append(tache.getDotType()+",");
				}
			}
			workflow.setDotProp(dotProp.toString());
		}
	}
	public void lineToWorkFlow(WorkFlow workflow,Template template){
		List<Line>lineList=lineDao.getLineByTempId(template.getTemplate_no());
		StringBuffer lineDotList=new StringBuffer();   //_line1;53,198;202,213_line2;202,213;633,88
		StringBuffer lineLikeList=new StringBuffer();  //;1,imgs1,imgs3;2,imgs3,imgs2
		StringBuffer lineProp=new StringBuffer();		//;1,,,1;2,,,1	
		for(int i =0;i<lineList.size();i++){
			Line line =lineList.get(i);
			lineDotList.append("_"+line.getLink_path());
			lineLikeList.append(";"+line.getLine_id()+",imgs"+line.getTache_id()+",imgs"+line.getEnd_tache_id());
			lineProp.append(";"+line.getLine_id()+",*lineLink_role="+line.getLineLink_role()+"*lineLink_props="+line.getLineLink_props()+",,"+line.getLine_type());
		}
		workflow.setLineDotList(lineDotList.toString());
		workflow.setLineLikeList(lineLikeList.toString());
		workflow.setLineProp(lineProp.toString());
	}
	@Override
	@Description("获取环节对应的连接线集合")
	public List<Tache> getDotTacheList(int templateno,int tachtId){
		List<Line> lineList =null;
		List<Tache> tachelist=new ArrayList<Tache>();
		lineList=lineDao.getDotTacheList(templateno, tachtId);
		for(int i =0;i<lineList.size();i++){
			Line line =lineList.get(i);
			int nextLineTache = line.getEnd_tache_id();
			int temolate_no =line.getTemplate_id();
			Tache tache =tacheDao.getTacheByTempIdAndTacheId(temolate_no, nextLineTache);
			tachelist.add(tache);
		}
		return tachelist;
	}
	
	@Override
	@Description("获取到流程的环节信息")
	public Tache getTache(int templateNO,int tacheId){
		Tache tache = tacheDao.getTacheByTempIdAndTacheId(templateNO, tacheId);
		return tache;
	}

	@Override
	@Description("删除图形化模板的数据")
	public int delteTemplateAndTacheAndLine(int templateNo) {
		// TODO Auto-generated method stub
		int num =0;
		tempDao.delete(templateNo);
		tacheDao.deleteTacheInfo(templateNo);
		lineDao.delete(templateNo);
		return num;
	}

	@Override
	@Description("获取连接线的信息")
	public Line getLineInfo(String templateId, String beforeTacheId,String endTacheId) {
		// TODO Auto-generated method stub
		return lineDao.getLineInfo(templateId, beforeTacheId, endTacheId);
	}
	@Description("更新附件数据")
	public int updateAttatch(String template_no,String tacheId,String attachId){
		return tacheDao.updateAttatch(template_no, tacheId, attachId);
	}
}
