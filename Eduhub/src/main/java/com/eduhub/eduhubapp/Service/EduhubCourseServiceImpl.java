package com.eduhub.eduhubapp.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.DTO.EnrollInfoStudentDTO;
import com.eduhub.eduhubapp.DTO.LessonDetailsDTO;
import com.eduhub.eduhubapp.DTO.SubmissionRequestDTO;
import com.eduhub.eduhubapp.Dao.CertificateDao;
import com.eduhub.eduhubapp.Dao.CourseDao;
import com.eduhub.eduhubapp.Dao.EduhubUserDao;
import com.eduhub.eduhubapp.Dao.EnrollmentDao;
import com.eduhub.eduhubapp.Dao.LessonDao;
import com.eduhub.eduhubapp.Dao.QuestionDao;
import com.eduhub.eduhubapp.Dao.QuizDao;
import com.eduhub.eduhubapp.Dao.SubmissionDao;
import com.eduhub.eduhubapp.entity.Certificate;
import com.eduhub.eduhubapp.entity.Course;
import com.eduhub.eduhubapp.entity.EduhubUser;
import com.eduhub.eduhubapp.entity.Enrollment;
import com.eduhub.eduhubapp.entity.Lesson;
//import com.eduhub.eduhubapp.entity.Options;
import com.eduhub.eduhubapp.entity.Question;
import com.eduhub.eduhubapp.entity.Quiz;
import com.eduhub.eduhubapp.entity.Submission;
import com.google.gson.Gson;

@Service
public class EduhubCourseServiceImpl implements EduhubCourseService {
	
	@Autowired
	Question question;
	
	@Autowired
	QuestionDao questionDao;
	
	@Autowired
	Quiz quiz;
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	Lesson lesson;
	
	@Autowired
	LessonDao lessonDao;
	
	@Autowired
	Course course;
	
	@Autowired
	CourseDao courseDao;

	@Autowired
	EduhubUserDao eduhubUserDao;
	
	@Autowired
	Enrollment enrollment;
	
	@Autowired
	EnrollmentDao enrollmentDao;
	
	@Autowired
	Certificate certificate;
	
	@Autowired
	CertificateDao certificateDao;
	
	@Autowired
	EduhubUserServiceImpl eduhubUserServiceImpl;
	
	@Autowired
	Submission submission;
	
	@Autowired
	SubmissionDao submissionDao;

