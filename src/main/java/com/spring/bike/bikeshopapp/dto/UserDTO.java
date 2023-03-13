package com.spring.bike.bikeshopapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.bike.bikeshopapp.model.User;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private @NotBlank @Pattern(regexp = "[a-zA-Z]") String firstName;
    private @NotBlank @Pattern(regexp = "[a-zA-Z]") String lastName;
    private @NotBlank @Pattern(regexp = "[a-zA-Z0-9]") String loginName;
    private @NotBlank @Pattern(regexp = "[a-zA-Z0-9@#$%]") String password;
    private @NotBlank String address;
    private @NotBlank @Pattern(regexp = "^\\+\\d{3}$") String phone;
    private @NotNull Long roleId;
    public UserDTO() {

    }
    public UserDTO(User user) {
        this.setId(user.getId());
        this.setFirstName(user.getFirstName());
        this.setLastName(user.getLastName());
        this.setLoginName(user.getLoginName());
        this.setPassword(user.getPassword());
        this.setAddress(user.getAddress());
        this.setPhone(user.getPhone());
        this.setRoleId(user.getRole().getId());
    }
}

