package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NotNull(message = "curveId:Null") @NotBlank(message = "curveId:Required")
    Integer curveId;
    @NotNull(message = "asOfDate:Null")
    private Timestamp asOfDate;
    @NotNull(message = "term:Null")
    private Double term;
    @NotNull(message = "value:Null")
    private Double value;
    @NotNull(message = "creationDate:Null")
    private Timestamp creationDate;
}
