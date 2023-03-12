package com.spring.bike.bikeshopapp.repository;

import com.spring.bike.bikeshopapp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
