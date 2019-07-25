package com.fdmgroup.main;

import com.fdmgroup.controller.AdminController;

public class MainApp {
	public static void main(String [] args){
		
		String s = "{'vertices':[{'name':'A','behaviors':['forward','turnL']},{'name':'B','behaviors':['backward','spinR','forward']},{'name':'C','behaviors':['turnL','forward','forward']},{'name':'D','behaviors':['spinL']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'B'},{'event':{'name':'Blc','input':'ObstacleLC'},'fromState':'B','toState':'D'},{'event':{'name':'Dlrc','input':'ObstacleAll'},'fromState':'D','toState':'C'},{'event':{'name':'Cr','input':'ObstacleR'},'fromState':'C','toState':'D'},{'event':{'name':'Arc','input':'ObstacleRC'},'fromState':'A','toState':'C'},{'event':{'name':'Ccr','input':'ObstacleRC'},'fromState':'C','toState':'A'},{'event':{'name':'Ac','input':'ObstacleC'},'fromState':'A','toState':'B'}],'startState':'A','endStates':[]}";
		
		AdminController ac = new AdminController();

		ac.run(s);
		
		
	}
}
