package main.java.dao;

import main.java.DBUtil;
import main.java.model.Course;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
	private Connection conn = null;
	
	public CourseDAO() throws IOException {
		conn = DBUtil.getConnection();
		createTable();
	}
	
	private void createTable() throws IOException {
		String sql = "CREATE TABLE IF NOT EXISTS courseProgram (" +
						"course_code   SERIAL 	   PRIMARY KEY ," +
						"course_name   varchar(50) NOT NULL ," +
						"duration      varchar(20) NOT NULL ," +
						"description   varchar(50) NOT NULL)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
	}
	
	public boolean insertCourse( Course course ) throws IOException {
		String sql = "INSERT INTO courseProgram (course_name, duration, description) VALUES (?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDuration());
			stmt.setString(3, course.getDescription());
			
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			if ( "23505".equals( e.getSQLState() ) ) {	// duplicate key
				return false;
			} else {
				e.printStackTrace();
				throw new IOException ( e );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return true;
	}

	public boolean updateCourse( Course course ) throws IOException {
		boolean rowUpdated = false;
		
		String sql = "UPDATE courseProgram SET course_name = ?, duration = ?, description = ? WHERE course_code = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDuration());
			stmt.setString(3, course.getDescription());
			stmt.setInt(4, course.getCourseCode());

			rowUpdated = stmt.executeUpdate() > 0;
		} catch ( SQLException e ) {
			if ( "23505".equals( e.getSQLState() ) ) {	// duplicate key
				return false;
			} else {
				e.printStackTrace();
				throw new IOException ( e );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return rowUpdated;
	}

	public boolean deleteCourse(Course course) throws IOException {
		boolean rowDeleted = false;

		String sql = "DELETE FROM courseProgram WHERE course_code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, course.getCourseCode());

			rowDeleted = stmt.executeUpdate() > 0;
		} catch ( SQLException e ) {
			if ( "23503".equals( e.getSQLState() ) ) {	// violates foreign key
				return false;
			} else {
				e.printStackTrace();
				throw new IOException ( e );
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return rowDeleted;
	}

	public Course selectRecordByCourse(int courseCode) throws IOException {
		Course course = null;

		String sql = "SELECT course_code, course_name, duration, description FROM courseProgram WHERE course_code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, courseCode);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if ( rs.next() ) {
					course = new Course( courseCode, 
						rs.getString("course_name"), 
						rs.getString("duration"), 
						rs.getString("description"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return course;
	}

	public List<Course> selectAllCourses() throws IOException {
		List<Course> courseList = new ArrayList<Course>();

		String sql = "SELECT course_code, course_name, duration, description FROM courseProgram ORDER BY course_code";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = stmt.executeQuery();) {
				while ( rs.next() ) {
					Course course = new Course(
						rs.getInt("course_code"), 
						rs.getString("course_name"), 
						rs.getString("duration"), 
						rs.getString("description"));
					courseList.add(course);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return courseList;
	}
}