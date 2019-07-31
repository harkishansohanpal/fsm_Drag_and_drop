
var testString = "";
var currentIndex = 0;
var currentState;
var nextState_id;
var recentEvent;
var stepInProgress = false;

var oldBorder;

function onRun(){
	while(onStep()){
		
	}
}

function onStep(){

	if (currentIndex >= testString.length){
		onKill();
		alert ("Completed Succesfully");
		return false;
	}
	

	let stepValid = false;
	
	if(!stepInProgress){
		stepInProgress = true;
		currentState = stateData[0];
		renderCurrentState();
		stepValid = false;
		return true;
	}
	else{
		recentEvent = getRecentEvent();
		++currentIndex;
		for (var event in eventData){
			console.log("" + eventData[event].label + recentEvent + eventData[event].fromState +  currentState._id )
			if (eventData[event].label == recentEvent && eventData[event].fromState == currentState._id){
				stepValid = true;
				nextState_id = eventData[event].toState;
				unRenderCurrentState();
				currentState = findNextState(nextState_id);
				renderCurrentState();
				return true;
			}
		}
		onKill();
		console.log("No transition available on truth table");
		alert("Error. Event not Specified for current state");
		return false;
	}
}

function renderCurrentState(){
	var curState = document.getElementsByClassName("State-" + currentState._id);  
	oldBorder = curState["0"].style.border;
	curState["0"].style.border = "2px solid blue";
}

function unRenderCurrentState(){
	var curState = document.getElementsByClassName("State-" + currentState._id);
	curState["0"].style.border = oldBorder;
}

function findNextState(id){
	for (var state in stateData){
		if (stateData[state]._id == id){
			return stateData[state];
		}
	}
	return null;
}

function onTest(){
	testString = "";
	
}


function onKill(){
	unRenderCurrentState();
	testString = "";
	currentIndex = 0;
	stepInProgress = false;
}

function getRecentEvent(){
	switch ("" + testString.charAt(currentIndex)){
		case "n":
			return "No Obstacle";
		case "r":
			return "Obstacle Right";
		case "l":
			return "Obstacle Left";
		case "c":
			return "Obstacle Center";
		case "s":
			return "Light";
		default:
			return "Unrecognised";
	}
}




/*
function onTestNoObstacle(){
	testString += "n";
}

function onTestObstacleAll(){
	testString += "c";
}

function onTestObstacleRight(){
	testString += "r";
}

function onTestObstacleLeft(){
	testString += "l";
}

function onTestLight(){
	testString += "s";
}

function onTestModalClose(){
	testable  = true;
	
}
*/
