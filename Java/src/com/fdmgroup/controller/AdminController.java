package com.fdmgroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.dao.JSONFsmDAO;
import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.State;

@Controller
public class AdminController {

	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	
	@RequestMapping(value = "/Run", method = RequestMethod.POST)
	public String run(Model model, @RequestParam("fsm") String s){
		System.out.println(s);
		//Parse JSON string to fsm object
		FSMtoCodeController fsm2c = new FSMtoCodeController();
		FSM fsm = fsm2c.parseJSON(s);
		
		System.out.println(fsm);
		
		ExecuteStateController esc = new ExecuteStateController();
		
		esc.execute(fsm.getCurrState());
		
		State fromState = fsm.getInitialState();
		Event obstacle = new Event();
		Event light = new Event();
		
		light.setInput(Input.Light);
		
		
		//Continous while loop to execute fsm unless light is sensed
		while(!obstacle.equals(light)){
			
			System.out.println(esc.myFinch.getLeftLightSensor());
			
			/*if(esc.myFinch.isLeftLightSensor(93) || esc.myFinch.isRightLightSensor(93)){
				System.out.println(esc.myFinch.getLeftLightSensor());
				//obstacle.setInput(Input.light);
			}*/
			
			if(esc.myFinch.isObstacle()){
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
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		
		return "Admin";
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deleteFSM(Model model, @RequestParam("fsm") int id){
		
		jfsmDAOObj.delete(id);
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		
		return "Admin";
	}
	
}
