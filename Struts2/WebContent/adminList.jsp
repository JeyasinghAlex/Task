<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "s" uri = "/struts-tags" %>
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
        <div class="container">
            <a href="admin/getAllStudentsData"><button class="btn btn-primary">Get students data</button></a><br><br>
          		<table class = "table" style="width:100%">
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
		        <s:iterator var = "student" value="studentsData">		        		
		        		<td><s:property value="#student.id"/></td>
		        		<td><s:property value="#student.name"/></td>
		        		<td><s:property value="#student.department"/></td>
		        	<s:iterator var = "subject" value = "#student.subjects">
		        		<tr>
		        		  <td></td>
		        		  <td></td>
		        		  <td></td>
						  <td><s:property value="#subject.name"/></td>
						  <td><s:property value="#subject.mark"/></td>
						  <td><s:property value="#subject.staff"/></td>						  
					   </tr>
		        	</s:iterator>										
			    </s:iterator>		
                </tbody>
		  </table>
		    <a href="admin/getTopStudents" ><button class="btn btn-primary">Get college topers</button></a><br><br>
        	<table class = "table" style="width:100%">
				<thead>
				   <tr>
				   		<th>Student Rank</th>
					    <th>Student Id</th>
					    <th>Student Name</th>
					    <th>Student Department</th>					    
				   </tr>
		        </thead>
		        
		        <tbody>
		        <s:iterator value="clgTopStudens">					
					<tr>
						  <td><s:property value="rank"/></td>
						  <td><s:property value="id"/></td>
						  <td><s:property value="name"/></td>
						  <td><s:property value="department"/></td>						  
					</tr>
			    </s:iterator>		
                </tbody>
		  </table>
		  
		  
		     <a href="admin/getEachDeptTopStudents"><button class="btn btn-primary">Get department topers</button></a><br><br>
        	<table class = "table" style="width:100%">
				<thead>
				   <tr>
				   		<th>Student Rank</th>
					    <th>Student Id</th>
					    <th>Student Name</th>
					    <th>Student Department</th>					    
				   </tr>
		        </thead>
		        
		        <tbody>
		        <s:iterator value="eachDeptTopStudens">					
					<tr>
						  <td><s:property value="rank"/></td>
						  <td><s:property value="id"/></td>
						  <td><s:property value="name"/></td>
						  <td><s:property value="department"/></td>						  
					</tr>
			    </s:iterator>		
                </tbody>
		  </table>
				
			<button class="btn btn-primary" id = "rank">Get Nth rank student</button><br><br>
        	 <div style = "display : none" id = "rankdiv">
        	<s:form action = "admin/getNthRankStudents">
        		 	<s:textfield label = "Enter the rank " key = "rank"/>
        		 	<s:submit/>        	
        	</s:form>
        	</div>
        	 	
        	<table class = "table" style="width:100%">
				<thead>
				   <tr>
					    <th>Student Id</th>
					    <th>Student Name</th>
					    <th>Student Department</th>					    
				   </tr>
		        </thead>
		        
		        <tbody>
		        <s:iterator value="nthRankStudent">					
					<tr>
						  <td><s:property value="id"/></td>
						  <td><s:property value="name"/></td>
						  <td><s:property value="department"/></td>						  
					</tr>
			    </s:iterator>		
                </tbody>
		  </table>
			
        	<a href="admin/getStudentsRankwise"><button class="btn btn-primary">Get students rank wise</button></a><br><br>     
        	
        	<table class = "table" style="width:100%">
				<thead>
				   <tr>
				   		<th>Rank</th>
					    <th>Student Id</th>
					    <th>Student Name</th>
					    <th>Student Department</th>
					    <th>Subject Name</th>
					    <th>Subject Mark</th>
					    <th>Staff Name</th>					    
				   </tr>
		        </thead>
		        
		        <tbody>
		        <s:iterator var = "student" value="studentsRank">
		        		<td><s:property value="#student.rank"/></td>
		        		<td><s:property value="#student.id"/></td>
		        		<td><s:property value="#student.name"/></td>
		        		<td><s:property value="#student.department"/></td>
		        	<s:iterator var = "subject" value = "#student.subjects">
		        		<tr>
		        		  <td></td>
		        		  <td></td>
		        		  <td></td>
		        		  <td></td>
						  <td><s:property value="#subject.name"/></td>
						  <td><s:property value="#subject.mark"/></td>
						  <td><s:property value="#subject.staff"/></td>						  
					   </tr>
		        	</s:iterator>										
			    </s:iterator>		
                </tbody>
		  </table>
		  	  
        </div>
    
        
        <div class="container" >
           
            <a href="admin/getEachSubjectAverageMark"><button class="btn btn-primary">getEachSubjectAverageMark</button></a><br><br>
            
			<table class = "table" style="width:100%">
				<thead>
				   <tr>
					    <th>Subject Name</th>
					    <th>Average Mark</th>
				   </tr>
		        </thead>
		        
		        <tbody>
		        <s:iterator value="average">
                        <tr>
                        	<td><s:property value="key"/></td>
            				<td><s:property value="value"/> </td>        					
                        </tr>                    
                </s:iterator>
                </tbody>
		  </table>
        </div>
        
         <div class="container" >
            <a href="admin/getEachSubjectHighestMark" ><button class="btn btn-primary">getEachSubjectHighestMark</button></a><br><br>
            <table class = "table">
				<thead>			
					<tr>
					    <th>Student name</th>
					    <th>Subject Name</th>
					    <th>Staff Name</th>
					    <th>Mark</th>  
					</tr>
				</thead>
				<tbody>
					<s:iterator var="row" value="highMarkStudents">						
						  <tr>
						  <s:iterator value="#row">						  	
						    <td><s:property/></td>
						  </s:iterator>
						 </tr>
					</s:iterator>			
				</tbody>
			</table>
            
        </div>
        
         <div class="container" >            
            <a href="admin/getDepartmentwisePassPercentage"><button class="btn btn-primary">getDepartmentwisePassPercentage</button></a><br><br>                       
            <table class = "table" style="width:100%">
				<thead>
				   <tr>
					    <th>Department Name</th>
					    <th>Pass Percentage</th>
				   </tr>
		        </thead>
		        
		        <tbody>
		        	<s:iterator value="deptwisePassPercentage">
                        <tr>
                        	<td><s:property value="key"/></td>
            				<td><s:property value="value"/> </td>        					
                        </tr>                    
               	 	</s:iterator>
               	 </tbody>
		  </table>
		  
        </div>
        
        
         <div class="container" >
            
            <a href="admin/getStaffwisePassPercentage"><button class="btn btn-primary">getStaffwisePassPercentage</button></a><br><br>
            
            <table class = "table" style="width:100%">
				<thead>
				   <tr>
					    <th>Staff Name</th>
					    <th>Pass Percentage</th>
				   </tr>
		        </thead>
		        
		        <tbody>
		        	<s:iterator value="stfwisePassPercentage">
                        <tr>
                        	<td><s:property value="key"/></td>
            				<td><s:property value="value"/> </td>        					
                        </tr>                    
                	</s:iterator>
                </tbody>
		  </table> 
        </div>
        
        <script>
        	$('#rank').on('click', function(){
        		$('#rankdiv').show();
        	});
        </script>
    </body>
</html>
