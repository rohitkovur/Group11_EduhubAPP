package com.eduhub.eduhubapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Question;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface QuestionDao extends JpaRepository<Question,Integer> {
	
	List<Question> findByQuizId(Integer quizId);
	
	@Modifying
	@Query("INSERT INTO Question (quizId,questionDescription,option1,option2,option3,option4,correctAnswer)"
			+ " VALUES (:quizId,:questionDescription,:option1,:option2,:option3,:option4,:correctAnswer)")
	void addQuestionForQuiz(Integer quizId, String questionDescription, String option1, String option2, String option3,
			String option4, String correctAnswer);
	
	@Query("SELECT correctAnswer FROM Question where questionId=:questionId")
	String findCorrectAnswerByQuestionId(Integer questionId);

}
