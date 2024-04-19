package com.eduhub.eduhubapp.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class Certificate {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer internalCertificateId;
	private String externalCertificateId;
	private Integer userId;
	private String userName;
	private Integer courseId;
	private String courseName;
	public String getExternalCertificateId() {
		return externalCertificateId;
	}
	public void setExternalCertificateId(String externalCertificateId) {
		this.externalCertificateId = externalCertificateId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private LocalDate completionDate;
	private Float score;
	public Integer getInternalCertificateId() {
		return internalCertificateId;
	}
	public void setInternalCertificateId(Integer certificateId) {
		this.internalCertificateId = certificateId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public LocalDate getCompletionDate() {
		return completionDate;
	}
	public void setCompletionDate(LocalDate completionDate) {
		this.completionDate = completionDate;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	

}
