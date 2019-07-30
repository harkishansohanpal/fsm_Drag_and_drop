package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.JSONFsm;

public class JSONFsmDAO {

	@Autowired
	private DBConnection con;
	
	public DBConnection getCon() {
		return con;
	}

	public void setCon(DBConnection con) {
		this.con = con;
	}
	
	
	//CRUD
	public void addFSM(JSONFsm jFsm){
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(jFsm);
		em.getTransaction().commit();
		
	}
	
	public List<JSONFsm> getList(){
		EntityManager em = con.getEntityManager();
		
		TypedQuery<JSONFsm> query = em.createNamedQuery("j.getList", JSONFsm.class);
		
		List<JSONFsm> list = query.getResultList();
		
		em.close();
		
		return list;
		
	}
	
	public JSONFsm findById(int id){
		EntityManager em = con.getEntityManager();
		
		Query q = em.createNamedQuery("j.getId");
		q.setParameter(1, id);
		
		JSONFsm fsm = (JSONFsm) q.getSingleResult();
		
		return fsm;
		
	}
	
	public void delete(int id){
		EntityManager em = con.getEntityManager();
		
		JSONFsm fsm = findById(id);
		
		em.getTransaction().begin();
		
		JSONFsm managed = em.merge(fsm);
		em.remove(managed);
		
		em.getTransaction().commit();
		
		em.close();
		
	}
}
