package com.fdmgroup.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.LoadModel;

public class LoadModelDAO {

	@Autowired
	private DBConnection con;

	public DBConnection getCon() {
		return con;
	}

	public void setCon(DBConnection con) {
		this.con = con;
	}
	
	public void addModel(LoadModel model){
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
			em.persist(model);
		em.getTransaction().commit();
		
		
	}
	
	
	
}
