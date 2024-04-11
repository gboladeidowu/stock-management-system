package com.example.stockkeeping.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;


@Data
@Entity
@Table(name = "stock")
public class Stock{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotEmpty
    @Column(unique = true)
    private String itemCode;
    @NotEmpty
    private String itemName;
    private int itemQuantity;
    private int remainingQuantity = itemLeft();
    private LocalDate date = LocalDate.now();


    public int itemLeft () {
        Issuance issuance = new Issuance();
        return (getItemQuantity() - issuance.getIssuedQuantity());
    }
}





