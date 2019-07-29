package com.fdmgroup.model;

public class Event {
	
	private int EventID;
	private String eventName;
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

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
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
		if (input != other.input)
			return false;
		return true;
	}

	
	
	
	
}
