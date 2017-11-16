package com.hnzskj.persist.bean.flow;
/**
 * 工作流对象的基本属性
 * @author Administrator
 *
 */
public class WorkFlow {
	private int template_no;// 模板号
	private int instance_no;// 实例号
	private int task_id;// 任务号
	private int tache_id;// 环节号
	private String enti_id;// 环节处理人
	private String nextinfoname;// 下一环节名称
	private String nextinfoid;// 下一环节编号
	private String nextEnti_ids;;// 下一环节处理人
	
	private String dotProp;// 节点属性
	private String lineProp;// 连线属性
	private String lineDotList;// 连线点列表
	private String lineLikeList;// 连线节点列表
	private String flowMain;// 流程主要信息
	private String flowName;// 流程名称
	private String flowNo;// 流程代码
	private String oldTache;// 已经走的流程
	private String nowTache;// 当前流程
	private String handlePerson;  //代办里人员
	private String handlePersonName;
	private Integer flowReturn=0;    //实例被驳回
	
	public WorkFlow() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getTemplate_no() {
		return template_no;
	}

	public void setTemplate_no(int templateNo) {
		template_no = templateNo;
	}

	public int getInstance_no() {
		return instance_no;
	}

	public void setInstance_no(int instanceNo) {
		instance_no = instanceNo;
	}

	public int getTask_id() {
		return task_id;
	}

	public void setTask_id(int taskId) {
		task_id = taskId;
	}

	public int getTache_id() {
		return tache_id;
	}

	public void setTache_id(int tacheId) {
		tache_id = tacheId;
	}

	public String getEnti_id() {
		return enti_id;
	}
	public void setEnti_id(String entiId) {
		enti_id = entiId;
	}
	public String getNextinfoname() {
		return nextinfoname;
	}
	public void setNextinfoname(String nextinfoname) {
		this.nextinfoname = nextinfoname;
	}
	public String getNextinfoid() {
		return nextinfoid;
	}
	public void setNextinfoid(String nextinfoid) {
		this.nextinfoid = nextinfoid;
	}
	public String getDotProp() {
		return dotProp;
	}
	public void setDotProp(String dotProp) {
		this.dotProp = dotProp;
	}
	public String getLineProp() {
		return lineProp;
	}
	public void setLineProp(String lineProp) {
		this.lineProp = lineProp;
	}
	public String getLineDotList() {
		return lineDotList;
	}
	public void setLineDotList(String lineDotList) {
		this.lineDotList = lineDotList;
	}
	public String getLineLikeList() {
		return lineLikeList;
	}
	public void setLineLikeList(String lineLikeList) {
		this.lineLikeList = lineLikeList;
	}
	public String getFlowMain() {
		return flowMain;
	}
	public void setFlowMain(String flowMain) {
		this.flowMain = flowMain;
	}
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}
	public String getFlowNo() {
		return flowNo;
	}
	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}
	public String getOldTache() {
		return oldTache;
	}
	public void setOldTache(String oldTache) {
		this.oldTache = oldTache;
	}
	public String getNowTache() {
		return nowTache;
	}
	public void setNowTache(String nowTache) {
		this.nowTache = nowTache;
	}
	
	public String getNextEnti_ids() {
		return nextEnti_ids;
	}

	public void setNextEnti_ids(String nextEntiIds) {
		nextEnti_ids = nextEntiIds;
	}

	/**
	 * @return the handlePerson
	 */
	public String getHandlePerson() {
		return handlePerson;
	}

	/**
	 * @param handlePerson the handlePerson to set
	 */
	public void setHandlePerson(String handlePerson) {
		this.handlePerson = handlePerson;
	}

	/**
	 * @return the handlePersonName
	 */
	public String getHandlePersonName() {
		return handlePersonName;
	}

	/**
	 * @param handlePersonName the handlePersonName to set
	 */
	public void setHandlePersonName(String handlePersonName) {
		this.handlePersonName = handlePersonName;
	}

	/**
	 * @return the flowReturn
	 */
	public Integer getFlowReturn() {
		return flowReturn;
	}

	/**
	 * @param flowReturn the flowReturn to set
	 */
	public void setFlowReturn(Integer flowReturn) {
		this.flowReturn = flowReturn;
	}

	public void println(){
		System.out.println(
				 "template_no 模板号:"+ template_no+"<br/>"+
				 "instance_no 实例号:"+instance_no+"<br/>"+
				 "task_id 任务号:"+task_id+"<br/>"+
				 "tache_id 环节号:"+tache_id+"<br/>"+
				 "enti_id 环节处理人:"+enti_id+"<br/>"+
				 "nextinfoname 下一环节名称:"+nextinfoname+"<br/>"+
				 "nextinfoid 下一环节编号:"+nextinfoid+"<br/>"+
				 "dotProp 节点属性:"+dotProp+"<br/>"+
				 "lineProp 连线属性:"+lineProp+"<br/>"+
				 "lineDotList 连线点列表:"+lineDotList+"<br/>"+
				 "lineLikeList 连线节点列表:"+lineLikeList+"<br/>"+
				 "flowMain 流程主要信息:"+flowMain+"<br/>"+
				 "flowName 流程名称:"+flowName+"<br/>"+
				 "flowNo 流程代码:"+flowNo+"<br/>"+
				 "oldTache 已经走的流程:"+oldTache+"<br/>"+
				 "nowTache 当前流程	:"+nowTache	
		);
	}
}
