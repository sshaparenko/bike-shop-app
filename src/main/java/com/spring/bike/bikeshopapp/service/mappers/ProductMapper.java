package com.spring.bike.bikeshopapp.service.mappers;

import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.model.Category;
import com.spring.bike.bikeshopapp.model.Product;

public interface ProductMapper {
    default Product DTOtoProduct(CreateProductDTO createProductDTO, Category category) {
        return Product.builder()
                .name(createProductDTO.getName())
                .price(createProductDTO.getPrice())
                .description(createProductDTO.getDescription())
                .inStorage(createProductDTO.isInStorage())
                .imageBytes(createProductDTO.getImageBytes())
                .color(createProductDTO.getColor())
                .category(category)
                .build();
    }
}
