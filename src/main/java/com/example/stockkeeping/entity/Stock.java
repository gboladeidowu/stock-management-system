package com.example.stockkeeping.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stock")
public class Stock{
    @Id
    private String itemCode;
    private String itemName;
    private Integer itemQuantity;
    private LocalDate date = LocalDate.now();

}





