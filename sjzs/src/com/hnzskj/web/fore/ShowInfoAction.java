package com.hnzskj.web.fore;

import java.util.ArrayList;
import java.util.List;

import com.hnzskj.common.Constant;
import com.hnzskj.common.Page;
import com.hnzskj.persist.bean.fore.ClientUploadDTO;
import com.hnzskj.persist.bean.fore.ContentMap;
import com.hnzskj.persist.bean.sjzs.DataDicDTO;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.FangFaAnLi;
import com.hnzskj.persist.bean.sjzs.Law;
import com.hnzskj.persist.bean.sjzs.ShiShiFangAnDTO;
import com.hnzskj.persist.bean.sjzs.SjdhMethod;
import com.hnzskj.persist.bean.system.Employee;
import com.hnzskj.service.fore.ClientUploadService;
import com.hnzskj.service.fore.ShowInfoService;
import com.hnzskj.service.sjzs.DataDicService;
import com.hnzskj.service.sjzs.DingXingYiJuService;
import com.hnzskj.service.sjzs.FangFaAnLiService;
import com.hnzskj.service.sjzs.ShenJiFaGuiService;
import com.hnzskj.service.sjzs.ShenJiZhuShouService;
import com.hnzskj.service.sjzs.ShiShiFangAnService;
import com.hnzskj.service.sjzs.SjdhMethodService;
import com.hnzskj.web.BaseAction;

public class ShowInfoAction extends BaseAction {

	private static final long serialVersionUID = 9062281672929730186L;
	private ShowInfoService showInfoService;
	private ShenJiZhuShouService shenJiZhuShouService;
	private ShenJiFaGuiService sjfgService;
	private DataDicService sjsxService;
	private DingXingYiJuService dxyjService;
	private FangFaAnLiService ffalService;
	private ShiShiFangAnService ssfaService;
	private SjdhMethodService sjdhService;
	
	private ClientUploadService clientUpload;
	
	private DataDicDTO sjsx;
	private DxyjLaw dxyj;
	private FangFaAnLi ffal;
	private Law sjfg;
	private SjdhMethod sjdh;
	private ShiShiFangAnDTO ssfa;
	
	private Page<DataDicDTO> pageSjsx = new Page<DataDicDTO>();
	private Page<DxyjLaw> pageDxyj = new Page<DxyjLaw>();
	private Page<FangFaAnLi> pageFfal = new Page<FangFaAnLi>();
	private Page<Law> pageSjfg = new Page<Law>();
	private Page<SjdhMethod> pageSjdh = new Page<SjdhMethod>();
	private Page<ShiShiFangAnDTO> pageSsfa = new Page<ShiShiFangAnDTO>();
	
	private Page<ClientUploadDTO> pageUpload = new Page<ClientUploadDTO>();
	
	// 用于页面treeview树的生成
	private String sjzs_json_str;

	private final String SY = "showSy";
	private final String WDZS = "showWdzs";

	/*
	 * 查询类型
	 * 如:sjdh dxyj等   
	 * init:查询最新的n条记录
	 */
	private String type;
	
	private String searchType;
	
	private int accTime = 0;

	private List<ContentMap> contentMapList = new ArrayList<ContentMap>();
	private ContentMap contentMap = new ContentMap();
	
	

	public ClientUploadService getClientUpload() {
		return clientUpload;
	}

	public void setClientUpload(ClientUploadService clientUpload) {
		this.clientUpload = clientUpload;
	}

	public ShowInfoService getShowInfoService() {
		return showInfoService;
	}

	public void setShowInfoService(ShowInfoService showInfoService) {
		this.showInfoService = showInfoService;
	}

	public List<ContentMap> getContentMapList() {
		return contentMapList;
	}

	public void setContentMapList(List<ContentMap> contentMapList) {
		this.contentMapList = contentMapList;
	}

	public ContentMap getContentMap() {
		return contentMap;
	}

	public void setContentMap(ContentMap contentMap) {
		this.contentMap = contentMap;
	}

	public int getAccTime() {
		return accTime;
	}

	public void setAccTime(int accTime) {
		this.accTime = accTime;
	}

	public String getType() {
		return type;
	}	
	
	public String getSearchType() {
		if(searchType == null){
			searchType=Constant.ALL;
		}
		return searchType;
	}

