package com.spring.bike.bikeshopapp.model;

import com.spring.bike.bikeshopapp.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private @NotNull String name;
    private @NotNull Integer price;
    private @NotNull String description;
    private @NotNull boolean inStorage;
    private @NotNull Long categoryId;

    public ProductDTO(@NotNull String name, @NotNull Integer price, @NotNull String description, @NotNull boolean inStorage, @NotNull Long categoryId) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inStorage = inStorage;
        this.categoryId = categoryId;
    }
    public ProductDTO(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setPrice(product.getPrice());
        this.setDescription(product.getDescription());
        this.setInStorage(product.isInStorage());
        this.setCategoryId(product.getCategory().getId());
    }
}
