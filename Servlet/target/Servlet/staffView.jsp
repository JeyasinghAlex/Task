<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        
    </head>
    <body>
    <h5 style = "text-align:center"><b>Admin Operations</b></h5>
			
    <div>
    	<a href="api/v0.1/departments/subjects/exams/results/average"><button class="btn btn-primary">Get students data</button></a><br><br>
    	<table>
		    <s:forEach items="${average}" var="entry">
		    	<tr>
		    		<td>Key = ${average.key}, value = ${average.value}</td>
		    	</tr>    			
			</s:forEach>
		</table>
    </div>
        <script>
        	$('#rank').on('click', function(){
        		$('#rankdiv').show();
        	});
        </script>
    </body>
</html>