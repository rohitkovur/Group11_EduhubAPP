package com.eduhub.eduhubapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.DTO.CourseDetailsDTO;
import com.eduhub.eduhubapp.entity.Course;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface CourseDao extends JpaRepository<Course,Integer>{
	
	Course findByCourseId(Integer courseId);
	
	Course findByTitle(String title);
	
	@Query("SELECT new com.eduhub.eduhubapp.DTO.CourseDetailsDTO(c.courseId, c.title, c.description, c.coursePrice, eu.name, c.imageUrl) "
			+ "FROM Course c join EduhubUser eu on c.userId=eu.userId")
	List<CourseDetailsDTO> fetchDetailsOfAllCourses();
	
	@Modifying
	@Query("INSERT INTO Course(title,subtitle,imageUrl,coursePrice,description,userId) VALUES "
			+ "(:title,:subtitle,:imageUrl,:coursePrice,:description,:userId)")
	void insertCourseDetails(String title,String subtitle,String imageUrl,Float coursePrice,String description,Integer userId);

	//List<Course> findByUserId(Integer userId);
	
	@Query("SELECT c from Course c where c.userId=:userId")
	List<Course> findByUserId(Integer userId);
}
