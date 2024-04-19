package com.eduhub.eduhubapp.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.EduhubUser;

@Repository
public interface EduhubUserDao extends JpaRepository<EduhubUser,Integer>{
	
	EduhubUser findByEmailId(String emailId);
	
	EduhubUser findByUserId(Integer userId);

}
