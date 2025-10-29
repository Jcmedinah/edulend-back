package com.edulend.articles.useCases;

import java.util.List;
import java.util.Optional;

import com.edulend.articles.datasource.ArticleDatasource;
import com.edulend.articles.models.Article;

public class ArticleUseCase {

    private final ArticleDatasource articleDatasource;

    public ArticleUseCase(ArticleDatasource articleDatasource) {
        this.articleDatasource = articleDatasource;
    }

    public Article create(Article article) {
        return articleDatasource.save(article);
    }

    public List<Article> getAll() {
        return articleDatasource.findAll();
    }

    public Optional<Article> getById(int id) {
        return articleDatasource.findById(id);
    }

    public List<Article> getByCategory(int category_id) {
        return articleDatasource.findByCategory(category_id);
    }

    public Article update(int id, Article articleData) {
        Article existing = articleDatasource.findById(id)
                .orElseThrow(() -> new RuntimeException("Artículo no encontrado"));

        existing.setName(articleData.getName());
        existing.setDescription(articleData.getDescription());
        existing.setCategory(articleData.getCategory());
        existing.setQuantity_available(articleData.getQuantity_available());

        return articleDatasource.save(existing);
    }

    public void delete(int id) {
        articleDatasource.deleteById(id);
    }
}