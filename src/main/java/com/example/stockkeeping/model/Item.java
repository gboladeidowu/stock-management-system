package com.example.stockkeeping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @Column(name = "item_code")
    private String itemCode;

    @NotEmpty
    @Column(name = "item_name")
    private String itemName;

    @NotEmpty
    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_quantity")
    private Integer itemQuantity;

    @Column(name = "date")
    private LocalDate date;
}
