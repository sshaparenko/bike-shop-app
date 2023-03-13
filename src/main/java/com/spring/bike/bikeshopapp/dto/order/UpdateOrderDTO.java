package com.spring.bike.bikeshopapp.dto.order;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Date;
@Data
public class UpdateOrderDTO {
    @DateTimeFormat(pattern = "year-month-day")
    @Past(message = "invalid date")
    private Date creationDate;
    @DateTimeFormat(pattern = "year-month-day")
    @Future(message = "invalid date")
    private Date deliveryDate;
    @Positive
    private Integer price;
}
