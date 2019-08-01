package com.fdmgroup.model;

import java.util.List;

public class State {

	private int stateID;
	private String stateName;
	private List<RobotAction> robotActions;

	public State() {
		super();
	}

	public State( String stateName, List<RobotAction> robotActions) {
		super();
		this.stateName = stateName;
		this.robotActions = robotActions;
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

	public List<RobotAction> getRobotActions() {
		return robotActions;
	}

	public void setRobotActions(List<RobotAction> robotActions) {
		this.robotActions = robotActions;
	}

	@Override
	public String toString() {
		return "\nState [stateID=" + stateID + ", stateName=" + stateName + ", robotActions=" + robotActions + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((robotActions == null) ? 0 : robotActions.hashCode());
		result = prime * result + stateID;
		result = prime * result + ((stateName == null) ? 0 : stateName.hashCode());
		return result;
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
		if (robotActions == null) {
			if (other.robotActions != null)
				return false;
		} else if (!robotActions.equals(other.robotActions))
			return false;
		return true;
	}
	
	
	
	
	
}
