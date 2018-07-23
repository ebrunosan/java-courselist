package main.java.model;

public class User {
	protected int userId;
	protected String userName;
	protected String pass;
	protected String firstName;
	protected String lastName;
	protected String email;

	public User( int userId ) {
		this.userId = userId;
	}
	
	public User( int userId, String userName, String pass, String firstName, String lastName, String email ) {
		this.userId = userId;
		this.userName = userName;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public User( String userName, String pass, String firstName, String lastName, String email) {
		this.userName = userName;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
