package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.entity.Category;
import com.spring.bike.bikeshopapp.entity.Product;
import com.spring.bike.bikeshopapp.model.ProductDTO;
import com.spring.bike.bikeshopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository repository;
    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    public List<ProductDTO> listProducts() {
        return repository.findAll(Sort.by("id").ascending()).stream().map(p -> new ProductDTO(p)).collect(Collectors.toList());
    }
    public void createProduct(ProductDTO productDTO, Category category) {
        repository.save(DTOtoProduct(productDTO, category));
    }
    public Optional<Product> readProduct(Long id) {
        return repository.findById(id);
    }
    public void deleteProduct(Long id) {
        repository.deleteById(id);
    }
    public void updateProduct(Long id, ProductDTO productDTO, Category category) {
        Product product = DTOtoProduct(productDTO, category);
        product.setId(id);
        repository.save(product);
    }
    private Product DTOtoProduct(ProductDTO productDTO, Category category) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setInStorage(productDTO.isInStorage());
        product.setCategory(category);
        return product;
    }
}
