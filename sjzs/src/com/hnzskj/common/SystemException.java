/*
 * @项目名称: htglxt
 * @文件名称: DeleteException.java
 * @日期: 2011-5-27
 * @版权: 2011 河南中审科技有限公司 Inc. All rights reserved.
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common;

/**        
 * 
 * 类名称：DeleteException     <br/>
 * 类描述：自定义异常处理，用于处理系统出现的运行时异常<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-5-27 上午11:14:41   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-5-27 上午11:14:41   <br/>  
 * 修改备注：     <br/>
 * @version   1.0     
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = -8740754945679740701L;

	public SystemException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SystemException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SystemException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
