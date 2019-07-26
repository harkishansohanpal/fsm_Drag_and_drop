package com.fdmgroup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FSMEvent")
public class Event {
	
	@Id
	@SequenceGenerator(name = "FSMEventSeq", sequenceName = "FSM_Event_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FSMEventSeq")
	private int EventID;
	private String eventName;
	
	private Input input;
	
	public Event() {
		super();
	}

	public Event(String eventName, Input input) {
		super();
		this.eventName = eventName;
		this.input = input;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "Event [eventName=" + eventName + ", input=" + input + "]\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + EventID;
		result = prime * result + ((eventName == null) ? 0 : eventName.hashCode());
		result = prime * result + ((input == null) ? 0 : input.hashCode());
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
		Event other = (Event) obj;
		if (EventID != other.EventID)
			return false;
		if (input != other.input)
			return false;
		return true;
	}
	
	
	
}
