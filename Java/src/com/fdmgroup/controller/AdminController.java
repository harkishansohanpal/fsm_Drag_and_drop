package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
		
		//*************
		
		ExecuteStateController esc = new ExecuteStateController();
		
		//*************************************** USER INPUT ********************************************************
		Scanner scanner = new Scanner(System.in);
		
		Event input0 = new Event();
		input0.setInput(Input.NoObstacle);
		
		Event input1 = new Event();
		input1.setInput(Input.ObstacleLC);
		
		Event input2 = new Event();
		input2.setInput(Input.ObstacleAll);
		
		Event input3 = new Event();
		input3.setInput(Input.ObstacleR);
		
		Event input4 = new Event();
		input4.setInput(Input.ObstacleRC);
		
		Event input5 = new Event();
		input5.setInput(Input.ObstacleC);
		
		List<Event> edges = new ArrayList<>();
		edges.add(input0);
		edges.add(input1);
		edges.add(input2);
		edges.add(input3);
		edges.add(input4);
		edges.add(input5);
		
		//***************************
		
		esc.execute(fsm.getInitialState());
		
		State fromState = fsm.getInitialState();
		
		/*State fromState =  fsm.start(fsm.getTruthTable().getEdge().get(0));
		esc.execute(fromState);*/
		
		//***************************
		
		while(true){
			
			Event inputEvent = new Event();
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
				
				
			}
			
			//*****************************************************
			
			State toState = fsm.step(inputEvent, fromState);

			fromState = toState;
			esc.execute(toState);
		}
	}
	
}
