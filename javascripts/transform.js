function transform(stateData, eventData){
	var verticesArray = [];
	var edgesArray = [];
	var endStates = [];
	
	for (var state in stateData){
		let behArray = [];
		
		let stopChecked = false;
		
		for (var beh in stateData[state].behaviourArray){
			
			let specificBehaviour = stateData[state].behaviourArray[beh].behaviourType;
			behArray.push({
				behaviour: specificBehaviour,
				time: stateData[state].behaviourArray[beh].time
			}});
			
			if (specificBehaviour == "Stop" && !stopChecked){
				stopChecked = true;
				endStates.push(String.fromCharCode(65 + stateData[state]._id));
			}
							
		}
		
		verticesArray.push({name : String.fromCharCode(65 + stateData[state]._id),
										robotActions : behArray});
	}
	
	
	for (var event in eventData){
		let c;
		let input;
		
		switch (eventData[event].label){
			case "No Obstacle":
				c = "n";
				input = "NoObstacle";
				break;
			case "Obstacle Right":
				c = "r";
				input = "ObstacleR";
				break;
			case "Obstacle Left":
				c = "l";
				input = "ObstacleL";
				break;
			case "Obstacle Center":
				c = "a";
				input = "ObstacleAll"
				break;
			default:
				c = "s";
				input = "light";
				break;`
		}
		
		let from = String.fromCharCode(65 + eventData[event].fromState);
		let to = String.fromCharCode(65 + eventData[event].toState);
		
		edgesArray.push({
						event: {
										name: from + c,
										input: input
						},
						fromState: from,
						toState: to
						
		})

					
	}
	
	var jsonOutput = {
					
					vertices: verticesArray,
					edges: edgesArray,
					startState: String.fromCharCode(65 + stateData[0]["_id"]),
					endState: endStates
					
	}
	
	return jsonOutput;
                
}
