package com.fdmgroup.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fdmgroup.model.Behaviour;
import com.fdmgroup.model.Event;
import com.fdmgroup.model.FSM;
import com.fdmgroup.model.Input;
import com.fdmgroup.model.RobotAction;
import com.fdmgroup.model.State;
import com.fdmgroup.model.TruthTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
	
	public RobotAction parseAction(JSONObject s){
		return new RobotAction(Behaviour.valueOf(s.getString("behavior")), s.getInt("time"));
	}
	
	public Input parseInput(String s){
		return Input.valueOf(s);       
	}
	
	public State parseState(JSONObject js){
		List<RobotAction> beh = new ArrayList<RobotAction>();
		JSONArray jsl = js.getJSONArray("robotActions");
		for(int i=0;i<jsl.length();i++){
			beh.add(parseAction(jsl.getJSONObject(i)));
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
	
	public JSONObject inverseParseState(State s){
		List<JSONObject> objs= s.getRobotActions().stream().map((x -> new JSONObject("{behavior:"+ x.getBehaviour().toString() + ",time:" + Integer.toString(x.getTime()) +"}" ))).collect(Collectors.toList());
		
		JSONArray json = new JSONArray(objs);
		JSONObject json2 = new JSONObject();
		json2.put("name", s.getStateName());
		json2.put("robotActions", json);
		return json2;
		
	}
	public JSONObject inverseParseEvent(Event e){
		Map<String, String> jsonMap = new HashMap();
		jsonMap.put("name", e.getEventName());
		jsonMap.put("input", e.getInput().toString());
		return new JSONObject(jsonMap);
		
	}
	// order of vertices might be different
	public String inverseParseJSON(FSM fsm){
		TruthTable tt = fsm.getTruthTable();
		JSONObject result = new JSONObject();
		
		// first, inverse parse the truth table.
		ArrayList<State> states = new ArrayList();
		ArrayList<JSONObject> edges = new ArrayList<JSONObject>();
		for(int i=0;i<tt.getFromState().size();i++){
			State s1 = tt.getFromState().get(i);
			Event e = tt.getEdge().get(i);
			State s2= tt.getToState().get(i);
			JSONObject json = new JSONObject();
			// inverse parse every event
			json.put("event", inverseParseEvent(e));
			json.put("fromState", s1.getStateName());
			json.put("toState", s2.getStateName());
			// make a list of states
			if(getStateFromString(states, s1.getStateName()) == null){
				states.add(s1);
			}
			if(getStateFromString(states, s2.getStateName()) == null){
				states.add(s2);
			}
			edges.add(json);
		}
		result.put("edges", edges );	

		// get the vertices as well

		List<JSONObject> strs= states.stream().map((x -> inverseParseState(x) )).collect(Collectors.toList());
		result.put("vertices", strs);
		
		//set the start and end states.
		result.put("startState", fsm.getInitialState().getStateName());
		result.put("endStates", new JSONArray(fsm.getFinalStates().stream().map((x -> inverseParseState(x) )).collect(Collectors.toList())));
		return result.toString();
	}

}
