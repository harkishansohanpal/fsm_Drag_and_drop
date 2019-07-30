function transform(stateData, eventData){
	var verticesArray = [];
	var edgesArray = [];
	
	for (var state in stateData){
		let behArray = [];
		
		for (var beh in stateData[state].behaviourArray){
			var s = stateData[state].behaviourArray[beh].behaviourType;
			console.log(s);
			var replace_table = {"Forward":"forward",
			"Backward":"backward",
			"Turn Left":"turnL",
			"Turn Right":"turnR",
			"Spin":"spin",
			}
			behArray.push(replace_table[s]);
			
		}
		
		verticesArray.push({name : String.fromCharCode(65 + stateData[state]._id),
				behaviors : behArray});
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
			case "Obstacle Centre":
				c = "a";
				input = "ObstacleAll"
				break;
			case "Light":
				c = "l";
				input = "Light";
				break;
			default :
				throw eventData[event].label;
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
		endStates: []
		
	}
	
	return jsonOutput;
	
}