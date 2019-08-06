<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.List" %>
    <style>
		<%@ include file="../../resources/css/formStyle.css" %>
	</style>
    
<%@ page import="com.fdmgroup.model.JSONFsm" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.css"
    />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.js"></script>

    <title>DRAGON DROBOTICS</title>

    <link rel="stylesheet" href="resources/css/style.css" />
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  </head>
  <style>
  .buttontest {
    margin:  3px;
  }
  
  </style>
  <body>
    <div
      class="container-fluid"
      style="position: absolute;bottom: 0px;top: 0px;left: 0px;right: 0px;"
    >
    <nav class="navbar navbar-light bg-light" style="margin: 0;">
      <a class="navbar-brand" href="#">
        <img src="dragon.png" width="30" height="30" class="d-inline-block align-top" alt="">
        Dragon Drobotics
      </a>
      <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModaltest" onclick="onTest()">Test</button>
      <button id="stepButton" type="button" class="btn btn-info btn-lg" onclick="onStep()" >Step</button>
      <button id="runButton" type="button" class="btn btn-info btn-lg" onclick="onRun()">Run</button>
      <button id="killButton" type="button" class="btn btn-info btn-lg" onclick="onKill()">Stop</button>
      <form method="post" id="fsm" action="Save">
      	<input type="text" name="fsmName" placeholder="FSM Name" required /> 
      </form>
      <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="onTest()">Load</button>
      <button type="button" class="btn btn-info btn-lg" onclick="window.location.href='Logout'">Logout</button>
    </nav>
    <div class="modal fade" id="myModaltest" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
            <button id="noObstTest" class="btn btn-info btn-lg buttontest" onclick="addToList('No Obstacle')" >No Obstacle</button>
            <button id="ObstLeftTest" class="btn btn-info btn-lg buttontest" onclick="addToList('Obstacle Left')">Obstacle Left</button>
            <button id="ObstRightTest" class="btn btn-info btn-lg buttontest" onclick="addToList('Obstacle Right')" >Obstacle Right</button>
            <button id="ObstCenterTest" class="btn btn-info btn-lg buttontest" onclick="addToList('Obstacle Center')" >Obstacle Center</button>
            <button id="LightTest" class="btn btn-info btn-lg buttontest" onclick="addToList('Light')">Light</button>
            
          </div>
            <div class="modal-body">
            <ol id="addEventList" style="height: 175px; border: 2px solid rgb(204, 181, 181);overflow: scroll;">
            </ol>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="onClose()">Close</button>
          </div>
        </div>
        
      </div>
    </div>
    <div class="modal fade" id="myModalNewUser" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
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
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Close</button>
          </div>
        </div>
        
      </div>
    </div>
    
    
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div>
          <div class="modal-body">
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
						System.out.println(f.getJsonFsm());
						%>
						
						<tr>
							<td><%= f.getUser().getUsername() %></td>
							<td><%= f.getId() %></td>
							<td>
								<form action="Load" style="display:inline" method="post">
									<input type="hidden" id="<%= f.getId() + "fsmLoadButton" %>" name="fsm" value='<%= f.getLoadModel().getModel() %>'>
									<input type="button" value="Load Model" onclick="loadModel(<%= (f.getId())%>)" class="buttonclass run">
									<input type="hidden" id="<%= f.getId() + "fsmRunButton" %>" name="fsm" value=<%= f.getJsonFsm() %>>
								</form>
								
								<form action="Delete" style="display:inline" method="post">
									<input type="hidden" id="fsmDeleteButton" name="fsm" value="<%= f.getId() %>">
									<input type="submit" value="Delete" class="buttonclass delete">
								</form>
								
							</td>
						</tr>
						
						<% } %>

					</table>
            </div>
      </div>
            
            
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Close</button>
          </div>
        </div>
        
      </div>
    </div>
    <div class="row" style="height: 100%;position: relative; overflow:hidden;">
        <div class="col-2" style="border-right: 2px solid black; min-width: 160px;">
          <div class="row states" style="position: relative;">
            <h5>States</h5>
            <div class="state-container stateOuterDiv-menu">
            </div>
          </div>
          <div class="row behaviours" style="position: relative;">
            <h5>Behaviours</h5>
            <form action="Run" style="display:inline" method="post">
				<input id="executejsonfsm" type="hidden" id="fsm" name="fsm">
				<input type="submit" value="Execute" class="buttonclass run">
			</form>
			
			<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModalNewUser" onclick="onTest()">Add New User</button>
			
          </div>
          <div class="row events" style="position: relative;">
            <h5>Events</h5>
            
          </div>
        </div>
	    	<canvas id="theCanvas" width=3000, height=3000> </canvas>
        <div class="col-10 canvas"></div>
        </div>
      </div>
    </div>
	<script src="resources/javascripts/transform.js"></script>

    <script src="resources/javascripts/script.js"></script>
    <script src="resources/javascripts/user.js"></script>
    <script src="resources/javascripts/inputTest.js"></script>

    <script src="resources/javascripts/lines.js"></script>
	
  </body>
</html>
    