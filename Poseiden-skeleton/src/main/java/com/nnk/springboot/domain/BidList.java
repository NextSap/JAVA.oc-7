package com.nnk.springboot.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "bidlist")
public class BidList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer BidListId;
    @NotNull(message = "account:Null") @NotBlank(message = "account:Required")
    private String account;
    @NotNull(message = "type:Null") @NotBlank(message = "type:Required")
    private String type;
    @NotNull(message = "bidQuantity:Null")
    private Double bidQuantity;
    @NotNull(message = "askQuantity:Null")
    private Double askQuantity;
    @NotNull(message = "bid:Null")
    private Double bid;
    @NotNull(message = "ask:Null")
    private Double ask;
    @NotNull(message = "benchmark:Null") @NotBlank(message = "benchmark:Required")
    private String benchmark;
    @NotNull(message = "bidListDate:Null") @NotBlank(message = "bidListDate:Required")
    private Timestamp bidListDate;
    @NotNull(message = "commentary:Null")
    private String commentary;
    @NotNull(message = "security:Null") @NotBlank(message = "security:Required")
    private String security;
    @NotNull(message = "status:Null") @NotBlank(message = "status:Required")
    private String status;
    @NotNull(message = "trader:Null") @NotBlank(message = "trader:Required")
    private String trader;
    @NotNull(message = "book:Null") @NotBlank(message = "book:Required")
    private String book;
    @NotNull(message = "creationName:Null") @NotBlank(message = "creationName:Required")
    private String creationName;
    @NotNull(message = "creationDate:Null")
    private Timestamp creationDate;
    @NotNull(message = "revisionName:Null") @NotBlank(message = "revisionName:Required")
    private String revisionName;
    @NotNull(message = "revisionDate:Null")
    private Timestamp revisionDate;
    @NotNull(message = "dealName:Null") @NotBlank(message = "dealName:Required")
    private String dealName;
    @NotNull(message = "dealType:Null") @NotBlank(message = "dealType:Required")
    private String dealType;
    @NotNull(message = "sourceListId:Null") @NotBlank(message = "sourceListId:Required")
    private String sourceListId;
    @NotNull(message = "side:Null") @NotBlank(message = "side:Required")
    private String side;
}
