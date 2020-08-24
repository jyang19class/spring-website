package com.webcrawl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webcrawl.model.Article;

@Repository
public interface UserRepository extends JpaRepository<Article, Integer>{
	
	List<Article> findTop10ByWebsite(String website);
	
	List<Article> findByWebsite(String website);
}