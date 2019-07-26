


$(init);

function init() {

  //diagram is the main array, we push data into it
  var diagram = [];
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
          behaviour:[],
          type: "state"
        };
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

        var dom = $(html)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        })
        //make state-container in canvas draggable
        .draggable({
          stop: function(event, ui) {
            console.log(ui);
            var id = ui.helper.attr("id");
            for (var i in diagram) {
              if (diagram[i]._id == id) {
                diagram[i].position.top = ui.position.top;
                diagram[i].position.left = ui.position.left;
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
              var htmlBehaviour = `<h6  class="behaviour"
              >${ui.helper["0"].innerText}
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