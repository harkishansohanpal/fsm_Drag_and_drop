var connections = [];
var context =document.getElementById("theCanvas").getContext("2d");
var diagram = null;
var labelinput ="";
var colorinput = "";
var eventID=0;
var eventData=[];
var stateData = [];
var connectionIndex=0;

//code for adding a new connection
var addConnectionState = "not begin";
function addConnection(data){
	// when add connection is clicked
	if(data == "start"){
		addConnectionState = [];
	}
	// if a state is clicked and the current addConnectionState is an array:
	if(typeof(data) == "number" && typeof(addConnectionState) == "object"){
		////console.log(addConnectionState);
		addConnectionState.push(data)
		if(addConnectionState.length == 2){
      addConnectionState.push(colorinput);
      addConnectionState.push(labelinput);
      addConnectionState.push(eventID++);
      
			if(addConnectionState[2] != null){
				//first remove all "identical" outs
				removeConnection(addConnectionState[0], "*", addConnectionState[3]);
				connections.push(addConnectionState);
				eventData.push({
				  id:addConnectionState[4],
				  fromState:addConnectionState[0],
				  toState:addConnectionState[1],
				  label:addConnectionState[3]}
					);
				  connectionIndex++;
				////console.log(eventData);
				drawLines();
			}
				addConnectionState = "not begin";
			
		}


	}
}
// remove by source/destination/label. Use "*" for placeholder
function removeConnection(source, destination, label){
	for(var i=connections.length-1; i>=0;i--){
		if((eventData[i]["fromState"] == source || source == "*") && (eventData[i]["toState"] == destination || destination == "*") && (eventData[i]["label"] == label || label == "*")){
			eventData.splice(i,1);
			connections.splice(i,1);
		}
	}
}


//gets the width of the screen
function getWidth(){
	return $(window).width();
}

//draws a line with the given coordinates and color

function drawLine(x0, y0, x1, y1, color){
//	//console.log(x0, y0, x1, y1)
context.strokeStyle = color;
context.font = "14px Arial";
context.textBaseline = "bottom";
context.lineWidth = 3;
  context.beginPath();
  context.stroke(); 
  context.moveTo(x0,y0);
  context.lineTo(x1,y1);
  context.stroke();
}


function drawLabel( ctx, text, p1, p2, alignment, offset ){
  if (!alignment) alignment = 'center';
  var dx = p2.left - p1.left;
  var dy = p2.top - p1.top;
  ctx.save();
  ctx.textAlign = alignment;
  //console.log(p1.left+dx*offset,p1.top+dy*offset)
  ctx.translate(p1.left+dx*offset,p1.top+dy*offset);
  //ctx.rotate(Math.atan2(dy,-dx));
  ctx.fillText(text,0,0);
  ctx.restore();
}

//draws a circle with the given coordinates and color
function drawCircle(x,y,r, color,label){
  ////console.log(x,y,r)
  context.strokeStyle = color;
  context.font = "14px Arial";
  context.textBaseline = "bottom";
  context.lineWidth = 3;
	context.beginPath();
	context.arc(x,y,r,0*Math.PI,2*Math.PI);
	context.strokeStyle = color;
  context.stroke();
  var p1 = { x: x, y: y };
  var p2 = { x: x, y: y };
	
}
//get the coordinates of a given state object
function getLocation(i){
	return diagram[0][i].position;
}


//adds a connection to those states
function connect(x,y, color){	
	if(color == undefined){
		throw "undefined color";
	}
	connections.push([x,y,color])
	drawLine(getLocation(x).left+150, getLocation(x).top+150, getLocation(y).left+150, getLocation(y).top+150,color);
}

//connect every element in list1 with its corresponding element in lst2
function connectMultiplePairs(lst1, lst2, colors){
	context.clearRect(0,0,3000, 3000);
	for(var i=0;i<lst1.length;i++){
		connect(lst1[i] , lst2[i],colors[i]);
	}
}

