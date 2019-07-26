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
			
			Query query = em.createNamedQuery("User.getAllUsers");
			userList = query.getResultList();
			System.out.println(userList);
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









		
	/*	/////////////
        @RequestMapping(value="/processLogin",method=RequestMethod.POST)
        public String processLogin(Model model, @Validated User userSF , BindingResult br){
                        
                        if(br.hasErrors()){
                                        return "log";
                        }
                        
                        List<User> userList = new ArrayList<>();
                        userList = userDAOObj.searchAllUser();
                        
                        boolean isFound = false;
                        int foundID = 0;
                        User foundUser = null;
                        
                        for(int i = 0; i<userList.size();i++){
                                        if(userList.get(i).getUser_email_id().equals(userSF.getUser_email_id()) && userList.get(i).getUser_password().equals(userSF.getUser_password())){
                                                        
                                                                        isFound = true;
                                                                        foundID = userList.get(i).getUser_Id();
                                        }
                        }
                        
                        SessionUser.setLoggedInUser(foundID);
                        System.out.println(SessionUser.getLoggedInUser());
                        
                        if(isFound==true){
                                        foundUser = userDAOObj.searchUserById(foundID);
                                        SessionUser.setLoggedInUserObj(foundUser);
                                        
                                        
                                        List<Advertisement> allAdvList = advertisementDAOObj.searchAlladvertisement();
                                        
                                        System.out.println("all records  " + allAdvList);
                                        
                                        System.out.println("*************************************************************");
                                        List<Advertisement> lastFourRecordList = advertisementDAOObj.searchLastFourAd(allAdvList);
                                        
                                        System.out.println(lastFourRecordList);
                                        
                                        System.out.println("*************************************************************");
                                        model.addAttribute("userSF", foundUser);
                                        model.addAttribute("lastFourRecordList",lastFourRecordList);
                                        return "forward:/loggedin";
                        }
                        
                        model.addAttribute("errorInfo", "Wrong credentials");
                        model.addAttribute("userSF", new User());
                        return "forward:/log";   */
       

		

