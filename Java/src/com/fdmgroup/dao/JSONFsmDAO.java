package com.fdmgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.JSONFsm;
import com.fdmgroup.model.User;

public class JSONFsmDAO {

	@Autowired
	private DBConnection con;
	
	@Autowired
	private UserDAO userDao;
	
	public DBConnection getCon() {
		return con;
	}

	public void setCon(DBConnection con) {
		this.con = con;
	}
	
	public void addSamples(){
		System.out.println("adding samples");
		User u1 = new User();
		u1.setUsername("DragonSlayer");
		u1.setPassword("1234");
		u1.setUserType("Admin");
		
		User u2 = new User();
		u2.setUsername("User1");
		u2.setPassword("1234");
		u2.setUserType("User");
		
		User u3 = new User();
		u3.setUsername("User2");
		u3.setPassword("1234");
		u3.setUserType("User");
		
		userDao.addUser(u1);
		userDao.addUser(u2);
		userDao.addUser(u3);
		
		/*JSONFsm jsonFsmObj = new JSONFsm();
		
		jsonFsmObj.setJsonFsm("{'vertices':[{'name':'A','behaviours':['forward']},{'name':'B','behaviours':['backward','turnL']},{'name':'C','behaviours':['backward','turnR']},{'name':'D','behaviours':['stop']},{'name':'E','behaviours':['backward','spin']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'},{'event':{'name':'As','input':'Light'},'fromState':'A','toState':'D'},{'event':{'name':'Bs','input':'Light'},'fromState':'B','toState':'D'},{'event':{'name':'Cs','input':'Light'},'fromState':'C','toState':'D'},{'event':{'name':'Aa','input':'ObstacleAll'},'fromState':'A','toState':'E'},{'event':{'name':'Ba','input':'ObstacleAll'},'fromState':'B','toState':'E'},{'event':{'name':'Ca','input':'ObstacleAll'},'fromState':'C','toState':'E'},{'event':{'name':'En','input':'NoObstacle'},'fromState':'E','toState':'A'}],'startState':'A','endStates':['D']}");
		jsonFsmObj.setUser(u1);
		addFSM(jsonFsmObj);
		
		JSONFsm jsonFsmObj2 = new JSONFsm();
		
		jsonFsmObj2.setJsonFsm("{'vertices':[{'name':'A','behaviours':['forward']},{'name':'B','behaviours':['backward','turnL']},{'name':'C','behaviours':['backward','turnR']}],'edges':[{'event':{'name':'An','input':'NoObstacle'},'fromState':'A','toState':'A'},{'event':{'name':'Ar','input':'ObstacleR'},'fromState':'A','toState':'B'},{'event':{'name':'Bn','input':'NoObstacle'},'fromState':'B','toState':'A'},{'event':{'name':'Al','input':'ObstacleL'},'fromState':'A','toState':'C'},{'event':{'name':'Cn','input':'NoObstacle'},'fromState':'C','toState':'A'},{'event':{'name':'Br','input':'ObstacleR'},'fromState':'B','toState':'B'},{'event':{'name':'Cl','input':'ObstacleL'},'fromState':'C','toState':'C'}],'startState':'A','endStates':[]}");
		jsonFsmObj2.setUser(u2);
		addFSM(jsonFsmObj2);*/
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
