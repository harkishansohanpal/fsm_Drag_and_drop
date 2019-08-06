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
  ctx.fillStyle = "white";
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
      // top left corner of box DOM element, vs top left corner of actual box.
      var boxoffsetL = 46;
      var boxoffsetT = 30;
      //width and height of the box
      var boxWidth=184;
      var boxHeight=144;
      //width and height of the entire thing
      var totalBoxWidth = 2*boxoffsetL+boxWidth;
      var totalBoxHeight = 2*boxoffsetT+boxHeight; 
      
      //line offsets must be between boxoffset and boxoffset +boxwidth/height
      var line_offset_left = 50+9*offset-document.getElementsByClassName("canvas")[0].scrollLeft; //was0.01
      var line_offset_top = 50+9*offset-document.getElementsByClassName("canvas")[0].scrollTop;
      var circle_offset_left = [boxoffsetL+boxWidth, boxoffsetL, boxoffsetL, boxoffsetL+boxWidth][offsetNumber%4]-document.getElementsByClassName("canvas")[0].scrollLeft;
      var circle_offset_top = [boxoffsetT, boxoffsetT, boxoffsetT+boxHeight, boxoffsetT+boxHeight][offsetNumber%4]-document.getElementsByClassName("canvas")[0].scrollTop;
      var circle_radius = 1000*(0.03 - (offsetNumber > 4? 0.01 : 0));
				try{
				var id = getIdFromString(ui.helper[0].children[0].getAttribute("class"));
				} catch(e){
					var id = null;
				}
				
				var domainPosition = domain == id ? ui.position : diagram[0][domain].position; 
				var targetPosition = target == id ? ui.position : diagram[0][target].position;
				
				if(domain== target){
					//draw a circle
					  drawCircle(domainPosition.left+circle_offset_left, domainPosition.top+circle_offset_top,circle_radius, color);
					  var offsettedLocation = {left:domainPosition.left+circle_offset_left+(circle_offset_left - totalBoxWidth/2), top:domainPosition.top+circle_offset_top+(circle_offset_top - totalBoxHeight/2)*(offsetNumber > 4 ? 0.5 :1)}
					  drawLabel(context, label, offsettedLocation, offsettedLocation, "center", 0);					
				}
				else {
					//draw a line
					
					var p1x = domainPosition.left+line_offset_left;
					var p1y = domainPosition.top+line_offset_top;
					var p2x = targetPosition.left+line_offset_left;
					var p2y = targetPosition.top+line_offset_top;
					
					//get the intersection points
					var tlx = domainPosition.left-document.getElementsByClassName("canvas")[0].scrollLeft+ boxoffsetL;
					var tly = domainPosition.top-document.getElementsByClassName("canvas")[0].scrollTop+ boxoffsetT;
					try{
						var int1 =getLineEnd(p1x, p1y, p2x ,p2y,tlx, tly ,boxWidth, boxHeight);
					} catch (e){
						if(e == "p2 inside rectangle"){
							int1 = [p1x, p1y]
						} 
					}
					var tlx = targetPosition.left-document.getElementsByClassName("canvas")[0].scrollLeft+ boxoffsetL;
					var tly = targetPosition.top-document.getElementsByClassName("canvas")[0].scrollTop+ boxoffsetT;
					
					try{
						var int2 =getLineEnd(p2x ,p2y,p1x, p1y, tlx, tly ,boxWidth, boxHeight);
					} catch (e){
						if(e == "p2 inside rectangle"){
							int2 = [p2x, p2y]
						}
					}
					drawLine(int1[0], int1[1], int2[0], int2[1], color);
					//draw the small circles
					drawCircle(int1[0], int1[1], 10, color)
					drawCircle(int2[0], int2[1], 10, color)
					//draw the labels
					var position1 = {"left":int1[0], "top": int1[1]};
					var position2 = {"left":int2[0], "top": int2[1]};
					console.log(position1)
					console.log(position2)
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
	  dummyDiagram = [[...stateData], [...eventData] , [...connections]];
	  dummyConnections=[...connections];
	  var string = JSON.stringify(transform(stateData, eventData));  
	  var dummyString = "{diagram:" + JSON.stringify(dummyDiagram) + "}";
	  var filename = document.getElementById("fsmName").value;
	   
	 // var strinag ='{"vertices":[{"name":"A","behaviors":["Forward"]},{"name":"B","behaviors":["Backward","Backward"]},{"name":"C","behaviors":[]}],"edges":[{"event":{"name":"An","input":"NoObstacle"},"fromState":"A","toState":"B"},{"event":{"name":"Cs","input":"light"},"fromState":"C","toState":"A"},{"event":{"name":"Cr","input":"ObstacleR"},"fromState":"C","toState":"B"}],"startState":"A","endStates":[]}';
	  

	   return fetch("http://localhost:8080/FinchFSM/Save", {method:"post", body:JSON.stringify({

		   filename:filename,
		   fsm:string,
		   diagram: dummyString
		   }
	     )}).then(function(msg) {
	    	 alert("Saved succesfully");
	    	 console.log(msg)
	    	 });
	  
	}

