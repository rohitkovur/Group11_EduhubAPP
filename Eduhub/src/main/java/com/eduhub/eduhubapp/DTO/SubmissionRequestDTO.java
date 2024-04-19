package com.eduhub.eduhubapp.DTO;

public class SubmissionRequestDTO {
	
	private Integer userId;
	public SubmissionRequestDTO(Integer userId, Integer quizId, Integer questionId, String response) {
		super();
		this.userId = userId;
		this.quizId = quizId;
		this.questionId = questionId;
		this.response = response;
	}
	private Integer quizId;
	private Integer questionId;
	private String response;
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
	public Integer getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	

}
