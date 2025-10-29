package com.edulend.articles.datasource;

import java.util.List;
import java.util.Optional;

import com.edulend.articles.models.Article;

public class ArticleDatasource {

    private final ArticleRepository articleRepository;

    public ArticleDatasource(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article save(Article article) {
        return articleRepository.save(article);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Optional<Article> findById(int id) {
        return articleRepository.findById(id);
    }

    public List<Article> findByCategory(int categoryId) {
        // 🔥 Aquí el cambio correcto:
        return articleRepository.findByCategory_CategoryId(categoryId);
    }

    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }
}
