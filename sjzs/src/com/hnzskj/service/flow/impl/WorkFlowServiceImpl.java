/**
 * flow
 *com.hnzskj.service.flow
 *2012-4-72012下午06:51:13
 *郑辉
 *
 */
package com.hnzskj.service.flow.impl;

import java.util.List;

import com.hnzskj.common.Constant;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.DateUtil;
import com.hnzskj.common.Page;
import com.hnzskj.common.annotation.Description;
import com.hnzskj.persist.bean.flow.FlowMess;
import com.hnzskj.persist.bean.flow.InstanceExceInfo;
import com.hnzskj.persist.bean.flow.InstanceWF;
import com.hnzskj.persist.bean.flow.Line;
import com.hnzskj.persist.bean.flow.Tache;
import com.hnzskj.persist.bean.flow.TaskList;
import com.hnzskj.persist.bean.flow.Template;
import com.hnzskj.persist.bean.flow.WorkFlow;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.persist.dao.flow.FlowMessDao;
import com.hnzskj.persist.dao.flow.InstanceExceInfoDao;
import com.hnzskj.persist.dao.flow.InstanceWFDao;
import com.hnzskj.persist.dao.flow.LineDao;
import com.hnzskj.persist.dao.flow.TacheDao;
import com.hnzskj.persist.dao.flow.TaskListDao;
import com.hnzskj.persist.dao.flow.TemplateDao;
import com.hnzskj.persist.dao.sjzs.WorkFlowDao;
import com.hnzskj.persist.dao.system.EmployeeDao;
import com.hnzskj.persist.dao.system.RoleDao;
import com.hnzskj.service.flow.WorkFlowService;

/**
 *创建人：郑辉 描述： 创建时间：2012-4-7 下午06:51:13 修改人：郑辉 修改时间：
 */
public class WorkFlowServiceImpl implements WorkFlowService{
	private TemplateDao tempDao;
	private TacheDao tacheDao;
	private LineDao lineDao;
	private InstanceWFDao iwfDao;
	private InstanceExceInfoDao ieiDaoImpl;
	private TaskListDao taskDaoImpl;
	private FlowMessDao flowMessDao;
	private RoleDao roleDao ;
	private EmployeeDao employeeDao;
	private WorkFlowDao workFlowDao;
	
	
	
	
	/**
	 * @return the workFlowDao
	 */
	public WorkFlowDao getWorkFlowDao() {
		return workFlowDao;
	}

	/**
	 * @param workFlowDao the workFlowDao to set
	 */
	public void setWorkFlowDao(WorkFlowDao workFlowDao) {
		this.workFlowDao = workFlowDao;
	}

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

	public InstanceWFDao getIwfDao() {
		return iwfDao;
	}

	public void setIwfDao(InstanceWFDao iwfDao) {
		this.iwfDao = iwfDao;
	}

	public InstanceExceInfoDao getIeiDaoImpl() {
		return ieiDaoImpl;
	}

	public void setIeiDaoImpl(InstanceExceInfoDao ieiDaoImpl) {
		this.ieiDaoImpl = ieiDaoImpl;
	}

	public TaskListDao getTaskDaoImpl() {
		return taskDaoImpl;
	}

	public void setTaskDaoImpl(TaskListDao taskDaoImpl) {
		this.taskDaoImpl = taskDaoImpl;
	}

	public FlowMessDao getFlowMessDao() {
		return flowMessDao;
	}

