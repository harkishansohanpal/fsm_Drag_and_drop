$(init);
function init() {
  var diagram = [];
  var canvas = $(".canvas");
  $(".state-container").draggable({
    helper: "clone"
  });
  $(".behaviour").draggable({
    helper: "clone"
  });

  var stateID = 0;
  var behaviourID = 0;
  canvas.droppable({
    drop: function(event, ui) {
      if (ui.helper.hasClass("state-container")) {
        var state = {
          _id: stateID++,
          position: ui.helper.position(),
          type: "state"
        };
      } else if (ui.helper.hasClass("behaviour")) {
        var behaviour = {
          _id: stateID++,
          position: ui.helper.position(),
          type: "behaviour"
        };
      } else if (ui.helper.hasClass("tool-3")) {
        var event = {
          _id: eventID++,
          position: ui.helper.position(),
          type: "event"
        };
      } else {
        return;
      }
      diagram.push(state);
      renderDiagram(diagram, state._id);
    }
  });
  function renderDiagram(diagram, _id) {
    canvas.empty();
    for (var d in diagram) {
      var state = diagram[d];
      var html = "";
      if (state.type == "state") {
        html = `<div class="state-container-${_id} state-container-oncanvas"><div class="state-container-title"><h6 >State ${
          state._id
        }</h6></div><div class="state-container-body">                
                            </div></div>`;
      } else if (state.type === "behaviour") {
        html = '<h6 class="behaviour"></h6>';
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
      canvas.append(dom);
    }
    $(function() {
      $(".state-container-oncanvas").resizable();
    });
  }
}
