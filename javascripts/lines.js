// given two points of a line (given as p1x, p1y, p2x, p2y), and a rectangle (given by coordinates of the top left, and height and width), with p1 inside the rectangle and p2 outside
// returns the coordinates of where the line intersects the rectangle
function pointInsideRectangle(px, py, tlx, tly, width, height){
	if(px < tlx || px > tlx+width || py < tly || py > tly+height){
		return false;
	}
	return true;
}
function getIntersection(line1, line2){
	// lines are to be in the form of "ax + by = c", the lines are coefficients.
	var a = line1[0] , b = line1[1], c = line2[0], d = line2[1];
	var determinant = a*d-b*c;
	if (Math.abs(determinant) < 0.000001){
		throw "lines are too close to parallel";
	}
	// get the inverse matrix
	var ai = d/determinant, bi = -b/determinant, ci = -c/determinant, di = a/determinant;
	// now multiply
	return [ai * line1[2] + bi * line2[2], 	ci * line1[2] + di * line2[2]];
	
}
function getLineEnd(p1x, p1y, p2x, p2y, tlx, tly, width,height){
	// ensure p1 is inside and 
	if(!pointInsideRectangle(p1x, p1y, tlx, tly,  width,height)){
		throw "p1 outside of rectangle";
	}
	if(pointInsideRectangle(p2x, p2y, tlx, tly, width,height)){
		throw "p2 inside rectangle";
	}
	//convert the line to ax+by=c
	// a (p2x - p1x) = -b (p2y - p1y)
	var a,b,c
	if(p2y - p1y != 0){ // a is not 0, set a = 1 (use this chart)
	// if a = 0 then b = 0 as well, we have 0 = c, so c = 0. This gives [0,0,0] which is not a point in P^2
	// a (p2x - p1x)/(p2y - p1y) = -b 
		a = 1;
		b = -(p2x - p1x)/(p2y - p1y);
		c = a*p1x + b*p1y ;
	} else {
		//p2y = p1y, so subtracting the equations gives a  = 0/(p2x - p1x) = 0
		// now we are in P^1 with b and c. We are solving by=c in P^1. 
		// so if y = 0 then we have [0,1,0]. Else, we have [0,?,1]
		a = 0;
		if(p2y == 0){
			b = 1;
			c = 0;
		} else{
			c = 1;
			b = c/p2y;
		}
	}
	var lineCoefficients = [a,b,c];
	var topLine = [0, 1, tly];// y = top left y
	var leftLine = [1, 0, tlx] // x = tlx
	var rightLine =[1, 0, tlx+width] // x = tlx+width
	var bottomLine = [0, 1, tly+height];// y = top left y + height
	var lines = [topLine, leftLine, rightLine, bottomLine]
	for(var i=0; i<4; i++){
		var line = lines[i]
		try {
			var intersection = getIntersection(lineCoefficients, line);
			// intersection must be inside the rectangle
			if(pointInsideRectangle(intersection[0], intersection[1],  tlx, tly,  width,height)){
			// and must also be in the correct direction of the second line:
				if((intersection[0] - p1x) * (p2x-p1x) + (intersection[1] - p1y) * (p2y-p1y) >= 0){
					return intersection;
				}
			}
		}catch (e){
			if(e == "lines are too close to parallel"){
				;
			} else {
				throw e;
			}
		}
	}
}

function testCases(){
	//getLineEnd(p1x, p1y, p2x, p2y, tlx, tly, height, width){
	console.log("This should be 5,5")
	console.log(getLineEnd(0,0,100,100,-10,-5,20,10)); // output should be 5,5, line is [1,-1,0]	
	
	console.log("This should be 166.216, 390")
	console.log(getLineEnd(159.1,337.34,207.9,689.46,133,260,150,130)); // output should be 166.216, 390, line is [3.7,-0.5,420]

	
	console.log("This should be 207.407, 260")
	console.log(getLineEnd(242,291.133,80,145.333,133,260,150,130)); // output should be 207.407, 260, line is [2.7,-3,-220]
	
	
	console.log("This should be 283, 328.033")
	console.log(getLineEnd(242,291.133,445, 473.833,133,260,150,130)); // output should be 283, 328.033, line is [2.7,-3,-220]  
	
	console.log("This should be 174, 390 (vertical line)")
	console.log(getLineEnd(174 ,300,174, 600,133,260,150,130)); // output should be 174, 390, line is [1,0,174] 
	
	
	console.log("This should be 133, 290 (horizontal line)")
	console.log(getLineEnd(211 ,290,1, 290,133,260,150,130)); // output should be 133, 290, line is [0,1,290] 
	
	console.log("all done")
}