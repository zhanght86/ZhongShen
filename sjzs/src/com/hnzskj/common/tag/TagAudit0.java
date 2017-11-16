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
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.hnzskj.persist.bean.flow.Tache;
import com.hnzskj.service.flow.DotLineService;


/**
 *创建人：郑辉
 *描述： 	
 *创建时间：2012-4-10 下午06:27:25
 *修改人：郑辉
 *修改时间：
 */
@SuppressWarnings("serial")
public class TagAudit0 extends RequestContextAwareTag{

	private String template_no;	//模板编号
	private String tache_id;	//当前环节编号
	private String type;		//标签的类型
	private DotLineService  dotSer =null;
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

//	public int doStartTag()throws JspException   
//    {   
//		
//    }

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
		// TODO Auto-generated method stub
		dotSer = (DotLineService)this.getRequestContext().getWebApplicationContext().getBean("dotLineService");
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
		/*
		 *	 var tacheText =document.getElementById('tache').options[document.getElementById('tache').selectedIndex].text
		 *
		 *	<script charset="utf-8" src="kindeditor/kindeditor-min.js"></script>
		 *	<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
		 */
		StringBuffer sb =new StringBuffer();
		//判断路径
		sb.append("<script type=\"text/javascript\">\n");
		sb.append("function getNextTache(val){\n");
		sb.append("var tacheText =document.getElementById('tache').options[document.getElementById('tache').selectedIndex].text\n");
		sb.append("if(tacheText!=\"请选择路径\"){ document.getElementById(\"flow.nextinfoname\").value=tacheText;}else{alert(\"请选择路径\");}\n");
		sb.append("}\n");
		//获取人员列表
		sb.append("function getPerson(){\n var url=\"notice!getOrgTree.action\";\n var menu =\"dialogWidth=800px;dialogHeight=500px;top=0;left=0;toolbar=no;location=no;titlebar=no;menubar=no\";");
		
		sb.append("var returnVal=window.showModalDialog(url,\"选择人员\",\"dialogWidth:900px;\");");
		sb.append("if(returnVal!=null){");
		sb.append(" var str3 = ArrayToString(returnVal[0]);");
		sb.append("var str4 = ArrayToString(returnVal[1]);");
		sb.append("window.document.forms[0].elements[\"flow.nextEnti_ids\"].value=str3;");
		sb.append("window.document.forms[0].elements[\"empName\"].value=str4;");
		sb.append("}else{");
		sb.append("alert(\"请选择待处理的人员！\");return;");
		sb.append("}}");
		
		
		sb.append("	function ArrayToString(arr) {");
		sb.append("var str = \"\";");
		sb.append("for (var i = 0; i < arr.length; i++) {");
		sb.append("	str +=  arr[i]+\"/\";");
		sb.append("}");
		sb.append("str = str.substring(0,str.length-1) ;");
		sb.append("return str;");
		sb.append("}");
		sb.append("</script>\n");
		
		//路径问题
		sb.append("<table border=\"0\" width=\"560px\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"border_A6C4DC\" style=\"margin-top:10px;margin-left:0px\">\n");
		sb.append("<tr>\n");
		sb.append("<td width=\"24%\" align=\"center\">\n");
		sb.append("下一环节\n");
		sb.append("</td>\n");
		sb.append("<td>\n");
		if(tachelist.size()>1){
		sb.append("<select name=\"flow.nextinfoid\"  style=\"width:328px\" onchange=\"getNextTache(this.value)\" id =\"tache\">\n");
		for(int i =0;i<tachelist.size();i++){
			Tache tache =tachelist.get(i);
			sb.append("<option value=\"").append(tache.getTache_id()).append("\">").append(tache.getTache_name()).append("</option>\n");
		} 
		sb.append("<input type=\"hidden\" id =nextname name=\"flow.nextinfoname\"  value=\"\" readonly=readonly/>\n");
		sb.append("</select>\n");
		}else{
			sb.append("<input type=\"hidden\" name=\"flow.nextinfoid\" size=\"50\" style=border:none value="+tachelist.get(0).getTache_id()+" readonly=readonly/>\n");
			sb.append("<input type=\"text\" id =nextname name=\"flow.nextinfoname\" size=\"50\" style=border:none value="+tachelist.get(0).getTache_name()+" readonly=readonly\n/>");
		}
		sb.append("</td>\n");
		sb.append("</tr>\n");
		
		
		
		
		//获取下一步人员的选择页面
		Tache tache = dotSer.getTache(Integer.parseInt(template_no), Integer
				.parseInt(tache_id));
		Tache nextTache = dotSer.getTache(Integer.parseInt(template_no),
				Integer.parseInt(tache_id)+1);
		if (null != nextTache) {

			// 用现实人员选择页面
			int nameModel = nextTache.getNameModel();
			if (nameModel == 2) {
				sb.append("<tr>\n");
				sb.append("<td align=\"center\">\n");
				sb.append("人员选择\n");
				sb.append("</td>\n");
				sb.append("<td>\n");
				sb
						.append("<input type=\"text\" id=flowEmpName name=\"empName\" size=\"30\"/><input type=\"hidden\" name=\"flow.nextEnti_ids\" /> <input type=\"button\" value=\"请选择人员\" onclick=\"getPerson()\">");
				sb.append("</td>\n");
				sb.append("</tr>\n");
			}
		}
		
		
		
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
			sb.append("<tr>\n");
			sb.append("<td align=\"center\">\n");
			sb.append("审批意见\n");
			sb.append("</td>\n");
			sb.append("<td>");
			sb.append("<textarea rows=\"6\" cols=\"60\" name=\"mes\"></textarea>\n");
			sb.append("</td>\n");
			sb.append("</tr>\n");
		}
		sb.append("</table>\n");		
		
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