	public void setSearchType(String searchType) {
		if(searchType == null){
			searchType=Constant.ALL;
		}
		this.searchType = searchType;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 *方法描述：显示首页 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:03
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showSy() {
		contentMapList = showInfoService.getContentMaps();
		this.setType("Sy");
		return SY;
	}
	
	public String showMain(){
		return "showMain";
	}

	/**
	 *方法描述：显示审计法规 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:13
	 * 
	 *@return
	 *@version 1.0
	 */

	@SuppressWarnings("unchecked")
	public String showSjfg() {
		//如果查询类型为init，查询最新的前n条数据，并返回列表页面
		if("init".equals(type)){
			pageSjfg = this.showInfoService.getLatestMethods("sjfg", null, pageSjfg);
			return "listSjfg";
		}
		//如果sjfg为空，初始化菜单树，并返回列表主页面;否则按条件查询
		if(sjfg==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("1");
			return "showMainList";
		}else{
			pageSjfg = sjfgService.searchByCondition("*", sjfg, pageSjfg);
			return "listSjfg";
		}
//		contentMap = showInfoService.getContentSjfgByField("lawDate", 22);
//		return SJFG;
	}

	/**
	 *方法描述：显示定性依据信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:21
	 * 
	 *@return
	 *@version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String showDxyj() {
		if("init".equals(type)){
			pageDxyj = showInfoService.getLatestMethods("dxyj", null, pageDxyj);
			return "listDxyj";
		}
		if(dxyj==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("2");
			return "showMainList";
		}else{
			pageDxyj = dxyjService.searchByCondition("*", dxyj, pageDxyj);
			return "listDxyj";
		}
//		contentMap = showInfoService.getContentDxyjByField("writeDate", 22);
//		return DXYJ;
	}

	/**
	 *方法描述：显示方法案例信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:24
	 * 
	 *@return
	 *@version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String showFfal() {
		if("init".equals(type)){
			pageFfal = showInfoService.getLatestMethods("sjff", null, pageFfal);
			return "listFfal";
		}
		if(ffal==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("3");
			return "showMainList";
		}else{
			pageFfal = ffalService.searchByCondition("*", ffal, pageFfal);
			return "listFfal";
		}
//		contentMap = showInfoService.getContentFfalByField("ffalDateTime", 22);
//		return FFAL;
	}

	/**
	 *方法描述：显示审计导航信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:27
	 * 
	 *@return
	 *@version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String showSjdh() {
		if("init".equals(type)){
			pageSjdh = showInfoService.getLatestMethods("sjdh", null, pageSjdh);
			return "listSjdh";
		}
		if(sjdh==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("4");
			return "showMainList";
		}else{
			pageSjdh = sjdhService.search(pageSjdh, sjdh);
			return "listSjdh";
		}
//		contentMap = showInfoService.getContentSjdhByField("createTime", 22);
//		return SJDH;
	}

	/**
	 *方法描述： 显示审计事项<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-2 下午03:44:04
	 * 
	 *@return
	 *@version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String showSjsx() {
		if("init".equals(type)){
			pageSjsx = showInfoService.getLatestMethods("sjsx", null, pageSjsx);
			return "listSjsx";
		}
		if(sjsx==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("5");
			return "showMainList";
		}else{
			pageSjsx = sjsxService.searchByCondition("*", sjsx, pageSjsx);
			return "listSjsx";
		}
//		contentMap = showInfoService.getContentSjsxByField("updateDate", 21);
//		return SJSX;
	}

	/**
	 *方法描述： 显示审计实施方案<br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-4-2 下午03:44:07
	 * 
	 *@return
	 *@version 1.0
	 */
	@SuppressWarnings("unchecked")
	public String showSsfa() {
		if("init".equals(type)){
			pageSsfa = showInfoService.getLatestMethods("ssfa", null, pageSsfa);
			return "listSsfa";
		}
		if(ssfa==null){
			sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("6");
			return "showMainList";
		}else{
			pageSsfa = ssfaService.searchByCondition("*", ssfa, pageSsfa);
			return "listSsfa";
		}
//		contentMap = showInfoService.getContentSsfaByField("writeDate", 22);
//		return SSFA;
	}

	/**
	 *方法描述：显示我的助手信息 <br/>
	 *创建人：平西强 <br/>
	 *创建时间：2013-3-22 上午09:23:29
	 * 
	 *@return
	 *@version 1.0
	 */
	public String showWdzs() {
		Employee empl = null;
		if(getSessoinAttr("employee")==null){
			return this.showSy();
		}else{
			empl = (Employee)getSessoinAttr("employee");
			ClientUploadDTO uploadDTO = new ClientUploadDTO();
			uploadDTO.setClientId(empl.getEmplId());
			uploadDTO.setCheckFlag(Constant.CHECK_ALL);
			pageUpload.setMaxResult(10);
			pageUpload = clientUpload.searchByCondition(pageUpload, uploadDTO);
			this.setType("Wdzs");
			return WDZS;
		}
	}

	public ShenJiZhuShouService getShenJiZhuShouService() {
		return shenJiZhuShouService;
	}

	public void setShenJiZhuShouService(ShenJiZhuShouService shenJiZhuShouService) {
		this.shenJiZhuShouService = shenJiZhuShouService;
	}

	public ShenJiFaGuiService getSjfgService() {
		return sjfgService;
	}

	public void setSjfgService(ShenJiFaGuiService sjfgService) {
		this.sjfgService = sjfgService;
	}

	public DataDicService getSjsxService() {
		return sjsxService;
	}

	public void setSjsxService(DataDicService sjsxService) {
		this.sjsxService = sjsxService;
	}

	public DingXingYiJuService getDxyjService() {
		return dxyjService;
	}

	public void setDxyjService(DingXingYiJuService dxyjService) {
		this.dxyjService = dxyjService;
	}

	public FangFaAnLiService getFfalService() {
		return ffalService;
	}

	public void setFfalService(FangFaAnLiService ffalService) {
		this.ffalService = ffalService;
	}

	public ShiShiFangAnService getSsfaService() {
		return ssfaService;
	}

	public void setSsfaService(ShiShiFangAnService ssfaService) {
		this.ssfaService = ssfaService;
	}

	public SjdhMethodService getSjdhService() {
		return sjdhService;
	}

	public void setSjdhService(SjdhMethodService sjdhService) {
		this.sjdhService = sjdhService;
	}

	public DataDicDTO getSjsx() {
		return sjsx;
	}

	public void setSjsx(DataDicDTO sjsx) {
		this.sjsx = sjsx;
	}

	public DxyjLaw getDxyj() {
		return dxyj;
	}

	public void setDxyj(DxyjLaw dxyj) {
		this.dxyj = dxyj;
	}

	public FangFaAnLi getFfal() {
		return ffal;
	}

	public void setFfal(FangFaAnLi ffal) {
		this.ffal = ffal;
	}

	public Law getSjfg() {
		return sjfg;
	}

	public void setSjfg(Law sjfg) {
		this.sjfg = sjfg;
	}

	public SjdhMethod getSjdh() {
		return sjdh;
	}

	public void setSjdh(SjdhMethod sjdh) {
		this.sjdh = sjdh;
	}

	public ShiShiFangAnDTO getSsfa() {
		return ssfa;
	}

	public void setSsfa(ShiShiFangAnDTO ssfa) {
		this.ssfa = ssfa;
	}

	public Page<DataDicDTO> getPageSjsx() {
		return pageSjsx;
	}

	public void setPageSjsx(Page<DataDicDTO> pageSjsx) {
		this.pageSjsx = pageSjsx;
	}

	public Page<DxyjLaw> getPageDxyj() {
		return pageDxyj;
	}

	public void setPageDxyj(Page<DxyjLaw> pageDxyj) {
		this.pageDxyj = pageDxyj;
	}

	public Page<FangFaAnLi> getPageFfal() {
		return pageFfal;
	}

	public void setPageFfal(Page<FangFaAnLi> pageFfal) {
		this.pageFfal = pageFfal;
	}

	public Page<Law> getPageSjfg() {
		return pageSjfg;
	}

	public void setPageSjfg(Page<Law> pageSjfg) {
		this.pageSjfg = pageSjfg;
	}

	public Page<SjdhMethod> getPageSjdh() {
		return pageSjdh;
	}

	public void setPageSjdh(Page<SjdhMethod> pageSjdh) {
		this.pageSjdh = pageSjdh;
	}

	public Page<ShiShiFangAnDTO> getPageSsfa() {
		return pageSsfa;
	}

	public void setPageSsfa(Page<ShiShiFangAnDTO> pageSsfa) {
		this.pageSsfa = pageSsfa;
	}

	public String getSjzs_json_str() {
		return sjzs_json_str;
	}

	public void setSjzs_json_str(String sjzsJsonStr) {
		sjzs_json_str = sjzsJsonStr;
	}

	public Page<ClientUploadDTO> getPageUpload() {
		return pageUpload;
	}

	public void setPageUpload(Page<ClientUploadDTO> pageUpload) {
		this.pageUpload = pageUpload;
	}
	
}
