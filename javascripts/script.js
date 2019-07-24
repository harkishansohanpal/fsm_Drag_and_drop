$(init);
function init() {
    var diagram = [];
    var canvas = $(".canvas");
    var tools = $(".tools");
    $(".tool").draggable({
        helper: "clone"
    });
    canvas.droppable({
        drop: function(event, ui) {
            var node = {
                _id: (new Date).getTime(),
                position: ui.helper.position()
            };
            node.position.left -= canvas.position().left;
            if(ui.helper.hasClass("state-container")){
                node.type = "state";
            } else if(ui.helper.hasClass("tool-2")){
                node.type = "TOOL-2";
            } else if(ui.helper.hasClass("tool-3")){
                node.type = "TOOL-3";
            } else {
                return;
            }
            diagram.push(node);
            renderDiagram(diagram, node._id);
        }
    });
    function renderDiagram(diagram, _id) {
        canvas.empty();
        for(var d in diagram) {
            var node = diagram[d];
            var html = "";
            if(node.type === "state") {
                
                html = `<div class="tool tool-1 state-container-${_id} state-container-oncanvas"><div class="state-container-title"><h6 >State</h6></div><div class="state-container-body">                
                            </div></div>`;
            } else if(node.type === "TOOL-2") {
                html = "<h3>TOOL 2</h3>";
            } else if(node.type === "TOOL-3") {
                html = "<h3>TOOL 3</h3>";
            }
            var dom = $(html).css({
                "position": "absolute",
                "top": node.position.top,
                "left": node.position.left
            }).draggable({
                stop: function(event, ui) {
                    console.log(ui);
                    var id = ui.helper.attr("id");
                    for(var i in diagram) {
                        if(diagram[i]._id == id) {
                            diagram[i].position.top = ui.position.top;
                            diagram[i].position.left = ui.position.left;
                        }
                    }
                }
            }).attr("id", node._id);
            canvas.append(dom);
        }
        $(function(){
            $(".state-container-oncanvas").resizable();
            console.log("here in resize")
        });
    }

    
}
