package com.spring.bike.bikeshopapp.dto.order;

import com.spring.bike.bikeshopapp.dto.product.CreateProductDTO;
import com.spring.bike.bikeshopapp.model.Order;
import lombok.Data;

import java.sql.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class OrderDetailsDTO {
    private Long id;
    private Date creationDate;
    private Date deliveryDate;
    private Integer price;
    private Set<CreateProductDTO> products;

    public OrderDetailsDTO(Order order) {
        this.setId(order.getId());
        this.setCreationDate(order.getCreationDate());
        this.setDeliveryDate(order.getDeliveryDate());
        this.setPrice(order.getPrice());
        this.setProducts(order.getProducts().stream().map(CreateProductDTO::new).collect(Collectors.toSet()));
    }
}
