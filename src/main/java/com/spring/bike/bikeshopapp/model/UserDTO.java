package com.spring.bike.bikeshopapp.model;

import com.spring.bike.bikeshopapp.entity.User;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO {
    private Long id;
    private @NotNull String firstName;
    private @NotNull String lastName;
    private @NotNull String loginName;
    private @NotNull String password;
    private @NotNull String address;
    private @NotNull String phone;
    private @NotNull Long roleId;

    public UserDTO() {
    }

    public UserDTO(@NotNull String firstName, @NotNull String lastName, @NotNull String loginName, String password, String address, String phone, Long roleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.roleId = roleId;
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