//get all locations
function getAllLocation(){
	for(var i=0; i<diagram.length;i++){
		////console.log(i + " " + getLocation(i).left + " " + getLocation(i).top);
	}
	
}

//clears the canvas then draws all connections
function drawLines(ui){
				context.clearRect(0,0,3000,3000);
				var counter = {}; // count how many time a pair has showed up
				//so we can draw new lines that don't overlap with old ones.
				connections.forEach(function(d){
				
				var domain = d[0];
				var target = d[1];
				var color = d[2];
				var labelinput = d[3];
				var label=labelinput + "("+domain+"=>"+target+")";
				if(domain < target){
						var temp = domain;
						domain = target;
						target = temp;
					}
				counter[domain + " " + target] = (counter[domain + " " + target] == undefined ? 1 : counter[domain + " " + target]+1);
				var offsetNumber = counter[domain + " " + target];
				var offset = ["",0,9,3,7,2,8,4,1,6,5][offsetNumber]; 
				////console.log(domain + " " + target + " " + offset);
        //offsets 
        //console.log(document.getElementsByClassName("canvas")[0].scrollTop);
				var line_offset_left = 45+7*offset-document.getElementsByClassName("canvas")[0].scrollLeft; //was0.01
				var line_offset_top = 50+15*offset-document.getElementsByClassName("canvas")[0].scrollTop;
				var circle_offset_left = 140-document.getElementsByClassName("canvas")[0].scrollLeft;
				var circle_offset_top = 40-document.getElementsByClassName("canvas")[0].scrollTop;
				var circle_radius = 1000*(0.04-0.005*offset);
				try{
				var id = getIdFromString(ui.helper[0].children[0].getAttribute("class"));
				} catch(e){
					var id = null;
				}
				
				var domainPosition = domain == id ? ui.position : diagram[0][domain].position; 
				var targetPosition = target == id ? ui.position : diagram[0][target].position;
				
				if(domain== target){
						drawCircle(domainPosition.left+circle_offset_left, domainPosition.top+circle_offset_top,circle_radius, color,label);
											
				}
				else {
					var position1 = {"left":domainPosition.left+line_offset_left, "top": domainPosition.top+line_offset_top};
					var position2 = {"left":targetPosition.left+line_offset_left, "top": targetPosition.top+line_offset_top};
					
					drawLine(domainPosition.left+line_offset_left, domainPosition.top+line_offset_top,targetPosition.left+line_offset_left, targetPosition.top+line_offset_top, color,label);
					drawLabel(context, label, position1, position2, "center", 0.75-0.05*offset);
				}
				
				//ctx, text, p1, p2, alignment, offset 
				})
	
}

//gets the ID from the given state class
//for example: state-container-oncanvas State-2 ui-draggable ui-draggable-handle ui-droppable -> 2
               //state-container-oncanvas State-
function getIdFromString(s){
		////console.log("ID");
		////console.log(s);
		return parseInt(s.substr(31));
}


function clickState(){
	
	addConnection("start");
}

function save(){
	var string = JSON.stringify(transform(stateData, eventData));
	console.log(string);
	//var string ='{"vertices":[{"name":"A","behaviors":["Forward"]},{"name":"B","behaviors":["Backward","Backward"]},{"name":"C","behaviors":[]}],"edges":[{"event":{"name":"An","input":"NoObstacle"},"fromState":"A","toState":"B"},{"event":{"name":"Cs","input":"light"},"fromState":"C","toState":"A"},{"event":{"name":"Cr","input":"ObstacleR"},"fromState":"C","toState":"B"}],"startState":"A","endStates":[]}';
	
	return fetch("http://localhost:8088/FincFSM/Save", {method:"post", body:JSON.stringify({fsm:string})}).then(function(x) {console.log(x)});
	
}

/* Event visuals */


