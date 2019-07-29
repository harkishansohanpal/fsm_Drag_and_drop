package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fdmgroup.dao.JSONFsmDAO;
import com.fdmgroup.dao.UserDAO;
import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.User;

@Controller
@SessionAttributes({"UserFSM"})
public class LoginController {
	
	@Autowired
	private UserDAO userDaoObj;
	
	@Autowired
	private JSONFsmDAO jfsmDAOObj;
	
	//display login page
	@RequestMapping(value = "/")
	public String showLogin(){			
		return "login";
	}
	
	@RequestMapping(value="/processLogin" , method=RequestMethod.POST)
	public String processLogin(@RequestParam("username") String userName, @RequestParam("password") String userPassword, Model model){
		
		/*User u1 = new User();
		u1.setUsername("1234");
		u1.setPassword("1234");
		
		userDaoObj.addUser(u1);*/
		
		List<User> userList = new ArrayList<>();
		userList = userDaoObj.findAllUsers();
		
		boolean isFound = false;
		int foundId = 0;
		User foundUser = null;
		
		for(int i = 0; i<userList.size(); i++){
			if(userList.get(i).getUsername().equals(userName) &&
					userList.get(i).getPassword().equals(userPassword)){
				
				isFound = true;
				foundId = userList.get(i).getUserID();					
			}
		}
		
		
		if(isFound == true){
			foundUser = userDaoObj.findUserById(foundId);			
			
			model.addAttribute("UserFSM", foundUser);
			
			List<JSONFsm> Fsms =  jfsmDAOObj.getList();
			model.addAttribute("FSMs", Fsms);
			
			return "ShowFSMs";
		}
		
		return "login";
		
	}

}
