package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.FSM;

public class FSMDAO {
	
	@Autowired
	private DBConnection con;
	
	List<FSM> FSMs;
	
	public DBConnection getCon() {
		return con;
	}

	public void setCon(DBConnection con) {
		this.con = con;
	}

	
	//CRUDS
	public void addFSM(FSM fsm){
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
		em.persist(fsm.getTruthTable());
		/*em.persist(fsm.getTruthTable().getToState());
		em.persist(fsm.getTruthTable().getEdge());*/
		
		em.persist(fsm);
		em.getTransaction().commit();
		
	}
	
	public List<FSM> getAllFSMs(){
		EntityManager em = con.getEntityManager();
		
		TypedQuery<FSM> query = em.createNamedQuery("FSM.getAllFSMs", FSM.class);
		
		try{
			FSMs = query.getResultList();

			em.close();
			
			return FSMs;
			
		}catch(NoResultException e){
			return null;
		}
		
	}
	
	public FSM getFSMByID(int id){
		EntityManager em = con.getEntityManager();
		
		Query query = em.createNamedQuery("FSM.getFSMByID");
		query.setParameter(1, id);
		
		try{
			FSM fsm = (FSM) query.getSingleResult();
			
			em.close();
			
			return fsm;
			
		}catch(NoResultException e){
			return null;
		}
		
	}
	
	
	
}
