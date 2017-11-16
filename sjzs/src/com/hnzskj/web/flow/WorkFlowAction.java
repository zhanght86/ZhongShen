package com.hnzskj.web.flow;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.DataUtil;
import com.hnzskj.common.Page;
import com.hnzskj.common.xml.XmlDTOParseLine;
import com.hnzskj.persist.bean.flow.FlowRef;
import com.hnzskj.persist.bean.flow.InstanceWF;
import com.hnzskj.persist.bean.flow.Line;
import com.hnzskj.persist.bean.flow.Tache;
import com.hnzskj.persist.bean.flow.Template;
import com.hnzskj.persist.bean.flow.WorkFlow;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.flow.DotLineService;
import com.hnzskj.service.flow.FlowRefService;
import com.hnzskj.service.flow.WorkFlowService;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.service.sjzs.SjdhMethodService;
import com.hnzskj.service.system.EmployeeService;
import com.hnzskj.web.BaseAction;
import com.hnzskj.common.xml.XmlDTOParseLine;

@SuppressWarnings("serial")
public class WorkFlowAction extends BaseAction {
	private static Logger log = Logger.getLogger(WorkFlowAction.class);
	// 工作流实例用来保存可视化界面的参数
	public WorkFlow flow = new WorkFlow();
	// 工作流模版实例
	public Template template = new Template();
	public Tache tache = new Tache();
	public Line line =new Line();
	
	public InstanceWF iwf =new InstanceWF();
	private DotLineService wfsi ;
	private WorkFlowService workFlowService;
	private SjdhMethodService sjdhService =null;

	private AttachSJZSService attachSJZSService;

	
	private EmployeeService employeeService;
	public List<Tache> tacheList =new ArrayList<Tache>();
	public List<Line> lineList =new ArrayList<Line>();

	public Page<Template> tempPage =new Page<Template>();
	
	private FlowRefService flowRefService;
	
	private String type="";	
	private String linePropties;
	private String son_str;
	private String allName;
	private String num;
	private String tachememo;
	
	private String textXml;
	
	private WorkFlowDTO workFlowDTO;
	
	
	private String id = "";//sjzs_sjdh-->template_no 对应workflowxml主键
	
	//用于接收节点属性的字符串
	private String dataStr = "";
	private String root;
	
	public AttachSJZSService getAttachSJZSService() {
		return attachSJZSService;
	}

	public void setAttachSJZSService(AttachSJZSService attachSJZSService) {
		this.attachSJZSService = attachSJZSService;
	}

	public FlowRefService getFlowRefService() {
		return flowRefService;
	}

