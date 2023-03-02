package com.spring.bike.bikeshopapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "login_name")
    private String loginName;
    private String password;
    private String address;
    private String phone;
    @Column(name = "admin")
    private Boolean adminRights;

    public User() {

    }
    public User (String firstName, String lastName, String loginName, String password, String address, String phone, Boolean adminRights) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.adminRights = adminRights;
    }
}
