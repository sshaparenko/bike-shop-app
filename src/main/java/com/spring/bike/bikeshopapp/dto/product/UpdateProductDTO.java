package com.spring.bike.bikeshopapp.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
public class UpdateProductDTO {
    private String name;
    private @Positive Integer price;
    private String description;
    private Boolean inStorage;
    private Long categoryId;
}