package com.eduhub.eduhubapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Quiz;

@Repository
public interface QuizDao extends JpaRepository<Quiz,Integer>{
	
	Quiz findByQuizIdAndLessonId(Integer quizId,Integer lessonId);
	
	Quiz findByLessonId(Integer lessonId);
	
	Quiz findByTitleAndLessonId(String title, Integer lessonId);
	
	@Query("SELECT lessonId FROM Quiz WHERE quizId=:quizId")
	Integer findLessonIdByQuizId(Integer quizId);
	
	@Query("SELECT max(quizId) FROM Quiz WHERE lessonId=:lessonId")
	Integer findMaxQuizIdByLessonId(Integer lessonId);

}
