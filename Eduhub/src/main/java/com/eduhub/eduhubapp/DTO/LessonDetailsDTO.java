package com.eduhub.eduhubapp.DTO;

public class LessonDetailsDTO {
	
	
	private String title;
	private String description;
	private Integer courseId;
	private String subtitle;
	private String imageUrl;
	private Float quizScore;
	public LessonDetailsDTO(String title, String description, Integer courseId, String subtitle, String imageUrl,
			Float quizScore) {
		super();
		this.title = title;
		this.description = description;
		this.courseId = courseId;
		this.subtitle = subtitle;
		this.imageUrl = imageUrl;
		this.quizScore = quizScore;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Float getQuizScore() {
		return quizScore;
	}
	public void setQuizScore(Float quizScore) {
		this.quizScore = quizScore;
	}
	

}
