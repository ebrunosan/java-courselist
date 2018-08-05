package main.java.dao;

import main.java.DBUtil;
import main.java.model.Student;
import main.java.model.CourseProgram;

import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
	private Connection conn = null;
	
	public StudentDAO() throws IOException {
		conn = DBUtil.getConnection();
		createTable();
	}

	private void createTable() throws IOException {
		String sql = "CREATE TABLE IF NOT EXISTS student (" +
						"student_id SERIAL 	   	PRIMARY KEY ," +
						"name  		varchar(50) NOT NULL UNIQUE ," +
						"age      	varchar(3) 	NOT NULL ," +
						"gender 	varchar(10) NOT NULL ," +
						"course_id 	int 		NULL ," +
						"country  	varchar(50) NOT NULL," + 
						"CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES courseProgram(course_code) )";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
	}
	
	public boolean insertRecord(Student student) throws IOException {
		String sql = "INSERT INTO student (name, age, gender, country, course_id) VALUES (?, ?, ?, ?, ?)";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getAge());
			stmt.setString(3, student.getGender());
			stmt.setString(4, student.getCountry());
			stmt.setInt(5, student.getCourse().getCourseCode() );
			stmt.executeUpdate();
		} catch ( SQLException e ) {
			if ( "23505".equals( e.getSQLState() ) ) {	// duplicate key
				return false;
			} else {
				e.printStackTrace();
				throw new IOException ( e );
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		return true;
	}

	public boolean updateRecord(Student student) throws IOException {
		boolean rowUpdated = false;
		
		String sql = "UPDATE student SET name = ?, age = ?, gender = ?, country = ?, course_id = ? WHERE student_id = ?";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, student.getName());
			stmt.setString(2, student.getAge());
			stmt.setString(3, student.getGender());
			stmt.setString(4, student.getCountry());
			stmt.setInt(5, student.getCourse().getCourseCode());
			stmt.setInt(6, student.getStudentId());
			rowUpdated = stmt.executeUpdate() > 0;
		} catch ( SQLException e ) {
			if ( "23505".equals( e.getSQLState() ) ) {	// duplicate key
				return false;
			} else {
				e.printStackTrace();
				throw new IOException ( e );
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return rowUpdated;
	}

	public boolean deleteRecord(Student student) throws IOException {
		boolean rowDeleted = false;

		String sql = "DELETE FROM student WHERE student_id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, student.getStudentId());
			rowDeleted = stmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return rowDeleted;
	}

	public Student selectRecordByStudent(int studentId) throws IOException {
		Student student = null;

		String sql = "SELECT name, age, gender, country, course_id FROM student WHERE student_id = ?";
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, studentId);
			
			try (ResultSet rs = stmt.executeQuery();) {
				if ( rs.next() ) {
					student = new Student( studentId, 
						rs.getString("name"), 
						rs.getString("age"), 
						rs.getString("gender"), 
						rs.getString("country"), new CourseProgram());
						//rs.getInt("course_id")); find the course by the id
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return student;
	}

	public List<Student> selectAllRecords() throws IOException {
		List<Student> studentsList = new ArrayList<Student>();

		String sql = "SELECT student_id, name, age, gender, country, course_id FROM student ORDER BY student_id";

		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			try (ResultSet rs = stmt.executeQuery();) {
				while ( rs.next() ) {
					Student student = new Student(
						rs.getInt("student_id"), 
						rs.getString("name"), 
						rs.getString("age"), 
						rs.getString("gender"), 
						rs.getString("country"), new CourseProgram());
						//rs.getInt("course_id")); find the course by the id
					studentsList.add(student);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException ( e );
		}
		
		return studentsList;
	}
}
