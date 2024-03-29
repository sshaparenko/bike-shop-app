package com.spring.bike.bikeshopapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull String name;
    private @NotNull @Positive Integer price;
    private @NotNull String description;
    @Column(name = "in_storage")
    private @NotNull boolean inStorage;
    @Column(name = "image")
    private byte[] imageBytes;
    private String color;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    Category category;
    @JsonIgnore
    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    Set<Order> orders;
    public Product(){

    }
}
