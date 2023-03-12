package com.spring.bike.bikeshopapp.controller.admin;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.dto.product.UpdateProductDTO;
import com.spring.bike.bikeshopapp.model.Category;
import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.model.Product;
import com.spring.bike.bikeshopapp.service.CategoryService;
import com.spring.bike.bikeshopapp.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
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
@RequestMapping("/api/v1/admin")
public class ProductBaseController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping("/products")
    public List<CreateProductDTO> getProducts() {
        return productService.listProducts();
    }
    @PostMapping("product/create")
    public ResponseEntity<ApiResponse> addProduct(@Valid @RequestBody CreateProductDTO createProductDTO) {
        Optional<Category> optionalCategory = categoryService.readCategory(createProductDTO.getCategoryId());
        if (optionalCategory.isEmpty())
            return new ResponseEntity<>(new ApiResponse(false, "category is invalid"), HttpStatus.NOT_FOUND);
        Category category = optionalCategory.get();
        productService.createProduct(createProductDTO, category);
        return new ResponseEntity<>(new ApiResponse(true, "new product was created successfully"), HttpStatus.CREATED);
    }
    @DeleteMapping("product/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        if (productService.readProduct(id).isPresent()) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(new ApiResponse(true, "product was deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "product id is invalid"), HttpStatus.NOT_FOUND);
    }
    @PatchMapping("product/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable @Positive Long id, @Valid @RequestBody UpdateProductDTO dto) {
        Optional<Product> productOptional = productService.readProduct(id);
        if (productOptional.isPresent()) {
            ApiResponse response = productService.updateProduct(dto, productOptional.get());
            if (!response.isSuccess())
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(true, "product id is invalid"), HttpStatus.NOT_FOUND);
    }
}