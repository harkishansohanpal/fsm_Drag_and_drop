package com.fdmgroup.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBConnection {

	private static final String persistenceName = "FinchFSM";
	
	private static DBConnection con = null;
	private EntityManagerFactory emf = null;
	
	private DBConnection(){
		init();
	}
	
	private void init(){
		if(emf == null || !emf.isOpen()){
			emf = Persistence.createEntityManagerFactory(persistenceName);
		}
	}
	
	public static DBConnection getInstance(){
		if(con == null){
			con = new DBConnection();
		}
		return con;
	}
	
	public EntityManager getEntityManager(){
		init();
		return emf.createEntityManager();
	}
	
	public void close(){
		if(emf != null && emf.isOpen()){
			emf.close();
		}
	}
	
}
