package com.hnzskj.web.sjzs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.hnzskj.common.DeCompressUtil;
import com.hnzskj.common.ExcelOperate;
import com.hnzskj.common.Page;
import com.hnzskj.common.batch.BatchAttach;
import com.hnzskj.common.batch.BatchExecl;
import com.hnzskj.common.batch.ExeclUtil;
import com.hnzskj.common.batch.TraverseFile;
import com.hnzskj.persist.bean.attach.Attach;
import com.hnzskj.persist.bean.sjzs.DxyjLaw;
import com.hnzskj.persist.bean.sjzs.SjzhMenuTree;
import com.hnzskj.persist.bean.sjzs.UpdateDataLog;
import com.hnzskj.persist.util.MyDataGenerator;
import com.hnzskj.service.sjzs.AttachSJZSService;
import com.hnzskj.service.sjzs.ShenJiZhuShouService;
import com.hnzskj.service.sjzs.UpdateDataLogService;
import com.hnzskj.web.BaseAction;
import com.thoughtworks.xstream.alias.ClassMapper.Null;

@SuppressWarnings("serial")
public class ShenJiZhuShouAction extends BaseAction {

	private ShenJiZhuShouService shenJiZhuShouService = null;
	private AttachSJZSService attachSJZSService = null;
	private SjzhMenuTree sjzhMenuTree;
	private UpdateDataLogService updateDataLogService;
	private String root;
	private static String type;
	private String filedata;
	private String backDir;
	private String attIds;
	// 用于页面treeview树的生成
	private String sjzs_json_str;
	private String parentid;
	private  String menuType;
	/**
	 *flag 作为一个标志，根据前台设置的flag值判断跳转页面
	 */
	private String flag = "";

	private String newFlag = "";
	/**
	 * 法规实体类
	 */
	private DxyjLaw auditLaw = new DxyjLaw();

	private ArrayList<Attach> attachList = new ArrayList<Attach>();

	/**
	 * 分页
	 */
	private Page<DxyjLaw> page = new Page<DxyjLaw>();
	private List<DxyjLaw> auditLawList = new ArrayList<DxyjLaw>();

	private Attach attach = new Attach();
	private ArrayList<SjzhMenuTree> sjfgList = new ArrayList<SjzhMenuTree>();
	private ArrayList<SjzhMenuTree> dxyjList = new ArrayList<SjzhMenuTree>();
	private ArrayList<SjzhMenuTree> ffalList = new ArrayList<SjzhMenuTree>();
	private ArrayList<SjzhMenuTree> sjdhList = new ArrayList<SjzhMenuTree>();
	private ArrayList<SjzhMenuTree> sjsxList = new ArrayList<SjzhMenuTree>();
	private ArrayList<SjzhMenuTree> SSFAList = new ArrayList<SjzhMenuTree>();

	private UpdateDataLog updateDataLog = null;
	
	// 菜单编号，执行批量删除时使用
	private String menuCodes;

	private String parentCode;

	/**
	 * @return the sSFAList
	 */
	public ArrayList<SjzhMenuTree> getSSFAList() {
		return SSFAList;
	}

	/**
	 * @param sSFAList
	 *            the sSFAList to set
	 */
	public void setSSFAList(ArrayList<SjzhMenuTree> sSFAList) {
		SSFAList = sSFAList;
	}

	public UpdateDataLogService getUpdateDataLogService() {
		return updateDataLogService;
	}

	public void setUpdateDataLogService(
			UpdateDataLogService updateDataLogService) {
		this.updateDataLogService = updateDataLogService;
	}

	
	
	/**
	 * @return the updateDataLog
	 */
	public UpdateDataLog getUpdateDataLog() {
		return updateDataLog;
	}

	/**
	 * @param updateDataLog the updateDataLog to set
	 */
	public void setUpdateDataLog(UpdateDataLog updateDataLog) {
		this.updateDataLog = updateDataLog;
	}

	/**
	 * @return the page
	 */
	public Page<DxyjLaw> getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(Page<DxyjLaw> page) {
		this.page = page;
	}

