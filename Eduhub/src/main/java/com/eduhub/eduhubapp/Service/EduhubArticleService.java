package com.eduhub.eduhubapp.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.entity.Article;

@Service
public interface EduhubArticleService {
	
	ResponseEntity<String> createNewArticle(Article articleDetails);
	ResponseEntity<String> viewAllArticles();
	ResponseEntity<String> viewArticle(Integer articleId);
	ResponseEntity<String> editArticle(Article articleEditReq);

}
