package com.eduhub.eduhubapp.entity;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Component
public class Submission {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer submissionId;
	private Integer userId;
	private Integer quizId;
	private Float score;
	public Float getQuizScore() {
		return score;
	}
	public void setQuizScore(Float quizScore) {
		this.score = quizScore;
	}
	public Integer getSubmissionId() {
		return submissionId;
	}
	public void setSubmissionId(Integer submissionId) {
		this.submissionId = submissionId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getQuizId() {
		return quizId;
	}
	public void setQuizId(Integer quizId) {
		this.quizId = quizId;
	}
	

}
