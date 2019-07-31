<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
    
<%@ page import="com.fdmgroup.model.JSONFsm" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Add New User</title>
	<style>
		<%@ include file="../../resources/css/adminStyle.css" %>
		<%@ include file="../../resources/css/formStyle.css" %>
	</style>
</head>
<body>

	<div class="row row1">
		<nav class="navbar top-bg navbar-light bg-light col-12"> <a
			class="navbar-brand" href="#"> <img
			src="/docs/4.3/assets/brand/bootstrap-solid.svg" width="30"
			height="30" class="d-inline-block align-top" alt="">

			<h3>Dragon Drobotics</h3>
		</a> <a class="saveButton" href="Cancel"> Home </a> </nav>
	</div>
	
	<div class="row content row1">
	
		<!-- Side navigation -->
		<div class="sidenav col-2">
			<h3>Admin Name</h3>
			</br>
		</div>
		
		<div class="main col-10">
			<div id="addEmp-frame" class="Add-employee">
                <div class="input-form">
                    <h2>Add New Users</h2>
                    <form action="AddUser" method="post">
                       
						<input type="text" name="user" placeholder="Username" /> 
                        <input type="text" name="name" placeholder="Name" />
                        <input type="password" name="pass" placeholder="Password" />
						 <select name="type" placeholder="User Type">
							<option value="User">User</option>
							<option value="Admin">Admin</option>
						  </select>
						<input type="submit" class="buttonclass run" value="Add User" />
						<a href="Cancel"> <button class="buttonclass delete" >Cancel</button></a>
							              
                    </form>
                    
                </div>
            </div>
		</div>
	</div>
</body>
</html>




