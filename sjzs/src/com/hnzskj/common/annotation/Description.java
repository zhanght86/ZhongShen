/*
 * @项目名称: htglxt
 * @文件名称: Description.java
 * @日期: 2011-6-21
 * @版权: 2011 河南中审科技有限公司 
 * @开发公司或单位：河南中审科技有限公司研发部
 */

package com.hnzskj.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**        
 * 
 * 类名称：Description     <br/>
 * 类描述：方法注解，用于日志模块描述方法的执行功能<br/>
 * 创建人：苏国庆   <br/>
 * 创建时间：2011-6-21 上午08:03:01   <br/>  
 * 修改人：Administrator  <br/>
 * 修改时间：2011-6-21 上午08:03:01   <br/>  
 * 修改备注：     <br/>
 * @version   1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Description {
	public String value() default "";
}
