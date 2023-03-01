package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.model.ProductDTO;
import com.spring.bike.bikeshopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductDTO> listProducts() {
        return repository.findAll().stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
    }
}
