package com.spring.bike.bikeshopapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "year-month-day")
    @Past(message = "invalid date")
    @Column(name = "creation_date")
    private Date creationDate;
    @DateTimeFormat(pattern = "year-month-day")
    @Future(message = "invalid date")
    @Column(name = "delivery_date")
    private Date deliveryDate;
    @NotNull
    @Min(value = 0, message = "invalid price")
    private Integer price;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    User user;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "order_has_product",
            joinColumns = @JoinColumn(name = "id_order"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    Set<Product> products;
}