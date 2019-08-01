document.getElementById("stepButton").disabled = true;
document.getElementById("killButton").disabled = true;
document.getElementById("runButton").disabled = true;
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
		
		for (var state in stateData){
			if (stateData[state] != null){
				currentState = stateData[state];
				break;
			}
				
		}
		
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
	//curState["0"].style.border = "2px solid blue";
	//curState["0"].style.backgroundImage =  "url(../State2Focus.png)";
	var stateBack = document.getElementsByClassName("state-container-title-oncanvas-forDeletion" + currentState._id)["0"];
	stateBack.style.backgroundImage =  "url(./resources/images/State2Focus.png)";
}

function unRenderCurrentState(){
	var curState = document.getElementsByClassName("State-" + currentState._id);
	//curState["0"].style.backgroundImage = oldBorder;
	var stateBack = document.getElementsByClassName("state-container-title-oncanvas-forDeletion" + currentState._id)["0"];
	stateBack.style.backgroundImage =  "url(./resources/css/State2.png)";
}
	//$("State-" + currentState._id).css("background-image", "url(../State2.png)");
//}

function findNextState(id){
	for (var state in stateData){
		if (stateData[state] != null && stateData[state]._id == id){	
			return stateData[state];
		}
	}
	return null;
}

function onKill(){
	unRenderCurrentState();
	testString = "";
	currentIndex = 0;
	stepInProgress = false;
	document.getElementById("stepButton").disabled = true;
	document.getElementById("killButton").disabled = true;
	document.getElementById("runButton").disabled = true;
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


function addToList(s) {
	var list = document.getElementById("addEventList");
  
	if (s === "No Obstacle") {
	  testString += "n";
	} else if (s === "Obstacle Left") {
	  testString += "l";
	} else if (s === "Obstacle Right") {
	  testString += "r";
	} else if (s === "Obstacle Center") {
	  testString += "c";
	} else if (s === "Light") {
	  testString += "s";
	}
	console.log(testString);
	var newListItem = document.createElement("li");
	newListItem.innerHTML = s;
  
	list.appendChild(newListItem);
  }
  
  function onClose() {
	if (testString === "") {
	  alert("test String is empty");
	}
  
	var list = document.getElementById("addEventList");
	list.innerHTML = "";
	testable = true;
	if (testString.length > 0) {
	  document.getElementById("stepButton").disabled = false;
	}
	if (testString.length > 0) {
	  document.getElementById("killButton").disabled = false;
	}
	if (testString.length > 0) {
	  document.getElementById("runButton").disabled  = false;
	}
	
  }
  function onTest() {
	var list = document.getElementById("addEventList");
	list.innerHTML = "";
	testString = "";
	testable = false;
  }
  


