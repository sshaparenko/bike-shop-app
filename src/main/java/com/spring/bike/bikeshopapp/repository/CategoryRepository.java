package com.spring.bike.bikeshopapp.repository;

import com.spring.bike.bikeshopapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
