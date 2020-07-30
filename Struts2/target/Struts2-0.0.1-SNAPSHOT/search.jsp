<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "s" uri = "/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>search</title>
</head>
<body>
	<s:form action = "tutorials/getTutorial" >
		<s:textfield label = "Enter the language search for" key = "language"/>
		<s:submit />
	</s:form>
</body>
</html>