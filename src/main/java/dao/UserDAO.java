package main.java.dao;

import main.java.DBUtil;
import main.java.model.User;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
	private Connection conn = null;
	
	public UserDAO() {
		conn = DBUtil.getConnection();
		createTable();
	}

	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS users (" +
						"user_id   SERIAL 	   PRIMARY KEY     	," +
						"email     varchar(40) NOT NULL UNIQUE 	," +
						"firstName varchar(20) NOT NULL        	," +
						"lastName  varchar(30) NOT NULL        	," +
						"pass      varchar(8)  NULL        		," +
						"token     varchar(40) NULL        		)" ;
		
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.executeUpdate();
		} catch ( Exception e ) {
			e.printStackTrace();
			//throw new IOException( "Error create table USERS" );
		}
	}
	
	public boolean insertRecord( User user ) throws IOException {
		String sql = "INSERT INTO users ( email, pass, firstName, lastName, token ) VALUES (?, ?, ?, ?, ?)";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, user.getEmail() 		);
			stmt.setString( 2, user.getPass() 		);
			stmt.setString( 3, user.getFirstName() 	);
			stmt.setString( 4, user.getLastName() 	);
			stmt.setString( 5, user.getToken() 		);
			
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			//if ( e instanceof SQLIntegrityConstraintViolationException ) {
			if ( e.getErrorCode() == 23505 ) {
				return false;
			} else {
				throw new IOException ( e );
			}
		} catch ( Exception ex ) {
			throw new IOException ( ex );
		}
		return true;
	}

	public boolean updateRecord( User user ) throws IOException {
		boolean rowUpdated = false;
		
		String sql = "UPDATE users SET email = ?, pass = ?, firstName = ?, lastName=? WHERE user_id = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, user.getEmail() );
			stmt.setString( 2, user.getPass() );
			stmt.setString( 3, user.getFirstName() );
			stmt.setString( 4, user.getLastName() );
			stmt.setInt( 5, user.getUserId() );
			
			rowUpdated = ( stmt.executeUpdate() > 0 );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return rowUpdated;
	}

	public boolean deleteRecord( int userId ) throws IOException {
		boolean rowDeleted = false;

		String sql = "DELETE FROM users WHERE user_id = ?";
		try ( PreparedStatement stmt = conn.prepareStatement(sql) ) {
			stmt.setInt( 1, userId );
			
			rowDeleted = ( stmt.executeUpdate() > 0 );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return rowDeleted;
	}

	public User getUserResultSet( ResultSet rs ) throws IOException {
		User user = null;
		try {
			if ( rs.next() ) {
				user = new User( 
					rs.getInt( "user_id" )		,
					rs.getString( "email" )		,
					rs.getString( "firstName" )	, 
					rs.getString( "lastName" )	,
					rs.getString( "pass" )		,
					rs.getString( "token" )		
				);
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return user;
	}

	public User selectRecordByUser( int userId ) throws IOException {
		User user = null;

		String sql = "SELECT * FROM users WHERE user_id = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setInt( 1, userId );
			
			try ( ResultSet rs = stmt.executeQuery(); ) {
				user = getUserResultSet( rs );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return user;
	}

	public User selectRecordByEmail( String email ) throws IOException {
		User user = null;

		String sql = "SELECT * FROM users WHERE email = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, email );
			
			try ( ResultSet rs = stmt.executeQuery(); ) {
				user = getUserResultSet( rs );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return user;
	}

	public User selectRecordByToken( String token ) throws IOException {
		User user = null;

		String sql = "SELECT * FROM users WHERE token = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, token );
			
			try ( ResultSet rs = stmt.executeQuery(); ) {
				user = getUserResultSet( rs );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return user;
	}

	public List<User> selectAllRecords() throws IOException {
		List<User> usersList = new ArrayList<User>();

		String sql = "SELECT * FROM users ORDER BY user_id";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			
			try ( ResultSet rs = stmt.executeQuery() ) {
				while ( rs.next() ) {
					User user = new User( 
						rs.getInt( "user_id" )		,
						rs.getString( "email" )		,
						rs.getString( "firstName" )	, 
						rs.getString( "lastName" )	,
						rs.getString( "pass" )		,
						rs.getString( "token" )		
					);
					usersList.add( user );
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return usersList;
	}
	
	public boolean setNewToken( User user ) throws IOException {
		boolean rowUpdated = false;
		String token = UUID.randomUUID().toString();
		
		String sql = "UPDATE users SET token = ? WHERE user_id = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, token );
			stmt.setInt( 2, user.getUserId() );

			rowUpdated = ( stmt.executeUpdate() > 0 );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		if ( rowUpdated ) 
		{ 
			user.setToken( token );		// set user token
		}
		return rowUpdated;
	}
	
	public boolean setNewPassword( User user ) throws IOException {
		boolean rowUpdated = false;
		
		String sql = "UPDATE users SET token = NULL, pass = ? WHERE user_id = ?";
		try ( PreparedStatement stmt = conn.prepareStatement( sql ) ) {
			stmt.setString( 1, user.getPass() );
			stmt.setInt( 2, user.getUserId() );

			rowUpdated = ( stmt.executeUpdate() > 0 );
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		user.setToken( null );			// reset user token
		return rowUpdated;
	}
}
