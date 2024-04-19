package com.eduhub.eduhubapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.Dao.EduhubUserDao;
import com.eduhub.eduhubapp.entity.EduhubUser;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import org.json.*;
@Service
public class EduhubUserServiceImpl implements EduhubUserService{
	
	@Autowired
	EduhubUser eduhubUser;
	
	@Autowired
	EduhubUserDao eduhubUserDao;

	@Override
	public ResponseEntity<String> addNewUser(EduhubUser eduhubUserReq) {
		// TODO Auto-generated method stub
		if(checkIfUserExists(eduhubUserReq.getEmailId())!=0) {
			Map<Object,Object> ob=new HashMap<>();
			ob.put("userId", checkIfUserExists(eduhubUserReq.getEmailId()));
			ob.put("message", "user exists");
			Gson gson=new Gson();
			String json=gson.toJson(ob);
			return new ResponseEntity<>(json,HttpStatus.ALREADY_REPORTED);
		}
		eduhubUser.setEmailId(eduhubUserReq.getEmailId());
		eduhubUser.setName(eduhubUserReq.getName());
		eduhubUser.setRole(eduhubUserReq.getRole());
		try {
			eduhubUserDao.save(eduhubUser);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("userId", eduhubUserDao.findByEmailId(eduhubUserReq.getEmailId()).getUserId());
			Gson gson=new Gson();
			String json=gson.toJson(ob);
			return new ResponseEntity<>(json,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	@Override
	public ResponseEntity<String> fetchUserDetails(String emailId) {
		// TODO Auto-generated method stub
		try {
			
			return new ResponseEntity<>(new Gson().toJson(eduhubUserDao.findByEmailId(emailId)),HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return null;
	}
	
	public String fetchUserNameForCertificate(Integer userId) {
		
		EduhubUser foundUser= eduhubUserDao.findByUserId(userId);
		return foundUser.getName();
		
	}
	
	public Integer checkIfUserExists(String emailId) {
		if(eduhubUserDao.findByEmailId(emailId)==null) {
			return 0;
		}
		Integer userId=eduhubUserDao.findByEmailId(emailId).getUserId();
		return userId;
	}

}
