package com.fdmgroup.controller;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.State;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class ExecuteStateController {

	public Finch myFinch = new Finch();
	
	public void execute(State state){
		for (int i = 0; i < state.getBehaviours().size(); i++) {
			
			Behaviour action = state.getBehaviours().get(i);
			
			switch(action){
<<<<<<< HEAD
			case Forward:
=======
			case forward:
>>>>>>> 97697152844fdbd2b9906ccf4add01224b32abe8
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, 200);
				break;
			case Backward:
				System.out.println("BACKWARD");
				myFinch.setWheelVelocities(-255, -255, 200);
				break;
			case TurnL:
				System.out.println("TURN LEFT");
				myFinch.setWheelVelocities(0, 255, 975);
				break;
			case TurnR:
				System.out.println("TURN RIGHT");
				myFinch.setWheelVelocities(255, 0, 975);
				break;
<<<<<<< HEAD
			case Spin:
				System.out.println("SPIN");
				myFinch.setWheelVelocities(180, -180, 1000);
				break;
			case Stop:
				System.out.println("STOP");
				myFinch.setLED(0, 0, 255);
=======
			case spin:
				System.out.println("SPIN");
				myFinch.setWheelVelocities(180, -180, 500);
				break;
			case stop:
				myFinch.setLED(0, 0, 255);
				System.out.println("STOP");
>>>>>>> 97697152844fdbd2b9906ccf4add01224b32abe8
				break;
			default:
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, 200);
<<<<<<< HEAD
				break;
=======
>>>>>>> 97697152844fdbd2b9906ccf4add01224b32abe8
			}
			
		}
	}
	
}
