package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NotNull(message = "moodysRating:Null") @NotBlank(message = "moodysRating:Required")
    private String moodysRating;
    @NotNull(message = "sandPRating:Null") @NotBlank(message = "sandPRating:Required")
    private String sandPRating;
    @NotNull(message = "fitchRating:Null") @NotBlank(message = "fitchRating:Required")
    private String fitchRating;
    @NotNull(message = "orderNumber:Null")
    Integer orderNumber;
}
