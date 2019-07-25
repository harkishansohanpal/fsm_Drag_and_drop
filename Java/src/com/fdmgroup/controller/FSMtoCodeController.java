package com.fdmgroup.controller;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.State;
import com.fdmgroup.model.TruthTable;

import java.util.ArrayList;
import java.util.List;

import org.json.*;

public class FSMtoCodeController {

	public FSM parseJSON(String s){
		JSONObject json = new JSONObject(s);
		JSONArray lst = json.getJSONArray("vertices");
		// part 1 : read JSON for states
		List<State> states = new ArrayList<State>();
		for(int i=0; i<lst.length(); i++){
			states.add(parseState(lst.getJSONObject(i)));
		}
		//part 2 : build the truth table
		JSONArray lst2 = json.getJSONArray("edges");
		TruthTable truthTable = new TruthTable();
		for(int i=0; i<lst2.length(); i++){
			JSONObject thisEvent = lst2.getJSONObject(i);
			Event e = parseEvent(thisEvent.getJSONObject("event"));
			State s1 = getStateFromString(states, thisEvent.getString("fromState"));
			State s2 = getStateFromString(states, thisEvent.getString("toState"));
			truthTable.addEntry(s1, e, s2);
		}		
		// part 3 : make the FSM
		State start  = getStateFromString(states, json.getString("startState"));
		JSONArray endLst = json.getJSONArray("endStates");
		List<State> ends = new ArrayList<State>();
		for(int i=0;i<endLst.length();i++){
			 ends.add(getStateFromString(states, endLst.getString(i)));
		}

		FSM output = new FSM();
		output.setFinalStates(ends);
		output.setTruthTable(truthTable);
		output.setCurrState(start);
		output.setRecentEvent(null);
		output.setInitialState(start);
		return output;
	}
	
	public Behaviour parseBehaviour(String s){
		return Behaviour.valueOf(s);
	}
	
	public Input parseInput(String s){
		return Input.valueOf(s);       
	}
	
	public State parseState(JSONObject js){
		List<Behaviour> beh = new ArrayList<Behaviour>();
		JSONArray jsl = js.getJSONArray("behaviors");
		for(int i=0;i<jsl.length();i++){
			beh.add(parseBehaviour(jsl.getString(i)));
		}
		return new State(js.getString("name"), beh);
	}
	
	public Event parseEvent(JSONObject js){
		return new Event(js.getString("name"), parseInput(js.getString("input")));
	}
	
	public State getStateFromString(List<State> s, String s2){
		for (State st : s){
			if(st.getStateName().equals(s2)){
				return st;
			}
		}
		return null;
	}
	
}
