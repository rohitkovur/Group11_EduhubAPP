package com.eduhub.eduhubapp.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduhub.eduhubapp.DTO.EnrollInfoStudentDTO;
import com.eduhub.eduhubapp.DTO.LessonDetailsDTO;
import com.eduhub.eduhubapp.DTO.SubmissionRequestDTO;
import com.eduhub.eduhubapp.Service.EduhubCourseServiceImpl;
import com.eduhub.eduhubapp.entity.Article;
import com.eduhub.eduhubapp.entity.Certificate;
import com.eduhub.eduhubapp.entity.Course;
import com.eduhub.eduhubapp.entity.Enrollment;
import com.eduhub.eduhubapp.entity.Lesson;
import com.eduhub.eduhubapp.entity.Question;
import com.eduhub.eduhubapp.entity.Quiz;

@CrossOrigin
@RestController
@RequestMapping("course")
public class EduhubCourseController {
	
	@Autowired
	Enrollment enrollment;
	
	@Autowired
	Quiz quiz;
	
	@Autowired
	EduhubCourseServiceImpl eduhubCourseServiceImpl;
	
	@PostMapping("createCourse")
	public ResponseEntity<String> createNewCourse(@RequestBody Course course){
		return eduhubCourseServiceImpl.createACourse(course);
	}
	
	@PostMapping("createQuiz")
	public ResponseEntity<String> createNewQuiz(@RequestBody Quiz quiz){
		return eduhubCourseServiceImpl.createAQuizForLesson(quiz);
	}
	
	@PostMapping("createLesson")
	public ResponseEntity<String> createNewLesson(@RequestBody Lesson lessonReq){
		return eduhubCourseServiceImpl.createALessonForCourse(lessonReq);
	}
	
	@GetMapping("viewCourse")
	public ResponseEntity<String> viewCourse(@RequestParam String courseId){
		return eduhubCourseServiceImpl.viewCourse(Integer.valueOf(courseId));
	}
	
	@GetMapping("viewAllCourse")
	public ResponseEntity<String> viewAllCourse(){
		return eduhubCourseServiceImpl.viewAllCourses();
	}
	
	@PostMapping("buyCourse")
	public ResponseEntity<String> buyCourse(@RequestBody Enrollment enrollRequest){
		return eduhubCourseServiceImpl.enrollStudentToCourse(enrollRequest);
	}
	
	@GetMapping("certificateDetails")
	public ResponseEntity<String> generateCertificate(@RequestParam String userId,@RequestParam String courseId){
		enrollment.setUserId(Integer.valueOf(userId));
		enrollment.setCourseId(Integer.valueOf(courseId));
		/*enrollment.setEnrollmentDate(LocalDate.parse(enrollmentDate));
		enrollment.setScore(Float.valueOf(score));*/
		return eduhubCourseServiceImpl.generateCertificate(enrollment);
	}
	
	@GetMapping("generateStudentList")
	public ResponseEntity<String> generateStudentList(@RequestParam String courseId){
		return eduhubCourseServiceImpl.generateStudentListForCourse(Integer.valueOf(courseId));
	}
	
	@GetMapping("getAllLessons")
	public ResponseEntity<String> listOfLessonsForCourse(@RequestParam String courseId,@RequestParam String userId){
		return eduhubCourseServiceImpl.getListOfLessonsForCourse(Integer.valueOf(courseId),Integer.valueOf(userId));
	}
	
	@GetMapping("getLesson")
	public ResponseEntity<String> getLessonForCourse(@RequestParam String lessonId,@RequestParam String userId){
		return eduhubCourseServiceImpl.getLessonForCourse(Integer.valueOf(lessonId),Integer.valueOf(userId));
	}
	
	@GetMapping("getQuizForLesson")
	public ResponseEntity<String> getQuizForLesson(@RequestParam String quizId,@RequestParam String lessonId){
		return eduhubCourseServiceImpl.getQuizForLesson(Integer.valueOf(quizId), Integer.valueOf(lessonId));
	}
	
	@GetMapping("getAllQuizForLesson")
	public ResponseEntity<String> getAllQuizesForLesson(@RequestParam String lessonId){
		return eduhubCourseServiceImpl.getAllQuizesForLesson(Integer.valueOf(lessonId));
	}
	
	@GetMapping("loadQuestionsForQuiz")
	public ResponseEntity<String> loadQuestionsForQuiz(@RequestParam String quizId){
		//quiz.setQuizId(Integer.valueOf(quizId));
		return eduhubCourseServiceImpl.getAllQuestionsForQuiz(Integer.valueOf(quizId));
	}
	
	@PostMapping("createQuestions")
	public ResponseEntity<String> addQuestionsToQuiz(@RequestBody List<Question> questionList){
		return eduhubCourseServiceImpl.addQuestionsToQuiz(questionList);
	}
	
	@PutMapping("editCourse")
	public ResponseEntity<String> editCourseDetails(@RequestBody Course courseEditDets){
		return eduhubCourseServiceImpl.editCourse(courseEditDets);
	}
	
	@PutMapping("editLesson")
	public ResponseEntity<String> editLessonDetails(@RequestBody Lesson lessonEditDets){
		return eduhubCourseServiceImpl.editLesson(lessonEditDets);
	}
	
	@PostMapping("evaluateQuiz")
	public ResponseEntity<String> quizEvaluateAndUpdate(@RequestBody List<SubmissionRequestDTO> quizRequest){
		return eduhubCourseServiceImpl.evaluateQuizSubmission(quizRequest);
	}
	
	@GetMapping("myCreatedCourses")
	public ResponseEntity<String> getMyCreatedCourses(@RequestParam String userId){
		return eduhubCourseServiceImpl.getCreatedCourses(Integer.valueOf(userId));
	}
	
	@GetMapping("myEnrolledCourses")
	public ResponseEntity<String> getMyEnrolledCourses(@RequestParam String userId){
		return eduhubCourseServiceImpl.getEnrolledCourses(Integer.valueOf(userId));
	}
	
	

}
