package com.eduhub.eduhubapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Certificate;

@Repository
public interface CertificateDao extends JpaRepository<Certificate,Integer> {
	
	Certificate findByUserIdAndCourseId(Integer userId,Integer courseId);

}
