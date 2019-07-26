package com.fdmgroup.model;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FSMTable")
@NamedQueries({
	@NamedQuery(name="FSM.getAllFSMs", query="SELECT f FROM FSM f"),
	@NamedQuery(name="FSM.getFSMByID", query="SELECT f FROM FSM f WHERE f.FSMID = ?1")
})
public class FSM {

	@Id
	@SequenceGenerator(name = "FSMSeq", sequenceName = "FSM_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FSMSeq")
	private int FSMID;
	
	@Resource
	@OneToOne(cascade = CascadeType.ALL)
	private TruthTable truthTable;
	
	@OneToOne(cascade = CascadeType.ALL)
	private State currState;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Event recentEvent;
	
	@OneToOne(cascade = CascadeType.ALL)
	private State initialState;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<State> finalStates;
	
	@ManyToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
				return state;
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
