package com.eduhub.eduhubapp.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eduhub.eduhubapp.Dao.ArticleDao;
import com.eduhub.eduhubapp.entity.Article;
import com.google.gson.Gson;

@Service
public class EduhubArticleServiceImpl implements EduhubArticleService{
	
	@Autowired
	Article article;
	
	@Autowired
	ArticleDao articleDao;

	@Override
	public ResponseEntity<String> createNewArticle(Article articleDetails) {
		// TODO Auto-generated method stub
		article.setUserId(articleDetails.getUserId());
		article.setDescription(articleDetails.getDescription());
		article.setPublishedDate(articleDetails.getPublishedDate());
		article.setTitle(articleDetails.getTitle());
		//article.setTags(articleDetails.getTags());
		article.setSubtitle(articleDetails.getSubtitle());
		article.setImageUrl(articleDetails.getImageUrl());
		try {
			articleDao.save(article);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("articleId",articleDao.findByTitle(articleDetails.getTitle()).getArticleId());
			ob.put("message", "Created the article");
			Gson gson=new Gson();
			String json=gson.toJson(ob);
			return new ResponseEntity<>(json,HttpStatus.OK);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Error occured.Check logs",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//return null;
	}

	@Override
	public ResponseEntity<String> viewAllArticles() {
		// TODO Auto-generated method stub
		Map<Object,Object> ob=new HashMap<>();
		ob.put("articleList",articleDao.findAll());
		ob.put("message", "Fetched the articles");
		Gson gson=new Gson();
		String json=gson.toJson(ob);
		return new ResponseEntity<>(json,HttpStatus.OK);
		//return null;
	}

	@Override
	public ResponseEntity<String> viewArticle(Integer articleId) {
		// TODO Auto-generated method stub
		try {
			Article fecthArticle=articleDao.findByArticleId(articleId);
			Gson gson=new Gson();
			String json=gson.toJson(fecthArticle);
			return new ResponseEntity<>(json,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Override
	public ResponseEntity<String> editArticle(Article articleEditReq){
		try {
			Article articleFromDB=articleDao.findByArticleId(articleEditReq.getArticleId());
			if(articleEditReq.getDescription()!=null) {
				articleFromDB.setDescription(articleEditReq.getDescription());
			}
			if(articleEditReq.getTitle()!=null) {
				articleFromDB.setTitle(articleEditReq.getTitle());
			}
			/*if(articleEditReq.getTags()!=null) {
				articleFromDB.setTags(articleEditReq.getTags());
			}*/
			if(articleEditReq.getSubtitle()!=null) {
				articleFromDB.setSubtitle(articleEditReq.getSubtitle());
			}
			if(articleEditReq.getImageUrl()!=null) {
				articleFromDB.setImageUrl(articleEditReq.getImageUrl());
			}
			articleDao.save(articleFromDB);
			Map<Object,Object> ob=new HashMap<>();
			ob.put("message", "Success");
			
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public ResponseEntity<String> getUserArticles(Integer userId){
		List<Article> userArticleList=articleDao.findByUserId(userId);
		//return new ResponseEntity<>(userArticleList,HttpStatus.OK);
		List<Map<Object,Object>> responseArray=new ArrayList<>();
		if(userArticleList!=null) {
			for(Article userArticle:userArticleList) {
				Map<Object,Object> resOb=new HashMap<>();
				resOb.put("articleId",userArticle.getArticleId());
				resOb.put("ownerId", userArticle.getUserId());
				resOb.put("title", userArticle.getTitle());
				resOb.put("description", userArticle.getDescription());
				/*resOb.put("tags", userArticle.getTags());*/
				resOb.put("subtitle", userArticle.getSubtitle());
				resOb.put("imageUrl", userArticle.getImageUrl());
				resOb.put("publishedDate", userArticle.getPublishedDate().toString());
				responseArray.add(resOb);
			}
			
			Map<Object,Object> ob=new HashMap<>();
			ob.put("articleList", responseArray);
			ob.put("message", "success");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.OK);
		}
		else {
			Map<Object,Object> ob=new HashMap<>();
			ob.put("message","No articles found for user");
			return new ResponseEntity<>(new Gson().toJson(ob),HttpStatus.UNAUTHORIZED);
		}
	}

}
