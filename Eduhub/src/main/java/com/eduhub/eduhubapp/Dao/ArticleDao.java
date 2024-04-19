package com.eduhub.eduhubapp.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eduhub.eduhubapp.entity.Article;

@Repository
public interface ArticleDao extends JpaRepository<Article,Integer>{
	
	Article findByTitle(String title);

	Article findByArticleId(Integer articleId);
	
	@Query("SELECT a FROM Article a where a.userId=:ownerId")
	List<Article> findByUserId(Integer ownerId);

	//List<Article> findByUserId(Integer userId);

}
