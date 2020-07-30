<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StudentOperations</title>

	<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        
    </head>
    
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
			response.sendRedirect("studentLogin.jsp");
		}
	%>
	<div class="text-right" style="padding-right:80px; padding-top : 30px">
	<form action = "Logout">
		Logout : <input type = "submit" value = "logout">
	</form>
	</div>
	
	    <div>
	   
		 <button class="btn btn-primary" id="showbtn">Get Mark </button><br><br>
            <div style="padding: 20px;display:none" id="divbtn">
                <form action = "api/v1/exam/student/id?studentid=rk" method = "GET">
                	Enter Id : <input type="text" name="studentid" value=""/>
        			<input type="submit" value="submit">
                </form>
            </div >
		
		<table class = "table" style="width:50%">
				<thead>
				   <tr>
					    <th>Student Id</th>
					    <th>Student Name</th>
					    <th>Student Department</th>
					    <th>Subject Name</th>
					    <th>Subject Mark</th>
					    <th>Staff Name</th>					    
				   </tr>
		        </thead>
		        <tbody>
				    <s:forEach items="${listStudents}" var="student">
				    	<td>${student.id}</td>
				    	<td>${student.name}</td>
				    	<td>${student.department}</td>	
				    	<s:forEach items = "${student.subjects}" var = "subject">
					    	<tr>
				        		  <td></td>
				        		  <td></td>
				        		  <td></td>
					    		 <td>${subject.name}</td>					    		 
					    		 <td>${subject.mark}</td>
					    		 <td>${subject.staff}</td>
					    	</tr> 				    	
				    	</s:forEach>			    	
				    	   			
					</s:forEach>
			</tbody>		        
		</table>
		
	    	
	    	<a href="api/v1/departments/subjects/exams/results/average"><button class="btn btn-primary">
	    	Get Subject Average :</button></a><br><br>
	    	
	    	<table class = "table" style="width:50%">
				<thead>
					<tr>
						<th>Subject Name</th>
						<th>Average Mark</th>
					</tr>
			    </thead>
			    <tbody>
			    <s:forEach items="${average}" var="entry">
			    	<tr>
			    		 <td> ${entry.key}</td>
			    		 <td>${entry.value}</td>
			    	</tr>    			
				</s:forEach>
				</tbody>
			</table>
			
			<a href="api/v1/departments/result"><button class="btn btn-primary">
			Get Department wise Pass Percentage</button></a><br><br>   
			
			<table class = "table" style="width:50%">
				<thead>
					<tr>
						<th>Department Name</th>
						<th>percentage</th>
					</tr>
			    </thead>
			    <tbody>
			    <s:forEach items="${deptPassPercentage}" var="entry">
			    	<tr>
			    		 <td> ${entry.key}</td>
			    		 <td>${entry.value}</td>
			    	</tr>    			
				</s:forEach>
				</tbody>
			</table>
			
			<a href="api/v1/departments/subjects/exams/result/mark" ><button class="btn btn-primary">
			Get Each Subject Highest Mark</button></a><br><br>
			
			<table class = "table" style="width:50%">
					<thead>			
						<tr>
						    <th>Student name</th>
						    <th>Subject Name</th>
						    <th>Staff Name</th>
						    <th>Mark</th>  
						</tr>
					</thead>
					<tbody>
						<s:forEach var="outer" items="${highestMark}">						
							  <tr>
							  <s:forEach var="inner" items = "${outer}">						  	
							    <td>"${inner}"</td>
							  </s:forEach>
							 </tr>
						</s:forEach>			
					</tbody>
				</tbody>		        	
			</table>
    </div>
	
	<script>	
		 $('#showbtn').click(function () {
	         $('#divbtn').show();
	     });
	     
	</script>
</body>
</html>