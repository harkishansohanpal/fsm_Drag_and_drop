package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.State;

import com.fdmgroup.controller.ExecuteStateController;
import com.fdmgroup.dao.FSMDAO;

public class AdminController {
	
	public void run(String s){
		
		//*************
		FSMtoCodeController fsm2c = new FSMtoCodeController();
		
		FSM fsm = fsm2c.parseJSON(s);
		
		System.out.println(fsm);
		
		/*FSMDAO dao = new FSMDAO();
		
		dao.addFSM(fsm);*/
		
		//*************
		
		ExecuteStateController esc = new ExecuteStateController();
		
		//*************************************** USER INPUT ********************************************************
		//Scanner scanner = new Scanner(System.in);
		
		Event input0 = new Event();
		input0.setInput(Input.NoObstacle);
		
		Event input1 = new Event();
		input1.setInput(Input.ObstacleAll);
		
		Event input2 = new Event();
		input2.setInput(Input.ObstacleL);
		
		Event input3 = new Event();
		input3.setInput(Input.ObstacleR);
		
		List<Event> edges = new ArrayList<>();
		edges.add(input0);
		edges.add(input1);
		edges.add(input2);
		edges.add(input3);

		
		//***************************
		
		esc.execute(fsm.getCurrState());
		
		State fromState = fsm.getInitialState();
		
		//***************************
		
		Event obstacle = new Event();
		
		while(true){ //Change will be to continue until halt state reached
						// Or until an error has occurred
			System.out.println("CHECKIN INPUT - START");
			
			if(esc.myFinch.isObstacle()){
				obstacle.setInput(Input.ObstacleAll);
			}
			
			if(!esc.myFinch.isObstacle()){
				obstacle.setInput(Input.NoObstacle);
			}
			
			if(esc.myFinch.isObstacleLeftSide()){
				obstacle.setInput(Input.ObstacleL);
			}
			
			if(esc.myFinch.isObstacleRightSide()){
				obstacle.setInput(Input.ObstacleR);
			}
			
			if(esc.myFinch.isObstacleLeftSide() || esc.myFinch.isObstacleRightSide() || esc.myFinch.isObstacle()){
				System.out.println("INPUT");
			}
			
			if(!esc.myFinch.isObstacle()) {
				System.out.println("NO INPUT");
			}
			
			/*Event inputEvent = new Event();
			System.out.println("Please select Input");
			for (int i = 0; i < edges.size(); i++) {
				System.out.println(i + " " + edges.get(i).getInput());
			}
			
			int input = scanner.nextInt();
			switch (input){
			case 0: 
				inputEvent = input0;
				break;
				
			case 1: 
				inputEvent = input1;
				break;
				
			case 2: 
				inputEvent = input2;
				break;	
			
			case 3: 
				inputEvent = input3;
				break;
				
			case 4: 
				inputEvent = input4;
				break;
				
			case 5: 
				inputEvent = input5;
				break;	
			}*/
			
			//*****************************************************
			
			System.out.println("CHECKIN INPUT - END");
			
			System.out.println(obstacle);
			
			State toState = fsm.step(obstacle, fromState);

			if(toState == null){
				System.out.println("No toState found for " + obstacle + " and " + fromState);
				break;
			}
			
			esc.execute(toState);
			fromState = toState;
			
		}
	}
	
}
