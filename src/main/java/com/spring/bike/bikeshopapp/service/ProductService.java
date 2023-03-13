package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.dto.product.UpdateProductDTO;
import com.spring.bike.bikeshopapp.model.Category;
import com.spring.bike.bikeshopapp.model.Product;
import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.repository.CategoryRepository;
import com.spring.bike.bikeshopapp.repository.ProductRepository;
import com.spring.bike.bikeshopapp.service.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductMapper {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public List<CreateProductDTO> listProducts() {
        return productRepository.findAll(Sort.by("id").ascending()).stream().map(p -> new CreateProductDTO(p)).collect(Collectors.toList());
    }
    public void createProduct(CreateProductDTO createProductDTO, Category category) {
        productRepository.save(DTOtoProduct(createProductDTO, category));
    }

    public Optional<Product> readProduct(Long id) {
        return productRepository.findById(id);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
    public ApiResponse updateProduct(UpdateProductDTO updateProductDTO, Product product) {
        if (updateProductDTO.getCategoryId() != null) {
            Optional<Category> category = categoryRepository.findById(updateProductDTO.getCategoryId());
            if (category.isEmpty()) return new ApiResponse(false, "invalid category");
            product.setCategory(category.get());
        }
        if (updateProductDTO.getName() != null) {
            product.setName(updateProductDTO.getName());
        }
        if (updateProductDTO.getDescription() != null) {
            product.setDescription(updateProductDTO.getDescription());
        }
        if (updateProductDTO.getPrice() != null) {
            product.setPrice(updateProductDTO.getPrice());
        }
        if (updateProductDTO.getInStorage() != null) {
            product.setInStorage(updateProductDTO.getInStorage());
        }
        productRepository.save(product);
        return new ApiResponse(true, "product was updated successfully");
    }
}