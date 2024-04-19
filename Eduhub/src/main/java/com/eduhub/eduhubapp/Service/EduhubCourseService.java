package com.eduhub.eduhubapp.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.DTO.EnrollInfoStudentDTO;
import com.eduhub.eduhubapp.DTO.LessonDetailsDTO;
import com.eduhub.eduhubapp.DTO.SubmissionRequestDTO;
import com.eduhub.eduhubapp.entity.Certificate;
import com.eduhub.eduhubapp.entity.Course;
import com.eduhub.eduhubapp.entity.Enrollment;
import com.eduhub.eduhubapp.entity.Lesson;
//import com.eduhub.eduhubapp.entity.Options;
import com.eduhub.eduhubapp.entity.Question;
import com.eduhub.eduhubapp.entity.Quiz;

@Service
public interface EduhubCourseService {
	
	public void createAQuestionForQuiz(Question question);
	public ResponseEntity<String> createAQuizForLesson(Quiz lessonToQuiz);
	public ResponseEntity<String> createALessonForCourse(Lesson lessonDetails);
	public ResponseEntity<String> createACourse(Course courseDetails);
	ResponseEntity<String> enrollStudentToCourse(Enrollment enrollRequest);
	ResponseEntity<String> addQuestionsToQuiz(List<Question> questionList);
	ResponseEntity<String> getAllQuestionsForQuiz(Integer quizId);
	ResponseEntity<String> generateCertificate(Enrollment enrollCert);
	ResponseEntity<String> viewAllCourses();
	ResponseEntity<String> viewCourse(Integer courseId);
	ResponseEntity<String> generateStudentListForCourse(Integer courseId);
	//ResponseEntity<List<LessonDetailsDTO>> getListOfLessonsForCourse(Integer courseId);
	ResponseEntity<String> getLessonForCourse(Integer lessonId,Integer userId);
	ResponseEntity<String> getQuizForLesson(Integer quizId, Integer lessonId);
	ResponseEntity<String> getAllQuizesForLesson(Integer lessonId);
	ResponseEntity<String> editCourse(Course courseEditReq);
	ResponseEntity<String> editLesson(Lesson lessonEditReq);
	ResponseEntity<String> getListOfLessonsForCourse(Integer courseId, Integer userId);
	ResponseEntity<String> evaluateQuizSubmission(List<SubmissionRequestDTO> quizRequest);
	ResponseEntity<String> getCreatedCourses(Integer userId);
	ResponseEntity<String> getEnrolledCourses(Integer userId);

}
