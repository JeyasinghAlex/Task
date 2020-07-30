<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentLogin</title>
</head>
<body>
	<form action = "api/v1/login/student" method = "post">
		Enter username : <input type = "text" name = "username"/><br>
		Enter password : <input type = "password" name = "password"/><br><br>
		<input type = "submit" value = "login">
	</form>
</body>
</html>