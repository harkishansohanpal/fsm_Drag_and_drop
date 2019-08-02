function transform(stateData, eventData){
	var verticesArray = [];
	var edgesArray = [];
	var endStates = [];
	
	for (var state in stateData){
		let behArray = [];
		
		let stopChecked = false;
		
		for (var beh in stateData[state].behaviourArray){
<<<<<<< HEAD
			
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
=======
			behArray.push(stateData[state].behaviourArray[beh].behaviourType);
		}
		
		verticesArray.push({name : String.fromCharCode(65 + stateData[state]._id),
				behaviours : behArray});
>>>>>>> 14e12b27d493c84e909ee1725549574b6af0d3b2
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
<<<<<<< HEAD
				break;`
=======
				break;
>>>>>>> 14e12b27d493c84e909ee1725549574b6af0d3b2
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
<<<<<<< HEAD
					
					vertices: verticesArray,
					edges: edgesArray,
					startState: String.fromCharCode(65 + stateData[0]["_id"]),
					endState: endStates
					
=======
		
		vertices: verticesArray,
		edges: edgesArray,
		startState: String.fromCharCode(65 + stateData[0]["_id"]),
		endState: []
		
>>>>>>> 14e12b27d493c84e909ee1725549574b6af0d3b2
	}
	
	return jsonOutput;
                
}
