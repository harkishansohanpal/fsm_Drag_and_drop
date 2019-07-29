<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
    
<%@ page import="com.fdmgroup.model.JSONFsm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List</title>
</head>
<body>

	<table>
	<tr>
		<td>User ID</td>
		<td>FSM ID</td>
		<td>Action</td>
	</tr>

	<% 
		List<JSONFsm> FSMs = (List<JSONFsm>) request.getAttribute("FSMs");
	
		for(JSONFsm f : FSMs){
	
	%>
	<tr>
		<td><%= f.getId() %></td>
		<td><%= f.getUser().getUserID() %></td>
		<td>
			<form action="Run">
				<input type="hidden" id="fsm" name="fsm" value="<%= f.getJsonFsm() %>">
				<input type="submit" value="Run">
			</form>
		</td>
	</tr>
	<% } %>

	</table>
	
</body>
</html>