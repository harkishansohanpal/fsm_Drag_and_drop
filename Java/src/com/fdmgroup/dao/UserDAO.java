package com.fdmgroup.dao;

import java.util.ArrayList;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;

import com.fdmgroup.model.User;



public class UserDAO {

	@Autowired
	private DBConnection con;
	
	List<User> users;
	
	
	public DBConnection getCon() {
		return con;
	}

	public void setCon(DBConnection con) {
		this.con = con;
	}

	public List<User> getListOfUsers(){
		EntityManager em = con.getEntityManager();
		
		TypedQuery<User> query =  em.createNamedQuery("User.getAllUsers", User.class);
		users = query.getResultList();
		
		em.close();
		
		return users;
		
	}
	
	public User getUserByUsername(String user){
		
		EntityManager em = con.getEntityManager();
		
		Query query = em.createNamedQuery("User.getByUsername");
		query.setParameter(1, user);
		
		try{
			User u = (User) query.getSingleResult();
			
			em.close();
			
			return u;
			
		}catch(NoResultException e){
			return null;
		}
	}
	
	public List<User> findAllUsers(){
		
		List<User> userList = new ArrayList<>();
		
		EntityManager em = con.getEntityManager();
		
		TypedQuery<User> query = em.createNamedQuery("User.getAllUsers", User.class);
		userList = query.getResultList();

		return userList;
	}
	
	public User findUserById(int userid){
		
		EntityManager em = con.getEntityManager();
		
		Query query = em.createNamedQuery("User.getByUserID");
		query.setParameter(1, userid);
		
		try{
			User u = (User) query.getSingleResult();
			
			em.close();
			
			return u;
			
		}catch(NoResultException e){
			return null;
		}
		
	}
	
	public void addUser(User u){
		EntityManager em = con.getEntityManager();
		
		em.getTransaction().begin();
			em.persist(u);
		em.getTransaction().commit();	
	}
	
}