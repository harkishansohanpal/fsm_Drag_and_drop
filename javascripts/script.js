


$(init);

function init() {

  //diagram is the main array, we push data into it
  var stateData = [];
  var eventData = [];
  var diagram = [stateData, eventData];
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
    if (ui.helper.hasClass("state-container")) {
        var state = {
          _id: stateID++,
          position: ui.helper.position(),
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
      var state = diagram[0][d];
      var html = "";
      
      console.log(state);   
      console.log(d);   
      //if state.type is state then declare html and render
      if (state.type == "state") {
        //console.log(state.behaviourArray);
        var behaviourDiv;
        if(state.behaviourArray.length>0){
        behaviourDiv=renderBehaviour(state.behaviourArray);
        console.log(state.behaviourArray);
        }else{
          behaviourDiv="";
        }
        console.log(behaviourDiv);

        html = `<div class="state-container-oncanvas State-${state._id}">
                    <div class="state-container-title">
                        <h6 >State-${state._id}</h6>
                    </div>
                    <div class="state-container-body state-container-body-oncanvas">     
                    ${behaviourDiv}
                    </div>
                </div>`;
        var dom = $(html)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left-200
        })
        //make state-container in canvas draggable
        .draggable({
          stop: function(event, ui) {
            console.log(ui);
            var id = ui.helper.attr("id");
            for (var i in diagram[0]) {
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
             console.log($(this));
            // var stateContainerId = $(this).attr("id");
            //console.log(diagram);
              //console.log(ui);
              if (ui.helper.hasClass("behaviour")) {
                  var behaviour = {
                      _id: behaviourID++,
                      position: ui.helper.position(),
                      behaviourType:ui.helper["0"].innerHTML
                  };
              }
              ////hard coded state//////
              diagram[0][`${$(this)["0"].attributes[1].value}`].behaviourArray.push(behaviour);
              // console.log(stateCanvasBody);
              var htmlBehaviour = `<h6  class="behaviour" data-behaviour="${ui.helper["0"].innerHTML}">${ui.helper["0"].innerHTML}
              </h6>`;
              $(".state-container-body-oncanvas",this)              
              .append(htmlBehaviour);              
               //$(this)["0"].childNodes[3].$(".state-container-body-oncanvas").append("htmlBehaviour");
          }
      })
      .attr("state_id", state._id)

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
      console.log(beh.behaviourType);
    var htmlBehaviour = `<h6 class="behaviour data-behaviour=${beh.behaviourType}">${beh.behaviourType}</h6>`;
        dom2 += htmlBehaviour;
    }
    
    return dom2;
      //stateCanvasBody.append(dom2);
  }
}