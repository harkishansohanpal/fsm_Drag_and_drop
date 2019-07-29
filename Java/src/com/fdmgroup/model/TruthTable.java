package com.fdmgroup.model;

import java.util.ArrayList;
import java.util.List;

public class TruthTable {

	private int ttID;
	private List<State> fromState;
	private List<State> toState;
	private List<Event> edge;
	
	public TruthTable() {
		super();
		fromState = new ArrayList<State>();
		toState = new ArrayList<State>();
		edge = new ArrayList<Event>();
	}
	public int getTtID() {
		return ttID;
	}
	public void setTtID(int ttID) {
		this.ttID = ttID;
	}
	public List<State> getFromState() {
		return fromState;
	}
	public void setFromState(List<State> fromState) {
		this.fromState = fromState;
	}
	public List<State> getToState() {
		return toState;
	}
	public void setToState(List<State> toState) {
		this.toState = toState;
	}
	public List<Event> getEdge() {
		return edge;
	}
	public void setEdge(List<Event> edge) {
		this.edge = edge;
	}
	
	/**
	 * This method adds a row to the truth table
	 * @param fromState The initial state we start at
	 * @param edge The event that is checked
	 * @param toState The resulting state
	 */
	public void addEntry(State fromState, Event edge, State toState){
		this.fromState.add(fromState);
		this.toState.add(toState);
		this.edge.add(edge);
	}
	
	/**
	 * Get a specific row from the truth table
	 * @param rowID The specific row that is to be retrieved
	 * @return The row from the truth table
	 */
	public TruthTable getRow(int rowID){
		return null;
	}
	
	/**
	 * Removes a specific row from the truth table
	 * @param rowID The row ID that is associated with the truth table
	 */
	public void removeEntry(int rowID){
		
	}

	@Override
	public String toString() {
		return "TruthTable [fromState=" + fromState + ", toState=" + toState + ", edge=" + edge + "]";
	}
	
	
	
}
