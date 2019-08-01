<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
    
<%@ page import="com.fdmgroup.model.JSONFsm" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Finch FSM</title>
	<style>
		<%@ include file="../../resources/css/adminStyle.css" %>
	</style>
</head>
<body>

	<div class="row row1">
		<nav class="navbar top-bg navbar-light bg-light col-12"> <a
			class="navbar-brand" href="#"> <img
			src="/docs/4.3/assets/brand/bootstrap-solid.svg" width="30"
			height="30" class="d-inline-block align-top" alt="">

			<h3>Dragon Drobotics</h3>
		</a> <a class="saveButton" href="#"> Save </a> </nav>
	</div>
	
	<div class="row content row1">
	
		<!-- Side navigation -->
		<div class="sidenav col-2">
			<h3>Admin Name</h3>
			</br>
			<button class="sideButton" onclick="window.location.href='NewUser'">Add User</button>
			<button class="sideButton" onclick="window.location.href='Kill'">Kill</button>
		</div>
		
		<div class="main col-10">

			<div class="main-div">
				<div class="tableUser">
					<table id="users">
						<tr>
							<th>Username</th>
							<th>FSM ID</th>
							<th>Actions</th>
						</tr>

						<% 
							List<JSONFsm> FSMs = (List<JSONFsm>) request.getAttribute("FSMs");
						
							for(JSONFsm f : FSMs){
						
						%>
						
						<tr>
							<td><%= f.getUser().getUsername() %></td>
							<td><%= f.getId() %></td>
							<td>
								<form action="Run" style="display:inline" method="post">
									<input type="hidden" id="fsm" name="fsm" value="<%= f.getJsonFsm() %>">
									<input type="submit" value="Run" class="buttonclass run">
								</form>
								
								<form action="Delete" style="display:inline" method="post">
									<input type="hidden" id="fsm" name="fsm" value="<%= f.getId() %>">
									<input type="submit" value="Delete" class="buttonclass delete">
								</form>
								
							</td>
						</tr>
						
						<% } %>

					</table>
				</div>
			</div>
		</div>
</body>
</html>