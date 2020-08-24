package com.webcrawl.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webcrawl.model.SavedArticle;

@Repository("savedarticles")
public interface SavedArticlesRepository extends JpaRepository<SavedArticle, Integer> {
	
	List<SavedArticle> findByEmail(String email);
	void deleteById(Integer id);
}