package com.spring.bike.bikeshopapp.controller;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.entity.Category;
import com.spring.bike.bikeshopapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * TODO:
 * get all categories
 * delete category
 * update category
 * create category
 * */
@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService service;
    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> body = service.listCategories();
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category) {
        if (Objects.nonNull(service.readCategory(category.getName()))){
            return new ResponseEntity<>(new ApiResponse(false, "category already exists"), HttpStatus.CONFLICT);
        }
        service.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "category was created successfully"), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        if(Objects.nonNull(service.readCategory(id))) {
            service.deleteCategory(id);
            return new ResponseEntity<>(new ApiResponse(true, "category was deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "there is no category with such id"), HttpStatus.NOT_FOUND);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category) {
        if (Objects.nonNull(service.readCategory(category.getName()))) return new ResponseEntity<>(new ApiResponse(false, "category with such name already exists"), HttpStatus.CONFLICT);
        if (Objects.nonNull(service.readCategory(id))) {
            service.updateCategory(id, category);
            return new ResponseEntity<>(new ApiResponse(true, "category was updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "there is no category with such id"), HttpStatus.NOT_FOUND);
    }
}