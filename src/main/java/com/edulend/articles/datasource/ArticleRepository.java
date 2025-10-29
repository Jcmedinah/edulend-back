package com.edulend.articles.datasource;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edulend.articles.models.Article;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByCategory_CategoryId(int categoryId);
}