package com.edulend.categories.datasource;

import java.util.List;
import java.util.Optional;

import com.edulend.categories.models.Category;

public class CategoryDatasource {

    private final CategoryRepository categoryRepository;

    public CategoryDatasource(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> findByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public void deleteById(int id) {
        categoryRepository.deleteById(id);
    }
}