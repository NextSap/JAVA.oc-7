package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotNull(message = "username:Null") @NotBlank(message = "username:Required")
    private String username;
    @NotNull(message = "password:Null") @NotBlank(message = "password:Required")
    private String password;
    @NotNull(message = "fullname:Null") @NotBlank(message = "fullname:Required")
    private String fullname;
    @NotNull(message = "role:Null") @NotBlank(message = "role:Required")
    private String role;
}
