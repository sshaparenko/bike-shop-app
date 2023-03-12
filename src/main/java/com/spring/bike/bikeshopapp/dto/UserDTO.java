package com.spring.bike.bikeshopapp.dto;

import com.spring.bike.bikeshopapp.model.User;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String loginName;
    private String password;
    private String address;
    private String phone;
    private Long roleId;
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

