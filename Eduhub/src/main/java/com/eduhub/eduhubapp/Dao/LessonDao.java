package com.eduhub.eduhubapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Lesson;

import jakarta.transaction.Transactional;

@Transactional
@Repository
public interface LessonDao extends JpaRepository<Lesson,Integer> {
	
	List<Lesson> findByCourseId(Integer courseId);
	Lesson findByLessonIdAndCourseId(Integer lessonId,Integer courseId);
	Lesson findByTitle(String title);
	
	@Modifying
	@Query("INSERT INTO Lesson (courseId,title,subtitle,imageUrl,description) VALUES "
			+"(:courseId,:title,:subtitle,:imageUrl,:description)")
	void insertLessonDetails(Integer courseId,String title,String subtitle,String imageUrl,String description);
	Lesson findByLessonId(Integer lessonId);
	
	@Query("SELECT courseId FROM Lesson WHERE lessonId=:lessonId")
	Integer findCourseIdByLessonId(Integer lessonId);

}
