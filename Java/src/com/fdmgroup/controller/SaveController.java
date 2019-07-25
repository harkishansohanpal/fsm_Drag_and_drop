package com.fdmgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.dao.FSMDAO;
import com.fdmgroup.model.FSM;

public class SaveController {

	@Autowired
	private FSMDAO dao;
	
	public void FSMtoDB(String s){
		
		//Convert JSON to FSM
		FSMtoCodeController fsm2c = new FSMtoCodeController();
		FSM fsm = fsm2c.parseJSON(s);
		
		dao.addFSM(fsm);
		
	}
	
}
