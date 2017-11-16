/**
 * flow
 *com.hnzskj.common.tag
 *2012-4-102012下午06:27:25
 *郑辉
 *
 */
package com.hnzskj.common.tag;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.hnzskj.persist.bean.flow.InstanceExceInfo;
import com.hnzskj.persist.bean.flow.Tache;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.flow.DotLineService;
import com.hnzskj.service.flow.WorkFlowService;


/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 下午06:27:25
 *修改人：郑辉
 *修改时间：
 */
@SuppressWarnings("serial")
public class TagAudit extends RequestContextAwareTag{

	private String template_no;	//模板编号
	private String tache_id;	//当前环节编号
	private String instance_no;
	private String type;		//标签的类型
	private DotLineService  dotSer =null;
	private WorkFlowService wfSer ; 

	public WorkFlowService getWfSer() {
		return wfSer;
	}

	public void setWfSer(WorkFlowService wfSer) {
		this.wfSer = wfSer;
	}
	public DotLineService getDotSer() {
		return dotSer;
	}

	public void setDotSer(DotLineService dotSer) {
		this.dotSer = dotSer;
	}

	public String getTemplate_no() {
		return template_no;
	}

	public void setTemplate_no(String templateNo) {
		template_no = templateNo;
	}

	public String getTache_id() {
		return tache_id;
	}

