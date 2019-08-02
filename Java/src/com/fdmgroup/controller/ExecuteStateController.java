package com.fdmgroup.controller;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.RobotAction;
import com.fdmgroup.model.State;

import edu.cmu.ri.createlab.terk.robot.finch.Finch;

public class ExecuteStateController {

	public Finch myFinch = new Finch();
	
	public void execute(State state){
		for (int i = 0; i < state.getBehaviours().size(); i++) {
			
			RobotAction action = state.getBehaviours().get(i);
			
			switch(action.getBehaviour()){
			case Forward:
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, action.getTime());
				break;
			case Backward:
				System.out.println("BACKWARD");
				myFinch.setWheelVelocities(-255, -255, action.getTime());
				break;
			case TurnL:
				System.out.println("TURN LEFT");
				myFinch.setWheelVelocities(0, 255, action.getTime());
				break;
			case TurnR:
				System.out.println("TURN RIGHT");
				myFinch.setWheelVelocities(255, 0, action.getTime());
				break;
			case Spin:
				System.out.println("SPIN");
				myFinch.setWheelVelocities(180, -180, action.getTime());
				break;
			case Stop:
				System.out.println("STOP");
				myFinch.setLED(0, 0, 255);
				break;
			default:
				System.out.println("FORWARD");
				myFinch.setWheelVelocities(255, 255, action.getTime());
				break;
			}
			
		}
	}
	
}
