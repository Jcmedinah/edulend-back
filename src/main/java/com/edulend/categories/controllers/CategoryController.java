package com.edulend.categories.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edulend.categories.models.Category;
import com.edulend.categories.useCases.CategoryUseCase;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    private final CategoryUseCase categoryUseCase;

    public CategoryController(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestParam String name) {
        Category category = new Category();
        category.setCategoryName(name);
        Category created = categoryUseCase.createCategory(category);
        return ResponseEntity.ok(created);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryUseCase.getAllCategories());
    }

    @GetMapping("/by-id")
    public ResponseEntity<Category> getCategoryById(@RequestParam int category_id) {
        Optional<Category> category = categoryUseCase.getCategoryById(category_id);
        return category.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<Category> getCategoryByName(@RequestParam String name) {
        Optional<Category> category = categoryUseCase.getCategoryByName(name);
        return category.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestParam String name) {
        Category existingCategory = categoryUseCase.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
        Category updated = categoryUseCase.updateCategory(existingCategory);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryUseCase.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}