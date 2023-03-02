package com.spring.bike.bikeshopapp.service;

import com.spring.bike.bikeshopapp.entity.User;
import com.spring.bike.bikeshopapp.model.UserDTO;
import com.spring.bike.bikeshopapp.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository repository;
    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    public List<UserDTO> listUsers() {
        return repository.findAll().stream().map(u -> new UserDTO(u)).collect(Collectors.toList());
    }
    public Optional<User> readUser(Long id) {
        return repository.findById(id);
    }

    public Optional<User> readUser(String loginName) {
        return repository.findByLoginName(loginName);
    }

    public void addUser(UserDTO userDTO) {
        repository.save(DTOtoUser(userDTO));
    }

    private User DTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setLoginName(userDTO.getLoginName());
        user.setPassword(hashPassword(userDTO.getPassword()));
        user.setAddress(userDTO.getAddress());
        user.setPhone(userDTO.getPhone());
        user.setAdminRights(userDTO.getAdminRights());
        return user;
    }
    //try builder here, only required fields should be changed
    public void updateUser(Long id, UserDTO userDTO) {
        User user = DTOtoUser(userDTO);
        user.setId(id);
        repository.save(user);
    }
    public void deleteUser(Long id) {
        repository.deleteById(id);
    }
    private String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public boolean checkPassword(String password, String password1) {
        return BCrypt.checkpw(password, password1);
    }
}
