package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NotNull(message = "name:Null") @NotBlank(message = "name:Required")
    private String name;
    @NotNull(message = "description:Null") @NotBlank(message = "description:Required")
    private String description;
    @NotNull(message = "json:Null") @NotBlank(message = "json:Required")
    private String json;
    @NotNull(message = "template:Null") @NotBlank(message = "template:Required")
    private String template;
    @NotNull(message = "sqlStr:Null") @NotBlank(message = "sqlStr:Required")
    private String sqlStr;
    @NotNull(message = "sqlPart:Null") @NotBlank(message = "sqlPart:Required")
    private String sqlPart;
}
