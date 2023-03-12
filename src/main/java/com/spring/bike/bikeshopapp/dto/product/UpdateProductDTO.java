package com.spring.bike.bikeshopapp.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UpdateProductDTO {

    private Long id;
    private @NotNull String name;
    private @NotNull @Min(value = 0) Integer price;
    private @NotNull String description;
    private @NotNull boolean inStorage;
    private @NotNull Long categoryId;
}