	public void setFlowRefService(FlowRefService flowRefService) {
		this.flowRefService = flowRefService;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the workFlowDTO
	 */
	public WorkFlowDTO getWorkFlowDTO() {
		return workFlowDTO;
	}

	/**
	 * @param workFlowDTO the workFlowDTO to set
	 */
	public void setWorkFlowDTO(WorkFlowDTO workFlowDTO) {
		this.workFlowDTO = workFlowDTO;
	}

	/**
	 * @return the textXml
	 */
	public String getTextXml() {
		return textXml;
	}

	/**
	 * @param textXml the textXml to set
	 */
	public void setTextXml(String textXml) {
		this.textXml = textXml;
	}

	public InstanceWF getIwf() {
		return iwf;
	}

	public void setIwf(InstanceWF iwf) {
		this.iwf = iwf;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



	public Tache getTache() {
		return tache;
	}


	public SjdhMethodService getSjdhService() {
		return sjdhService;
	}

	public void setSjdhService(SjdhMethodService sjdhService) {
		this.sjdhService = sjdhService;
	}

	public WorkFlow getFlow() {
		return flow;
	}

	public void setFlow(WorkFlow flow) {
		this.flow = flow;
	}


	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getAllName() {
		return allName;
	}

	public void setAllName(String allName) {
		this.allName = allName;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	
	public Page<Template> getTempPage() {
		return tempPage;
	}

	public void setTempPage(Page<Template> tempPage) {
		this.tempPage = tempPage;
	}

	public List<Tache> getTacheList() {
		return tacheList;
	}


	public DotLineService getWfsi() {
		return wfsi;
	}

	public String getSon_str() {
		return son_str;
	}

	public void setSon_str(String sonStr) {
		son_str = sonStr;
	}

	public String getTachememo() {
		return tachememo;
	}

	public void setTachememo(String tachememo) {
		this.tachememo = tachememo;
	}

	public void setWfsi(DotLineService wfsi) {
		this.wfsi = wfsi;
	}

	/**
	 * @return the line
	 */
	public Line getLine() {
		return line;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(Line line) {
		this.line = line;
	}

	/**
	 * @return the linePropties
	 */
	public String getLinePropties() {
		return linePropties;
	}

	/**
	 * @param linePropties the linePropties to set
	 */
	public void setLinePropties(String linePropties) {
		this.linePropties = linePropties;
	}

	public WorkFlowService getWorkFlowService() {
		return workFlowService;
	}

	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}

	/**
	 * @return the employeeService
	 */
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	/**
	 * @param employeeService the employeeService to set
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}



	


	public String toFlowPage(){
		return "success";
	}

	/**
	 * 
	 * @return
	 */
	public String saveWorkFlow() {
		/*
		 * 1将模版数据保存在sys_template
		 * 2保存环节属性，最少保存三条数据到sys_tache   解析数据中的 X  Y坐标
		 * 3保存连接线数据到  sys_line 表中
		 */
		
		String tempInfo =flow.getFlowMain();

		////获取模版数据信息
		getTemplateInfo(tempInfo);  //为对象赋值
		template.setFlowmain(tempInfo);
		
		////获取环节的数据
		String tacheInfo =flow.getDotProp();
		String [] tacheProps =tacheInfo.split(";");   
		for(int i =1;i<tacheProps.length;i++){
		    String tacheProp=tacheProps[i]; //;1,30,175,,开始,1
		    String [] props=tacheProp.split(",");
		    //判断环节是编号是 1  和  2 。 因为1 和2 是开始和结束环节。于自定义的环节属性处理方式不一个样。
		    Tache t = new Tache();
		    if(props[0].equals("1")||props[0].equals("2")){
		    	//开始和结束环节，只有一下几个属性值
		    	t.setTache_id(Integer.parseInt(props[0]));
		    	t.setXposition(Integer.parseInt(props[1]));
		    	t.setYposition(Integer.parseInt(props[2]));
		    	t.setTache_name(props[4]);
		    	t.setDotType(Integer.parseInt(props[5]));
		    }else{
		    	//自定义环节和系统环节有区别。除了编号的可以获取，大部分字段都是从字符串中获取到的。
		    	//;3,237,152,*tache_id=3*tache_type=0*tache_name=sdfsd*tache_desc=*emp_name=张三*emp_ids=zs*dottype=0*days=0*hours=0*minutes=0*tag=*is_back=,sdfsd,1
		    	//t.setTache_id(Integer.parseInt(props[0]));
		    	t.setXposition(Integer.parseInt(props[1]));
		    	t.setYposition(Integer.parseInt(props[2]));
		    	t.setDotType(Integer.parseInt(props[5]));
		    	
		    	getTacheInfo(t,props[3]);
		    	t.setTache_id(Integer.parseInt(props[0]));
		    }
		    tacheList.add(t);
		}
		////获取连接线的属性    以下两个属性转换成数组，长度对应
		String lineLikeList=flow.getLineLikeList();   //;1,imgs1,imgs3;2,imgs3,imgs2 连接线点的位置。
		String lineDotList=flow.getLineDotList();	   //_line1;53,198;202,213  连接线前端纵横坐标，后端纵横坐标
		String lineProp =flow.getLineProp();          //;1,,,1;2,,,1    判断线路类
		
		String [] lineLikes =lineLikeList.split(";");
		String [] lineDots =lineDotList.split("_");
		String [] lineProps =lineProp.split(";");
		
		for(int i =1;i<lineLikes.length;i++){
			Line line =new Line();
			line.setLink_path(lineDots[i]);
			String linelink =lineLikes[i];        //1,imgs1,imgs3;
			String[]lines =linelink.split(",");   //1    imgs1   imgs3;
			String lprop[] =lineProps[i].split(",");
			line.setLine_id(Integer.parseInt(lines[0]));  
			line.setTache_id(Integer.parseInt(lines[1].substring(4)));
			line.setEnd_tache_id(Integer.parseInt(lines[2].substring(4)));
			//*lineLink_role=7B177256-B7A4-461F-B10C-E80A3255A693_A3645FA4-62CE-48C3-9887-55D4DEA6629*lineLink_props=11111111111
			if(!lprop[1].equals("")){
				String[]linePropties =lprop[1].replaceAll("[*]", ",").split(",");
				
				line.setLineLink_role(linePropties[1].replace("lineLink_role=", ""));
				
				line.setLineLink_props(linePropties[2].replace("lineLink_props=", ""));
			}
			
			line.setLine_type(Integer.parseInt(lprop[3]));
			lineList.add(line);
		}
		
		wfsi.save(template,tacheList,lineList);
		
		
		//更新审计助手的编号
		
		sjdhService.updateTempNO(type, template.getTemplate_no());
		
		return "success";
	}

	
	/**
	 * 类描述：设置连接线的使用权限
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-7 下午02:26:45 
	 * 修改人：
	 * 修改时间：2012-8-7 下午02:26:45  
	 * 修改备注：   
	 * @version   1.0     
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("deprecation")
	public String toSetLineInfo() throws UnsupportedEncodingException{
		linePropties = java.net.URLDecoder.decode(java.net.URLDecoder.decode(linePropties, "UTF-8"));
		return"toLinePage";
	}
	
	
	
	/**
	 * 
	 *创建人：郑辉 
	 *描述： 用来获取可视化加载工作流模版对象 
	 *创建时间：2012-4-1 上午11:13:30 
	 *修改人：郑辉 
	 *修改时间： 返回类型：
	 * String
	 */
	public String toAddTemplate() {
		String tempInfo = this.getRequest().getParameter("tempInfo");
		System.out.println("tempInfo=" + tempInfo);
		try {
			tempInfo = java.net.URLDecoder.decode(tempInfo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		if ("" != tempInfo) {
			// 如果不为空，解析参数，并将数据传递个新页面
			getTemplateInfo(tempInfo);
		} else {

			int templateNo = wfsi.getTemplateMax();
			template.setTemplate_no(templateNo);
		}
		return "toTempPage";
	}

	/**
	 *创建人：郑辉
	 *描述： 	用来保存环节的临时信息
	 *创建时间：2012-4-6 下午06:26:32
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String toAddTacheInfo() {
		String tacheInfo =tachememo;
		try {
			allName = java.net.URLDecoder.decode(allName, "UTF-8");
			tacheInfo = java.net.URLDecoder.decode(tacheInfo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(DataUtil.getStackTraceAsString(e));;
		}
		if ("" != tacheInfo) {
			// 如果不为空了，初始化环节属性,从数据库加载相关属性，然后跳转到环节添加页面
			// 通过前台的参数描述环节属性
			getTacheInfo(tache,tacheInfo);			
		} else {
			// 如果为空，添加环节属性,直接跳转到环节属性添加的页面 新打开的页面要添加上 环节编号

			tache.setTache_id(Integer.parseInt(num));
		}
		return "toTachePage";
	}
	
	public String toTachePro(){
		try {
			allName = java.net.URLDecoder.decode(allName, "UTF-8");
			tachememo = java.net.URLDecoder.decode(tachememo, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fMain";
	}
	
	/**
	 *创建人：郑辉
	 *描述： 	通过传递过来的参数为环节对象赋值
	 *创建时间：2012-4-6 上午11:29:21
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	public void getTacheInfo(Tache t,String tacheInfo){
		tacheInfo=tacheInfo.replace("*", ",");
		String[] tacheProps = tacheInfo.split(",");
		for (int i = 1; i < tacheProps.length; i++) {
			String tacheProp = tacheProps[i];
			String key = tacheProp.substring(0, tacheProp.indexOf("="));
			String value = tacheProp.substring(tacheProp.indexOf("=") + 1);
			if ("tache_id".equals(key)) {
				t.setTache_id(Integer.parseInt(value));
				continue;
			}
			if ("tache_type".equals(key)) {
				if(""==value) value="0";
				t.setTache_type(Integer.parseInt(value));
				continue;
			}
			if ("tache_name".equals(key)) {
				t.setTache_name(value);
				continue;
			}
			if ("tache_desc".equals(key)) {
				t.setTache_DESCRIPTION(value.replaceAll("_", ","));
				continue;
			}
			if ("emp_name".equals(key)) {
				t.setEmp_names(value);
				continue;
			}

			if ("emp_ids".equals(key)) {
				t.setGuid(value);
				continue;
			}
			if ("dottype".equals(key)) {
				if(("").equals(value)||("0").equals(value)) value="1";
				t.setDotType(Integer.parseInt(value));
				continue;
			}
			if ("days".equals(key)) {
				if(""==value) value="0";
				t.setDays(value);
				continue;

			}
			if ("hours".equals(key)) {
				if(""==value) value="0";
				t.setHours(value);
			}
			if ("minutes".equals(key)) {
				if(""==value) value="0";
				t.setMinutes(value);
				continue;
			}

			if ("tag".equals(key)) {
				t.setMemo(value);
				continue;
			}

			
			if ("is_back".equals(key)) {
				if(""==value) value="0";
				t.setIs_back(value);
				continue;
			}
			
			if ("model".equals(key)) {
				if(""==value) value="0";
				t.setNameModel(Integer.parseInt(value));
				continue;
			}
			
			if ("roles_id".equals(key)) {
				t.setRoles_id(value);
				continue;
			}
			if ("roles_name".equals(key)) {
				t.setRoles_name(value);
				continue;
			}
			
			if("attach".equals(key)){
				t.setAttach(value);
				continue;
			}
		}
	}
	
	
	
	/**
	 *创建人：郑辉
	 *描述：  为模版属性建立临时数据
	 *创建时间：2012-4-7 上午10:52:55
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： void
	 */
	public  void getTemplateInfo(String tempInfo){
		tempInfo=tempInfo.replace("*", ",");
		String[] props = tempInfo.split(",");
		String no = "0";
		String name = "";
		String info = "";
		String time = "";
		String idx = "0";
		for (int i = 1; i < props.length; i++) {
			String prop = props[i];
			String key = prop.substring(0, prop.indexOf("="));
			String value = prop.substring(prop.indexOf("=") + 1);
			if ("tempno".equals(key)) {
				no = value;
			}
			if ("tempname".equals(key)) {
				name = value;
			}
			if ("tempinfo".equals(key)) {
				info = value;
			}
			if ("temptime".equals(key)) {
				if(value.equals("")) value=DataUtil.getNowTime();
				time = value;
			}

			if ("tempcode".equals(key)) {
				System.out.println(idx);
				idx = key;
			}
			System.out.println(prop + "i=" + i);
		}
		template.setTemplate_no(Integer.parseInt(no));
		template.setTemplate_name(name);
		template.setDescription_info(info);
		template.setCreate_time(time);
		template.setOrder_code(0);
	}
	
	/**
	 *创建人：郑辉
	 *描述：  加载所有的模版实例
	 *创建时间：2012-4-7 上午10:53:15
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String getTempList(){
		tempPage =wfsi.getAllTemplate(template);
		return "chooseTemp";
	}
	
	public String getTempListInfo(){
		tempPage =wfsi.getAllTemplate(template);
		return "getTempId";
	}
	
	/**
	 *创建人：郑辉
	 *描述：单选选中模版实例，为工作流对象初始化数据 	
	 *创建时间：2012-4-7 上午10:53:35
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String getTempInfo(){
		String id = workFlowDTO.getId();
		if(null!=workFlowDTO.getId() && !"".equals(workFlowDTO.getId())){
			workFlowDTO =this.workFlowService.getWorkFlowInfo(workFlowDTO.getId());
			if(workFlowDTO!=null){
				dataStr = buildDataStr(workFlowDTO.getId());
			}
		}
		if(workFlowDTO == null){
			workFlowDTO = new WorkFlowDTO();
			workFlowDTO.setId(id);
		}
		return "success";
	}
	
	public String getTempListToChoose(){
		tempPage =wfsi.getAllTemplate(template);
		return "choose";
	}
	

	
	/**
	 *创建人：郑辉
	 *描述：创建出工作流的实例对象 	
	 *创建时间：2012-4-9 上午11:05:49
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String createJob(){
		Employee emp =(Employee)this.getSession().getAttribute("loginEmpl");
		int insathce_id =workFlowService.createJob(template,emp);
		flow=workFlowService.initJob(insathce_id,emp,flow);
		return "";
	}
	/**
	 *创建人：郑辉
	 *描述：初始化工作流页面 	
	 *创建时间：2012-4-9 下午01:56:26
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String initJob(){
		Employee emp =(Employee)this.getSession().getAttribute("loginEmpl");
		flow=workFlowService.initJob(flow.getInstance_no(),emp,flow);
		return null;
	}
	
	public String initFlowView(){
		if(null!=workFlowDTO.getId() && !"".equals(workFlowDTO.getId())){
			workFlowDTO =this.workFlowService.getWorkFlowInfo(workFlowDTO.getId());
			if(workFlowDTO!=null)
				dataStr = buildDataStr(workFlowDTO.getId());
		}
		return "flowview";
	}
	/**
		前台用
	*/
	public String initFlowView(WorkFlowDTO workFlowDTO){
		if(null!=workFlowDTO.getId() && !"".equals(workFlowDTO.getId())){
			this.workFlowDTO =this.workFlowService.getWorkFlowInfo(workFlowDTO.getId());
			getRequest().setAttribute("workFlowDTO",this.workFlowDTO);
			if(this.workFlowDTO!=null)
				dataStr = buildDataStr(workFlowDTO.getId());
			
		}
		return dataStr;
	}
	/**
	 *创建人：郑辉
	 *描述：删除流程模板以及流程和环节
	 *创建时间：2012-4-23 上午10:46:13
	 *修改人：郑辉
	 *修改时间：
	 *返回类型： String
	 */
	public String delteTemplate(){
		wfsi.delteTemplateAndTacheAndLine(template.getTemplate_no());
		//workFlowService.deleteInstanceAndTask(template.getTemplate_no());
		return "success";
	}
	/**
	 * 类描述：获取连接线的属性设置
	 * 创建人：郑辉  <br/>
	 * 创建时间：2012-8-9 下午02:19:07 
	 * 修改人：
	 * 修改时间：2012-8-9 下午02:19:07  
	 * 修改备注：   
	 * @version   1.0     
	 */
	public String linePower(String lineNo,String tempNo){
		String lineP="";
		Line powerLine = workFlowService.getLineInfoByLineId(tempNo, lineNo);
		lineP=powerLine.getLineLink_props();
		return lineP;
	}
	
	public void addAttathId(){
		String attachId=getRequest().getParameter("attachId").toString();
		wfsi.updateAttatch(template.getTemplate_no()+"", tache.getTache_id()+"", attachId);
	}
	
	public String initTacheInfo(){
		tache = wfsi.getTache(template.getTemplate_no(),tache.getTache_id());
		return "showTache";
	}
	
	public String getSjList(){
		son_str = sjdhService.showSJFG();
		return "toShMain";
	}
	
	public String getSjListAnysc() {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		son_str = this.sjdhService.showSJFGAnysc(root);
		out.print(son_str);
		out.flush();
		out.close();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^");
		return null;
	
	}
	
	public String initTree(){
		System.out.println("initTree");
		
		return "toShMain";
	}
	
	public void saveWorkXml() {
		System.err.println("this.type=" + type + "   this.xml = " + this.textXml);
		PrintWriter out = null;
		try {
			out = getResponse().getWriter();
			if(!new XmlDTOParseLine().parseXML(textXml)){
				
				out.print("NDate");	
			}else{
					if (type == null || type.trim().equals("") || textXml == null || textXml.trim().equals("")) {
					out.print("NO");
				} else {
					WorkFlowDTO tempDto = workFlowService.findWorkXmlByType(this.type);
					int count = 0;
					boolean result = true;
					if (tempDto != null) {// 更新
						tempDto.setTextXml(textXml);
						count = workFlowService.updateWorkXml(tempDto);
						result = this.newBuildAndUpdFlowRefs(tempDto.getType(), tempDto.getId());
					} else {// 新增
						WorkFlowDTO workFlowDTO = new WorkFlowDTO();
						if(this.id == null || this.id.trim().equals("0")|| this.id.trim().equals("")){
							BaseDao baseDao = new BaseDao();
							workFlowDTO.setId(baseDao.getGUID());
						}else{
							workFlowDTO.setId(this.id);
						}
						workFlowDTO.setType(this.type);
						workFlowDTO.setTextXml(this.textXml);
						count = workFlowService.saveWorkXml(workFlowDTO);
						result = this.buildAndSaveFlowRefs(workFlowDTO.getType(), workFlowDTO.getId());
					}
					if (count > 0 && result) {
						if (tempDto == null) {// 新增
							int num = 0;
							if(this.id == null || this.id.trim().equals("0")|| this.id.trim().equals("")){
								num = sjdhService.updateSJDHType(type, workFlowDTO.getId());//回写sjzs_sjdh 的template_no字段
								if (num > 0) {
									out.print("OK");
								} else {
									out.print("NO");
								}
							}else{
								out.print("OK");
							}
						} else {
							out.print("OK");
						}
					} else {
						out.print("NO");
					}
				}}}
			 catch (Exception e) {
				out.print("NO");
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.flush();
					out.close();
				}
			}
		
	}
	
	/**
	 * 根据xmlId查出它所有节点的法规和附件信息，拼成字符串
	 * @param xmlId
	 */
	private String buildDataStr(String xmlId){
		StringBuilder sb = new StringBuilder("");
		List<FlowRef> flowRefs = flowRefService.getFlowRefByXmlId(xmlId);
		if(flowRefs!=null&&flowRefs.size()>0){
			for(FlowRef ref : flowRefs){
				sb.append(ref.getModeId() + ";").append(ref.getFangFaId() + ";").append(ref.getFangFaName() + ";")
				  .append(ref.getFangFaUrl() + ";").append(ref.getAttachId() + ";").append(ref.getAttachName() + ";")
				  .append(ref.getAttachUrl() + "^");
			}
		}
		System.out.println("***************" + sb.toString());
		return sb.toString();
	}
	
	/**
	 * 根据页面上传来的dataStr 拆分成多个FlowRef 并保存到数据库
	 * @param sjdhId
	 * @param xmlId
	 * @return
	 */
	private boolean buildAndSaveFlowRefs(String sjdhId, String xmlId){
		if(dataStr!=null&&!"".equals(dataStr)){
			boolean flag = true;
			String[] flowRefStrs = dataStr.split("\\^");
			for(int i=0;i<flowRefStrs.length;i++){
				String flowRefStr = flowRefStrs[i];
				if(flowRefStr!=""){
					String[] detals = flowRefStr.split(";");
					FlowRef flowRef = new FlowRef(sjdhId,xmlId,
							detals[0],detals[1],detals[2],detals[3],detals[4],detals[5],detals[6]);
					flag = flowRefService.addFlowRef(flowRef);
					if(flag == false)
						return false;
				}
			}
			return flag;
		}
		return true;
	}
	
	/**
	 * 导航图更新
	 * 对删除的节点同时也删除其附件；对更新的节点，如果附件变化，则删除原附件；对新增的节点进行插入
	 * @param sjdhId
	 * @param xmlId
	 * @return
	 */
	private boolean newBuildAndUpdFlowRefs(String sjdhId, String xmlId){
		// 查出该导航图的所有节点信息
		List<FlowRef> oldFlowRefs = flowRefService.getFlowRefByXmlId(xmlId);
		// 提交后新导航图的所有节点信息
		List<FlowRef> newFlowRefs = new ArrayList<FlowRef>();
		// 已插入节点数据标志
		Set<FlowRef> addFlowRefs = new HashSet<FlowRef>();
		// 把提交过来的节点信息字符串拆分后封装成FlowRef对象集合
		if (dataStr != null && !"".equals(dataStr)) {
			String[] flowRefStrs = dataStr.split("\\^");
			for (int i = 0; i < flowRefStrs.length; i++) {
				String flowRefStr = flowRefStrs[i];
				if (flowRefStr != "") {
					String[] detals = flowRefStr.split(";");
					FlowRef flowRef = new FlowRef(sjdhId, xmlId, detals[0],
							detals[1], detals[2], detals[3], detals[4],detals[5], detals[6]);
					newFlowRefs.add(flowRef);
				}
			}
		}
		// 结果信息集合
		List<Boolean> results = new ArrayList<Boolean>();
		// 单条语句执行的结果
		boolean result = false;
		// 如果原节点信息存在，则开始更新
		if (oldFlowRefs.size() > 0) {
			// 如果原节点信息存在，但新提交的节点字符串为空，则说明是把以前节点全部删除了，删除完后就直接返回
			if (dataStr == null || "".equals(dataStr)) {
				for (FlowRef oldFlowRef : oldFlowRefs) {
					String attachId = oldFlowRef.getAttachId();
					if (!" ".equals(attachId) && null != attachId) {
						result = attachSJZSService.delAttach(attachId);
						results.add(result);
					}
					result = flowRefService.delFlowRef(oldFlowRef.getId());
					results.add(result);
				}
				return !results.contains(false);
			}
			// 遍历原节点信息
			for (FlowRef oldFlowRef : oldFlowRefs) {
				FlowRef temp = oldFlowRef;
				// 如果新节点中不包含某个原节点，就把它删除，删除前先删掉节点的附件
				if (!newFlowRefs.contains(temp)) {
					if (!" ".equals(temp.getAttachId())&& !"".equals(temp.getAttachId())&& null != temp.getAttachId()) {
						result = attachSJZSService.delAttach(temp.getAttachId());
						results.add(result);
					}
					result = flowRefService.delFlowRef(temp.getId());
					results.add(result);
				} else {
					// 如果新节点中包含某个原节点，就更新原节点，更新前先更新节点附件
					for (FlowRef newFlowRef : newFlowRefs) {
						if (newFlowRef.getModeId().equals(temp.getModeId())) {
							if (!" ".equals(oldFlowRef.getAttachId())&& !newFlowRef.getAttachId().equals(temp.getAttachId())) {
								result = attachSJZSService.delAttach(oldFlowRef.getAttachId());
								results.add(result);
							}
							result = flowRefService.updFlowRef(newFlowRef);
							results.add(result);
						}
						// 如果原节点信息中不包含某个新节点，则说明该节点是新节点，插入
						if (!oldFlowRefs.contains(newFlowRef)) {
							if (!addFlowRefs.contains(newFlowRef)) {
								result = flowRefService.addFlowRef(newFlowRef);
								results.add(result);
								addFlowRefs.add(newFlowRef);
							}
						}
					}
				}
			}
		} else {
			// 如果原节点信息不存在，而新节点存在，则全部插入
			if (newFlowRefs.size() > 0) {
				for (FlowRef newFlowRef : newFlowRefs) {
					result = flowRefService.addFlowRef(newFlowRef);
					results.add(result);
				}
			}
		}
		// 判断更新结果，如果其中有一条记录执行失败，则操作失败，应回滚记录
		if (results.contains(false))
			return false;
		return true;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	

	
	
	
}
