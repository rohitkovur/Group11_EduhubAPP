package com.eduhub.eduhubapp.DTO;

import java.time.LocalDate;

public class EnrollInfoStudentDTO {
	
	private Integer userId;
	private String userName;
	private Integer courseId;
	private LocalDate enrollmentDate;
	public EnrollInfoStudentDTO(Integer userId, String userName, Integer courseId, LocalDate enrollmentDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.courseId = courseId;
		this.enrollmentDate = enrollmentDate;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	

}
