package main.java.dao;

import main.java.DBUtil;
import main.java.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection conn = null;
	
	public UserDAO() {
		conn = DBUtil.getConnection();
		createTable();
	}

	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS users_sample (" +
						"user_id   SERIAL 	   PRIMARY KEY ," +
						"username  varchar(50) NOT NULL ," +
						"pass      varchar(50) NOT NULL ," +
						"firstName varchar(50) NOT NULL ," +
						"lastName  varchar(50) NOT NULL )";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO treat exception
		}
	}
	
	public boolean insertRecord(User user) throws Exception {
		String sql = "INSERT INTO users_sample (username, pass, firstName, lastName) VALUES (?, ?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPass());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	public boolean updateRecord(User user) throws Exception {
		boolean rowUpdated = false;
		
		String sql = "UPDATE users_sample SET userName = ?, pass = ?, firstName = ?, lastName=? WHERE user_id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getUserName());
			stmt.setString(2, user.getPass());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setInt(5, user.getUserId());
			rowUpdated = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowUpdated;
	}

	public boolean deleteRecord(User user) throws Exception {
		boolean rowDeleted = false;

		String sql = "DELETE FROM users_sample WHERE user_id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, user.getUserId());
			rowDeleted = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowDeleted;
	}

	public User selectRecordByUser(int userId) throws Exception {
		User user = null;

		String sql = "SELECT username, pass, firstName, lastName FROM users_sample WHERE user_id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if ( rs.next() ) {
					user = new User( userId, 
						rs.getString("username"), 
						rs.getString("pass"), 
						rs.getString("firstName"), 
						rs.getString("lastName"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return user;
	}

	public List<User> selectAllRecords() throws Exception {
		List<User> usersList = new ArrayList<User>();

		String sql = "SELECT user_id, username, pass, firstName, lastName FROM users_sample ORDER BY user_id";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = stmt.executeQuery();) {
				while ( rs.next() ) {
					User user = new User(
						rs.getInt("user_id"), 
						rs.getString("username"), 
						rs.getString("pass"), 
						rs.getString("firstName"), 
						rs.getString("lastName"));
					usersList.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return usersList;
	}
}
