/*@目名称：lwsj
 *@文件名：SearchAction.java
 *@期：下午04:09:29
 *@权：2011 河南中审科技有限公司
 *@开发公司或单位:河南中审科技有限公司
 */
package com.hnzskj.web.fore;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.CommonsHttpSolrServer;
import org.apache.solr.client.solrj.request.AbstractUpdateRequest;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;

import com.hnzskj.common.BaseDao;
import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.common.solr.SolrTools;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.flow.Tache;
import com.hnzskj.persist.bean.flow.Template;
import com.hnzskj.persist.bean.flow.WorkFlow;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.QueryResult;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.bean.sjzs.WorkFlowDTO;
import com.hnzskj.service.flow.DotLineService;
import com.hnzskj.service.fore.ClientUploadService;
import com.hnzskj.service.fore.SearchService;
import com.hnzskj.web.BaseAction;
import com.hnzskj.web.flow.WorkFlowAction;
import com.hnzskj.web.sjzs.AttachSJZSAction;

/**
 *类名称:SearchAction 类描述：<br/>
 *创建人： 平西强<br/>
 *创建时间:2013-3-4下午04:19:17<br/>
 *修改人: Administrator<br/>
 *修改时间:2013-3-4下午04:19:17<br/>
 *修改备注: <br/>
 * 
 * @version 1.0
 *@param <E>
 */
@SuppressWarnings("serial")
public class SearchAction extends BaseAction {

	private final String NULL = "none";

	private String key = "";
	private String searchType = "";
	private String showType = "";
	private Law law = new Law();
	private DxyjLaw dxyjLaw = new DxyjLaw();
	private Attach attach = new Attach();
	private String attachContent = "";
	private String imageFileName = "";
	private FangFaAnLi fangFaAnLi = new FangFaAnLi();
	private SjdhMethod sjdhMethod = new SjdhMethod();
	private DataDicDTO sjsxMethod = new DataDicDTO();
	private ShiShiFangAnDTO ssfaMethod = new ShiShiFangAnDTO();
	private QueryResult resultList = new QueryResult();
	private ClientUploadDTO clientUpload =new ClientUploadDTO();

	private Page<QueryResult> page = new Page<QueryResult>();
	private AttachSJZSAction attachSJZSAction;
	// 流程图一块
	private WorkFlowAction workFlowAction;
	private WorkFlowDTO workFlowDTO = new WorkFlowDTO();
	private String dataStr = "";

	private String id = null;
	private SearchService searchService;
	private ClientUploadService clientUploadService;

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	/** 处理关键字临时字段 */
	private String tempKey;

	/**
	 * 处理关键字临时字段
	 * 
	 * @return the tempKey
	 */
	public String getTempKey() {
		return tempKey;
	}

