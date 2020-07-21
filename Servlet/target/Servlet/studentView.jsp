<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
			response.sendRedirect("studentLogin.jsp");
		}
	%>
	I am student view :<br>
	<form action = "Logout">
		Logout : <input type = "submit" value = "logout">
	</form>
	
</body>
</html>