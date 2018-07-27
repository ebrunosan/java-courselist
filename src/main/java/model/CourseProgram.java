package main.java.model;

public class CourseProgram {
	protected int courseCode;
	protected String courseName;
	protected String duration;
	protected String description;

	public CourseProgram() { }

	public CourseProgram(int courseCode) {
		this.courseCode = courseCode;
	}
	
	public CourseProgram(int courseCode, String courseName, String duration, String description) {
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.duration = duration;
		this.description = description;
	}
	
	public CourseProgram(String courseName, String duration, String description) {
		this.courseName = courseName;
		this.duration = duration;
		this.description = description;
	}
	
	public int getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(int courseCode) {
		this.courseCode = courseCode;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
