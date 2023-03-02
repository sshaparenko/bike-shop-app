package com.spring.bike.bikeshopapp.repository;

import com.spring.bike.bikeshopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLoginName(String loginName);
}