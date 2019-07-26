package com.fdmgroup.model;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "FSMUser")
@NamedQueries({
	@NamedQuery(name = "User.getByUserID", query="SELECT u FROM User u WHERE u.userID = ?1"),
	@NamedQuery(name = "User.getAllUsers", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.getByUsername", query = "SELECT u FROM User u WHERE u.username = ?1")
})
public class User {

	@Id
	@SequenceGenerator(name = "FSMUserSeq", sequenceName = "FSM_USER_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FSMUserSeq")
	private int userID;
	@Column(name="FSM_name")
	private String name;
	@Resource
	@Column(name="FSM_Username")
	private String username;
	@Resource
	@Column(name="FSM_Password")
	private String password;
	
	@OneToMany
	@Resource
	private List<JSONFsm> myFSM;

	public User() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<JSONFsm> getMyFSM() {
		return myFSM;
	}

	public void setMyFSM(List<JSONFsm> myFSM) {
		this.myFSM = myFSM;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", myFSM=" + myFSM + "]";
	}
	
	
	
	
}

