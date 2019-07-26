package com.fdmgroup.main;

import java.util.List;

import com.fdmgroup.controller.SaveController;
import com.fdmgroup.dao.FSMDAO;
import com.fdmgroup.model.FSM;

public class MainApp {

	public static void main(String args[]){
		
		String s = "{'vertices':[{'name':'A','behaviors':['forward','turnL']},{'name':'B','behaviors':['backward','spinR','forward']},{'name':'C','behaviors':['turnL','forward','forward']},{'name':'D','behaviors':['spinL']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'B'},{'event':{'name':'Blc','input':'ObstacleLC'},'fromState':'B','toState':'D'},{'event':{'name':'Dlrc','input':'ObstacleAll'},'fromState':'D','toState':'C'},{'event':{'name':'Cr','input':'ObstacleR'},'fromState':'C','toState':'D'},{'event':{'name':'Arc','input':'ObstacleRC'},'fromState':'A','toState':'C'},{'event':{'name':'Ccr','input':'ObstacleRC'},'fromState':'C','toState':'A'},{'event':{'name':'Ac','input':'ObstacleC'},'fromState':'A','toState':'B'}],'startState':'A','endStates':[]}";
		String s2 = "{'vertices':[{'name':'A','behaviors':['forward']},{'name':'B','behaviors':['backward','turnL']},{'name':'C','behaviors':['backward','turnR']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'}],'startState':'A','endStates':[]}";

		/*AdminController ac = new AdminController();

		ac.run(s2);*/
		
		SaveController saveCon = new SaveController();
		
		//saveCon.FSMtoDB(s2);
		
		FSMDAO dao = new FSMDAO();
		
		List<FSM> list = dao.getAllFSMs();
		
		System.out.println(list);
		
		
	}
}
