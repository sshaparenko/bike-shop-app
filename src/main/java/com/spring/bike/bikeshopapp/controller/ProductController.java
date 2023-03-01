package com.spring.bike.bikeshopapp.controller;

import com.spring.bike.bikeshopapp.model.ProductDTO;
import com.spring.bike.bikeshopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * all products
 * delete product
 * update product
 * add product
 * */
@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService service;
    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/")
    public List<ProductDTO> getProducts() {
        return service.listProducts();
    }
    
}
