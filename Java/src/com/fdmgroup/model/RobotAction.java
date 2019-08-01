package com.fdmgroup.model;
import com.fdmgroup.model.*;
public class RobotAction {
	private Behaviour behaviour;
	private int time;
	public Behaviour getBehaviour() {
		return behaviour;
	}
	public void getBehaviour(Behaviour behaviour) {
		this.behaviour = behaviour;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public RobotAction(Behaviour beh, int time) {
		super();
		this.behaviour = beh;
		this.time = time;
	}
	@Override
	public String toString() {
		return "RobotAction [behaviour=" + behaviour + ", time=" + time + "]";
	}
	

}
