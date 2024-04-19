package com.eduhub.eduhubapp.DTO;

public class CourseDetailsDTO {
	
	private Integer courseId;
	private String title;
	private String description;
	private Float coursePrice;
	private String userName;
	private String imageUrl;
	public CourseDetailsDTO(Integer courseId, String title, String description, Float coursePrice, String userName, String imageUrl) {
		super();
		this.courseId = courseId;
		this.title = title;
		this.description = description;
		this.coursePrice = coursePrice;
		this.userName = userName;
		this.imageUrl=imageUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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
	public Float getCoursePrice() {
		return coursePrice;
	}
	public void setCoursePrice(Float coursePrice) {
		this.coursePrice = coursePrice;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
