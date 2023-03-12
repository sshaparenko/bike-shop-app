package com.spring.bike.bikeshopapp.dto.order;

import com.spring.bike.bikeshopapp.model.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Date creationDate;
    private Date deliveryDate;
    private Integer price;
    private Long userId;
    public OrderDTO(Order order) {
        this.setId(order.getId());
        this.setCreationDate(order.getCreationDate());
        this.setDeliveryDate(order.getDeliveryDate());
        this.setPrice(order.getPrice());
        this.setUserId(order.getUser().getId());
    }
}