	public void setFlowMessDao(FlowMessDao flowMessDao) {
		this.flowMessDao = flowMessDao;
	}


	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public EmployeeDao getEmployeeDao() {
		return employeeDao;
	}

	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}

	@Override
	@Description("开始流程第一步，流程创建")
	public int createJob(Template template,Employee employee) {
		/*
		 * <1>1获取到模板编号，从页面中获取到，判断是否存在此流程， <2>2生成一条实例：插入信息：SYS_WFMS_INSTANCE_LIST
		 * <3>3创建实例环节信息：插入信息：SYS_WFMS_EXEC_INFO 该实例环节信息是实例下的第一个环节《开始》环节
		 * <4>4创建实例环节信息
		 * ：插入信息：SYS_WFMS_EXEC_INFO，该环节是开始环节下的第一个环节信息《开始》环节的下一个环节，此环节是通过工作流环节模板找到的
		 * 。 <5>5创建当前任务：插入信息：SYS_WFMS_TASK_LIST
		 */
		int instance_id = 0;
		Template temp = tempDao.getTemplateInfoById(template.getTemplate_no());
		/*
		 * 过程 1.
		 */
		if (null != temp) {
			// 判断是否存在流程模板，如果存在加载 在实例表中加载一条数据
			/*
			 * 过程 2.
			 */
			instance_id = initFlow(temp,employee);
			/*
			 * 过程3 4 通过模板编号获取到环节的基本属性；获取到得是个一个LIst集合 ，为开始环节和开始后的第一 个环节初始化
			 */
			List<Tache> tacheList = tacheDao.getTacheByTempId(template
					.getTemplate_no());
			InstanceExceInfo iel = null;
			for (int i = 0; i < tacheList.size(); i++) {
				Tache tache = tacheList.get(i);
				// 判断是不是第一个环节“开始”如果是的话出入实例列表
				iel = new InstanceExceInfo();
				if (1 == tache.getTache_id()) {
					// 如果是第一个环节 ”开始“ 初始化实例环节基本信息
					iel.setInstance_id(instance_id);
					iel.setTask_id(0); // 开始
					iel.setTache_id(tache.getTache_id());
					iel.setBefore_tache_id(1);
					iel.setState("1"); // 开始环节直接定义成完成
					iel.setTask_state("E");
					iel.setTemplate_id(temp.getTemplate_no());
					iel.setOvertime_flag("N");// 未超时
					iel.setLast_commitor(employee.getEmplId());// 上一步提交人员默认为创建流程人员，一般从用户session中获取
					iel.setTask_reach_time(DataUtil.getNowTime().substring(0,19));
					iel.setTask_commit_time(DataUtil.getNowTime().substring(0,19));
					iel.setTask_commitor(employee.getEmplId());// 当前环节提交人，一般从用户session中获取
					iel.setTache_type("" + tache.getTache_type());
					iel.setRemark("");
					iel.setDepartment(employee.getOrgId());// 从用户session中获取。
					iel.setTache_name(tache.getTache_name());
				} else if (3 == tache.getTache_id()) {
					// 获取第二个环节，流程环节。此环节是业务处理环节
					iel.setInstance_id(instance_id);
					iel.setTask_id(1); // 流程中开始后的第一个任务
					iel.setTache_id(tache.getTache_id());
					iel.setBefore_tache_id(1);   //在前两个环节不存在 上一环节的问题
					iel.setState("1"); // 开始环节直接定义成完成
					iel.setTemplate_id(temp.getTemplate_no());
					iel.setOvertime_flag("N");// 未超时
					iel.setLast_commitor(employee.getEmplId());// 最后提交人员
					iel.setTask_state("B");							
					iel.setTask_reach_time(DataUtil.getNowTime().substring(0,19));
					iel.setTask_commitor(employee.getEmplId());// 当前环节提交人，一般从用户session中获取
					iel.setTache_type("" + tache.getTache_type());
					iel.setRemark("");
					iel.setDepartment(employee.getOrgId());// 从用户session中获取。
					iel.setTache_name(tache.getTache_name());
				} else {
					
				}
				if (0 != iel.getInstance_id()) {
					ieiDaoImpl.addInstanceExceListInfo(iel); // 插入实例初始环节
				}
			}
		} else {
			
		}
		return instance_id;
	}

	/**
	 *创建人：郑辉 描述： 添加一个工作流实例 insert SYS_INSTANCE_LIST 并且返回实例号 创建时间：2012-4-9
	 * 上午09:11:12 修改人：郑辉 修改时间： 返回类型： int
	 */
	public int initFlow(Template temp,Employee employee) {
		int instance_id = 0;
		InstanceWF iwf = new InstanceWF();
		iwf.setTemplate_id(temp.getTemplate_no()+"");
		iwf.setStart_operator(employee.getEmplId()); // 此数据正确获取方式是从用户sesion中取得
		iwf.setDepartment(employee.getOrgId()); // 此数据正确获取方式是从用户sesion中取得
		iwf.setRemark("");
		iwf.setState(1+""); // 流程开始
		iwf.setStart_time(DataUtil.getNowTime().substring(0,19));
		iwf.setEnd_time("");
		instance_id = iwfDao.addInstanceWFInfo(iwf);
		return instance_id;
	}

	@Override
	@Description("初始化出流程的相关属性")
	public WorkFlow initJob(int instance_id,Employee employee,WorkFlow flow) {
		//int nextTacheId = 0; // 下一环节编号
		//String nextTacheName = ""; // 下一环节名称
		WorkFlow workflow = new WorkFlow();
		
		/*
		 * 1.获取当前的实例信息，
		 * 1.获取到当前的任务 
		 * 2.获取意见表中的信息 
		 * 3.环节属性字段进行处理
		 */

		//基本属性初始化完毕后， 流程在createJob完成的时候，taskList中没有任务数据。 所以可以按照有没有任务数据分类。
		TaskList task = new TaskList();
		task = taskDaoImpl.getTaskByInstanceID(instance_id,employee.getEmplId());
		if (task == null) {
			workflow =getWorkFlowNotTaskInfo(instance_id,flow);
		} else {
			workflow=getWorkFlowTaskInfo(instance_id,task);
		}
		workflow.setFlowReturn(flow.getFlowReturn());
		return workflow;
	}

	/**
	 *创建人：郑辉
	 *描述： 该方法用来获取 任务为空的时候的 工作流对象   主要是在第三环节和最后一个环节	
	 *创建时间：2012-6-11 下午03:19:35
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： WorkFlow
	 */
	public WorkFlow getWorkFlowNotTaskInfo(int instance_id,WorkFlow flow ){
		//task 为空有三种情况，一种是  刚开始一个任务实例，这个时候任务实例，还没有创建任务，出现的环节是流程的第三个环节 及 tache_id =3
		//另外一种情况是 结束环节， 因为下一个环节是结束环节，在 commitSave的时候，没有创建环节，所以在这里 task也是空。 在处理的时候可以根据上边的参数flow 来个控制
		//第三种是，用户任务在提交之后，被删除了。这个时候，initJob的时候，无法获取到当前用户的
		// 判断是不是有任务。要先通过实例号获取到模板
		WorkFlow workflow =new WorkFlow ();
		List<InstanceExceInfo> infolist = ieiDaoImpl.getlist(instance_id);
		if(flow.getInstance_no()==0){
			InstanceExceInfo nowIeI = null;
			for (int i = 0; i < infolist.size(); i++) {
				InstanceExceInfo info = infolist.get(i); // 获取第一个实力环节
				if (info.getTask_id() == 1) {
					nowIeI = info;
				}
			}
			// 获取第一个环节
			Tache nowTache = tacheDao.getTacheByTempIdAndTacheId(nowIeI
					.getTemplate_id(), nowIeI.getTache_id());
			Tache nextTache = tacheDao.getTacheByTempIdAndTacheId(nowIeI
					.getTemplate_id(), nowIeI.getTache_id() + 1);
			workflow.setInstance_no(instance_id);
			workflow.setEnti_id(nowTache.getGuid());
			workflow.setTache_id(nowTache.getTache_id());
			workflow.setTask_id(nowIeI.getTask_id());
			workflow.setTemplate_no(nowIeI.getTemplate_id());
			workflow.setNextinfoid(nextTache.getTache_id() + "");
			workflow.setNextinfoname(nextTache.getTache_name());
			workflow.setOldTache(",0,");
			workflow.setNowTache(","+nowTache.getTache_id() + ",");
		}else{
			InstanceWF wf =iwfDao.getInstanceWfInfo(instance_id);
			//表示任务已经结束
			if(wf.getState().equals("2")){
				workflow=new WorkFlow();
				workflow.setEnti_id(flow.getEnti_id());
				workflow.setInstance_no(flow.getInstance_no());
				workflow.setTemplate_no(flow.getTemplate_no());
				workflow.setNextinfoid(flow.getNextinfoid() + "");
				workflow.setNextinfoname(flow.getNextinfoname());
				String old=",";
				for (int i = 0; i < infolist.size(); i++) {
					InstanceExceInfo info = infolist.get(i); //获取处理过的所有环节
					if((info.getTache_id()==1)||(info.getTache_id()==2)||(info.getTask_state().equals("B"))){
						continue;
					}
					old+=info.getTache_id()+",";
				}
				workflow.setOldTache(old);
			}else{
				workflow.setInstance_no(instance_id);
				workflow.setEnti_id(flow.getEnti_id());
				workflow.setTache_id(flow.getTache_id());
				workflow.setTask_id(flow.getTask_id());
				workflow.setTemplate_no(flow.getTemplate_no());
				workflow.setNextinfoid(flow.getNextinfoid());
				workflow.setNextinfoname(flow.getNextinfoname());
				String old=",";
				String now="";
				for (int i = 0; i < infolist.size(); i++) {
					InstanceExceInfo info = infolist.get(i); //获取处理过的所有环节
					if(info.getTask_state().equals("B")){
						now=","+info.getTache_id()+",";
					}
					
					if((info.getTache_id()==1)||(info.getTache_id()==2)||(info.getTask_state().equals("B"))){
						continue;
					}
					old+=info.getTache_id()+",";
				}
				workflow.setOldTache(old);
				workflow.setNowTache(","+flow.getTache_id() + ",");
				if(flow.getTache_id()==0){
					workflow.setNowTache(now);
				}
				if (flow.getNextinfoid()!=null) {
					if(flow.getNextinfoid().equals("2")){
						workflow=new WorkFlow();
						workflow.setEnti_id(flow.getEnti_id());
						workflow.setNextinfoid(flow.getNextinfoid());
						workflow.setNextinfoname(flow.getNextinfoname());
						workflow.setInstance_no(flow.getInstance_no());
						workflow.setTemplate_no(flow.getTemplate_no());
					}
				}
			}
		}
		return workflow;
	}
	
	public WorkFlow getWorkFlowTaskInfo(int instance_id,TaskList task){
		int nextTacheId = 0; // 下一环节编号
		String nextTacheName = ""; // 下一环节名称
		WorkFlow workflow = new WorkFlow();
		List<Tache> tacheList = tacheDao.getTacheByTempId(task
				.getTemplate_id());
		Tache nowTache = null; // 当前环节
		Tache upTache = new Tache(); // 当前环节的上一个环节 思路：实例列表中 当前任务的编号的上一个任务就是
		Tache nextTache = null; // 当前任务的下一个环节
		
		for (int i = 0; i < tacheList.size(); i++) {
			Tache tache = tacheList.get(i); //
			/*
			 * 通过当前任务环节的编号与工作流模板中的环节进行比较，判断出是下一步是哪个一个环节
			 * 如果两者相等，流程环节的下一环节就是要进行的环节
			 */
			// 判断是否相等
			if (task.getTache_id() == tache.getTache_id()) {
				nowTache = tache;
				// 判断是否是结束的前一个环节 0,1,2 3
				if ((i + 1) >= tacheList.size()) {
					// 相等，获取下一个环节对象。
					nextTache = tacheList.get(1);
					nextTacheId = nextTache.getTache_id();
					nextTacheName = nextTache.getTache_name();

				} else {
					nextTache = tacheList.get(i + 1);
					nextTacheId = nextTache.getTache_id();
					nextTacheName = nextTache.getTache_name();
				}
			}
			/*
			 * 获取上一个环节，通过环节任务编号来判断
			 */
			boolean falg = (task.getTask_id() - 1) == 0; // 如果当前任务的上一个任务 -1
															// 等于1 说明是开始环节
															// 忽略掉
			if (falg == false) {
				// 不是开始环节
				InstanceExceInfo iei = ieiDaoImpl.getInstanceExceInfo(task
						.getInstance_id(), (task.getTask_id() - 1),
						"");
				upTache.setTache_id(iei.getTache_id());
			}
		}
		//已处理的节点结合
		List<InstanceExceInfo> dotlist =ieiDaoImpl.getOldTache(instance_id);
		String oldTache="";
		for(int i =0;i<dotlist.size();i++){
			oldTache+=dotlist.get(i).getTache_id()+",";
		}
		workflow.setInstance_no(instance_id);
		workflow.setEnti_id(task.getEnti_id());
		workflow.setTache_id(task.getTache_id());
		workflow.setTask_id(task.getTask_id());
		workflow.setTemplate_no(task.getTemplate_id());
		workflow.setNextinfoid(nextTacheId + "");
		workflow.setNextinfoname(nextTacheName);
		workflow.setOldTache(","+oldTache);
		workflow.setNowTache(","+nowTache.getTache_id() + ",");
		
		return workflow;
	}
	/**
	 *创建人：郑辉
	 *描述：获取环节属性中人员处理的 人员名称 	
	 *创建时间：2012-4-17 下午01:44:01
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： Object[]
	 */
	public Object[] getEmplIds(Tache tache,WorkFlow wflow){
		Object[] objs =null;
		int nameModel =tache.getNameModel();
		String enti_ids =tache.getGuid();  //   张张/程洪杰/王冲/
		InstanceWF w =iwfDao.getInstanceWfInfo(wflow.getInstance_no());
		if(nameModel==1){   //人员选择模式
			objs =enti_ids.split("/");
		}else if(nameModel==2){	//上一环节指定模式
			objs=wflow.getNextEnti_ids().split("/");
		}else if (nameModel==3){  //系统用户模式
			Object [] os ={wflow.getEnti_id()};
			objs=os;
		}else if(nameModel==4){  //选择角色模式
			//先获取到处理角色的信息，通过流程启动者，判断该部门对应的角色信息
			Employee emp =employeeDao.findEmployeeById(w.getStart_operator()); //流程启动者的信息
			
			List<Employee> empls =employeeDao.findEmpByRolesId(tache.getRoles_id());
			//一个角色对应多个员工，
			if(empls!=null && empls.size()>1){
				for(int i =0;i<empls.size();i++){
					if(empls.get(i).getOrgId().equals(emp.getOrgId())){
						Object [] os ={empls.get(i).getEmplId()};
						objs=os;
					}
				}
			}
			
			//一个角色只对应一个职工。
			if(empls!=null && empls.size()==1){
				Object [] os ={empls.get(0).getEmplId()};
				objs=os;
			}
			
		}else{
			Object [] os ={w.getStart_operator()};
			objs=os;
		}
		
		if(tache.getTache_id()==3){  //如果是环节 3   ，任务处理人应该是  实例启动者。
			Object [] os ={w.getStart_operator()};
			objs=os;
		}
		return objs;
	}
	@Override
	@Description("流程进行提交，并且创建新的实例和任务")
	public void commitSave(WorkFlow workflow,Employee employee) {
		/*
		 * 1.通过实例获取当前的任务，如果任务为空
		 *       2.获取当前的实例， 通过实例，添加当前任务。添加新的一条实例
		 * 3.如果不为空，要删除任务，然后在重新添加任务。 并且修改工作流实例的状态，再添加一条新的实例
		 * 
		 * 1.通过页面属性，在工作实例环节集合中咋插入一条数据。 然后在更新状态 2.更新当前任务表
		 */
		
		TaskList nowTask = new TaskList();
		// 1. 获取当前的任务，实例，环节
		nowTask = taskDaoImpl.getTaskByInstanceID(workflow.getInstance_no(),workflow.getEnti_id());
		InstanceExceInfo nowIei = ieiDaoImpl.getInstanceExceInfo(workflow
				.getInstance_no(), workflow.getTask_id(), workflow
				.getTache_id()
				+ "");

		//更新当前实例的处理状态为E   在更新之前要判断当前任务是否还有 没有处理的任务
		int num=0;   //num 用来判断 当前实例的所有任务是否全部完成，如果，完成，创建新的任务，以及下一步的任务。如果没有完成，不创建新的实例和任务。
		if (null == nowTask) {
			num = ieiDaoImpl.updateInstanceExceInfoStart("E", nowIei
					.getInstance_id(), nowIei.getTask_id(), nowIei.getTache_id());
		}else{
			//判断当前实例下有几条任务  ,如果大约1   那么先不修改  ，
			List<TaskList> tasklist =taskDaoImpl.getTaskByInstanceID(workflow.getInstance_no());
			if(tasklist.size()==1){
				num =ieiDaoImpl.updateInstanceExceInfoStart("E", nowIei
						.getInstance_id(), nowIei.getTask_id(), nowIei
						.getTache_id());
			}
		}
		Tache nowTache = tacheDao.getTacheByTempIdAndTacheId(Integer
				.parseInt(workflow.getTemplate_no() + ""), workflow
				.getTache_id());
		Tache nexttache = null; //下一个环节信息
		nexttache = tacheDao.getTacheByTempIdAndTacheId(nowTache
				.getTemplate_id(), Integer.parseInt(workflow
						.getNextinfoid()));
		int tasknum = 0; //任务是删除情况
		//判断审批流程是一个人处理还是需要所有人全部处理
		if(nowTache.getIs_back()!=null){
			int nowProceModel=Integer.parseInt(nowTache.getIs_back());
			if(nowProceModel==1){
				//当前环节如果是  一个人处理，直接删除任务，重新再创建新任务。
				tasknum=taskDaoImpl.deleteTaskByInstance(workflow.getInstance_no());
			}else{
				//如果是多个人处理，需要在最后一个人处理完之后删除。				
				// 删除当前用户的任务    暂时不创建新的任务
				tasknum=taskDaoImpl.deleteTask(workflow.getInstance_no(),
						employee.getEmplId());
				
			}
		}
		String emp_id ="";  //下一步的处理人员。
		if (0 != num) {
			//创建任务
			emp_id=createTask(nowTask,nexttache,workflow,employee, nowIei, nowTache , tasknum);
			// 插入一条新的实例
			createInstance(nexttache, workflow,employee,nowIei,emp_id);
		}
		//判断是不是最后一个环节，执行更新实例的结束时间操作。
		if(Integer.parseInt(workflow.getNextinfoid())==2){
			iwfDao.update(workflow.getInstance_no());
			if(workflow.getHandlePerson()!=null){
				Template noticeTemplate = tempDao.getTemplateInfoById(workflow.getTemplate_no());
//				Notice rev =new Notice();
//				rev.setNotTitle("待办任务查询");	
//				rev.setNotContent("<a href=\"javascript:void(0)\" title=\"\" onclick=\"javascript:self.parent.addTab('查看信息','"+noticeTemplate.getDescription_info().substring(0,noticeTemplate.getDescription_info().indexOf("!"))+"!initJob.action?&flow.instance_no="+workflow.getInstance_no()+"&type=history&tempalte.template_no="+workflow.getTemplate_no()+"','icon-add')\">任务："+"请点击查看关于"+noticeTemplate.getTemplate_name()+"的任务</a>");
//				rev.setNotSendId(employee.getEmplId());
//				rev.setNotSendName(employee.getEmplName());
//				
//				rev.setNotReceiveId(workflow.getHandlePerson());
//				rev.setNotReceiveName(workflow.getHandlePersonName());
//				rev.setNotSendTime(java.sql.Date.valueOf(DateUtil.getCurrentTime().substring(0, 10)));
//				rev.setNotStatus(1);
//				rev.setIsRead(1);
//				noticeDao.addNoticeReceive(rev);
			}
		}
	}
	
	/**
	 *创建人：郑辉
	 *描述： 在当前实例下创建一个任务	
	 *创建时间：2012-6-11 下午04:26:40
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	private String createTask(TaskList nowTask,Tache nexttache, WorkFlow workflow,
			Employee employee,InstanceExceInfo nowIei,Tache nowTache ,int tasknum){
		TaskList nextTask =new TaskList();
		String empId="";
		if (null == nowTask) {
			// 任务为空， 添加当前的任务 添加一条新的实例
			Object[] emps = getEmplIds(nexttache, workflow);
			for (int i = 0; i < emps.length; i++) {
				empId+=emps[i].toString()+",";
				TaskList newTask = new TaskList();
				newTask.setDepartment(1);
				newTask.setEnti_id(emps[i].toString());
				newTask.setEnti_type("1");
				newTask.setInstance_id(workflow.getInstance_no());
				newTask.setLast_commitor(nowIei.getTask_commitor());
				newTask.setOvertime_flag("N");
				newTask.setRole_code(0);
				newTask.setStart_operator(employee.getEmplId());
				newTask.setState("1");
				newTask.setTask_state("B");
				newTask.setTache_id(Integer.parseInt(workflow
						.getNextinfoid()));
				newTask.setTache_name(workflow.getNextinfoname());
				newTask.setTache_type(nowTache.getTache_type() + "");
				// 获取最大任务编号
				
				int maxno = ieiDaoImpl.getMaxTaskId(workflow
						.getInstance_no());
				newTask.setTask_id(maxno + 1);
				newTask.setTask_reach_time(DataUtil.getNowTime().substring(0,19));
				newTask.setTemplate_id(nowTache.getTemplate_id());
				newTask.setTache_name(workflow.getNextinfoname());
				// 添加成功
				taskDaoImpl.addTaskList(newTask);
				nextTask=newTask;   //获取到下一步任务
				//sendNotice(employee,nextTask);//发送短消息
			}
		} else {
			// 删除原有的要删除任务，然后在重新添加任务。
			Object[] emps = getEmplIds(nexttache, workflow);
			for (int i = 0; i < emps.length; i++) {
				empId+=emps[i].toString()+",";
				TaskList newTask = new TaskList();
				newTask.setDepartment(1);
				newTask.setEnti_id(emps[i].toString());
				newTask.setEnti_type("1");
				newTask.setInstance_id(workflow.getInstance_no());
				newTask.setLast_commitor(nowIei.getTask_commitor());
				newTask.setOvertime_flag("N");
				newTask.setRole_code(0);
				newTask.setStart_operator(nowTask.getStart_operator());
				newTask.setState("1");
				newTask.setTask_state("B");
				newTask.setTache_id(nexttache.getTache_id());
				newTask.setTache_name(nexttache.getTache_name());
				newTask.setTache_type(nexttache.getTache_type() + "");
				int maxno = ieiDaoImpl.getMaxTaskId(workflow
						.getInstance_no());
				newTask.setTask_id(maxno + 1);
				newTask.setTask_reach_time(DataUtil.getNowTime().substring(0,19));
				newTask.setTemplate_id(nexttache.getTemplate_id());
				newTask.setTache_name(workflow.getNextinfoname());
				if (tasknum != 0) {
					// 任务删除后，判断下要加入的任务是不是 结束环节的任务，如果是结束环节的任务，不添加
					if (newTask.getTache_id() != 2) {
						taskDaoImpl.addTaskList(newTask);				
					}
					if(newTask.getTache_id() == 2){
						nextTask=newTask;   //获取到下一步任务
						//sendNotice(employee,nextTask);//发送短消息
					}
				}
			}
		}
		if(empId.indexOf(",")>0){			
			return empId.substring(0,empId.lastIndexOf(","));
		}
		return "";
	}
	
	/**
	 *创建人：郑辉
	 *描述：创建一个实例 	
	 *创建时间：2012-6-11 下午04:49:46
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	private void createInstance(Tache nexttache, WorkFlow workflow,
			Employee employee,InstanceExceInfo nowIei,String emp_id){
		InstanceExceInfo iei = new InstanceExceInfo();
		iei.setInstance_id(workflow.getInstance_no());
		iei.setOvertime_flag("N"); // 未超时
		iei.setRemark("");
		iei.setState(1 + "");
		iei.setTache_id(Integer.parseInt(workflow.getNextinfoid()));
		iei.setBefore_tache_id(workflow.getTache_id());
		iei.setTache_name(nexttache.getTache_name());
		iei.setTache_type(nexttache.getTache_type() + "");
		iei.setTask_reach_time(DataUtil.getNowTime().substring(0,19));
		iei.setLast_commitor(employee.getEmplId());
		iei.setTask_commitor(emp_id); // 环节处理人员列表
		iei.setDepartment(employee.getOrgId());
		iei.setTask_id(nowIei.getTask_id() + 1);
		iei.setTask_state("B");
		if(Integer.parseInt(workflow.getNextinfoid())==2){
			iei.setTask_state("E");
			iwfDao.updateState(workflow.getInstance_no());
		}
		iei.setTemplate_id(nexttache.getTemplate_id());
		ieiDaoImpl.addInstanceExceListInfo(iei); // 插入下一个新的实力环节。
	}
	
	/**
	 *创建人：郑辉
	 *描述：发送短消息 	
	 *创建时间：2012-5-29 下午04:40:33
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	public void sendNotice(Employee employee,TaskList nextTask){
//		Notice notice =new Notice();
//		notice.setNotTitle("任务提醒");
//		notice.setNotSendId(employee.getEmplId());
//		notice.setNotSendName(employee.getEmplName());
//		notice.setIsRead(Constant.NOREAD);
//		notice.setNotStatus(Constant.NODEL);
//		notice.setNotTitle("任务提醒");
//		notice.setNotSendTime(java.sql.Date.valueOf(DateUtil.getCurrentTime().substring(0, 9)));
//		notice.setNotReceiveId(nextTask.getEnti_id());
//		notice.setNotReceiveName("");
//		notice.setNotContent(notice.getNotSendTime()+" "+notice.getNotSendName()+"给你发送一条任务，请及时查收。");
//		noticeDao.addNoticeSend(notice);
//		noticeDao.addNoticeReceive(notice);
	}
	
	@Override
	@Description("获取登录用户的审批任务")
	public List<TaskList> getTaskListByEntiID(String enti_id){
		List<TaskList> taskList =null;
		taskList =taskDaoImpl.getTaskListByEnti(enti_id);
		return taskList;
	}
	
	@Override
	@Description("获取当前任务的对应的实例")
	public  Page<InstanceWF> getInstanceWfByEmpId(String enti_id,InstanceWF wf, Page<InstanceWF> page){
		String params=null;
		String sqlCondition="where 1=1 ";
		if(wf!=null){
			if(wf.getTemplate_id()!=null&&!wf.getTemplate_id().equals("")){
					params=wf.getTemplate_id()+",";
				sqlCondition+=" and b.template_id=?";
			}
			//boolean  s =wf.getStart_time()!=null&&!wf.getStart_time().equals("");
			if(wf.getStart_time()!=null&&!wf.getStart_time().equals("")){
				if(params==null) params=wf.getStart_time()+" 00:00:01,";
				else
					params+=wf.getStart_time()+" 00:00:01,";
				sqlCondition+=" and b.start_time between ?";
			}
			
			if(wf.getEnd_time()!=null&&!wf.getEnd_time().equals("")){
				if(params==null)params=wf.getEnd_time()+",";
				else params+=wf.getEnd_time()+" 24:00:00,";
				sqlCondition+=" and ?";
			}
			
			if(wf.getState()!=null && !wf.getState().equals("0")){
				if(params == null) params=wf.getState()+",";
				else params+=wf.getState()+",";
				sqlCondition+=" and b.state=?";
			}
		}
		Object [] queryParams={};
		if(params!=null&&!params.equals("")){
			Object [] objectparmas =params.substring(0,params.lastIndexOf(",")).split(",");
			queryParams=objectparmas;
		}
			
		
		String fields="b.TEMPLATE_ID,dbo.getTemplateUrlById(b.TEMPLATE_ID) as templateUrl,dbo.getTemplateNameById(b.TEMPLATE_ID) as template_ids,b.STATE,b.START_TIME,b.END_TIME,dbo.getEmplNameById(b.START_OPERATOR) as start_operators,b.START_OPERATOR,b.REMARK,b.DEPARTMENT,b.instance_id";;
		page = this.iwfDao.searchPage(page, fields, sqlCondition, queryParams,enti_id);
		return page;
	}
	
	@Override
	@Description("添加审批意见")
	public void workflowMessSave(WorkFlow wf ,String mes,Employee emp){
		Tache tache = tacheDao.getTacheByTempIdAndTacheId(wf
				.getTemplate_no(), wf.getTache_id());
		FlowMess fm =new FlowMess();
		fm.setBody(mes);
		fm.setDept_id(emp.getOrgId());
		fm.setDept_name("");
		fm.setInstance_id(wf.getInstance_no());
		
		fm.setPerson_name(emp.getEmplName());
		fm.setPerson_id(emp.getEmplId());
		fm.setShenpi_time(DataUtil.getNowTime().substring(0,19));
		String tag =tache.getMemo().toString();
		
		String [] props =tag.split("/");
		String option ="";
		for(int i =0;i<props.length;i++){  //     审批=部门审批
			String  keys =props[i];
			if(props[i].indexOf("审批")==0){
				option=keys;
			}
		}
		@SuppressWarnings("unused")
		String key ="";
		String value="";
		if (!option.equals("")) {
			String[] keys = option.split("=");
			key = keys[0];
			value = keys[1];
		}
		fm.setLabel(value);
		flowMessDao.add(fm);
	}
	
	@Override
	@Description("获取审批人得审批意见")
	public List<FlowMess> getFlowMessByNoAndTache(int insatnce_no ,String LABEL){
		List<FlowMess> list =flowMessDao.getFlowMessByNoAndTache(insatnce_no, LABEL);
		return list;
	}
	
	@Override
	@Description("删除可视化模板信息")
	public int deleteInstanceAndTask(int template_no){
		int num =taskDaoImpl.deleteTaskList(template_no);
		return num;
	}
	
	@Override
	@Description("通过实例号和环节号，获取对应的实例信息")
	public List<InstanceExceInfo> getInstanceTacheInfo(String instance_no,String tache_id){
		List<InstanceExceInfo> list =ieiDaoImpl.getInstanceTacheInfo(instance_no, tache_id);
		if(null !=list && 0!=list.size()){
			return list;
		}
		return null;
	}
	
	@Override
	@Description("通过实例号，获取当前实例的上一个模板环节的信息")
	public InstanceExceInfo getInstanceExecInfoByTask(int instance_no){
		InstanceExceInfo nowInstance= ieiDaoImpl.getInstanceExceInfoByStatus(instance_no);
		if(nowInstance == null)
			return null;
		InstanceExceInfo beforeInstance=ieiDaoImpl.getInstanceExceInfoByTaskId(instance_no,nowInstance.getTache_id());
		return beforeInstance;
	}
	
	@Override
	@Description("获取员工的名称")
	public Employee getEmplByEmplId(String emplId){
		Employee emp =employeeDao.findEmployeeById(emplId); 
		return emp;
	}

	@Override
	@Description("通过模板和连接线信息获取到对连接线的属性")
	public Line getLineInfoByLineId(String templateId, String lineId) {
		// TODO Auto-generated method stub
		return lineDao.getLineInfoByLineId(templateId, lineId);
	}

	@Override
	@Description("获取到流程的的实例")
	public InstanceWF getInstanceWfInfo(int instanceNo) {
		// TODO Auto-generated method stub
		return iwfDao.getInstanceWfInfo(instanceNo);
	}
	
	/**
	 * 类描述：流程实例销毁
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-12-4 下午05:41:09 
	 * 修改人：
	 * 修改时间：2012-12-4 下午05:41:09  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public int deleteInstance(int instance_no){
		int result =0;
		//销毁当前的实例对象
		result = taskDaoImpl.deleteTaskByInstance(instance_no);
		return result ;
	}

	@Override
	public int saveWorkXml(WorkFlowDTO workFlowDTO) {
		int result =0;
		result = this.workFlowDao.saveWorkXml(workFlowDTO);
		return result;
	}

	@Override
	public WorkFlowDTO findWorkXmlByType(String type) {
		WorkFlowDTO workFlowDTO = this.workFlowDao.findByType(type);
		return workFlowDTO;
	}

	@Override
	public int updateWorkXml(WorkFlowDTO tempDto) {
		int result =0;
		result = this.workFlowDao.update(tempDto);
		return result;
	}

	@Override
	public WorkFlowDTO getWorkFlowInfo(String id) {
		WorkFlowDTO workFlowDTO = this.workFlowDao.findById(id);
		return workFlowDTO;
	}

	@Override
	public boolean delWorkFlowBySjdhId(String sjdhId) {
		return workFlowDao.delWorkFlowBySjdhId(sjdhId)>0;
	}
}
