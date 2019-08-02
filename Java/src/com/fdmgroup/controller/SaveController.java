package com.fdmgroup.controller;

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
import com.fdmgroup.dao.LoadModelDAO;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.LoadModel;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes({"UserFSM"})
public class SaveController {

	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	
	@Autowired
	private LoadModelDAO modelDao;
	
	@RequestMapping(value="/Save", method = RequestMethod.POST)
	@CrossOrigin
	public void FSMtoDB(Model model, @RequestBody String s, HttpServletRequest request){
		//System.out.println("load:" + load);
		System.out.println("s:" + s);
		
		JSONFsm jsonFsmObj = new JSONFsm();
		
		JSONObject json = new JSONObject(s);
		JSONObject fsm = new JSONObject(json.getString("fsm"));
		System.out.println(json.getString("diagram"));
		JSONObject diagram =  new JSONObject(json.getString("diagram"));
		String fsmName =  json.getString("filename");
		System.out.println(fsmName);
	
		
		
		String fsmString = fsm.toString();
		String diagramString = diagram.toString();
		
		//Parse the string to prepare for JSON to FSM algorithm
		String fsmReplace = fsmString.replace("\"", "'");
		String fsmReplace2 = fsmReplace.replace("Turn Right", "TurnR");
		String fsmReplace3 = fsmReplace2.replace("Turn Left", "TurnL");
		
		//Create JSONFsm object
		jsonFsmObj.setJsonFsm(fsmReplace3);
		//jsonFsmObj.setLoad(load);
		jsonFsmObj.setUser((User) request.getSession().getAttribute("UserFSM"));
		jsonFsmObj.setFsmName(fsmName);
		
		LoadModel loadModel = new LoadModel();
		loadModel.setModel(diagramString);
		
		jsonFsmObj.setLoadModel(loadModel);
		
		modelDao.addModel(loadModel);
		jfsmDAOObj.addFSM(jsonFsmObj);
		
		
	}
	
}
