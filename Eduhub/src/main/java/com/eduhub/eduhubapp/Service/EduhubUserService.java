package com.eduhub.eduhubapp.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.entity.EduhubUser;

@Service
public interface EduhubUserService {
	
	public ResponseEntity<String> addNewUser(EduhubUser eduhubUserReq);
	public ResponseEntity<String> fetchUserDetails(String emailId);

}
