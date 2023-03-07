package com.spring.bike.bikeshopapp.controller;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.entity.Category;
import com.spring.bike.bikeshopapp.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * get all categories
 * delete category
 * update category
 * create category
 * */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
@RequestMapping("/api/v1/admin/category")
public class CategoryController {
    private final CategoryService service;
    @GetMapping()
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
        if(service.readCategory(id).isPresent()) {
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
