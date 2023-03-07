package com.spring.bike.bikeshopapp.controller;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.entity.Category;
import com.spring.bike.bikeshopapp.model.ProductDTO;
import com.spring.bike.bikeshopapp.service.CategoryService;
import com.spring.bike.bikeshopapp.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * all products
 * delete product
 * update product
 * add product
 * */
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
@RequestMapping("/api/v1/admin/product")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping()
    public List<ProductDTO> getProducts() {
        return productService.listProducts();
    }
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());
        if (optionalCategory.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.NOT_FOUND);
        Category category = optionalCategory.get();
        productService.createProduct(productDTO, category);
        return new ResponseEntity<>(new ApiResponse(true, "new product was created successfully"), HttpStatus.CREATED);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        if (productService.readProduct(id).isPresent()) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(new ApiResponse(true, "product was deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product id is invalid"), HttpStatus.NOT_FOUND);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryService.readCategory(productDTO.getCategoryId());
        if (optionalCategory.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "invalid category"), HttpStatus.NOT_FOUND);
        Category category = optionalCategory.get();
        if (productService.readProduct(id).isPresent()) {
            productService.updateProduct(id, productDTO, category);
            return new ResponseEntity<>(new ApiResponse(true, "product was updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(true, "product id is invalid"), HttpStatus.NOT_FOUND);
    }
}