	/**
	 * 处理关键字临时字段
	 * 
	 * @param tempKey
	 *            the tempKey to set
	 */
	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		if (showType == null || "".equals(showType)) {
			this.showType = "sy";
		} else {
			this.showType = showType;
		}
	}

	// 工作流实例用来保存可视化界面的参数
	public WorkFlow flow = new WorkFlow();
	// 工作流模版实例
	public Template template = new Template();
	public Tache tache = new Tache();
	private DotLineService wfsi;

	/**
	 * @return the flow
	 */
	public WorkFlow getFlow() {
		return flow;
	}

	/**
	 * @param flow
	 *            the flow to set
	 */
	public void setFlow(WorkFlow flow) {
		this.flow = flow;
	}

	/**
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(Template template) {
		this.template = template;
	}

	/**
	 * @return the tache
	 */
	public Tache getTache() {
		return tache;
	}

	/**
	 * @param tache
	 *            the tache to set
	 */
	public void setTache(Tache tache) {
		this.tache = tache;
	}

	/**
	 * @return the wfsi
	 */
	public DotLineService getWfsi() {
		return wfsi;
	}

	/**
	 * @param wfsi
	 *            the wfsi to set
	 */
	public void setWfsi(DotLineService wfsi) {
		this.wfsi = wfsi;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the attach
	 */
	public Attach getAttach() {
		return attach;
	}

	/**
	 * @param attach
	 *            the attach to set
	 */
	public void setAttach(Attach attach) {
		this.attach = attach;
	}

	/**
	 * @return the attachContent
	 */
	public String getAttachContent() {
		return attachContent;
	}

	/**
	 * @param attachContent
	 *            the attachContent to set
	 */
	public void setAttachContent(String attachContent) {
		this.attachContent = attachContent;
	}

	/**
	 * @return the imageFileName
	 */
	public String getImageFileName() {
		return imageFileName;
	}

	/**
	 * @param imageFileName
	 *            the imageFileName to set
	 */
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	/**
	 * @return the law
	 */
	public Law getLaw() {
		return law;
	}

	/**
	 * @param law
	 *            the law to set
	 */
	public void setLaw(Law law) {
		this.law = law;
	}

	/**
	 * @return the dxyjLaw
	 */
	public DxyjLaw getDxyjLaw() {
		return dxyjLaw;
	}

	/**
	 * @param dxyjLaw
	 *            the dxyjLaw to set
	 */
	public void setDxyjLaw(DxyjLaw dxyjLaw) {
		this.dxyjLaw = dxyjLaw;
	}

	/**
	 * @return the fangFaAnLi
	 */
	public FangFaAnLi getFangFaAnLi() {
		return fangFaAnLi;
	}

	/**
	 * @param fangFaAnLi
	 *            the fangFaAnLi to set
	 */
	public void setFangFaAnLi(FangFaAnLi fangFaAnLi) {
		this.fangFaAnLi = fangFaAnLi;
	}

	/**
	 * @return the sjdhMethod
	 */
	public SjdhMethod getSjdhMethod() {
		return sjdhMethod;
	}

	/**
	 * @param sjdhMethod
	 *            the sjdhMethod to set
	 */
	public void setSjdhMethod(SjdhMethod sjdhMethod) {
		this.sjdhMethod = sjdhMethod;
	}

	public DataDicDTO getSjsxMethod() {
		return sjsxMethod;
	}

	public void setSjsxMethod(DataDicDTO sjsxMethod) {
		this.sjsxMethod = sjsxMethod;
	}

	public ShiShiFangAnDTO getSsfaMethod() {
		return ssfaMethod;
	}

	public void setSsfaMethod(ShiShiFangAnDTO ssfaMethod) {
		this.ssfaMethod = ssfaMethod;
	}

	/**
	 * @return the resultList
	 */
	public QueryResult getResultList() {
		return resultList;
	}

	/**
	 * @param resultList
	 *            the resultList to set
	 */
	public void setResultList(QueryResult resultList) {
		this.resultList = resultList;
	}

	/**
	 * @return the page
	 */
	public Page<QueryResult> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<QueryResult> page) {
		this.page = page;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		if (key != null) {
			this.key = key.trim();
		}
		/* 修改trim后 */
	}

	/**
	 * @return the attachSJZSAction
	 */
	public AttachSJZSAction getAttachSJZSAction() {
		return attachSJZSAction;
	}

	/**
	 * @param attachSJZSAction
	 *            the attachSJZSAction to set
	 */
	public void setAttachSJZSAction(AttachSJZSAction attachSJZSAction) {
		this.attachSJZSAction = attachSJZSAction;
	}

	public WorkFlowAction getWorkFlowAction() {
		return workFlowAction;
	}

	public void setWorkFlowAction(WorkFlowAction workFlowAction) {
		this.workFlowAction = workFlowAction;
	}

	public WorkFlowDTO getWorkFlowDTO() {
		return workFlowDTO;
	}

	public void setWorkFlowDTO(WorkFlowDTO workFlowDTO) {
		this.workFlowDTO = workFlowDTO;
	}
	
	/**
	 * @return the searchService
	 */
	public SearchService getSearchService() {
		return searchService;
	}

	/**
	 * @param searchService
	 *            the searchService to set
	 */
	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}
	
	
	public ClientUploadService getClientUploadService() {
		return clientUploadService;
	}

	public void setClientUploadService(ClientUploadService clientUploadService) {
		this.clientUploadService = clientUploadService;
	}

	public ClientUploadDTO getClientUpload() {
		return clientUpload;
	}

	public void setClientUpload(ClientUploadDTO clientUpload) {
		this.clientUpload = clientUpload;
	}

	/**
	 *方法描述：检查用户是否有输入 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午09:30:11
	 * 
	 * @return
	 *@version 1.0
	 */
	public boolean checkNull() {
		if ("".equals(key.trim()) || key == null) {
			this.setKey(null);
			this.setPage(null);
			writeSession();
			setSessionAttr("noUser", null);
			return true;
		} else {
			writeSession();
			return false;
		}
	}

	/**
	 *方法描述：检查用户是否登录 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午04:00:11
	 * 
	 * @return
	 *@version 1.0
	 */
	public boolean checkUser() {
		if (getSession().getAttribute("employee") == null) {
			setSessionAttr("noUser", "用户没有登录，请先登录！");
			page = null;
			return true;
		} else {
			setSessionAttr("noUser", null);
			return false;
		}
	}

	/**
	 *方法描述：查找所有类型 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-6上午09:30:18
	 * 
	 * @return
	 *@version 1.0
	 */
	/*
	 * public String searchAll() { if (checkNull()) { return showInfo(); } else
	 * if (checkUser()) { return "noUser"; } else { page =
	 * this.searchService.searchAllByKey(key, page); writeSession(); return
	 * "list"; } }
	 */
	/**
	 * 
	 * 描述：加入solr 检索功能 创建人：丁艳伟 2013-3-25下午03:23:43
	 */
	@SuppressWarnings("deprecation")
	public String searchAll() throws MalformedURLException, SolrServerException {
		tempKey = key;
		String tempType = searchType;
		if (checkNull()) {
			return showInfo();
		} else if (checkUser()) {
			return "noUser";
		} else {
			SolrServer server = new CommonsHttpSolrServer(Constant.SOLRSERVER);
			ModifiableSolrParams params = new ModifiableSolrParams();
			// 过滤非法字符。
			tempKey = new BaseDao().strFilter(tempKey);
			if (tempKey.equals("All") || null == tempKey || tempKey.equals("")) {
				tempKey = "*:*";
			} else {
				tempKey = tempKey.replaceAll(",", "");
			}
			page.setMaxResult(Constant.INDEXPAGESIZE);// 设置每页显示的数量
			// 查询关键词
			if (null != searchType && !("").equals(searchType) && searchType.equals("All")) {
				searchType = "*:*".equals(tempKey)?tempKey:"attach:"+tempKey;
			} else {
				searchType = "*:*".equals(tempKey)?"type:" + searchType :"type:" + searchType + " AND attach:"+ tempKey;
			}
			params.set("q", searchType);
			tempKey = "";
			// 分页，，start=0就是从0开始，，rows=5当前返回5条记录，，，第二页就是变化start这个值为5就可以了。
			params.set("start", (page.getCurPage() - 1) * page.getMaxResult());

			params.set("rows", page.getMaxResult()); // 每页显示的数量
			// 排序，，如果按照id 排序，，那么将score desc 改成 id desc(or asc)
			// params.set("sort", "powerName");
			// 返回信息 * 为全部 这里是全部加上score，如果不加下面就不能使用score
			params.set("fl", "*,score");
			//开启高亮
			params.set("hl", "on");
			//params.set("hl.mergeContiguous", "true");
			// 设置高亮显示字段
			params.set("hl.fl", "attach");
			
			QueryResponse response = server.query(params);
			// 搜索得到的结果数
			// System.out.println("Find:"+
			// response.getResults().getNumFound()+"\n\n");
			// 输出结果
			List<QueryResult> results = new ArrayList<QueryResult>();
			
			// 获取高亮集合
			Map<String, Map<String, List<String>>> hl = response.getHighlighting();
			SolrDocumentList list = response.getResults();
			for (SolrDocument sd : list) {
				QueryResult result = new QueryResult();
				result.setId((sd.getFirstValue("id").toString()));
				result.setType((sd.getFirstValue("type").toString()));
				result.setDate(sd.getFirstValue("updatedate").toString());
				//标题
				/*if (hl.get(sd.getFieldValue("id")).get("title") != null) {
					result.setTitle(hl.get(sd.getFieldValue("id")).get("title").get(0).toString().trim());
				}else{
					result.setTitle(sd.getFirstValue("title").toString().toString().trim());
				}*/
				result.setTitle(sd.getFirstValue("title").toString().toString().trim());
				/*//内容
				if (hl.get(sd.getFieldValue("id")).get("content") != null) {
					result.setContent(hl.get(sd.getFieldValue("id")).get("content").get(0).toString().trim());
				}else{
					result.setContent(sd.getFirstValue("content").toString().trim());
				}*/
				//附件
				if (hl.get(sd.getFieldValue("id")).get("attach") != null) {
					result.setAttach(hl.get(sd.getFieldValue("id")).get("attach").get(0).toString().trim());
				}else{
					String attach=sd.getFirstValue("attach").toString().trim();
					if(attach.length()>=200){
						result.setAttach(attach.substring(0,200));
					}else{
						result.setAttach(attach);
					}
				}
				results.add(result);
			}
			
			
			/*for (SolrDocument doc : response.getResults()) {
				QueryResult result = new QueryResult();

				result.setId((doc.getFirstValue("id").toString()));
				result.setTitle((doc.getFirstValue("title").toString()));
				if (doc.getFirstValue("content") == null) {
					result.setContent("");
				} else {
					result.setContent((doc.getFirstValue("content").toString()));
				}
				result.setType((doc.getFirstValue("type").toString()));
				result.setDate(doc.getFirstValue("updatedate").toString());
				result.setAttach(doc.getFirstValue("attach").toString());
				results.add(result);
			}*/
			page.setTotalRecords((int) response.getResults().getNumFound());
			page.setList(results);
			searchType = tempType;
			return "list";
		}
	}

	/**
	 *方法描述：显示定性依据具体内容 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午04:00:31
	 * 
	 * @return
	 *@version 1.0
	 */
	public String showContentYJ() {
		if (checkUser()) {
			return "noUser";
		}
		dxyjLaw.setId(id);
		dxyjLaw = searchService.getDxyjLaw(dxyjLaw);
		if (dxyjLaw != null) {
			return "showDxyj";
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述：显示审计法规具体内容 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午04:00:35
	 * 
	 * @return
	 *@version 1.0
	 */
	public String showContentFG() {
		if (checkUser()) {
			return "noUser";
		}
		law.setLawId(id);
		law = searchService.getSjfgLaw(law);
		if (law != null) {
			attach.setAttachId(law.getAttachId());
			attachSJZSAction.setAttach(attach);
			attachContent = attachSJZSAction.showAtttachInfo();
			setImageFileName(attachSJZSAction.getImageFileName());
			if ("suc".equals(attachContent)) {
				return "showSjfg";
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述：显示方法案例具体内容 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午04:00:39
	 * 
	 * @return
	 *@version 1.0
	 */
	public String showContentAL() {
		if (checkUser()) {
			return "noUser";
		}
		fangFaAnLi.setId(id);
		fangFaAnLi = searchService.getFangFaAnLi(fangFaAnLi);
		if (fangFaAnLi != null) {
			if(fangFaAnLi.getUploadFlag()!=null && "0".equals(fangFaAnLi.getUploadFlag())){
				clientUpload =  clientUploadService.selectUploadById(fangFaAnLi.getId(), Constant.FFAL);
			}
			attach.setAttachId(fangFaAnLi.getAttachId());
			attachSJZSAction.setAttach(attach);
			attachContent = attachSJZSAction.showAtttachInfo();
			setImageFileName(attachSJZSAction.getImageFileName());
			if ("suc".equals(attachContent)) {
				return "showFfal";
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述：显示审计导航内容（现在审计导航显示部分需要修改，这个不能用） <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午04:00:43
	 * 
	 * @return
	 *@version 1.0
	 */
	public String showContentDH() {
		if (checkUser()) {
			return "noUser";
		}
		sjdhMethod.setId(id);
		sjdhMethod = searchService.getSjdhMethod(sjdhMethod);
		if (sjdhMethod != null) {
			return "showSjdh";
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述：查看审计导航 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-8 上午11:05:06
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showDHT() {
		if (checkUser()) {
			return "noUser";
		}
		workFlowDTO.setId(id);
		dataStr = workFlowAction.initFlowView(workFlowDTO);
		Object obj = getRequest().getAttribute("workFlowDTO");
		if (obj != null) {
			workFlowDTO = (WorkFlowDTO) obj;
			return "sjdh";
		} else {
			return FAIL;
		}

	}

	/**
	 *方法描述：显示审计事项的内容 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 上午11:53:42
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showContentSX() {
		if (checkUser()) {
			return "noUser";
		}
		sjsxMethod.setDicId(id);
		sjsxMethod = searchService.getSjsxMethod(sjsxMethod);
		if (sjsxMethod != null) {
			return "showSjsx";
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述：显示审计实施方案内容 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-26 上午11:53:47
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showContentEI() {
		if (checkUser()) {
			return "noUser";
		}
		ssfaMethod.setId(id);
		ssfaMethod = searchService.getSsfaMethod(ssfaMethod);
		if (ssfaMethod != null) {
			attach.setAttachId(ssfaMethod.getAttachId());
			attachSJZSAction.setAttach(attach);
			attachContent = attachSJZSAction.showAtttachInfo();
			setImageFileName(attachSJZSAction.getImageFileName());
			if ("suc".equals(attachContent)) {
				return "showSsfa";
			} else {
				return FAIL;
			}
		} else {
			return FAIL;
		}
	}

	/**
	 *方法描述： 用作防止重复刷新（未用）<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-7下午02:50:41
	 * 
	 * @return
	 *@version 1.0
	 */
	public String initTacheInfo() {
		tache = wfsi.getTache(template.getTemplate_no(), tache.getTache_id());
		return "showTache";
	}

	/**
	 *方法描述：向session 中写入搜索关键字和搜索类型信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-15下午01:44:10
	 * 
	 * @return
	 *@version 1.0
	 */
	public void writeSession() {
		setSessionAttr("key", key);
		setSessionAttr("type", searchType);
	}

	/**
	 *方法描述：确定选择了那个信息标签（用户登录后的下半部分显示内容标签） <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-25 下午03:28:11
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showInfo() {
		String showinfo = "syInfo";
		if (showType != null && !"".equals(showType.trim())) {
			showinfo = showType + "Info";
		}
		return showinfo;
	}

	public static void main(String[] args) {
		try {
			// Solr cell can also index MS file (2003 version and 2007 version)
			// types.
			String fileName = "D://test//1.doc";
			// this will be unique Id used by Solr to index the file contents.
			String solrId = "1.doc";
			indexFilesSolrCell(fileName, solrId);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	public static void indexFilesSolrCell(String fileName, String solrId)
			throws IOException, SolrServerException {

		String urlString = "http://localhost:8983/solr";
		SolrServer solr = new CommonsHttpSolrServer(urlString);

		ContentStreamUpdateRequest up = new ContentStreamUpdateRequest(
				"/update/extract");

		up.addFile(new File(fileName));

		up.setParam("literal.id", solrId);
		up.setParam("fmap.content", "attr_content");

		up.setAction(AbstractUpdateRequest.ACTION.COMMIT, true, true);

		solr.request(up);

		QueryResponse rsp = solr.query(new SolrQuery("*:*"));

		System.out.println(rsp);
	}

	/**
	 * 
	 * 描述：创建索引 创建人：丁艳伟 2013-4-9下午03:04:02
	 */
	public String testAddIndex() {
		new SolrTools().buildIndex(false);
		return null;
	}
}
