package main.java.dao;

import main.java.DBUtil;
import main.java.model.CourseProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class CourseProgramDAO {
	private Connection conn = null;
	
	public CourseProgramDAO() {
		conn = DBUtil.getConnection();
		createTable();
	}
	
	private void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS courseProgram (" +
						"course_code   SERIAL 	   PRIMARY KEY ," +
						"course_name   varchar(50)     NOT NULL ," +
						"duration      varchar(6) 	   NOT NULL ," +
						"description   varchar(50)     NULL ,";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO treat exception
		}
	}
	
	public boolean insertCourse(CourseProgram course) throws Exception {
		String sql = "INSERT INTO courseProgram (course_name, duration, description) VALUES (?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDuration());
			stmt.setString(3, course.getDescription());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return true;
	}

	public boolean updateCourse(CourseProgram course) throws Exception {
		boolean rowUpdated = false;
		
		String sql = "UPDATE courseProgram SET course_name = ?, duration = ?, description = ? WHERE course_code = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getDuration());
			stmt.setString(3, course.getDescription());
			stmt.setInt(4, course.getCourseCode());
			rowUpdated = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowUpdated;
	}

	public boolean deleteCourse(CourseProgram course) throws Exception {
		boolean rowDeleted = false;

		String sql = "DELETE FROM courseProgram WHERE course_code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, course.getCourseCode());
			rowDeleted = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return rowDeleted;
	}

	public CourseProgram selectRecordByCourse(int courseCode) throws Exception {
		CourseProgram course = null;

		String sql = "SELECT course_code, course_name, duration, description FROM courseProgram WHERE course_code = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, courseCode);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if ( rs.next() ) {
					course = new CourseProgram( courseCode, 
						rs.getString("course_name"), 
						rs.getString("duration"), 
						rs.getString("description"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return course;
	}

	public List<CourseProgram> selectAllCourses() throws Exception {
		List<CourseProgram> courseList = new ArrayList<CourseProgram>();

		String sql = "SELECT course_code, course_name, duration, description FROM courseProgram ORDER BY course_code";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = stmt.executeQuery();) {
				while ( rs.next() ) {
					CourseProgram course = new CourseProgram(
						rs.getInt("course_code"), 
						rs.getString("course_name"), 
						rs.getString("duration"), 
						rs.getString("description"));
					courseList.add(course);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return courseList;
	}
}
