package com.fdmgroup.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.dao.FSMDAO;
import com.fdmgroup.dao.JSONFsmDAO;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes({"UserFSM"})
public class SaveController {

	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	//private FSMDAO dao;
	
	@RequestMapping(value="/Save")
	public String FSMtoDB(Model model, @RequestParam("fsm") String s, HttpServletRequest request){
		
		//Convert JSON to FSM
		FSMtoCodeController fsm2c = new FSMtoCodeController();
		//FSM fsm = fsm2c.parseJSON(s);
		
		//System.out.println(fsm);
		
		JSONFsm jsonFsmObj = new JSONFsm();
		
		jsonFsmObj.setJsonFsm(s);
		jsonFsmObj.setUser((User) request.getSession().getAttribute("UserFSM"));
		jfsmDAOObj.addFSM(jsonFsmObj);
		
		//dao.addFSM(fsm);
		
		List<JSONFsm> Fsms =  jfsmDAOObj.getList();
		System.out.println(Fsms);
		model.addAttribute("FSMs", Fsms);
		
		return "ShowFSMs";
		
	}
	
}