	/**
	 * @return the auditLawList
	 */
	public List<DxyjLaw> getAuditLawList() {
		return auditLawList;
	}

	/**
	 * @param auditLawList
	 *            the auditLawList to set
	 */
	public void setAuditLawList(List<DxyjLaw> auditLawList) {
		this.auditLawList = auditLawList;
	}

	public SjzhMenuTree getSjzhMenuTree() {
		return sjzhMenuTree;
	}

	public void setSjzhMenuTree(SjzhMenuTree sjzhMenuTree) {
		this.sjzhMenuTree = sjzhMenuTree;
	}

	public String getSjzs_json_str() {
		return sjzs_json_str;
	}

	public void setSjzs_json_str(String sjzsJsonStr) {
		sjzs_json_str = sjzsJsonStr;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getMenuCodes() {
		return menuCodes;
	}

	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * @return the newFlag
	 */
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * @param newFlag
	 *            the newFlag to set
	 */
	public void setNewFlag(String newFlag) {
		this.newFlag = newFlag;
	}

	public void setMenuCodes(String menuCodes) {
		this.menuCodes = menuCodes;
	}

	public ShenJiZhuShouService getShenJiZhuShouService() {
		return shenJiZhuShouService;
	}

	public void setShenJiZhuShouService(
			ShenJiZhuShouService shenJiZhuShouService) {
		this.shenJiZhuShouService = shenJiZhuShouService;
	}

	/**
	 * @return the attachSJZSService
	 */
	public AttachSJZSService getAttachSJZSService() {
		return attachSJZSService;
	}

	/**
	 * @param attachSJZSService
	 *            the attachSJZSService to set
	 */
	public void setAttachSJZSService(AttachSJZSService attachSJZSService) {
		this.attachSJZSService = attachSJZSService;
	}

	/**
	 * @return the auditLaw
	 */
	public DxyjLaw getAuditLaw() {
		return auditLaw;
	}

	/**
	 * @param auditLaw
	 *            the auditLaw to set
	 */
	public void setAuditLaw(DxyjLaw auditLaw) {
		this.auditLaw = auditLaw;
	}

	/**
	 * @return the attachList
	 */
	public ArrayList<Attach> getAttachList() {
		return attachList;
	}

	/**
	 * @param attachList
	 *            the attachList to set
	 */
	public void setAttachList(ArrayList<Attach> attachList) {
		this.attachList = attachList;
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
	 * @return the sjfgList
	 */
	public ArrayList<SjzhMenuTree> getSjfgList() {
		return sjfgList;
	}

	/**
	 * @param sjfgList
	 *            the sjfgList to set
	 */
	public void setSjfgList(ArrayList<SjzhMenuTree> sjfgList) {
		this.sjfgList = sjfgList;
	}

	/**
	 * @return the dxyjList
	 */
	public ArrayList<SjzhMenuTree> getDxyjList() {
		return dxyjList;
	}

	/**
	 * @param dxyjList
	 *            the dxyjList to set
	 */
	public void setDxyjList(ArrayList<SjzhMenuTree> dxyjList) {
		this.dxyjList = dxyjList;
	}

	/**
	 * @return the ffalList
	 */
	public ArrayList<SjzhMenuTree> getFfalList() {
		return ffalList;
	}

	/**
	 * @param ffalList
	 *            the ffalList to set
	 */
	public void setFfalList(ArrayList<SjzhMenuTree> ffalList) {
		this.ffalList = ffalList;
	}

	/**
	 * @return the sjdhList
	 */
	public ArrayList<SjzhMenuTree> getSjdhList() {
		return sjdhList;
	}

	/**
	 * @param sjdhList
	 *            the sjdhList to set
	 */
	public void setSjdhList(ArrayList<SjzhMenuTree> sjdhList) {
		this.sjdhList = sjdhList;
	}

	/**
	 * @return the sjsxList
	 */
	public ArrayList<SjzhMenuTree> getSjsxList() {
		return sjsxList;
	}

	/**
	 * @param sjsxList
	 *            the sjsxList to set
	 */
	public void setSjsxList(ArrayList<SjzhMenuTree> sjsxList) {
		this.sjsxList = sjsxList;
	}

	public String showAll() {
		return "success";
	}

	/**
	 * 显示审计法规
	 * 
	 * @return
	 */
	public String showSJFG() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("1");
		return "menuMangePage";
	}

	/**
	 * 显示定型依据
	 * 
	 * @return
	 */
	public String showDXYJ() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("2");
		return "menuMangePage";
	}

