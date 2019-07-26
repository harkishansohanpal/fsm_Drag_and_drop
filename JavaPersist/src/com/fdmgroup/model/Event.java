package com.fdmgroup.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	
	@Enumerated(EnumType.STRING)
	private Input input;
	
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(String eventName, Input input) {
		super();
		this.eventName = eventName;
		this.input = input;
	}

	public int getEventID() {
		return EventID;
	}

	public void setEventID(int eventID) {
		EventID = eventID;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "Event [EventID=" + EventID + ", eventName=" + eventName + ", input=" + input + "]";
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
		if (eventName == null) {
			if (other.eventName != null)
				return false;
		} else if (!eventName.equals(other.eventName))
			return false;
		if (input != other.input)
			return false;
		return true;
	}

	
	
	
	
}
