package com.eduhub.eduhubapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eduhub.eduhubapp.Service.EduhubArticleServiceImpl;
import com.eduhub.eduhubapp.entity.Article;

@CrossOrigin
@RestController
@RequestMapping("/article")
public class EduhubArticleController {
	
	@Autowired
	EduhubArticleServiceImpl articleServiceImpl;
	
	@PostMapping("create")
	public ResponseEntity<String> creatNewArticle(@RequestBody Article article){
		return articleServiceImpl.createNewArticle(article);
	}
	
	@GetMapping("allArticles")
	public ResponseEntity<String> viewAllArticles(){
		return articleServiceImpl.viewAllArticles();
	}
	
	@GetMapping("viewArticle")
	public ResponseEntity<String> viewArticle(@RequestParam String articleId){
		return articleServiceImpl.viewArticle(Integer.valueOf(articleId));
		
	}
	
	@PutMapping("editArticle")
	public ResponseEntity<String> editArticle(@RequestBody Article articleEditReq){
		return articleServiceImpl.editArticle(articleEditReq);
	}
	
	@GetMapping("getMyArticle")
	public ResponseEntity<String> getMyArticles(@RequestParam String userId){
		return articleServiceImpl.getUserArticles(Integer.valueOf(userId));
	}

}
