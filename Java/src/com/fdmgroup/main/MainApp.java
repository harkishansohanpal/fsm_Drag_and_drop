package com.fdmgroup.main;

<<<<<<< HEAD
=======
import com.fdmgroup.controller.AdminController;

>>>>>>> 97697152844fdbd2b9906ccf4add01224b32abe8
public class MainApp {

	public static void main(String args[]){
		
		String s = "{'vertices':[{'name':'A','behaviors':['forward','turnL']},{'name':'B','behaviors':['backward','spinR','forward']},{'name':'C','behaviors':['turnL','forward','forward']},{'name':'D','behaviors':['spinL']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'B'},{'event':{'name':'Blc','input':'ObstacleLC'},'fromState':'B','toState':'D'},{'event':{'name':'Dlrc','input':'ObstacleAll'},'fromState':'D','toState':'C'},{'event':{'name':'Cr','input':'ObstacleR'},'fromState':'C','toState':'D'},{'event':{'name':'Arc','input':'ObstacleRC'},'fromState':'A','toState':'C'},{'event':{'name':'Ccr','input':'ObstacleRC'},'fromState':'C','toState':'A'},{'event':{'name':'Ac','input':'ObstacleC'},'fromState':'A','toState':'B'}],'startState':'A','endStates':[]}";
		String s2 = "{'vertices':[{'name':'A','behaviors':['forward']},{'name':'B','behaviors':['backward','turnL']},{'name':'C','behaviors':['backward','turnR']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'}],'startState':'A','endStates':[]}";
		String s3 = "{'vertices':[{'name':'A','behaviors':['forward']},{'name':'B','behaviors':['backward','turnL']},{'name':'C','behaviors':['backward','turnR']},{'name':'D','behaviors':['stop']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'},{'event':{'name':'As','input':'Light'},'fromState':'A','toState':'D'},{'event':{'name':'Bs','input':'Light'},'fromState':'B','toState':'D'},{'event':{'name':'Cs','input':'Light'},'fromState':'C','toState':'D'}],'startState':'A','endStates':['D']}";

<<<<<<< HEAD
		/*AdminController ac = new AdminController();

		ac.run(s2);*/
=======
		String s4 = "{'vertices':[{'name':'A','behaviors':['forward']},{'name':'B','behaviors':['backward','turnL']},{'name':'C','behaviors':['backward','turnR']},{'name':'D','behaviors':['stop']},{'name':'E','behaviors':['backward','spinL']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'},{'event':{'name':'As','input':'Light'},'fromState':'A','toState':'D'},{'event':{'name':'Bs','input':'Light'},'fromState':'B','toState':'D'},{'event':{'name':'Cs','input':'Light'},'fromState':'C','toState':'D'},{'event':{'name':'Aa','input':'ObstacleAll'},'fromState':'A','toState':'E'},{'event':{'name':'Ba','input':'ObstacleAll'},'fromState':'B','toState':'E'},{'event':{'name':'Ca','input':'ObstacleAll'},'fromState':'C','toState':'E'},{'event':{'name':'En','input':'NoObstacle'},'fromState':'E','toState':'A'},{'event':{'name':'Ea','input':'ObstacleAll'},'fromState':'E','toState':'E'},{'event':{'name':'El','input':'Light'},'fromState':'E','toState':'D'}],'startState':'A','endStates':['D']}";
		
		AdminController ac = new AdminController();

		ac.run(s4);
		
>>>>>>> 97697152844fdbd2b9906ccf4add01224b32abe8
		
		
	}
}
