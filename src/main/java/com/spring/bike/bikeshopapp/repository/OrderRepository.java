package com.spring.bike.bikeshopapp.repository;

import com.spring.bike.bikeshopapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.user.id = :userId")
    List<Order> findAllByUserId(@Param("userId") Long id);
}
