package com.fdmgroup.model;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="FSMJson")
@NamedQueries({
	@NamedQuery(name = "j.getList", query = "SELECT j FROM JSONFsm j"),
	@NamedQuery(name = "j.getId", query = "SELECT j FROM JSONFsm j WHERE j.id = ?1")
})
public class JSONFsm {

	@Id
	@SequenceGenerator(name = "FSMJsonSeq", sequenceName = "FSM_Josn_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FSMJsonSeq")
	private int id;
	
	@Resource
	private String FsmName;
	
	@Resource
	@Column(columnDefinition = "LONG")
	private String jsonFsm;
	
	@ManyToOne
	private User user;
	
	@OneToOne
	private LoadModel loadModel;
	
	public JSONFsm() {
		super();
		
	}

	public JSONFsm(int id, String jsonFsm) {
		super();
		this.id = id;
		this.jsonFsm = jsonFsm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJsonFsm() {
		return jsonFsm;
	}

	public void setJsonFsm(String jsonFsm) {
		this.jsonFsm = jsonFsm;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getFsmName() {
		return FsmName;
	}

	public void setFsmName(String fsmName) {
		FsmName = fsmName;
	}

	public LoadModel getLoadModel() {
		return loadModel;
	}

	public void setLoadModel(LoadModel loadModel) {
		this.loadModel = loadModel;
	}

	@Override
	public String toString() {
		return "JSONFsm [id=" + id + ", FsmName=" + FsmName + ", jsonFsm=" + jsonFsm + ", user="
				+ user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((jsonFsm == null) ? 0 : jsonFsm.hashCode());
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
		JSONFsm other = (JSONFsm) obj;
		if (id != other.id)
			return false;
		if (jsonFsm == null) {
			if (other.jsonFsm != null)
				return false;
		} else if (!jsonFsm.equals(other.jsonFsm))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
