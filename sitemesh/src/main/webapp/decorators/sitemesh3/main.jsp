<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>SiteMesh - <sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
</head>
<body>
	<h1>
		<sitemesh:write property='title' />
	</h1>
	<sitemesh:write property='body' />
</body>
</html>