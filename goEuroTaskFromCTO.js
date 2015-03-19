var _ = require('underscore');

var turnLeft = {
	draw: function(turtle){
		turtle.turnLeft(45)
	},
	split: function(){
		return turnLeft;
	}
}


var turnRight = {
	draw: function(turtle){
		turtle.turnRight(90)
	},
	split: function (){
		return turnRight;
	}
}

var goTo = function(whereTo){
	var result = {		
		draw: function(turtle){
			turtle.goTo(whereTo);
		},
		howFar: function(){
			return whereTo;	
		},
		split: function(){
			return [goTo(whereTo/3), turnLeft, goTo(whereTo/3), turnRight, goTo(whereTo/3), turnLeft, goTo(whereTo/3)];
		}
	}
	return result;
	
}



var consoleLogTurtle = {
	goTo : function (howFar){
		console.log("goTo " + howFar);
	},
	turnLeft : function (degrees){
		console.log("turnLeft "  + degrees);	
	},
	turnRight : function (degrees){
		console.log("turnRight "  + degrees);	
	}
};

function generateWithMap(howmany, initVectorSize){
	function buildMoves(currentMoves){
		if(howmany === 0){
			return currentMoves;
		}else{
			howmany --;
			var mapped = _.invoke(currentMoves, "split");
			return buildMoves(_.flatten(mapped));	
		}

	}
	return buildMoves([goTo(initVectorSize)])
}


function generateEagerly(howmany, initVectorSize, turtle){
	var finalDistance = initVectorSize/(Math.pow(3, howmany));
	function drawMoves(level){
		if(level === 0){
			goTo(finalDistance).draw(turtle);		
		}else{			
			drawMoves(level-1);
			turnLeft.draw(turtle);
			drawMoves(level-1);
			turnRight.draw(turtle);
			drawMoves(level-1);
			turnLeft.draw(turtle);
			drawMoves(level-1);
		}
	}	
	return drawMoves(howmany)
}


_.invoke(generateWithMap(8, 59049), "draw", consoleLogTurtle);

generateEagerly(20, 3486784401, consoleLogTurtle);
