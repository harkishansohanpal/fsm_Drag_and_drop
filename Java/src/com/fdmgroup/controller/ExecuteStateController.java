package com.fdmgroup.controller;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.State;

/**
 * Controller to handle all actions(robot movements/console output)
 * @author 
 *
 */
public class ExecuteStateController {

	public void execute(State state){
		for (int i = 0; i < state.getBehaviours().size(); i++) {
			
			Behaviour action = state.getBehaviours().get(i);
			
			switch(action){
			case forward:
				System.out.println("FORWARD");
				break;
			case backward:
				System.out.println("BACKWARD");
				break;
			case turnL:
				System.out.println("TURN LEFT");
				break;
			case turnR:
				System.out.println("TURN RIGHT");
				break;
			case spinL:
				System.out.println("SPIN LEFT");
				break;
			case spinR:
				System.out.println("SPIN RIGHT");
				break;
			default:
				System.out.println("FORWARD");
			}
		}
	}
	
}
