package main.java.model;

public class User {
	protected int 		userId;
	protected String 	email;
	protected String 	firstName;
	protected String 	lastName;
	protected String 	pass;
	protected String 	token;

	public User( ) {	}
	
	public User( int userId, String email, String firstName, String lastName, String pass, String token ) {
		this.userId 	= userId;
		this.email 		= email;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.pass 		= pass;
		this.token 		= token;
	}
	
	public User( String email, String firstName, String lastName, String pass, String token ) {
		this.email 		= email;
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.pass 		= pass;
		this.token 		= token;
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId( int userId ) {
		this.userId = userId;
	}
	
	public String getToken() {
		return token;
	}

	public void setToken( String token ) {
		this.token = token;
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass( String pass ) {
		this.pass = pass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName( String firstName ) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName( String lastName ) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email ) {
		this.email = email;
	}
}
