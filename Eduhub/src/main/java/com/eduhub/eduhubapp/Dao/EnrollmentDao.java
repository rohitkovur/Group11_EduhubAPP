package com.eduhub.eduhubapp.Dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

//import com.eduhub.eduhubapp.entity.EduhubUser;
import com.eduhub.eduhubapp.entity.Enrollment;

import jakarta.transaction.Transactional;

import com.eduhub.eduhubapp.DTO.*;

@Repository
@Transactional
public interface EnrollmentDao extends JpaRepository<Enrollment,Integer>{
	
	@Query("SELECT new com.eduhub.eduhubapp.DTO.EnrollInfoStudentDTO(eu.userId,eu.name,e.courseId,e.enrollmentDate)"
			+ " FROM Enrollment e join EduhubUser eu on e.userId=eu.userId"
			+" WHERE e.courseId=:courseId")
	List<EnrollInfoStudentDTO> findListOfStudentsForCourse(Integer courseId);
	
	Enrollment findByUserIdAndCourseId(Integer userId,Integer courseId);
	
	@Modifying
	@Query("INSERT INTO Enrollment (userId,courseId,enrollmentDate) VALUES (:userId,:courseId,:enrollmentDate)")
	void enrollStudentToCourse(Integer userId,Integer courseId,LocalDate enrollmentDate);
	
	@Modifying
	@Query("UPDATE Enrollment SET isComplete=:checkCourseComplete, completionDate=:now WHERE courseId=:courseId and userId=:userId")
	void updateCompletionForUser(Integer courseId, Integer userId, Boolean checkCourseComplete, LocalDate now);
	
	@Query("SELECT e FROM Enrollment e where e.userId=:userId")
	List<Enrollment> findByUserId(Integer userId);
	
	@Modifying
	@Query("Update Enrollment SET score=:finalScore where userId=:userId and courseId=:courseId")
	void updateScoreForEnrollment(Integer userId, Integer courseId, float finalScore);

}
