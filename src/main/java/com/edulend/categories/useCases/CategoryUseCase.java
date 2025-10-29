package com.edulend.categories.useCases;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.edulend.categories.datasource.CategoryRepository;
import com.edulend.categories.models.Category;

@Service
public class CategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        // Validar que no exista la categoría
        categoryRepository.findByCategoryName(category.getCategoryName())
            .ifPresent(c -> { throw new RuntimeException("Category already exists"); });
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(int id) {
        return categoryRepository.findById(id);
    }

    public Optional<Category> getCategoryByName(String name) {
        return categoryRepository.findByCategoryName(name);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }
}