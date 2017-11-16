package com.hnzskj.common.dwr;

import java.io.File;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.hnzskj.persist.dao.sjzs.impl.AttachSJZSDaoImpl;
import com.hnzskj.persist.dao.sjzs.impl.UpdateDataLogDaoImpl;

/**
 * 
 * 描述：js 调用后台java方法，可以获得web的相关路径<br/>
 * 创建人：wenxuanzhen <br/>
 * 创建时间：2013-4-22 <br/>
 * 修改备注： <br/>
 * 
 * @version 1.0
 */
public class DwrUtil {

	public DwrUtil() {

	}

	/**
	 * 
	 * 方法描述：根据指定的资源获取其绝对路径<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-22 上午11:52:14 <br/>
	 * 
	 * @version 1.0
	 */
	private String getRealPath(String path) {
		WebContext wContext = WebContextFactory.get();
		String realPath = wContext.getServletContext().getRealPath(path);
		return realPath;
	}

	/**
	 * 
	 * 描述：上传数据更新包时删除上传的文件<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-22 下午01:51:12 <br/>
	 * 
	 * @version 1.0
	 */
	public boolean delAttachUpdate(String name) {
		UpdateDataLogDaoImpl updateDataLogDaoImpl = new UpdateDataLogDaoImpl();
		boolean flag = false;
		String tempPath = this.getRealPath("/plugins/update/project/");
		File file = new File(tempPath + "/" + name);
		flag = file.delete();
		if (flag) {
			updateDataLogDaoImpl.deleteByName(name);
		}
		if (!file.exists()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * 描述：根据附件id 删除<br/>
	 * 创建人：wenxuanzhen <br/>
	 * 创建时间：2013-4-22 下午01:52:29 <br/>
	 * 
	 * @version 1.0
	 */
	public boolean delAttachJS(String attid) {
		int result = 0;
		AttachSJZSDaoImpl attachSJZSDaoImpl = new AttachSJZSDaoImpl();
		result = attachSJZSDaoImpl.delAttachJS(attid);
		return result > 0;
	}

	/**
	 * 
	 * @param attachname
	 * @return
	 */
	public boolean deleteByname(String attachname) {
		System.out.println(attachname);
		String updatedoc = this.getRealPath("/plugins/update/attach/");
		System.out.println(updatedoc);
		String filepath = updatedoc + "\\" + attachname;
		System.out.println(filepath);
		File attachFile = new File(filepath);
		if (!attachFile.exists()) {
			return true;
		} else {
			return attachFile.delete();
		}

	}

}