$("#noObstacle").mousedown(function () { 
  $(this).css("background-color","green");
  labelinput = "No Obstacle"
  colorinput = "green"
});

$(".eventlist").mouseup(function () { 
  $(this).css("background-color","");
});

$("#obstacleLeft").mousedown(function () { 
  $(this).css("background-color","black");
  labelinput = "Obstacle Left"
  colorinput = "black"
});

$("#obstacleRight").mousedown(function () { 
  $(this).css("background-color","blue");
  labelinput = "Obstacle Right"
  colorinput = "blue"
});

$("#obstacleCenter").mousedown(function () { 
  $(this).css("background-color","yellow");
  labelinput = "Obstacle Center"
  colorinput = "yellow"
});

$("#light").mousedown(function () { 
  $(this).css("background-color","orange");
  labelinput = "Light"
  colorinput = "orange"
});



/*===========================================================States=============================================================================== */

$(init);

function init() {

  //diagram is the main array, we push data into it
  diagram = [stateData, eventData];
  
  
  var canvas = $(".canvas");
  var stateCanvasBody = $(".state-container-oncanvas");
  
//make state container draggable
  $(".state-container").draggable({
    helper: "clone",
  });

//make behaviour draggable
  $(".behaviour").draggable({
    helper: "clone",
  });

  //Initialize state-id and behaviour-id
  var stateID = 0;
  var behaviourID = 0;

  //make canvas droppable
  canvas.droppable({
    // canvas can divs with below class
    accept:".state-container", 
    //function executed after drop event in canvas
    //check if it is state-container else return
    // if dropped div is state-container then make object state
    drop: function(event, ui) {
      //console.log(diagram)
    if (ui.helper.hasClass("state-container")) {
		var position =  ui.helper.position();
		//console.log(position)
		position.left = position.left -getWidth()*0.20; // TODO
		//console.log(position)
        var state = {
          _id: stateID++,
          position: position,
          behaviourArray:[],
          type: "state"
        };
      } else {
        return;
      }

      //push state to diagram array
      diagram[0].push(state);

      //call render function and pass diagram array and state-id
      renderStateContainer(diagram, state._id);
    }
  });

  
  function renderStateContainer(diagram, _id) {
    canvas.empty();
    //loop through diagram array
    for (var d in diagram[0]) {
      if(diagram[0][d] == null){
      continue;
      }
      var state = diagram[0][d];
      var html = "";
      
      ////console.log(state);      
      //if state.type is state then declare html and render
      if (state.type == "state") {
        ////console.log(state.behaviourArray);
        var behaviourDiv;
        if(state.behaviourArray.length>0){
        behaviourDiv=renderBehaviour(state.behaviourArray);
        }else{
          behaviourDiv="";
        }
        ////console.log(behaviourDiv);

        html = `<div class="stateOuterDiv state-container-title-oncanvas-forDeletion${state._id}">
                  <div class="state-container-oncanvas State-${state._id}">
                    <div class="state-container-title-oncanvas ">
                        <h6 >State-${state._id}</h6>
                    </div>
                    <div class="state-container-body state-container-body-oncanvas ">     
                    ${behaviourDiv}
                    </div>
                  </div>
                </div>`;
        var clickCount = 0;
        var dom = $(html)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        })
        //make state-container in canvas draggable
        .draggable({
          drag:function(event, ui){
            //draw all connections when dragged
            drawLines(ui);
            
          },
			
          stop: function(event, ui) {
            ////console.log(ui);
            var id = getIdFromString(ui.helper[0].children[0].getAttribute("class") );
			
            for (var i in diagram[0]) {
              if(diagram[0][i] == null){
              continue;
              }
              if (diagram[0][i]._id == id) {
                diagram[0][i].position.top = ui.position.top;
                diagram[0][i].position.left = ui.position.left;
              }
            }
          },
          containment: "parent"
        })
        //make new state-container droppable
        //and accept the behaviour div only
        .droppable({  
          accept:".behaviour",
          drop: function(event, ui){
             ////console.log($(this));
            // var stateContainerId = $(this).attr("id");
            ////console.log(diagram);
              ////console.log(ui);
              if (ui.helper.hasClass("behaviour")) {
                  var behaviour = {
                      _id: behaviourID++,
                      position: ui.helper.position(),
                      behaviourType:ui.helper["0"].innerHTML
                  };
              }
              

              //finds the index of the state that behaviour is to be addded into
              var indexOfTheState = -1;
              for(var i=0; i<diagram[0].length; i++){
                if(diagram[0][i] == null){
                  continue;
                }
                if($(this)["0"].attributes[1].value == diagram[0][i]._id){
                  indexOfTheState = i;
                }
              };
              diagram[0][indexOfTheState].behaviourArray.push(behaviour);
              //diagram[0][$(this)["0"].attributes[1].value].behaviourArray.push(behaviour);
               //console.log($(this)["0"].attributes);
               var htmlBehaviour = `<h6  style="color:white;" class="behaviour-oncanvas" data-behaviour="${ui.helper["0"].innerHTML}">${ui.helper["0"].innerHTML} </h6>`;
               $(".state-container-body-oncanvas",this).append(htmlBehaviour);              
               //$(this)["0"].childNodes[3].$(".state-container-body-oncanvas").append("htmlBehaviour");
          }
      })
      //adding the attribute state_id
      .attr("state_id", state._id)
      //to remove the element when even click occurs
      .click(function(e, ui){
		 // mark it for adding connection
		    addConnection(getIdFromString(e.currentTarget.children[0].getAttribute("class")))
        
		    clickCount++;
        if(clickCount%2 != 0){

          //change the border color to red when deleting 
          $(this).css({
            border: "2px solid red",
          });
          //create and add the delete button when clicked
          var htmlDeleteButton = `<h6 class="deleteButton"></h6>`;
          var stateIDToRemove = $(this)[0].attributes[1].value;
          //console.log($(this)[0].attributes[1].value);
          $(".state-container-title-oncanvas-forDeletion"+stateIDToRemove).append(htmlDeleteButton);
          //after clicking on the state container, if they click on X then the state will be removed
          $(".deleteButton").click(function(){
            removeConnection(stateIDToRemove, "*","*");
            removeConnection("*",stateIDToRemove, "*");
			  
			  //first delete all classes associated to it
            for(var i=0; i<diagram[0].length; i++){
              if(diagram[0][i] == null){
                continue;
              }
              if(diagram[0][i]._id == stateIDToRemove){
                //splice function finds the element at index i and then removes 1 element at/after that index
				//can't actually remove as that would result in indices being messed up.
                diagram[0][i] = null
                //have to render the diagram to reflect the changes on the canvas, the number -1 is given randomly as it is not used 
              }
            };
            renderStateContainer(diagram, -1);
          }).attr("state", $(this));//REMOVABLE AFTER TEST 
        }else{
          $(this).css({
            border : "none",
          });
          $(".deleteButton").css({
            display: "none",
          })
        }
      });
        canvas.append(dom);

      } else if (state.type === "behaviour") {
        

      } else if (state.type === "TOOL-3") {
        html = "<h3>TOOL 3</h3>";
      }
    }
  }


  //Render the behaviour
  function renderBehaviour(behaviourArray){
    var dom2="";
    for( var b in behaviourArray){
      var beh = behaviourArray[b];
      var htmlBehaviour = `<h6 style="color:white;" class="behaviour-oncanvas data-behaviour=${beh.behaviourType}">${beh.behaviourType}</h6>`;
        dom2 += htmlBehaviour;
    }
    
    return dom2;
      //stateCanvasBody.append(dom2);
  }

  document.getElementsByClassName("canvas")[0].addEventListener("scroll", function(e){

    drawLines();
  
  })
}