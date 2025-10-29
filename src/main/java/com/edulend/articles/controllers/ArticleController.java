package com.edulend.articles.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edulend.articles.datasource.ArticleDatasource;
import com.edulend.articles.datasource.ArticleRepository;
import com.edulend.articles.models.Article;
import com.edulend.articles.useCases.ArticleUseCase;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "*")
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    @Autowired
    public ArticleController(ArticleRepository articleRepository) {
        ArticleDatasource datasource = new ArticleDatasource(articleRepository);
        this.articleUseCase = new ArticleUseCase(datasource);
    }

    // ✅ Crear artículo
    @PostMapping
    public ResponseEntity<Article> create(@RequestBody Article article) {
        Article created = articleUseCase.create(article);
        return ResponseEntity.ok(created);
    }

    // ✅ Obtener todos los artículos
    @GetMapping
    public ResponseEntity<List<Article>> getAll() {
        return ResponseEntity.ok(articleUseCase.getAll());
    }

    // ✅ Obtener artículo por ID
    @GetMapping("/by-id")
    public ResponseEntity<Article> getById(@RequestParam int article_id) {
        return articleUseCase.getById(article_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Obtener artículos por categoría
    @GetMapping("/by-category")
    public ResponseEntity<List<Article>> getByCategory(@RequestParam int category_id) {
        List<Article> articles = articleUseCase.getByCategory(category_id);
        if (articles.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(articles);
    }

    // ✅ Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<Article> update(@PathVariable int id, @RequestBody Article article) {
        Article updated = articleUseCase.update(id, article);
        return ResponseEntity.ok(updated);
    }

    // ✅ Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        articleUseCase.delete(id);
        return ResponseEntity.ok("Artículo eliminado correctamente.");
    }
}