package com.spring.bike.bikeshopapp.controller;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.entity.User;
import com.spring.bike.bikeshopapp.model.UserDTO;
import com.spring.bike.bikeshopapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * all users
 * get user
 * add user
 * delete user
 * update user
 * login
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<UserDTO> getUsers() {
        return service.listUsers();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        Optional<User> user = service.readUser(id);
        if (user.isPresent()) {
            return user.get();
        }
        return new User();
    }
    @GetMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@RequestParam String loginName, @RequestParam String password) {
        Optional<User> user = service.readUser(loginName);
        if (user.isEmpty()) return new ResponseEntity<>(new ApiResponse(false, "your login name is invalid"), HttpStatus.NOT_FOUND);
        if (service.checkPassword(password, user.get().getPassword()) == false) return new ResponseEntity<>(new ApiResponse(false, "password is invalid"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(true, "you are logged in"), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDTO userDTO) {
        if (service.readUser(userDTO.getLoginName()).isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "user with this login already exists"), HttpStatus.CONFLICT);
        }
        service.addUser(userDTO);
        return new ResponseEntity<>(new ApiResponse(true, "user was added successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        if (service.readUser(id).isPresent()) {
            service.updateUser(id, userDTO);
            return new ResponseEntity<>(new ApiResponse(true, "user was updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "user id is invalid"), HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        if (service.readUser(id).isPresent()) {
            service.deleteUser(id);
            return new ResponseEntity<>(new ApiResponse(true, "user was deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "user id is invalid"), HttpStatus.NOT_FOUND);
    }
}
