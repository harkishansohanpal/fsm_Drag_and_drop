package com.fdmgroup.model;

import java.util.List;

public class FSM {

	private int FSMID;
	private TruthTable truthTable;
	private State currState;
	private Event recentEvent;
	private State initialState;
	private List<State> finalStates;

	public FSM() {
		super();
	}

	public FSM(TruthTable truthTable, State currState, Event recentEvent, State initialState, List<State> finalStates) {
		super();
		this.truthTable = truthTable;
		this.currState = currState;
		this.recentEvent = recentEvent;
		this.initialState = initialState;
		this.finalStates = finalStates;
	}

	public TruthTable getTruthTable() {
		return truthTable;
	}

	public void setTruthTable(TruthTable truthTable) {
		this.truthTable = truthTable;
	}

	public State getCurrState() {
		return currState;
	}

	public void setCurrState(State currState) {
		this.currState = currState;
	}

	public Event getRecentEvent() {
		return recentEvent;
	}

	public void setRecentEvent(Event recentEvent) {
		this.recentEvent = recentEvent;
	}

	public State getInitialState() {
		return initialState;
	}

	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}

	public List<State> getFinalStates() {
		return finalStates;
	}

	public void setFinalStates(List<State> finalStates) {
		this.finalStates = finalStates;
	}

	public int getFSMID() {
		return FSMID;
	}

	public void setFSMID(int fSMID) {
		FSMID = fSMID;
	}
	
	/**
	 * The initial method to start the FSM
	 * @param inputEvent The first event given
	 */
	public State start(Event inputEvent){
		
		for (int i = 0; i < truthTable.getEdge().size(); i++ ) {
			
			if(truthTable.getEdge().get(i).equals(inputEvent)){
				return truthTable.getToState().get(i);
			}
		}
		return null;
	}
	
	/**
	 * The next event for the FDM to execute
	 * @param inputEvent The next event given
	 * @return The state that is to be executed
	 */
	public State step(Event inputEvent, State fromState){
		
		//Check for halt state
		for (State state : finalStates) {
			if(fromState.equals(state))
				return new State("End" , null);
		}
		
		
		for(int i = 0; i < truthTable.getFromState().size(); i++){
			
			if(truthTable.getFromState().get(i).equals(fromState) && truthTable.getEdge().get(i).equals(inputEvent)){
				return truthTable.getToState().get(i);
			}
		}

		return null;
	}

	@Override
	public String toString() {
		return "FSM [truthTable=" + truthTable + ", currentState=" + currState + ", recentEvent=" + recentEvent
				+ ", initialState=" + initialState + ", finalState=" + finalStates + "]";
	}
	
	
	
	
}
