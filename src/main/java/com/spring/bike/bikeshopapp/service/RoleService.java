package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.spring.bike.bikeshopapp.model.Role;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository repository;

    public List<Role> list() {
        return repository.findAll();
    }
    public Optional<Role> readRole(Long id) {
        return repository.findById(id);
    }
    public Optional<Role> readRole(String name) {
        return repository.findByName(name);
    }

}
