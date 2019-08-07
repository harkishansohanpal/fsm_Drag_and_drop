package com.fdmgroup.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.dao.JSONFsmDAO;
import com.fdmgroup.dao.UserDAO;
import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.State;
import com.fdmgroup.model.User;

@Controller
public class AdminController {

	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	
	@Autowired
	private UserDAO userDao;
	
	private int kill = 1;
	
	@RequestMapping(value = "/Kill")
	public void kill(Model model){
		kill = 0;
	}
	
	@RequestMapping(value = "/Run", method = RequestMethod.POST)
	public void run(Model model, @RequestBody String s){
		kill = 1;
		System.out.println(s);

		JSONObject json = new JSONObject(s);
		JSONObject fsmJson = new JSONObject(json.getString("fsm"));
		
		s = fsmJson.toString();
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
		while(!obstacle.equals(light) && kill == 1){
			
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
			System.out.println("step"+toState);
			if(toState == null){
				System.out.println("No toState found for " + obstacle + " and " + fromState);
				esc.myFinch.setLED(255, 0, 0);
				break;
			}
			
			if(toState.getBehaviours() == null){
				System.out.println("Final state reached");
				esc.myFinch.setLED(255, 0, 0);
				break;
			}
			
			esc.execute(toState);
			fromState = toState;
			
		}
		
	}
	
	@RequestMapping(value = "/Delete", method = RequestMethod.POST)
	public String deleteFSM(Model model, @RequestParam("fsm") int id){
		
		jfsmDAOObj.delete(id);
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		
		return "Admin";
	}
	
	@RequestMapping(value = "/NewUser")
	public String newUser(Model model){
		return "addUser";
	}
	
	@RequestMapping(value = "/Cancel")
	public String cancel(Model model){
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		
		return "Admin";
	}
	
	@RequestMapping(value = "/AddUser")
	public String addUser(Model model, @RequestParam("user") String user, @RequestParam("name") String name,
			@RequestParam("pass") String pass, @RequestParam("type") String type){
		System.out.println(user);
		User u = new User();
		
		u.setUsername(user);
		u.setName(name);
		u.setPassword(pass);
		u.setUserType(type);
		
		userDao.addUser(u);
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		System.out.println("Test");
		return "Admin";
		
	}
	
}
