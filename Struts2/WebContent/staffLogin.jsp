<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "s" uri = "/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>adminLogin</title>
</head>
<body>
	<s:form action = "admin/login">
		<s:textfield label = "Admin name" key = "adminId"/>
		<s:password label = "Password" key = "pwd" />
		<s:submit />
	</s:form>
</body>
</html>