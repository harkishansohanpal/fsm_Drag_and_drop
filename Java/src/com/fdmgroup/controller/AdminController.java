package com.fdmgroup.controller;

import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.State;

import com.fdmgroup.controller.ExecuteStateController;

public class AdminController {
	
	public void run(String s){
		
		//*************
		FSMtoCodeController fsm2c = new FSMtoCodeController();
		
		FSM fsm = fsm2c.parseJSON(s);
		
		System.out.println(fsm);
		
		//*************
		
		ExecuteStateController esc = new ExecuteStateController();
		
		//***************************
		
		esc.execute(fsm.getCurrState());
		
		State fromState = fsm.getInitialState();
		
		//***************************
		
		Event obstacle = new Event();
		
		Event light = new Event();
		light.setInput(Input.Light);
		
		while(!obstacle.equals(light)){ //Change will be to continue until halt state reached
						// Or until an error has occurred
			System.out.println("CHECKIN INPUT - START");
			
			if(esc.myFinch.isLeftLightSensor(93) || esc.myFinch.isRightLightSensor(93)){
				System.out.println(esc.myFinch.getLeftLightSensor());
				obstacle.setInput(Input.Light);
			}
			
			else if(esc.myFinch.isObstacle()){
				obstacle.setInput(Input.ObstacleAll);
			}
			
			else if(esc.myFinch.isObstacleLeftSide()){
				obstacle.setInput(Input.ObstacleL);
			}
			
			else if(esc.myFinch.isObstacleRightSide()){
				obstacle.setInput(Input.ObstacleR);
			}
			
			else{
				obstacle.setInput(Input.NoObstacle);
			}
			
			//esc.myFinch.
			
			System.out.println("CHECKIN INPUT - END");
			
			System.out.println(obstacle);
			
			State toState = fsm.step(obstacle, fromState);

			if(toState == null){
				System.out.println("No toState found for " + obstacle + " and " + fromState);
				esc.myFinch.setLED(255, 0, 0);
				break;
			}
			
			esc.execute(toState);
			fromState = toState;
			
		}
	}
	
}
