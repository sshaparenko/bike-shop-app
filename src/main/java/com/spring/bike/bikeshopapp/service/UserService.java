package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.model.Role;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.dto.UserDTO;
import com.spring.bike.bikeshopapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public List<UserDTO> listUsers() {
        return repository.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }
    public Optional<User> readUser(Long id) {
        return repository.findById(id);
    }

    public Optional<User> readUser(String loginName) {
        return repository.findByLoginName(loginName);
    }

    public void addUser(UserDTO userDTO, Role role) {
        repository.save(DTOtoUser(userDTO, role));
    }
    public void addUser(User user) {
        repository.save(user);
    }

    private User DTOtoUser(UserDTO userDTO, Role role) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setLoginName(userDTO.getLoginName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setRole(role);
        return user;
    }
    public void updateUser(Long id, UserDTO userDTO, Role role) {
        User user = DTOtoUser(userDTO, role);
        user.setId(id);
        repository.save(user);
    }
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
}