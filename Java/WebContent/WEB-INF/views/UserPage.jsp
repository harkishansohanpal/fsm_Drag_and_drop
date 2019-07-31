<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
<link rel="stylesheet" href="resources/styles/style.css" />

<title>Dragon Drobotics</title>
</head>
<body>
<div>
<button style="float:right;" onclick="save()">Save</button>
</div>
 <div
      class="container-fluid"
      style="position: absolute;bottom: 0px;top: 0px;left: 0px;right: 0px;"
    >
    <div class="row" style="height: 100%;position: relative;">
        <div class="col-3">
          <div class="row states" style="position: relative;">
            <h5>States</h5>
            <div class="state-container">
              <div class="state-container-title">
                <h6>State</h6>
              </div>
              <div class="state-container-body"></div>
            </div>
          </div>
          <div class="row behaviours" style="position: relative;">
            <h5>Behaviours</h5>
            <h6 class="behaviour">Forward</h6>
            <h6 class="behaviour">Backward</h6>
            <h6 class="behaviour">Turn Right</h6>
            <h6 class="behaviour">Turn Left</h6>
            <h6 class="behaviour">Spin</h6>
            <h6 class="behaviour">Stop</h6>
          </div>
          <div class="row events" style="position: relative;">
            <h5>Events</h5>
            <h6 id="noObstacle" class="eventlist" onclick="clickState()">No Obstacle</h6>
            <h6 id="obstacleLeft" class="eventlist" onclick="clickState()">Obstacle Left</h6>
            <h6 id="obstacleRight" class="eventlist" onclick="clickState()">Obstacle Right</h6>
            <h6 id="obstacleCenter" class="eventlist" onclick="clickState()">Obstacle Center</h6>
            <h6 id="Light" class="eventlist" onclick="clickState()">Light</h6>
            
          </div>
        </div>
		<canvas id="theCanvas" width=1000, height=1000> </canvas>
        <div class="col-9 canvas">
          <h2>Canvas</h2>
        </div>
      </div>
    </div>
	<script src="resources/javascripts/transform.js"></script>

    <script src="resources/javascripts/script.js"></script>

</body>
</html>