package com.eduhub.eduhubapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Submission;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface SubmissionDao extends JpaRepository<Submission,Integer>{
	
	@Query("SELECT score FROM Submission where quizId=:quizId")
	Float findQuizScoreByQuizId(Integer quizId);
	
	
	@Modifying
	@Query("INSERT INTO Submission (userId,quizId,score)"
			+" VALUES (:userId,:quizId,:finalScore)")
	void insertSubmissionToDB(Integer userId, Integer quizId, float finalScore);

	@Query("SELECT score FROM Submission where quizId=:quizId and userId=:userId")
	Float findQuizScoreByQuizIdAndUserId(Integer quizId, Integer userId);

}
