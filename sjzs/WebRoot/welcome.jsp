<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<body background="<%=basePath %>images/welcome.png">
		<%--<img alt="欢迎使用审计助手" src="/sjzs/images/welcome.png">--%>
	</body>
</html>