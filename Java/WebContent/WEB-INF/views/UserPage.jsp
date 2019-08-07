	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	    pageEncoding="ISO-8859-1"%>
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
		<link rel="stylesheet" href="resources/css/adminStyle.css" />
		<link rel="stylesheet" href="resources/css/modalTestStyle.css" />
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
	      style="position: absolute;bottom: 0px;top: 0px;left: 0px;right: 0px;padding: 0;"
	    >
	    <div class="navbarC">
		<div class="logoDivTopBar"></div>
	        <div class="inputDivTopBar">
	          <input type="text" id="fsmName" placeholder="FSM Name"/>
	        </div>
	        <div class="buttonsDiv">
	          <div style="width: 14%; height: 60px; margin-left: 5px; opacity: 0;" id="runButton" type="button" class="btn btn-info btn-lg" onclick="onRun()">Run</div>
	          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" id="killButton" type="button" class="btn btn-info btn-lg" onclick="onKill()">Stop</div>
	          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModaltest" onclick="onTest()">Test</div>
	          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" id="stepButton" type="button" class="btn btn-info btn-lg" onclick="onStep()" >Step</div>
	          <div style="width: 14%; height: 60px; margin-left: 15px; opacity: 0;" type="button" class="btn btn-info btn-lg" onclick="save()">Save</div>
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
	          <div class="row" style="height: 85%;position: relative; overflow:hidden;">
	          <div class="col-2" style="border-right: 2px solid black; min-width: 160px;height: 100vh;">
	            <div class="row states" style="position: relative;">
	              <h4 style="margin-bottom: 0px;">States</h4>
	              <div class="state-container stateOuterDiv-menu">
	              </div>
	            </div>
	            <div class="row behaviours" style="position: relative;">
	              <h4 style="margin: auto; margin-top: 10px;">Behaviours</h4>
				  <div class="col-6" style="float: left;">
	              <h6 class="behaviour">Forward</h6>
	              <h6 class="behaviour">Backward</h6>
	              <h6 class="behaviour">Turn Right</h6>
				   </div>
				   <div class="col-6" style="float: left;">
	              <h6 class="behaviour">Turn Left</h6>
	              <h6 class="behaviour">Spin</h6>
	              <h6 class="behaviour">Stop</h6>
				  </div>
	            </div>
	            <div class="row events" style="position: relative;">
	              <h4 style="margin-bottom: 0px; margin-top: 5px;">Events</h4>
				  <div class="col-6" style="float: left; padding-right: 0px;">
	              <h6 id="noObstacle" class="eventlist" onclick="clickState()">No Obstacle</h6>
	              <h6 id="obstacleLeft" class="eventlist" onclick="clickState()">Obstacle Left</h6>
	              <h6 id="obstacleRight" class="eventlist" onclick="clickState()">Obstacle Right</h6>
				  </div>
				  <div class="col-6" style="float: left; padding-left: 0px;">
	              <h6 id="obstacleCenter" class="eventlist" onclick="clickState()">Obstacle Center</h6>
	              <h6 id="light" class="eventlist" onclick="clickState()">Light</h6>
	            </div>
				</div>
	          </div>
	          <canvas id="theCanvas" width=3000, height=3000> </canvas>
	          <div class="col-10 canvas"></div>
	      </div>
	    </div>
		<script src="resources/javascripts/transform.js"></script>
	
	    <script src="resources/javascripts/script.js"></script>
	    <script src="resources/javascripts/user.js"></script>
	    <script src="resources/javascripts/inputTest.js"></script>
	
	    <script src="resources/javascripts/lines.js"></script>
		
	  </body>
	</html>
	    