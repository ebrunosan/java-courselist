package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class UserDao {
	private Connection conn = null;
	
	public UserDao() {
		conn = DBUtil.getConnection();
	}

	public boolean insertRecord(User user) throws Exception {
		String sql = "insert into users_sample (username, pass, firstName, lastName) values (?, ?, ?, ?)";
		
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
		
		String sql = "update users_sample set firstName = ?, lastName=? where username = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getFirstName());
			stmt.setString(2, user.getLastName());
			stmt.setString(3, user.getUserName());
			rowUpdated = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowUpdated;
	}

	public boolean deleteRecord(User user) throws Exception {
		boolean rowDeleted = false;

		String sql = "delete user_sample where username = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getUserName());
			rowDeleted = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowDeleted;
	}

	public User selectRecordByUser(String username) throws Exception {
		User user = null;

		String sql = "select pass, firstName, lastName from users_sample where username = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, username);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if ( rs.next() ) {
					user = new User( username, 
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

		String sql = "select username, pass, firstName, lastName from users_sample";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = stmt.executeQuery();) {
				while ( rs.next() ) {
					User user = new User(
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
