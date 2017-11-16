/**
*com.hnzskj.web.system
 * 2012-10-13
*SqlDefineAction.java
*OA
*毛俊玲
 */
package com.hnzskj.web.system;

import com.hnzskj.service.system.SqlDefineService;
import com.hnzskj.web.BaseAction;

/**
 * @author 毛俊玲
 *2012-10-13上午09:27:14
 */
public class SqlDefineAction extends BaseAction {
	private SqlDefineService sqlDefineService;
	
	public SqlDefineService getSqlDefineService() {
		return sqlDefineService;
	}

	public void setSqlDefineService(SqlDefineService sqlDefineService) {
		this.sqlDefineService = sqlDefineService;
	}

	//存储页面中输入的sql语句
	private String sqlContent = "";
	
	public String getSqlContent() {
		return sqlContent;
	}

	public void setSqlContent(String sqlContent) {
		this.sqlContent = sqlContent;
	}

	//打开执行sql设置的页面
	public String toExecuteSqls(){
		return "toExecutePage";
	}
	//执行自定义的sql语句
	public String executeSqls(){
			int result = sqlDefineService.executeSql(sqlContent);
			if(result > 0 ){
				this.addActionMessage("执行sql语句成功！");
			}else {
				this.addActionError("执行sql语句失败！");
			}
		return "resultSuc";
	}

}
