package com.fdmgroup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.dao.JSONFsmDAO;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes({"UserFSM"})
public class SaveController {

	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	
	@RequestMapping(value="/Save", method = RequestMethod.POST)
	@CrossOrigin
	public void FSMtoDB(Model model, @RequestBody String s, HttpServletRequest request){
		System.out.println(s);
		JSONFsm jsonFsmObj = new JSONFsm();
		
		JSONObject json = new JSONObject(s);
		JSONObject fsm = new JSONObject(json.getString("fsm"));
		String fsmString = fsm.toString();
		
		String fsmReplace = fsmString.replace("\"", "'");
		String fsmReplace2 = fsmReplace.replace("Turn Right", "TurnR");
		String fsmReplace3 = fsmReplace2.replace("Turn Left", "TurnL");
		
		System.out.println(fsmReplace3);
		
		jsonFsmObj.setJsonFsm(fsmReplace3);
		
		jsonFsmObj.setUser((User) request.getSession().getAttribute("UserFSM"));
		jfsmDAOObj.addFSM(jsonFsmObj);
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		model.addAttribute("FSMs", Fsms);
		
		//return "Admin";
		
	}
	
	@RequestMapping(value = "/Save2", method = RequestMethod.POST)
	@CrossOrigin
	public String FSMtoDB2(Model model, @RequestBody String s){
		//System.out.println(s);
		JSONObject json = new JSONObject(s);
		JSONObject fsm = new JSONObject(json.getString("fsm"));
		System.out.println(fsm.toString());
		return "Admin";
	}
	
	@RequestMapping(value = "Save2", method = RequestMethod.GET)
	@CrossOrigin
	public String FSMtoDB3(Model model, @RequestBody String s){
		System.out.println(s);
		
		return "Admin";
	}
	
}
