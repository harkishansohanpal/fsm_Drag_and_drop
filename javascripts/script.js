$(init);
function init() {
  var diagram = [];
  var canvas = $(".canvas");
  var stateCanvasBody = $(".state-container");


  $(".state-container").draggable({
    helper: "clone",
  });
  $(".behaviour").droppable({
    helper: "clone",
  });

  var stateID = 0;
  var behaviourID = 0;
  canvas.droppable({
    accept:".state-container,.behaviour",
    drop: function(event, ui) {
    if (ui.helper.hasClass("state-container")) {
        var state = {
          _id: stateID++,
          position: ui.helper.position(),
          type: "state"
        };
      } else {
        return;
      }
      diagram.push(state);
      renderDiagram(diagram, state._id);
    }
  });

 

/*================================================render function=======================================================*/


  function renderDiagram(diagram, _id) {
    canvas.empty();
    for (var d in diagram) {
      var state = diagram[d];
      var html = "";
      var htmlBehaviour = "";
      if (state.type == "state") {
        html = `<div class="state-container-oncanvas">
                    <div class="state-container-title">
                        <h6 >State ${state._id}</h6>
                    </div>
                    <div class="state-container-body state-container-body-oncanvas">                
                    </div>
                </div>`;
      } else if (state.type === "behaviour") {
        htmlBehaviour = '<h6 class="behaviour"></h6>';
      } else if (state.type === "TOOL-3") {
        html = "<h3>TOOL 3</h3>";
      }
      var dom = $(html)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        })
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
        
        .attr("id", state._id);


        var dom2 = $(htmlBehaviour)
        .css({
          position: "absolute",
          top: state.position.top,
          left: state.position.left
        });
        
        canvas.append(dom);

    }

  }

  
  
}