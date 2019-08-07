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
    <link rel="stylesheet" href="resources/css/modalUserStyle.css" />
    <link rel="stylesheet" href="resources/css/modalTestStyle.css" />
    <link rel="stylesheet" href="resources/css/adminStyle.css" />
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
      style="position: absolute;bottom: 0px;top: 0px;left: 0px;right: 0px; padding: 0;"
    >
    <div class="navbarCAdmin">
        <div class="logoDivTopBar"></div>
        <div class="inputDivTopBar">
          <input type="text" id="fsmName" placeholder="FSM Name"/>
        </div>
        <div class="buttonsDiv">
          <div style="width: 14%; height: 60px; margin-left: 5px; opacity: 0;" id="runButton" type="button" class="btn btn-info btn-lg" onclick="onRun()">Run</div>
          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" id="killButton" type="button" class="btn btn-info btn-lg" onclick="onKill()">Stop</div>
          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModaltest" onclick="onTest()">Test</div>
          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" id="stepButton" type="button" class="btn btn-info btn-lg" onclick="onStep()" >Step</div>
          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" onclick="onTest()">Load</div>
          <div style="width: 17%; height: 60px; margin-left: 15px; opacity: 0;" type="button" class="btn btn-info btn-lg" onclick="window.location.href='Logout'">Logout</div>
        </div>
    </div>
    <div class="modal fade" id="myModaltest" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content background" id="modalHeight">
          <div class="modal-body">
            <div class="upperButtons">
				<div class="noObs" onclick="addToList('No Obstacle')">&nbsp;
				</div>
				<div class="obsLeft" onclick="addToList('Obstacle Left')">&nbsp;
				</div>
				<div class="obsRight" onclick="addToList('Obstacle Right')">&nbsp;
				</div>
			</div>
			<div class="lowerButtons">
				<div class="obsCenter" onclick="addToList('Obstacle Center')">&nbsp;
				</div>
				<div class="light" onclick="addToList('Light')">&nbsp;
				</div>
			</div>
			<ol id="addEventList" class="list">
			</ol>
			<div>
				<div class="close" onclick="onClose()" data-dismiss="modal">
				</div>
			</div>
            
          </div>
            
          
        </div>
        
      </div>
    </div>
    <div class="modal fade" id="myModalNewUser" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content userBackground" id="modalHeight">
         <div class="modal-body">
         <form action="AddUser" method="POST">
         	<div class="inputText">
	          <input type="text" class="fsmNewUser" id="newUsername" placeholder="Username" name="user" />
	        </div>
	        <div class="inputText">
	          <input type="text" class="fsmNewUser" id="newName" placeholder="Name" name="name" />
	        </div>
	        <div class="inputText">
	          <input type="text" class="fsmNewUser" id="newPassword" placeholder="Password" name="pass" />
	        </div>
	        <div class="inputText">
	          <select name="type" placeholder="User Type" class="fsmNewUser" id="newUserType">
				<option value="User">User</option>
				<option value="Admin">Admin</option>
			  </select>
	        </div>
	        <div id="userButtons">
				<button type="submit" class="addNewUser">&nbsp;
	            </button>
	            <div class="cancelUser" onclick="" data-dismiss="modal">&nbsp;
	            </div>
         	</div>
         	</form>
         </div>

        </div>
        
      </div>
    </div>
    
    
    <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">
      
        <!-- Modal content-->
        <div class="modal-content" style="width: 100vh">
          <!-- <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">&times;</button>
          </div> -->
          <div class="modal-body" id="modalLoadBackground">
            <!-- <div class="main-div"> -->
            <div class="tableUser">
            <table id="users">
              <!-- <tr>
                <th>Username</th>
                <th>FSM ID</th>
                <th>Actions</th>
              </tr> -->

              <% 
                List<JSONFsm> FSMs = (List<JSONFsm>) request.getAttribute("FSMs");
              
                for(JSONFsm f : FSMs){
              %>
              
              <tr>
                <td class="tableData usernameData"><%= f.getUser().getUsername() %></td>
                <td class="tableData fsmnameData"><%= f.getFsmName() %></td>
                
               <!--   <td class="tableData usernameData">ALI</td>
                <td class="tableData fsmnameData">Ali FSM</td>  -->
                
                <td class="buttonData">

					<input id="<%= f.getId() + "fsmRunButton" %>" type="hidden" id="fsm" name="fsm" value="<%= f.getJsonFsm() %>">
                    <input type="hidden" id="<%= f.getId() + "fsmLoadButton" %>" name="fsm" value='<%= f.getLoadModel().getModel() %>'>
                    <input type="button" class="loadButtonModal" onclick='loadModel(<%= (f.getId())%>)'>
                  
                  <form  action="Delete" style="display:inline" method="post">
                    <input type="hidden" id="fsmDeleteButton" name="fsm" value="<%= f.getId() %>">
                    <input type="submit" value="" class="deleteButtonModal">
                    <!-- <input type="submit" value="Delete" class="buttonclass delete"> -->
                  </form>
                  
                </td>
              </tr>
              
               <% } %>

            </table>
              </div>
            <!-- </div> -->
            
            <div type="button" class="closeButton btn btn-default" data-dismiss="modal"></div>
            
          </div>
         
          <!-- <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" onclick="">Close</button>
          </div> -->
        </div>
        
      </div>
    </div>
    
    
   <div class="row" style="height: 80%;position: relative; overflow:hidden;">
        <div class="col-2" style="min-width: 160px; height: 100%;">

          
          <div class="row buttonsLeft" style="position: relative;">
            
            <!-- <form action="Run" style="display:inline" method="post">
				<input id="executejsonfsm" type="hidden" id="fsm" name="fsm">
				<input type="submit" value="Run" class="buttonclass run" style="width: 125px;">
			</form> -->
			
			
			
			<button type="button" class="buttonclass addUser" style="width: 125px;" data-toggle="modal" data-target="#myModalNewUser" onclick="onTest()">Add New User</button>
			<button type="button" id="executejsonfsm" onclick="execute()" class="buttonclass run" style="width: 125px;">Execute</button>
			<button type="button" onclick="kill()" class="buttonclass kill" style="width: 125px;">Kill</button>
			
			
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
    