	/**
	 * 显示方法案例
	 * 
	 * @return
	 */
	public String showFFAL() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("3");
		return "menuMangePage";
	}

	/**
	 * 显示审计导航
	 * 
	 * @return
	 */
	public String showSJDH() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("4");
		return "menuMangePage2";
	}

	/**
	 * 显示审计事项
	 * 
	 * @return
	 */
	public String showSJSX() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("5");
		return "menuMangePage";
	}

	/**
	 * 显示审计实施方案
	 * 
	 * @return
	 */
	public String showSSFA() {
		this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr("6");
		return "menuMangePage";
	}

	/**
	 * 显示导出
	 * 
	 * @return
	 */
	public String showExport() {
		//this.getAllMenuList();
		this.updateDataLog = updateDataLogService.findLatestUpdateTime();
		return "showExport";
	}
	
	/**
	 * 
	 * 方法描述: 管理员审核功能<br/>
	 * 创建人: 王鹏飞
	 * 创建时间: 2013-4-25 下午02:23:48
	 * @param
	 *
	 */
	public String checkUpload() {
		
		return "checkList";
	}
	
	public String AsyncTree() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("type********************" + type);

		switch (Integer.parseInt(type)) {
		case 1:
			// 显示审计法规
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"1", root);
			break;
		case 2:
			// 显示定型依据
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"2", root);
			break;
		case 3:
			// 显示方法案例
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"3", root);
			break;
		case 4:
			// 显示审计导航
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"4", root);
			break;
		case 5:
			// 显示审计事项
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"5", root);
			break;
		case 6:
			// 显示审计实施方案
			this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStrAsync(
					"6", root);
			break;

		}

		out.print(sjzs_json_str);
		out.flush();
		out.close();

		return null;

	}
