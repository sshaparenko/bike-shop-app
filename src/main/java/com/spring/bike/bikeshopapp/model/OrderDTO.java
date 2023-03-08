package com.spring.bike.bikeshopapp.model;

import com.spring.bike.bikeshopapp.entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.sql.Date;
@Data
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "year-month-day")
    @Past(message = "invalid date")
    private Date creationDate;
    @DateTimeFormat(pattern = "year-month-day")
    @Future(message = "invalid date")
    private Date deliveryDate;
    @NotNull
    @Min(value = 0, message = "invalid price")
    private Integer price;
    @NotNull
    private Long userId;
    public OrderDTO(Order order) {
        this.setId(order.getId());
        this.setCreationDate(order.getCreationDate());
        this.setDeliveryDate(order.getDeliveryDate());
        this.setPrice(order.getPrice());
        this.setUserId(order.getUser().getId());
    }
}