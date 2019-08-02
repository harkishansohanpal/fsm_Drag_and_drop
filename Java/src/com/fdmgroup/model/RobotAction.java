package com.fdmgroup.model;

public class RobotAction {

	private Behaviour behaviour;
	private int time;
	
	public RobotAction() {
		super();
	}

	public RobotAction(Behaviour behaviour, int time) {
		super();
		this.behaviour = behaviour;
		this.time = time;
	}

	public Behaviour getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "RobotAction [behaviour=" + behaviour + ", time=" + time + "]";
	}
	
	
	
}
