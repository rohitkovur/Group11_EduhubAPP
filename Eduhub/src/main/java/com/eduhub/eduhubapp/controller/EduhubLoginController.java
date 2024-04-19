package com.eduhub.eduhubapp.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduhub.eduhubapp.Service.EduhubUserServiceImpl;
import com.eduhub.eduhubapp.entity.EduhubUser;

@CrossOrigin
@RestController
@RequestMapping("user")
public class EduhubLoginController{
	/*
	@PostMapping("/oauth2")
	public ResponseEntity<Boolean> userLogin(@RequestParam String token){
		return new ResponseEntity<>(true,HttpStatus.OK);
	}
	
	@GetMapping("/hello")
	public ResponseEntity<String> helloPage(){
		return new ResponseEntity<>("Hello There",HttpStatus.OK);
	}
	*/
	/*@GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {

        //log.info("User Attributes: {}", principal.getAttributes());
        return Collections.singletonMap("principal", principal);
    }*/
	
	@Autowired
	EduhubUserServiceImpl userServiceImpl;
	
	@PostMapping("new")
	public ResponseEntity<String> addNewUser(@RequestBody EduhubUser userDetails){
		return userServiceImpl.addNewUser(userDetails);
	}
	
	@GetMapping("details")
	public ResponseEntity<String> getUserDetails(@RequestParam String emailId){
		return userServiceImpl.fetchUserDetails(emailId);
	}
}