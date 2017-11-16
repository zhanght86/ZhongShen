package com.hnzskj.common.monitor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import com.hnzskj.persist.bean.system.Employee;

/**
 * session监听器，监听session关闭，当session销毁时，从ServletContext中移除此session对应的ip和Employee对象
 * @author 田玉江
 * 创建时间：2011-6-28
 *
 */
public class SessionMonitor implements HttpSessionListener{
	private static final long serialVersionUID = 1L;
	
	private static List<HttpSession> sessionContainer = new ArrayList<HttpSession>();//缓存
	private static final Logger LOGGER = Logger.getLogger(SessionMonitor.class);
	

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		sessionContainer.add(event.getSession());
	}

	/**
	 * 方法描述：session销毁时调用的方法，当session销毁时，从ServletContext中移除此session对应的ip和Employee对象
	 * @author 田玉江 
	 * 创建时间：2011-6-28
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		ServletContext servletContext = session.getServletContext();
		Employee empl = (Employee) session.getAttribute("loginEmpl");
		if(empl != null) {
			List<String> ips = (List<String>) servletContext.getAttribute("ips");
			List<Employee> empls = (List<Employee>) servletContext.getAttribute("empls");
			if(ips != null) {
				ips.remove(empl.getLoginIp());
			}
			if(empls != null) {
				empls.remove(empl);
			}
			//从集合中移除要销毁的session
			sessionContainer.remove(session);
			servletContext.setAttribute("ips", ips);
			servletContext.setAttribute("empls", empls);
			LOGGER.info("loginout:" + empl);
		}
	}
	
	/**
	 * 方法描述：如果服务端的session中已经存在当前登陆用户的信息，
	 * 将已经存在的session中的用户信息销毁<br/>
	 * 创建人：苏国庆   <br/>
	 * 创建时间：2011-7-23 上午08:46:54<br/>         
	 * @param empl
	 * @version   1.0  
	 */
	@SuppressWarnings("unchecked")
	public static void removeExistSession(Employee empl) {
		for (HttpSession curSession : sessionContainer) {
			Employee tempEmpl = (Employee) curSession.getAttribute("loginEmpl");
			if (null != tempEmpl) {
				//如果当前用户已经登陆或当前机器已经登陆了一个用户
				if (empl.equals(tempEmpl) 
						|| empl.getLoginIp().equals(tempEmpl.getLoginIp())) {
					//将seesion中用户的信息清除
					curSession.removeAttribute("loginEmpl");
					
					//向session中添加forced属性，提醒被迫下线的用户
					curSession.setAttribute("forced", true);
					
					ServletContext servletContext = curSession.getServletContext();
					List<String> ips = (List<String>) servletContext.getAttribute("ips");
					List<Employee> empls = (List<Employee>) servletContext.getAttribute("empls");
					
					//从登陆ip列表清除IP信息
					if(ips != null) {
						ips.remove(empl.getLoginIp());
					}
					
					//用户登陆列表中清除用户
					if(empls != null) {
						empls.remove(empl);
					}
				}
			}
		}
	}
}
