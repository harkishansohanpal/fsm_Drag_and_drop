


var connections = [[0,1, "red"],[0,2, "blue"],[1,2, "black"]];
var context =document.getElementById("theCanvas").getContext("2d");
var diagram = null;
function drawLine(x0, y0, x1, y1, color){
	console.log("drawing line")
	console.log(x0, y0, x1, y1)
	 context.beginPath(); 
  // Staring point (10,45)
   context.moveTo(x0,y0);
  // End point (180,47)
  context.lineTo(x1,y1);
  context.strokeStyle = color;
  // Make the line visible
  context.stroke();
}
function drawCircle(x,y,r){
	console.log(x,y,r)
	context.beginPath();
	context.arc(x,y,r,0*Math.PI,2*Math.PI);
	context.stroke();
	
}
function getLocation(i){
	return diagram[i].position;
}
function connect(x,y){	
	drawLine(getLocation(x).left+150, getLocation(x).top+150, getLocation(y).left+150, getLocation(y).top+150);
}
function connectMultiplePairs(lst1, lst2){
	context.clearRect(0,0,3000, 3000);
	for(var i=0;i<lst1.length;i++){
		connect(lst1[i] , lst2[i]);
	}
}
function getAllLocation(){
	for(var i=0; i<diagram.length;i++){
		console.log(i + " " + getLocation(i).left + " " + getLocation(i).top);
	}
	
}
$(init);
function init() {

  //diagram is the main array, we push data into it
  diagram = [];
  var canvas = $(".canvas");
  var stateCanvasBody = $(".state-container-oncanvas");
  context = $("#theCanvas")[0].getContext("2d");
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
    if (ui.helper.hasClass("state-container")) {
        var state = {
          _id: stateID++,
          position: ui.helper.position(),
          behaviour:[],
          type: "state"
        };
		state.position.left -= 300;
      } else {
        return;
      }

      //push state to diagram array
      diagram.push(state);

      //call render function and pass diagram array and state-id
      renderStateContainer(diagram, state._id);
    }
  });


  
  function renderStateContainer(diagram, _id) {
    canvas.empty();
    //loop through diagram array
    for (var d in diagram) {
      var state = diagram[d];
      var html = "";
      
      //if state.type is state then declare html and 
      if (state.type == "state") {
        html = `<div class="state-container-oncanvas State-${state._id}">
                    <div class="state-container-title">
                        <h6 >State-${state._id}</h6>
                    </div>
                    <div class="state-container-body state-container-body-oncanvas">                
                    </div>
                </div>`;
		console.log(diagram[d]["_id"] + "," + d)
		console.log( + " " + state.position.top+ " " + state.position.left + " not drag")
	    var dom = $(html)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        })
        //make state-container in canvas draggable
        .draggable({
			drag:function(event, ui){
				console.log(event)
				context.clearRect(0,0,3000,3000);
				connections.forEach(function(d){
				var domain = d[0];
				var target = d[1];
				var color = d[2];
				var id = parseInt((ui.helper[0].getAttribute("class").substr(31, 1)));
				if(target == id){
					try{
					drawLine(diagram[domain].position.left+150, diagram[domain].position.top+150,ui.position.left+150, ui.position.top+150, color);
					} catch{
						;
					}
				}
				else if(domain == id){
					try{
					drawLine(ui.position.left+150, ui.position.top+150,diagram[target].position.left+150, diagram[target].position.top+150, color);
					} catch{
						;
					}				
				} else{
					
					drawLine(diagram[domain].position.left+150, diagram[domain].position.top+150 , diagram[target].position.left+150, diagram[target].position.top+150, color);
				}
				})
			},
			stop: function(event, ui) {
            var id = parseInt((ui.helper[0].getAttribute("class").substr(31, 1)))
            for (var i in diagram) {
				console.log(id)
              if (diagram[i]._id == id) {
				 var state = diagram[d]; 
                diagram[i].position.top = ui.position.top;
                diagram[i].position.left = ui.position.left;
				console.log(id + " " + state.position.top + " " + state.position.left+ " drag")
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
			  
            // console.log($(this));
            // var stateContainerId = $(this).attr("id");
              console.log(ui);
              if (ui.helper.hasClass("behaviour")) {
                  var behaviour = {
                      _id: behaviourID++,
                      position: ui.helper.position(),
                      type: "behaviour"
                  };
              }
              diagram.push(behaviour);
              // console.log(stateCanvasBody);
			  console.log(ui);
              var htmlBehaviour = `<h6  class="behaviour"
              >${ui.helper["0"].innerHTML}
              </h6>`;
              $(".state-container-body-oncanvas",this).append(htmlBehaviour);
              
               //$(this)["0"].childNodes[3].$(".state-container-body-oncanvas").append("htmlBehaviour");
          }
      })

        canvas.append(dom);

      } else if (state.type === "behaviour") {
        

      } else if (state.type === "TOOL-3") {
        html = "<h3>TOOL 3</h3>";
      }
    }
  }


  function renderBehaviour(){
    htmlBehaviour = '<h6 class="behaviour "></h6>';

        var dom2 = $(htmlBehaviour)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        });

      stateCanvasBody.append(dom2);
  }
}