function loadModel(e){
	console.log(e + "fsmLoadButton");
	var data = JSON.parse(document.getElementById(e + "fsmLoadButton").getAttribute("value")).diagram;
	stateData = data[0];
	eventData = data[1];
	connections = data[2];
	init();
	
	document.getElementById("executejsonfsm").value = document.getElementById(e + "fsmRunButton").value;
	
}

//function save(){
//	var string = JSON.stringify(transform(stateData, eventData));
//	console.log(string);
//	//var string ='{"vertices":[{"name":"A","behaviors":["Forward"]},{"name":"B","behaviors":["Backward","Backward"]},{"name":"C","behaviors":[]}],"edges":[{"event":{"name":"An","input":"NoObstacle"},"fromState":"A","toState":"B"},{"event":{"name":"Cs","input":"light"},"fromState":"C","toState":"A"},{"event":{"name":"Cr","input":"ObstacleR"},"fromState":"C","toState":"B"}],"startState":"A","endStates":[]}';

//	
//}

/* Event visuals */


$("#noObstacle").mousedown(function () { 
  $(this).css("background-color","green");
  labelinput = "No Obstacle"
  colorinput = "red"
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
  
  if(stateData.length!=0){
	  renderStateContainer(diagram, 1);
	  drawLines();
	  
  }
  
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
          type: "state",
          halt:false

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
      var stateHalt = state.halt ? "halt" : "";
      console.log(state + stateHalt)
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
                        <h6 >State-${state._id} ${stateHalt}</h6>
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
                      behaviourType:ui.helper["0"].innerHTML,
					  time: 100, //default
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
               var htmlBehaviour = `<div  style="color:black;" class="behaviour-oncanvas" data-behaviour="${ui.helper["0"].innerHTML}">${ui.helper["0"].innerHTML} 
                                      <input style="width: 30px;" id="behaviourInput${behaviour._id}" type="number"/>
                                    </div>`;
               $(".state-container-body-oncanvas",this).append(htmlBehaviour);    
                $(`#behaviourInput${behaviour._id}`).on("change", function(){
                  behaviour.time = this.value;
                })              
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
          $(this).css("background-image", "url(./Java/WebContent/resources/images/State2Focus.png)");
          //create and add the delete button when clicked
          var htmlDeleteButton = `<h6 class="deleteButton"></h6>`;
          var stateIDToRemove = $(this)[0].attributes[1].value;
          //console.log($(this)[0].attributes[1].value);
          $(".state-container-title-oncanvas-forDeletion"+stateIDToRemove).append(htmlDeleteButton);
          //after clicking on the state container, if they click on X then the state will be removed
          $(".deleteButton").click(function(){
        	console.log("delete called");
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
			drawLines();
          }).attr("state", $(this));//REMOVABLE AFTER TEST 
    
          var htmlHaltButton = `<h6 class="haltButton"></h6>`;
          var stateIDToHalt = $(this)[0].attributes[1].value;
          var children = $(".state-container-title-oncanvas-forDeletion"+stateIDToHalt).children();
          $(".state-container-title-oncanvas-forDeletion"+stateIDToHalt).append(htmlHaltButton);
	      $(".haltButton").click(function(){
	         stateData[stateIDToHalt].halt = !stateData[stateIDToHalt].halt;
	         console.log("halt called");
	         renderStateContainer(diagram, 0);
	      })

        }else{
          $(this).css("background-image", "url(./Java/WebContent/resources/css/State2.png)");
          $(".deleteButton").css({
            display: "none",
          })

          $(".haltButton").css({
            display: "none",
          })

        }
      });
        canvas.append(dom);

      } 
    }
  }


  //Render the behaviour
  function renderBehaviour(behaviourArray){
    var dom2="";
    for( var b in behaviourArray){
      var beh = behaviourArray[b];
      var htmlBehaviour = `<div style="color:black;" class="behaviour-oncanvas data-behaviour=${beh.behaviourType}">${beh.behaviourType}
                              <input style="width: 30px;" id="behaviourInput${beh._id}" type="number"/>                     
                            </div>`;
        dom2 += htmlBehaviour;
        $(`#behaviourInput${beh._id}`).on("change", function(){
          behaviour.time = this.value;
        })
    }
    
    return dom2;
      //stateCanvasBody.append(dom2);
  }

  document.getElementsByClassName("canvas")[0].addEventListener("scroll", function(e){

    drawLines();
  
  })
  
}