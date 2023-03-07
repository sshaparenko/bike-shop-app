package com.spring.bike.bikeshopapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private @NotNull String name;
    private @NotNull Integer price;
    private @NotNull String description;
    @Column(name = "in_storage")
    private @NotNull boolean inStorage;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_category", nullable = false)
    Category category;

    public Product(String name, Integer price, String description, boolean inStorage, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.inStorage = inStorage;
        this.category = category;

    }
}
