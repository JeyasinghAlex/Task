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
        <h5 style = "text-align:center"><b>Student Operations</b></h5>
        <div class="container" >
           
            <a href="students/getEachSubjectAverageMark"><button class="btn btn-primary">getEachSubjectAverageMark</button></a><br><br>
            
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
            <a href="students/getEachSubjectHighestMark" ><button class="btn btn-primary">getEachSubjectHighestMark</button></a><br><br>
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
					<%-- <s:iterator value="highMarkStudents">					
					<tr>
						  <td><s:property value="name"/></td>
						  <td><s:property value="highMarkStudents(1).name" /></td>
					</tr>
					</s:iterator>	 --%>	
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
            
            <a href="students/getDepartmentwisePassPercentage"><button class="btn btn-primary">getDepartmentwisePassPercentage</button></a><br><br>           
            
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
            
            <a href="students/getStaffwisePassPercentage"><button class="btn btn-primary">getStaffwisePassPercentage</button></a><br><br>
            
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
        
        
    </body>
</html>
