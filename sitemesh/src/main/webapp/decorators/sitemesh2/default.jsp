<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<!DOCTYPE html>
<html>
<head>
<title>SiteMesh - <decorator:title default="SiteMesh2!" /></title>
<decorator:head />
</head>
<body>
	<h1>
		<decorator:title default="Welcome to SiteMesh!" />
	</h1>
	<decorator:body />
</body>
<footer>© SiteMesh2 版权所有 </footer>
</html>