package com.fdmgroup.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FSMState")
public class State {
	
	@Id
	@SequenceGenerator(name = "FSMStateSeq", sequenceName = "FSM_State_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FSMStateSeq")
	private int stateID;
	private String stateName;
	
	private List<Behaviour> behaviours;
	
	public State() {
		super();
	}
	
	public State(String stateName, List<Behaviour> behaviours) {
		super();
		this.stateName = stateName;
		this.behaviours = behaviours;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public List<Behaviour> getBehaviours() {
		return behaviours;
	}

	public void setBehaviours(List<Behaviour> behaviours) {
		this.behaviours = behaviours;
	}

	@Override
	public String toString() {
		return "State [stateName=" + stateName + ", behaviours=" + behaviours + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((behaviours == null) ? 0 : behaviours.hashCode());
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
		if (behaviours == null) {
			if (other.behaviours != null)
				return false;
		} else if (!behaviours.equals(other.behaviours))
			return false;
		if (stateID != other.stateID)
			return false;
		return true;
	}
	
	
	
}
