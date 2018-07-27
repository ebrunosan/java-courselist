package main.java.model;

import main.java.model.CourseProgram;

public class Student {
	private int studentId;
	private String name;
	private String age;
	private String gender;
	private String country;
	private CourseProgram course;

	public Student(){
		this.course = new CourseProgram();
	}
		
	public Student(int studentId) {
		this.studentId = studentId;
		this.course = new CourseProgram();
	}
	
	public Student(int studentId, String name, String age, String gender, String country, CourseProgram course) {
		this.studentId = studentId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.course = course;
		this.country = country;
	}
	
	public Student(String name, String age, String gender, String country, CourseProgram course) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.country= country;
		this.course = course;
	}
	
	public int getStudentId() {
		return this.studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public CourseProgram getCourse() {
		return this.course;
	}

	public void setCourse(CourseProgram course) {
		this.course = course;
	}
	
	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
