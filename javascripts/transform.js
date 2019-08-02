function transform(stateData, eventData){
	var verticesArray = [];
	var edgesArray = [];
	
	for (var state in stateData){
		let behArray = [];
		
		for (var beh in stateData[state].behaviourArray){
			behArray.push(stateData[state].behaviourArray[beh].behaviourType);
		}
		
		verticesArray.push({name : String.fromCharCode(65 + stateData[state]._id),
				behaviours : behArray});
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
				break;
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
		endState: []
		
	}
	
	return jsonOutput;
	
}