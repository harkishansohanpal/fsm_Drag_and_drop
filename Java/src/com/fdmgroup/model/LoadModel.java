package com.fdmgroup.model;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="FSM_LoadModel")
@NamedQueries({
	@NamedQuery(name = "L.findByID", query = "SELECT l FROM LoadModel l WHERE l.id = ?1")
})
public class LoadModel {

	@Id
	@SequenceGenerator(name = "LoadModelSeq", sequenceName = "LoadModel_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LoadModelSeq")
	private int id;
	
	@Resource
	@Column(columnDefinition = "LONG")
	private String model;

	public LoadModel() {
		super();
	}

	public LoadModel(String model) {
		super();
		this.model = model;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String toString() {
		return "LoadModel [id=" + id + ", model=" + model + "]";
	}
	
	
	
	
	
}
