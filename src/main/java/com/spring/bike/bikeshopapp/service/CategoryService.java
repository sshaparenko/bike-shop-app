package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.entity.Category;
import com.spring.bike.bikeshopapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository repository;
    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

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
