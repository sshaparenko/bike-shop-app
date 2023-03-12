package com.spring.bike.bikeshopapp.controller.admin;

import com.spring.bike.bikeshopapp.common.ApiResponse;
import com.spring.bike.bikeshopapp.model.Role;
import com.spring.bike.bikeshopapp.model.User;
import com.spring.bike.bikeshopapp.dto.UserDTO;
import com.spring.bike.bikeshopapp.service.RoleService;
import com.spring.bike.bikeshopapp.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
@RequiredArgsConstructor
@SecurityRequirement(name = "jwt")
@RequestMapping("/api/v1/admin/users")
public class ClientBaseController {
    private final UserService userService;
    private final RoleService roleService;

    @GetMapping()
    public List<UserDTO> getUsers() {
        return userService.listUsers();
    }

    @GetMapping("/user/{id}")
    public UserDTO getUser(@PathVariable Long id) {
        Optional<User> user = userService.readUser(id);
        return user.map(UserDTO::new).orElseGet(UserDTO::new);
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> addUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors())
            return new ResponseEntity<>(new ApiResponse(false, "bad request"), HttpStatus.BAD_REQUEST);
        if (userService.readUser(userDTO.getLoginName()).isPresent())
            return new ResponseEntity<>(new ApiResponse(false, "user with this login already exists"), HttpStatus.CONFLICT);
        Optional<Role> optionalRole = roleService.readRole(userDTO.getRoleId());
        if (optionalRole.isEmpty())
            return new ResponseEntity<>(new ApiResponse(false, "role is invalid"), HttpStatus.NOT_FOUND);
        userService.addUser(userDTO, optionalRole.get());
        return new ResponseEntity<>(new ApiResponse(true, "user was added successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        Optional<Role> optionalRole = roleService.readRole(userDTO.getRoleId());
        if (optionalRole.isEmpty())
            return new ResponseEntity<>(new ApiResponse(false, "role is invalid"), HttpStatus.NOT_FOUND);
        if (userService.readUser(id).isPresent()) {
            userService.updateUser(id, userDTO, optionalRole.get());
            return new ResponseEntity<>(new ApiResponse(true, "user was updated successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "user id is invalid"), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        if (userService.readUser(id).isPresent()) {
            userService.deleteUser(id);
            return new ResponseEntity<>(new ApiResponse(true, "user was deleted successfully"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse(false, "user id is invalid"), HttpStatus.NOT_FOUND);
    }
}
