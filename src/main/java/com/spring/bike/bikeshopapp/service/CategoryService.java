package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.model.Category;
import com.spring.bike.bikeshopapp.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;

    public List<Category> listCategories() {
        return repository.findAll();
    }

    public Category readCategory(String name) {
        return repository.findByName(name);
    }
    public Optional<Category> readCategory(Long id) {
        return repository.findById(id);
    }

    public void createCategory(Category category) {
        repository.save(category);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }

    public void updateCategory(Long id, Category category) {
        Category toBeUpdatedCategory = repository.findById(id).get();
        toBeUpdatedCategory.setName(category.getName());
        repository.save(toBeUpdatedCategory);
    }
}
