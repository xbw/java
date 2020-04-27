<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>
<html>
<head>
<title>SiteMesh - <decorator:title default="Main!" /></title>
<decorator:head />
</head>
<body>
	<h1>
		<decorator:title default="Main Decorator" />
	</h1>
	<decorator:body />
</body>
</html>