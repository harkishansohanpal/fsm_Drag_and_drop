package com.fdmgroup.model;

import java.util.List;

public class State {

	private int stateID;
	private String stateName;
	private List<RobotAction> behaviours;

	public State() {
		super();
	}
	
	public State(String stateName, List<RobotAction> behaviours) {
		super();
		this.stateName = stateName;
		this.behaviours = behaviours;
	}

	public int getStateID() {
		return stateID;
	}

	public void setStateID(int stateID) {
		this.stateID = stateID;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<RobotAction> getBehaviours() {
		return behaviours;
	}

	public void setBehaviours(List<RobotAction> behaviours) {
		this.behaviours = behaviours;
	}
	
	@Override
	public String toString() {
		return "State [stateID=" + stateID + ", stateName=" + stateName + ", behaviours=" + behaviours + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (behaviours == null) {
			if (other.behaviours != null)
				return false;
		} else if (!behaviours.equals(other.behaviours))
			return false;
		return true;
	}
	
	
	
	
	
}
