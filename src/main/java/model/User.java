package jdbc;

public class User {
	private String username;
	private String pass;
	private String firstName;
	private String lastName;

	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String pass, String firstName, String lastName) {
		this.username = username;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
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
}