/**
 * 动态获取迁移的树结构
 * @return
 */
	
	public String moveTree() {
		System.out.println("结果" + menuType);
		 this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr(menuType);
		 return "movePage";
	}
	
	/**
	 * 操作迁移记录
	 * @return
	 */

	public String moveNotesInfo() {
		boolean flag = false;
		// System.out.println("结果" + type);
		// this.sjzs_json_str = this.shenJiZhuShouService.getMenuJsonStr(type);
//		String sqldxyj = "update  sjzs_dxyj set parentID= " + parentid+"where id="+
//		String sql2 = "update sjzs_ffal set sort=" + parentid;
//		String sql3 = "update sjzs_sjfg set lawSort=" + parentid;
//		String sql4 = "update sjzs_ssfn set  sort=" + parentid;
		
		flag=	this.shenJiZhuShouService.moveNotesBatch(menuType, attIds, parentid);
		System.out.println(flag);
		if (flag) {
			return "movesuc";
		} else {
			return "fail";
		}
		
	}
	
	/**
	 * 显示导出
	 * 
	 * @return
	 */
	public String showExportAsync() {
		this.getAllMenuList();
		return "showExport";
	}

	private void getAllMenuList() {
		this.sjfgList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '1' ");
		this.dxyjList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '2' ");
		this.ffalList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '3' ");
		this.sjdhList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '4' ");
		this.sjsxList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '5' ");
		this.SSFAList = this.shenJiZhuShouService
				.findMenusByCondition(" where menuType = '6' ");
	}

	/**
	 * 
	 * 描述：删除菜单<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-3-26 下午01:19:59 <br/>
	 * 
	 * @version 1.0
	 */
	public String deleteMenu() {
		if (menuCodes != null && !menuCodes.trim().equals("")) {// 菜单一次只能删除一个
			SjzhMenuTree sjzhMenuTree = this.shenJiZhuShouService
					.findMenuById(menuCodes);
			boolean result = this.shenJiZhuShouService.deleteMenuByCodes(
					sjzhMenuTree.getMenuType(), menuCodes,sjzhMenuTree.getMenuParent());
			String forward = "FAIL";
			System.out.println("结果" + result);
			if (result) {
				if (sjzhMenuTree.getMenuType() == 1) {
					forward = "showSJFG";
				} else if (sjzhMenuTree.getMenuType() == 2) {
					forward = "showDXYJ";
				} else if (sjzhMenuTree.getMenuType() == 3) {
					forward = "showFFAL";
				} else if (sjzhMenuTree.getMenuType() == 4) {
					forward = "showSJDH";
				} else if (sjzhMenuTree.getMenuType() == 5) {
					forward = "showSJSX";
				} else if (sjzhMenuTree.getMenuType() == 6) {
					forward = "showSSFA";
				} else {
					forward = "FAIL";
				}
				return forward;
			}
		}
		this.addActionError("删除失败！");
		return FAIL;
	}

	public String deleteMenuAsync() {
		if (menuCodes != null && !menuCodes.trim().equals("")) {// 菜单一次只能删除一个
			SjzhMenuTree sjzhMenuTree = this.shenJiZhuShouService
					.findMenuById(menuCodes);
			boolean result = this.shenJiZhuShouService.deleteMenuByCodes(
					sjzhMenuTree.getMenuType(), menuCodes,sjzhMenuTree.getMenuParent());
			String forward = "FAIL";
			System.out.println("结果" + result);
			if (result) {
				if (sjzhMenuTree.getMenuType() == 1) {
					forward = "showSJFGAsync";
				} else if (sjzhMenuTree.getMenuType() == 2) {
					forward = "showDXYJAsync";
				} else if (sjzhMenuTree.getMenuType() == 3) {
					forward = "showFFALAsync";
				} else if (sjzhMenuTree.getMenuType() == 4) {
					forward = "showSJDHAsync";
				} else if (sjzhMenuTree.getMenuType() == 5) {
					forward = "showSJSXAsync";
				} else if (sjzhMenuTree.getMenuType() == 6) {
					forward = "showSSFAAsync";
				} else {
					forward = "FAIL";
				}
				return forward;
			}
		}
		this.addActionError("删除失败！");
		return FAIL;
	}

	/**
	 * 
	 * 描述：查询顶级菜单<br/>
	 * 创建人：wxz <br/>
	 * 创建时间：2013-1-28 下午04:10:42 <br/>
	 * 
	 * @version 1.0
	 */
	public String findMenuTopParentCode(String jmcode) {
		String parentCode = this.shenJiZhuShouService
				.findMenuParentCode(jmcode);
		String tempCode = "";
		if ("11111111".equals(parentCode)) {
			tempCode = "11111111";
			return tempCode;
		} else if ("22222222".equals(parentCode)) {
			tempCode = "22222222";
			return tempCode;
		} else if ("33333333".equals(parentCode)) {
			tempCode = "33333333";
			return tempCode;
		} else if ("44444444".equals(parentCode)) {
			tempCode = "44444444";
			return tempCode;
		} else if ("55555555".equals(parentCode)) {
			tempCode = "55555555";
			return tempCode;
		} else if ("66666666".equals(parentCode)) {
			tempCode = "66666666";
			return tempCode;
		} else {
			return findMenuTopParentCode(parentCode);
		}
	}

	public String goUpdatePage() {
		this.sjzhMenuTree = this.shenJiZhuShouService.findMenuById(sjzhMenuTree
				.getMenuId());
		return UPDATEPAGE;
	}

	public String goDaoRuPage() {
		this.sjzhMenuTree = this.shenJiZhuShouService.findMenuById(sjzhMenuTree
				.getMenuId());
		return "daoru";
	}

	/**
	 * 
	 * 方法描述：进入机构添加页面<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-5-27 下午02:50:09<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	public String goAddPage() {
		return ADDPAGE;
	}

	public String addMenu() {
		String path = FAIL;
		boolean flag = false;
		flag = this.shenJiZhuShouService.addMenu(sjzhMenuTree);
		if (flag) {
			path = ADDSUC;
			this.addActionMessage("添加成功！");
		} else {
			this.addActionError("添加失败");
		}
		return path;
	}

	/**
	 * 方法描述：修改机构信息<br/>
	 * 创建人：苏国庆 <br/>
	 * 创建时间：2011-5-27 下午02:58:23<br/>
	 * 
	 * @return
	 * @version 1.0
	 */
	public String updateMenu() {
		boolean flag = false;
		flag = this.shenJiZhuShouService.updateMenu(this.sjzhMenuTree);
		if (flag) {
			return UPDATESUC;
		}
		return FAIL;

	}