	public void setTache_id(String tacheId) {
		tache_id = tacheId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getInstance_no() {
		return instance_no;
	}

	public void setInstance_no(String instanceNo) {
		instance_no = instanceNo;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		dotSer = (DotLineService)this.getRequestContext().getWebApplicationContext().getBean("dotLineService");
		wfSer = (WorkFlowService)this.getRequestContext().getWebApplicationContext().getBean("workFlowService");
		@SuppressWarnings("unused")
		Employee employee =(Employee) ServletActionContext.getRequest().getSession().getAttribute("loginEmpl");
		Tache nowTache = dotSer.getTache(Integer.parseInt(template_no), Integer
				.parseInt(tache_id));
		//获取上一个流程模板处理环节， 不是上一个提交人。
		InstanceExceInfo beforeInsance =wfSer.getInstanceExecInfoByTask(Integer.parseInt(instance_no));
		if(beforeInsance ==null) return SKIP_BODY;
//		//环节减去一的上一环节
		Tache beforeTache =dotSer.getTache(nowTache.getTemplate_id(), beforeInsance.getBefore_tache_id());
		List<Tache> tachelist =null;
		tachelist = dotSer.getDotTacheList(Integer.parseInt(template_no), Integer.parseInt(tache_id));
		//排序
		if(tachelist!=null&&tachelist.size()>1){
			Collections.sort(tachelist, new Comparator<Tache>() {
				public int compare(Tache s1, Tache s2) {
					Integer p1 = s1.getTache_id();
					Integer p2 = s2.getTache_id();
					return p2.compareTo(p1);
				}
			});
		}
		
		
		StringBuffer sb =new StringBuffer();		
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function loadHandleInfo(){\n");
		if(!"".equals(nowTache.getTache_DESCRIPTION())&&nowTache.getTache_DESCRIPTION()!=null&&nowTache.getTache_DESCRIPTION().indexOf(",")!=-1){
			sb.append("document.getElementById(\"wfCommit\").value=\"转发相关人员办理\";\n");
			sb.append("var handText =\n");
			sb.append("\"<input type=hidden name='flow.handlePerson' value='' tacheId=-1 readonly=readonly>  "+
					"<input type=text size=50 name=flow.handlePersonName value='' tacheId=-1 readonly=true > "+
					"<input type =button value='请选择相关办理人员' onclick='getHandlePersonInfo()' tacheId=-1> \" \n");
			sb.append("document.getElementById(\"handlePerson\").style.display=\"block\";\n");			
			sb.append("document.getElementById(\"handle\").innerHTML=handText; \n");
		}else if(!"".equals(nowTache.getTache_DESCRIPTION())&&nowTache.getTache_DESCRIPTION()!=null&&nowTache.getTache_DESCRIPTION().indexOf(",")==-1){
			sb.append("document.getElementById(\"wfCommit\").value=\"转发相关人员办理\";\n");
			Employee e =wfSer.getEmplByEmplId(nowTache.getTache_DESCRIPTION());
			sb.append("var handText =\n");
			sb.append("\"<input type=hidden name='flow.handlePerson' value='"+nowTache.getTache_DESCRIPTION()+"' tacheId=-1 readonly=readonly>  "+
					"<input type=text size=50 name=flow.handlePersonName value='"+e.getEmplName()+"' tacheId=-1 readonly=true > \" \n");
			sb.append("document.getElementById(\"handlePerson\").style.display=\"block\";\n");			
			sb.append("document.getElementById(\"handle\").innerHTML=handText; \n");
		}else{}
		sb.append("}\n");
		
		sb.append("function getNextTache(getNextPerson,val){\n");
		sb.append(" var tacheText =document.getElementById('tache').options[document.getElementById('tache').selectedIndex].text\n");
		sb.append(" if(tacheText!=\"请选择环节\"){ \n");
		sb.append("  document.getElementById(\"flow.nextinfoname\").value=tacheText;\n " );
		sb.append("  document.getElementById(\"flow.nextinfoid\").value=document.getElementById('tache').value; \n" );
		sb.append("  if(val==2){\n");
		sb.append("     loadHandleInfo();\n");
		sb.append("  }else{\n");			
		sb.append("  	document.getElementById(\"handlePerson\").style.display=\"none\";\n");
		sb.append("  	document.getElementById(\"handle\").innerHTML=\"\"; \n");
		sb.append("		document.getElementById(\"wfCommit\").value=\"流程提交\";\n");
		sb.append("}}}\n");
		
		sb.append("function endInstanceFlow(){\n");
		sb.append("  document.getElementById(\"flow.nextinfoname\").value='结束';\n " );
		sb.append("  document.getElementById(\"flow.nextinfoid\").value=2;\n" );
		sb.append("document.forms[0].elements(\"flow.flowReturn\").value=\"1\";\n");
		sb.append("document.forms[0].elements(\"isScrap\").value=\"1\";\n");
		sb.append("document.forms[0].submit();\n");;
		sb.append("}\n");
		
		
		sb.append("function getPhraseInfo(){\n");
		sb.append("  var url='phrasesAction!choosePhrasesList.action?time="+new Date().getTime()+"';\n var menu =\"dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no\";\n");
		sb.append("  var returnVal=window.showModalDialog(url,\"常用语\",\"dialogWidth:500px;dialogHeight:400px\");\n");
		sb.append("  if(returnVal!=null){\n");
		sb.append("  	window.document.forms[0].elements[\"mes\"].value=returnVal;\n");
		sb.append("  }\n ");
		sb.append("}\n");
		//获取人员列表
		sb.append("function getHandlePersonInfo(){\n var url='workflow!getTacheHandleEmpl.action?handleEmp="+nowTache.getTache_DESCRIPTION()+"&time="+new Date().getTime()+"';\n var menu =\"dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no\";\n");
		sb.append("var returnVal=window.showModalDialog(url,\"选择人员\",\"dialogWidth:600px;\");");
		sb.append("if(returnVal!=null){");
		sb.append("var str3 = ArrayToString(returnVal[0]);\n");
		sb.append("var str4 = ArrayToString(returnVal[1]);\n");
		sb.append("window.document.forms[0].elements[\"flow.handlePerson\"].value=str3;\n");
		sb.append("window.document.forms[0].elements[\"flow.handlePersonName\"].value=str4;\n");
		sb.append("}}");
		sb.append("	function ArrayToString(arr) {\n");
		sb.append("var str = \"\";");
		sb.append("for (var i = 0; i < arr.length; i++) {\n");
		sb.append("str +=  arr[i]+\",\";");
		sb.append("}");
		sb.append("str = str.substring(0,str.length-1) ;\n");
		sb.append("return str;\n");
		sb.append("}\n");
		
		sb.append("function loadHTMLInfo(){\n");
		
		sb.append("}\n");
		
		sb.append("function flowReturn(){\n");
		sb.append("document.forms[0].elements(\"flow.nextinfoid\").value=\""+beforeTache.getTache_id()+"\";\n");
		sb.append("document.forms[0].elements(\"flow.nextinfoname\").value=\""+beforeTache.getTache_name()+"\";\n");
		sb.append("document.forms[0].elements(\"flow.flowReturn\").value=\"1\";\n");
		sb.append("document.forms[0].submit();\n");
		sb.append("}\n");
		sb.append("</script>\n");
		
		//路径问题
//		sb.append("<table border=\"0\" width=\"75%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"border_A6C4DC\" style=\"margin-top:10px;margin-left:0px\">\n");
//		sb.append("<tr>\n");
//		sb.append("<td width=\"24%\" align=\"center\">\n");
//		sb.append("下一环节\n");
//		sb.append("</td>\n");
//		sb.append("<td>\n");
//		if(tachelist.size()>1){
//		sb.append("<select name=\"nextinfoid\" tacheId=-1 style=\"width:328px\" onchange=\"getNextTache('getNextPerson',this.value)\" id =\"tache\">\n");
//		for(int i =0;i<tachelist.size();i++){
//			Tache tache =tachelist.get(i);
//			//找出连接线的条件设置。如果为空了，继续下边的，如果不为空，要对连接线进行判断。
//			//在当前标签页，只判断连接线是否是角色拥有
//			//通过开始环节，结束环节，以及模板来获取到连接线属性。
//			Line line =dotSer.getLineInfo(nowTache.getTemplate_id()+"", nowTache.getTache_id()+"", tache.getTache_id()+"");
//			if(line.getLineLink_role().equals("")){
//				//如果连接线的角色设置为空的时候，直接输出构建路径的下拉列表
//				sb.append("<option lineId=").append(line.getLine_id()+" tempNo=").append(line.getTemplate_id()).append(" value=\"").append(tache.getTache_id()).append("\">").append(tache.getTache_name()).append("</option>\n");
//			}else if(!line.getLineLink_role().equals("")){
//				//如果连接线不为空的时候，这个时候只验证连接线的角色设置和不是和当前环节操作人得角色一样
//				Set<Role> roles =employee.getRoles();
//				Iterator<Role> iterator= roles.iterator();
//				while(iterator.hasNext()){
//					Role r =iterator.next();
//					if(line.getLineLink_role().indexOf(r.getRoleId())!=-1){
//						sb.append("<option lineId=").append(line.getLine_id()+" tempNo=").append(line.getTemplate_id()).append(" value=\"").append(tache.getTache_id()).append("\">").append(tache.getTache_name()).append("</option>\n");
//						
//						break;
//					}
//				}	
//			}
//			
//		} 
//		sb.append("</select>\n");
//		sb.append("<input type=\"hidden\" tacheId=-1 id name=\"flow.nextinfoid\" value=\""+tachelist.get(0).getTache_id()+"\" >\n");
//		sb.append("<input type=\"hidden\" tacheId=-1 id =nextname name=\"flow.nextinfoname\"  value=\""+tachelist.get(0).getTache_name()+"\" >\n");
//		}else if(tachelist.size()==1){
//			sb.append("<input type=\"hidden\" name=\"flow.nextinfoid\" tacheId=-1 size=\"50\" style=border:none value="+tachelist.get(0).getTache_id()+">\n");
//			sb.append("<input type=\"text\" id =\"nextname\" tacheId=-1 name=\"flow.nextinfoname\" size=\"50\" style=\"border:1px solid white;\" value=\""+tachelist.get(0).getTache_name()+"\">\n" );
//		}else{
//			
//		}
//
//		sb.append("</td>\n");
//		sb.append("</tr></table>\n");
		
		//最后一个环节的办理人员
		sb.append("<table border=\"0\" id=\"handlePerson\" width=\"75%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"border_A6C4DC\" style=\"margin-top:10px;margin-left:0px;display:none\">\n");
		sb.append("<tr>\n");
		sb.append("<td width=\"24%\" align=\"center\">\n");
		sb.append("办理人员\n");
		sb.append("</td>\n");
		sb.append("<td>\n");
		sb.append("<div id=\"handle\"></div>\n");
		sb.append("</td>\n");
		sb.append("</tr></table>\n");
		//报废标示
		sb.append("<input type=hidden id='isScrap' name='isScrap' value='0'>");
		
		
		Tache tache = dotSer.getTache(Integer.parseInt(template_no), Integer
				.parseInt(tache_id));
		@SuppressWarnings("unused")
		Tache nextTache = dotSer.getTache(Integer.parseInt(template_no),
				Integer.parseInt(tache_id)+1);
		
		
		//用来显示审批意见多行文本框
		String tag =tache.getMemo();

		String [] props =tag.split("/");
		String option ="";
		for(int i =0;i<props.length;i++){  //     审批=部门审批
			String  keys =props[i];
			if(props[i].indexOf("审批")==0){
				option=keys;
			}
		}
		String key="";
		if (!option.equals("")) {
			String[] keys = option.split("=");
			key = keys[0];
			@SuppressWarnings("unused")
			String value = keys[1];
		}
		if (key.equals("审批")) {
			sb.append("<table border=\"0\" width=\"75%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"border_A6C4DC\" style=\"margin-top:1px;margin-left:0px\"><tr>\n");
			sb.append("<td width=\"24%\" align=\"center\">\n");
			sb.append(tache.getTache_name());
			sb.append("</td>\n");
			sb.append("<td>");
			sb.append("<textarea rows=\"6\" id=\"mes\" tacheId=-1 cols=\"70\" name=\"mes\"></textarea><input type=\"button\" tacheId=-1 value=\"常用语\" onclick=\"getPhraseInfo()\">\n");
			sb.append("</td>\n");
			sb.append("</tr></table>\n");
		}		
		JspWriter out = pageContext.getOut();
		try {
			if (!type.equals("history")) {
				out.println(sb.toString()); // 页面中显示的内容
			}
		} catch (IOException e) {
			throw new JspException(e);
		}
        return SKIP_BODY; //不包含主体内容   
	} 
}