	@Override
	public void createAQuestionForQuiz(Question questionRequest) {
		// TODO Auto-generated method stub
		/*question.setQuestionDescription(questionRequest.getQuestionDescription());
		question.setOption1(questionRequest.getOption1());
		question.setOption2(questionRequest.getOption2());
		question.setOption3(questionRequest.getOption3());
		question.setOption4(questionRequest.getOption4());
		question.setQuizId(questionRequest.getQuizId());
		question.setCorrectAnswer(questionRequest.getCorrectAnswer());*/
		try {
			questionDao.addQuestionForQuiz(questionRequest.getQuizId(),questionRequest.getQuestionDescription(),questionRequest.getOption1(),
					questionRequest.getOption2(),questionRequest.getOption3(),questionRequest.getOption4(),questionRequest.getCorrectAnswer());
			//return new ResponseEntity<>("Created a question for the quiz",HttpStatus.OK);
		} catch(Exception e){
			e.printStackTrace();
			//return new ResponseEntity<>("Failed to create a question. Check Logs",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return null;
	}

	@Override
	public ResponseEntity<String> createAQuizForLesson(Quiz lessonToQuiz) {
		// TODO Auto-generated method stub
		quiz.setDescription(lessonToQuiz.getDescription());
		quiz.setLessonId(lessonToQuiz.getLessonId());
		quiz.setTitle(lessonToQuiz.getTitle());
		try {
			quizDao.save(quiz);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("quizId", quizDao.findByTitleAndLessonId(lessonToQuiz.getTitle(),lessonToQuiz.getLessonId()).getQuizId());
			ob.put("message", "Created a quiz");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<String> createALessonForCourse(Lesson lessonDetails) {
		// TODO Auto-generated method stub
		lesson.setCourseId(lessonDetails.getCourseId());
		lesson.setDescription(lessonDetails.getDescription());
		lesson.setTitle(lessonDetails.getTitle());
		lesson.setSubtitle(lessonDetails.getSubtitle());
		lesson.setImageUrl(lessonDetails.getImageUrl());
		//lesson.setVideoId(lessonDetails.getVideoId());
		try {
			lessonDao.insertLessonDetails(lessonDetails.getCourseId(),lessonDetails.getTitle(),lessonDetails.getSubtitle(),lessonDetails.getImageUrl()
					,lessonDetails.getDescription());
			Map<Object,Object> ob=new HashMap<>();
			ob.put("lessonId",lessonDao.findByTitle(lessonDetails.getTitle()).getLessonId());
			ob.put("message", "Created a lesson with lessonId");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		
	}

	@Override
	public ResponseEntity<String> createACourse(Course courseDetails) {
		// TODO Auto-generated method stub
		course.setTitle(courseDetails.getTitle());
		course.setDescription(courseDetails.getDescription());
		course.setCoursePrice(courseDetails.getCoursePrice());
		course.setUserId(courseDetails.getUserId());
		course.setSubtitle(courseDetails.getSubtitle());
		course.setImageUrl(courseDetails.getImageUrl());
		try{
			courseDao.insertCourseDetails(courseDetails.getTitle(),courseDetails.getSubtitle(),courseDetails.getImageUrl(),courseDetails.getCoursePrice()
					,courseDetails.getDescription(),courseDetails.getUserId());
			Map<Object,Object> ob=new HashMap<>();
			ob.put("courseId", courseDao.findByTitle(courseDetails.getTitle()).getCourseId());
			ob.put("message", "Created a course ");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	public ResponseEntity<String> enrollStudentToCourse(Enrollment enrollRequest){
		enrollment.setCourseId(enrollRequest.getCourseId());
		enrollment.setUserId(enrollRequest.getUserId());
		enrollment.setEnrollmentDate(enrollRequest.getEnrollmentDate());
		try {
			enrollmentDao.enrollStudentToCourse(enrollRequest.getUserId(), enrollRequest.getCourseId(), enrollRequest.getEnrollmentDate());
			Map<Object,Object> ob=new HashMap<>();
			ob.put("enrollmentId", enrollmentDao.findByUserIdAndCourseId(enrollRequest.getUserId(),enrollRequest.getCourseId()).getEnrollmentId());
			ob.put("message", "Success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return null;
	}
	
	@Override
	public ResponseEntity<String> generateCertificate(Enrollment enrollCert){
		if(!checkForEnrollment(enrollCert.getCourseId(),enrollCert.getUserId())) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		certificate.setExternalCertificateId("Eduhub");
		certificate.setCourseId(enrollCert.getCourseId());
		certificate.setUserId(enrollCert.getUserId());
		//add code to get courseName
		Enrollment certEnroll=enrollmentDao.findByUserIdAndCourseId(enrollCert.getUserId(), enrollCert.getCourseId());
		String courseName=courseDao.findByCourseId(enrollCert.getCourseId()).getTitle();
		certificate.setCourseName(courseName);
		certificate.setScore(certEnroll.getScore());
		certificate.setCompletionDate(certEnroll.getCompletionDate());
		certificate.setUserName(eduhubUserServiceImpl.fetchUserNameForCertificate(enrollCert.getUserId()));
		try {
			certificateDao.save(certificate);
			certificate.setExternalCertificateId("Eduhub00"+
			certificateDao.findByUserIdAndCourseId(enrollCert.getUserId(),enrollCert.getCourseId()).getInternalCertificateId());
			certificateDao.save(certificate);
			Map<Object,Object> temp=new HashMap<>();
			temp.put("certificateId", certificate.getExternalCertificateId());
			temp.put("userName", certificate.getUserName());
			temp.put("courseName", certificate.getCourseName());
			temp.put("score", certificate.getScore());
			temp.put("completionDate", certificate.getCompletionDate()+"");
			Map<Object,Object> ob=new HashMap<>();
			ob.put("certificate", temp);
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
//	@Override
//	public ResponseEntity<String> viewAllCourses(){
//		try {
//			List<Course> courseList=courseDao.findAll();
//			Map<Object,Object> ob=new HashMap<>();
//			ob.put("courseList", courseList);
//			ob.put("message", "success");
//			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}


	@Override
	public ResponseEntity<String> viewAllCourses() {
		try {
			List<Course> courseList = courseDao.findAll();
			List<Map<String, Object>> coursesWithUserName = new ArrayList<>();

			for (Course course : courseList) {
				// Fetch user details based on userId
				EduhubUser user = eduhubUserDao.findByUserId(course.getUserId());
				if (user != null) {
					Map<String, Object> courseWithUserName = new HashMap<>();
					courseWithUserName.put("courseId", course.getCourseId());
					courseWithUserName.put("title", course.getTitle());
					courseWithUserName.put("description", course.getDescription());
					courseWithUserName.put("userId", course.getUserId());
					courseWithUserName.put("userName", user.getName()); // Adding user name
					courseWithUserName.put("coursePrice", course.getCoursePrice());
					courseWithUserName.put("imageUrl", course.getImageUrl());
					courseWithUserName.put("subtitle", course.getSubtitle());
					coursesWithUserName.add(courseWithUserName);
				}
			}

			Map<Object, Object> ob = new HashMap<>();
			ob.put("courseList", coursesWithUserName);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Override
	public ResponseEntity<String> viewCourse(Integer courseId){
		try {
			Course newCourse=courseDao.findByCourseId(courseId);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("course", newCourse);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> generateStudentListForCourse(Integer courseId){
		try {
			List<EnrollInfoStudentDTO> studentList=enrollmentDao.findListOfStudentsForCourse(courseId);
			List<Map<Object,Object>> responseList=new ArrayList<>();
			if(studentList!=null) {
				
				for(EnrollInfoStudentDTO student:studentList) {
					Map<Object,Object> temp=new HashMap<>();
					temp.put("userId", student.getUserId());
					temp.put("courseId", student.getCourseId());
					temp.put("userName", student.getUserName());
					temp.put("enrolllmentDate", student.getEnrollmentDate().toString());
					responseList.add(temp);
				}
			}
			Map<Object,Object> ob=new HashMap<>();
			ob.put("studentList", responseList);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> getListOfLessonsForCourse(Integer courseId,Integer userId){
		if(!checkForEnrollment(courseId,userId)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		try {
			List<LessonDetailsDTO> listDetails=new ArrayList<>();
			List<Lesson> lessonList=lessonDao.findByCourseId(courseId);
			for(Lesson eachLesson:lessonList) {
				Float quizScore;
				if(quizDao.findByLessonId(eachLesson.getLessonId())!=null) {
					Integer quizId=quizDao.findByLessonId(eachLesson.getLessonId()).getQuizId();
					Float tempsub=submissionDao.findQuizScoreByQuizIdAndUserId(quizId,userId);
					if(tempsub!=null) {
						quizScore=tempsub;
					}
					else {
						quizScore=0.0f;
					}
				}
				else {
					quizScore=0.0f;
				}
				LessonDetailsDTO newDTO=new LessonDetailsDTO(eachLesson.getTitle(), eachLesson.getDescription()
						,eachLesson.getCourseId(), eachLesson.getSubtitle(),eachLesson.getImageUrl(),quizScore);
				listDetails.add(newDTO);
			}
			Map<Object,Object> ob=new HashMap<>();
			ob.put("lessonList", listDetails);
			ob.put("message","success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@Override
	public ResponseEntity<String> getLessonForCourse(Integer lessonId,Integer userId){
		Integer courseId=lessonDao.findByLessonId(lessonId).getCourseId();
		if(!checkForEnrollment(courseId,userId)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Float quizScore;
		try {
			Lesson fetchedLesson=lessonDao.findByLessonIdAndCourseId(lessonId,courseId);
			if(quizDao.findByLessonId(fetchedLesson.getLessonId())!=null) {
				Integer quizId=quizDao.findByLessonId(fetchedLesson.getLessonId()).getQuizId();
				Float tempsub=submissionDao.findQuizScoreByQuizIdAndUserId(quizId,userId);
				if(tempsub!=null) {
					quizScore=tempsub;
				}
				else {
					quizScore=0.0f;
				}
			}
			else {
				quizScore=0.0f;
			}
			LessonDetailsDTO newDTO=new LessonDetailsDTO(fetchedLesson.getTitle(), fetchedLesson.getDescription()
					,fetchedLesson.getCourseId(), fetchedLesson.getSubtitle(),fetchedLesson.getImageUrl(),quizScore);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("lessonDetails", newDTO);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	private boolean checkForEnrollment(Integer courseId, Integer userId) {
		// TODO Auto-generated method stub
		if(enrollmentDao.findByUserIdAndCourseId(userId, courseId)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public ResponseEntity<String> getQuizForLesson(Integer quizId,Integer lessonId){
		try {
			Quiz fecthedQuiz=quizDao.findByQuizIdAndLessonId(quizId,lessonId);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("quiz", fecthedQuiz);
			ob.put("messgae", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> getAllQuizesForLesson(Integer lessonId){
		try {
			Quiz quizList=quizDao.findByLessonId(lessonId);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("quiz", quizList);
			ob.put("messgae", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> getAllQuestionsForQuiz(Integer quizId){
		try {
			List<Question> questionList=questionDao.findByQuizId(quizId);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("questionList", questionList);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> addQuestionsToQuiz(List<Question> questionList){
		try {
			for(Question questionOb:questionList) {
				createAQuestionForQuiz(questionOb);
				for(int i=0;i<1000;i++) {
					//wait
				}
			}
			Map<Object,Object> ob=new HashMap<>();
			//ob.put("questionList", questionList);
			ob.put("message", "Added Questions To Quiz");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> editCourse(Course courseEditReq){
		try {
			Course courseFromDB=courseDao.findByCourseId(courseEditReq.getCourseId());
			if(courseEditReq.getCoursePrice()!=null) {
				courseFromDB.setCoursePrice(courseEditReq.getCoursePrice());
			}
			if(courseEditReq.getDescription()!=null) {
				courseFromDB.setDescription(courseEditReq.getDescription());
			}
			if(courseEditReq.getImageUrl()!=null) {
				courseFromDB.setImageUrl(courseEditReq.getImageUrl());
			}
			if(courseEditReq.getSubtitle()!=null) {
				courseFromDB.setSubtitle(courseEditReq.getSubtitle());
			}
			if(courseEditReq.getTitle()!=null) {
				courseFromDB.setTitle(courseEditReq.getTitle());
			}
			
			courseDao.save(courseFromDB);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("message", "Success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> editLesson(Lesson lessonEditReq){
		try {
			Lesson lessonFromDB=lessonDao.findByLessonId(lessonEditReq.getLessonId());
			/*if(lessonEditReq.getCoursePrice()!=null) {
				courseFromDB.setCoursePrice(lessonEditReq.getCoursePrice());
			}*/
			if(lessonEditReq.getDescription()!=null) {
				lessonFromDB.setDescription(lessonEditReq.getDescription());
			}
			if(lessonEditReq.getImageUrl()!=null) {
				lessonFromDB.setImageUrl(lessonEditReq.getImageUrl());
			}
			if(lessonEditReq.getSubtitle()!=null) {
				lessonFromDB.setSubtitle(lessonEditReq.getSubtitle());
			}
			if(lessonEditReq.getTitle()!=null) {
				lessonFromDB.setTitle(lessonEditReq.getTitle());
			}
			
			lessonDao.save(lessonFromDB);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("message", "Success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Override
	public ResponseEntity<String> evaluateQuizSubmission(List<SubmissionRequestDTO> quizRequest){
		int points=0,noOfQues=0;
		float finalScore;
		Integer userId=quizRequest.get(0).getUserId();
		Integer quizId=quizRequest.get(0).getQuizId();
		for(SubmissionRequestDTO sub:quizRequest) {
			String correctAnswer=fetchAnswerForQuestion( sub.getQuizId(), sub.getQuestionId());
			//System.out.println("Correct Answer: "+correctAnswer+" Response: "+sub.getResponse());
			if(correctAnswer.equals(sub.getResponse())) {
				points+=1;
				//System.out.println(points);
			}
			noOfQues+=1;
		}
		
		finalScore=(points*1.0f/noOfQues)*100;
		//System.out.println("pont/ques: "+(points/noOfQues));
		updateScoreInEnrollment(userId,quizId,finalScore);
		submissionDao.insertSubmissionToDB(userId,quizId,finalScore);
		Boolean checkCourseComplete=checkAndUpdateForCourseCompletion(quizId,userId,finalScore);
		if(checkCourseComplete) updateCompletionStatusForEnrollment(quizId,userId,checkCourseComplete);
		
		Map<Object,Object> statusMap=new HashMap<>();
		statusMap.put("isComplete", checkCourseComplete);
		statusMap.put("quizScore", finalScore);
		
		
		return new ResponseEntity<>(new Gson().toJson(statusMap),HttpStatus.OK);
	}
	
	private void updateScoreInEnrollment(Integer userId, Integer quizId, float finalScore) {
		// TODO Auto-generated method stub
		Integer lessonId=quizDao.findLessonIdByQuizId(quizId);
		Integer courseId=lessonDao.findCourseIdByLessonId(lessonId);
		enrollmentDao.updateScoreForEnrollment(userId, courseId, finalScore);
		
	}

	private void updateCompletionStatusForEnrollment(Integer quizId, Integer userId, Boolean checkCourseComplete) {
		// TODO Auto-generated method stub
		Integer lessonId=quizDao.findLessonIdByQuizId(quizId);
		Integer courseId=lessonDao.findCourseIdByLessonId(lessonId);
		enrollmentDao.updateCompletionForUser(courseId,userId,checkCourseComplete,LocalDate.now());
		
	}

	private Boolean checkAndUpdateForCourseCompletion(Integer quizId, Integer userId, float finalScore) {
		// TODO Auto-generated method stub
		
		Integer lessonId=quizDao.findLessonIdByQuizId(quizId);
		Integer maxQuizId=quizDao.findMaxQuizIdByLessonId(lessonId);
		if(quizId<maxQuizId) {
			return false;
		}
		return true;
	}

	public String fetchAnswerForQuestion(Integer quizId,Integer questionId) {
		
		String correctAnswer=questionDao.findCorrectAnswerByQuestionId(questionId);
		return correctAnswer;
	}
	
	@Override
	public ResponseEntity<String> getCreatedCourses(Integer userId){
		List<Course> createdCourseList=courseDao.findByUserId(userId);
		if(createdCourseList!=null) {
			Map<Object,Object> ob=new HashMap<>();
			ob.put("courseList", createdCourseList);
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<String> getEnrolledCourses(Integer userId){
		List<Enrollment> enrolledList=enrollmentDao.findByUserId(userId);
		List<Map<Object,Object>> response=new ArrayList<>();
		if(enrolledList!=null) {
			for(Enrollment enroll:enrolledList) {
				Map<Object,Object> temp=new HashMap<>();
				temp.put("courseName", courseDao.findByCourseId(enroll.getCourseId()).getTitle());
				temp.put("enrollmentDate", enroll.getEnrollmentDate().toString());
				temp.put("completionDate", enroll.getCompletionDate()+"");
				response.add(temp);
			}
			
			Map<Object,Object> ob=new HashMap<>();
			ob.put("courseList", response);
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}


	
	
	
	

}