/**
 * 导入execl文件实现审计事项，定性依据的批量导入
 * @return
 */
	public String daoRuInfo() {
        String string ="fail";
		System.out.println(filedata);
        String failRowColum ="";
		boolean flag = false;
		int menuType = this.sjzhMenuTree.getMenuType();

		String menuId = this.sjzhMenuTree.getMenuId();
		String industry = this.sjzhMenuTree.getMenuName();
		String sql = "";
		if (menuType == 2) {

			System.out.println(menuId);
			sql = "insert into sjzs_dxyj(id,parentID,caption,department,lawNO,tiao,kuan,WriteDate,lawContent,updateDate) values (?,'"
					+ menuId + "',?,?,?,?,?,?,?,NOW())";
		}
		if (menuType == 5) {

			sql = "insert into sjzs_datadic(dicid,dicParentId,dicName,dicMemo,updatedate,industry,deleteflag) values (?,'"
					+ menuId + "',?,?,NOW(),'" + industry + "','0')";
		}
		System.out.println(sql);
		System.out.println("**********************");

		BatchExecl batch = new BatchExecl();
		File file = new File(filedata);
		ExcelOperate excelOperate = new ExcelOperate();
		System.out.println();
		System.out.println(filedata);
		String[][] result = null;
		System.out.println(file);
		ExeclUtil execlUtil=null;
		int numType=Integer.parseInt(type);
			
		   try {
			execlUtil =excelOperate.getData(file, 6,numType);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			result =execlUtil.getArrayData();
			int rowLength = result.length;
			if (rowLength==0) {
				string ="failfile";
				return   string;
				
			}
			for (int i = 0; i < rowLength; i++) {
				for (int j = 0; j < result[i].length; j++) {
					System.out.print(result[i][j] + "\t\t");
				}
				System.out.println();

			}

	
		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < result[i].length; j++) {
				System.out.print(result[i][j] + "\t\t");
			}
			System.out.println();
		}
		flag = batch.bath(sql, result);

			failRowColum = execlUtil.getFaileRowColum();
		
		if (flag) {
			if (failRowColum.length()!=0) {
				ServletActionContext.getRequest().setAttribute("list", failRowColum);
				string ="importSucFaileexecl";
			 return	string;
			}
			return "importSuc";
		} else {
			return "fail";
		}

	}
