package com.fdmgroup.controller;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.State;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

/**
 * Controller to handle all actions(robot movements/console output)
 * @author 
 *
 */
public class ExecuteStateController {

	public Finch myFinch = new Finch();
	
	public void execute(State state){
		
		for (int i = 0; i < state.getBehaviours().size(); i++) {
			
			Behaviour action = state.getBehaviours().get(i);
			
			switch(action){
			case forward:
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, 200);
				break;
			case backward:
				System.out.println("BACKWARD");
				myFinch.setWheelVelocities(-255, -255, 200);
				break;
			case turnL:
				System.out.println("TURN LEFT");
				myFinch.setWheelVelocities(0, 255, 975);
				break;
			case turnR:
				System.out.println("TURN RIGHT");
				myFinch.setWheelVelocities(255, 0, 975);
				break;
			case spin:
				System.out.println("SPIN");
				myFinch.setWheelVelocities(180, -180, 500);
				break;
			case stop:
				myFinch.setLED(0, 0, 255);
				System.out.println("STOP");
				break;
			default:
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, 200);
			}
		}
	}
	
}
