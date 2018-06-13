<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
	// 当前项目的根路径
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<body>
<h2>Hello World!</h2>
<a href="<%=basePath%>home/getData"> 点我看看 </a>
</body>
</html>