/**
 * 导入压缩包文件，实现审计法规，审计方法案例，实施方案的批量导入
 * @return
 */
	public String daoRuInfoAttach() {
		System.out.println("^^^^^^^^^^^^^^^^"+attIds);
		String  deletefilename[] =  attIds.split(",");
		String updatedoc = ServletActionContext.getServletContext().getRealPath("/plugins/update/attach/");
		TraverseFile traverseFile = new TraverseFile();
		Long temp = System.currentTimeMillis();
		String tempwenjia = updatedoc + "\\" + temp;
//		System.out.println(updatedoc);
//		TraverseFile.getFilesrar(updatedoc);
//		List<String> list = TraverseFile.listfileyasuo();
//		for (String str : list) {
		Map<String, String> map =null;
		try {
				DeCompressUtil.deCompress(updatedoc+"\\"+deletefilename[0], tempwenjia);
				//TraverseFile.listfileyasuo().removeAll(TraverseFile.listfileyasuo());
				 traverseFile.getFiles(tempwenjia);
				 System.out.println("文件的总数量:"+TraverseFile.listfile().size());
				 map= traverseFile.getDocSWF(TraverseFile.listfile());
				 System.out.println("符合格式的文件数量:"+map.size()*2);
				 TraverseFile.listfile().removeAll(TraverseFile.listfile());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TraverseFile.deletebyName(new File(updatedoc), deletefilename);
				return "failfile";
			}
		//}
   //  TraverseFile.getFiles(updatedoc);
	// TraverseFile.listfileyasuo().removeAll(TraverseFile.listfileyasuo());
		List<String> listfaile=	traverseFile.getFailfile();
		System.out.println("不符合格式的文件数量："+listfaile.size());
		String tempstr2=""; 
		int filesize =listfaile.size();
	 if(listfaile.size()!=0){
		
		for (String string : listfaile) {
		tempstr2 +=	string.substring(string.lastIndexOf("\\")+1)+",";
		}
		
	}
	 traverseFile.getFailfile().removeAll(traverseFile.getFailfile());
	
		if (map.size()==0) {
			TraverseFile.deletebyName(new File(updatedoc), deletefilename);
			return "failfile";
			
		}

		boolean flag = false;
		int menuType = this.sjzhMenuTree.getMenuType();

		String menuId = this.sjzhMenuTree.getMenuId();
		System.out.println(menuId);
		String sql = "";
		String sqlattach = "insert into sjzs_attach(attachId,attachName,attachContentDoc,attachContentSwf,uploadDate,updatedate,deleteflag) values (?,?,?,?,NOW(),NOW(),'0')";
		if (menuType == 1) {
			// 审计法规
			// 名称，内容简介，附件，日期；
			System.out.println(menuId);
			sql = "insert into sjzs_sjfg(lawId,lawName,lawContent,attachId,updateDate,lawDate,deleteflag,lawSort) values (?,?,?,?,NOW(),NOW(),'0','"
					+ menuId + "')";
		}
		if (menuType == 3) {
			// 审计方法案例
			// 标题，内容简介，附件，日期；
			sql = "insert into sjzs_ffal(id,title,content,attachId,updatedate,ffalDateTime,deleteflag,sort) values (?,?,?,?,NOW(),curdate(),'0','"
					+ menuId + "')";
		}
		if (menuType == 6) {
			//String industry = this.sjzhMenuTree.getMenuName();
			String filenmae=deletefilename[0];
			String tempindustry = filenmae.substring(filenmae.indexOf("_")+1,filenmae.lastIndexOf("."));
			// 实施方案
			// 名称，关键字，行业，附件，日期；
			sql = "insert into sjzs_ssfn(id,name,keyword,industry,attachid,updatedate,writedate,deleteflag,sort) values (?,?,?,'"
					+ tempindustry + "',?,NOW(),curdate(),'0','" + menuId + "')";
		}

		BatchAttach batchAttach = new BatchAttach();
		try {
			flag = batchAttach.bath(sqlattach, sql, map, menuType);
		//	TraverseFile.deleteNode(new File(updatedoc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		//	TraverseFile.deleteNode(new File(updatedoc));
			e.printStackTrace();
		}
		//List<String> listtemp2 = TraverseFile.listfile();
		//listtemp2.removeAll(listtemp2);
		TraverseFile.deletebyName(new File(updatedoc), deletefilename);
		if(flag&&filesize!=0){
			ServletActionContext.getRequest().setAttribute("list", tempstr2);
			return "importSucFaile";
		}
		if (flag) {
			return "importSuc";
		} else {
			return "fail";
		}

	}

	public String toAddAuditLaw() {
		return "addLawPage";
	}

	public void executeWork() {

		System.err.println("执行导出工作！！！");
		PrintWriter out = null;
//		String file = ServletActionContext.getServletContext().getRealPath(
//				"/plugins/update/data/新建  文件/");
//		String urlpath = ServletActionContext.getServletContext().getRealPath(
//				"WEB-INF\\config\\clientTables.properties");
//		String systempath = ServletActionContext.getServletContext()
//				.getRealPath("WEB-INF\\config\\system.xml");
		// System.out.println(urlpath+"**************88");
		// 查出最新版本的更新时间，如果存在，则导出的数据要大于数据库最新版本时间，如果不存在，则说明是第一次，导出数据要小于当前时间
		String versionDate = null;
		String whereString = null;
		String fileName = null;
		String oldVersion = "1.0";
		String newVersion = "1.1";
		UpdateDataLog updateDataLog = updateDataLogService.selectNewVersionByType(1);
		if (updateDataLog != null) {// 数据库存在版本
			versionDate = String.valueOf(updateDataLog.getUploadDate());
			oldVersion = updateDataLog.getNewVersion();
			BigDecimal d1 = new BigDecimal(String.valueOf(updateDataLog.getNewVersion()));
			BigDecimal d2 = new BigDecimal("0.1");
			newVersion = String.valueOf(d1.add(d2).doubleValue());
			fileName = "V" + oldVersion + "TOV" + newVersion + "_sql.sql";
			whereString = " UNIX_TIMESTAMP(updateDate)>UNIX_TIMESTAMP('"+ versionDate + "') ";
		} else {
			versionDate = String.valueOf(new Timestamp(System.currentTimeMillis()));
			whereString = " UNIX_TIMESTAMP(updateDate)<UNIX_TIMESTAMP('"+ versionDate + "') ";
			fileName = "V1.0TOV1.1_sql.sql";
		}
		String tempFileName = fileName.substring(0, fileName.indexOf(".sql"));
		tempFileName = tempFileName + ".zip";
		updateDataLog = new UpdateDataLog(tempFileName, oldVersion, newVersion,1);

		// String whereString = " date(updateDate)>date("+ date +") ";
		// 先查询数据库updateldatalog 得出要输出的文件名
		// String fileName = "V1.0TOV1.1_sql.sql";

		try {
			out = getResponse().getWriter();
			new MyDataGenerator().executeWork(whereString, fileName);
			updateDataLogService.addUpdateDataLog(updateDataLog);
			out.print("OK#"+versionDate);
		} catch (Exception e) {
			out.print("NO");
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String initmenutree() {
		System.out.println("init tree");
		return "initmenutree";

	}

	public String getFiledata() {
		return filedata;
	}

	public void setFiledata(String filedata) {
		this.filedata = filedata;
	}

	public String getBackDir() {
		return backDir;
	}

	public void setBackDir(String backDir) {
		this.backDir = backDir;
	}

	public String getAttIds() {
		return attIds;
	}

	public void setAttIds(String attIds) {
		this.attIds = attIds;
	}
  public boolean deleteByname(String attachname){
	  System.out.println("&&&&&&&&&&&");
	  WebContext wx = WebContextFactory.get();
	  String updatedoc =  wx.getServletContext().getRealPath("/plugins/update/attach/");
//		  ServletActionContext.getServletContext().getRealPath("/plugins/update/attach/");
	  System.out.println(updatedoc);
	  String filepath = updatedoc+"\\"+attachname;
	  System.out.println(filepath);
	  File attachFile  = new File(filepath);
	  if (!attachFile.exists()) {
		return  false;
	}else {
		return attachFile.delete();
	}
	  
  }
  
  public String diaozhuan(){
	  return "importSuc";
  }

public String getParentid() {
	return parentid;
}

public void setParentid(String parentid) {
	this.parentid = parentid;
}

public String getMenuType() {
	return menuType;
}

public void setMenuType(String menuType) {
	this.menuType = menuType;
}
  
  
}
