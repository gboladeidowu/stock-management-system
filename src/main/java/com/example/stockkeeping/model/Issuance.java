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
@Data
@Builder
@Entity
@Table(name = "issuance")
public class Issuance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String itemCode;
    @NotEmpty
    private String itemName;
    @NotEmpty
    private String itemDescription;

    private int issuedQuantity;
    @NotEmpty
    private String station;
    @NotEmpty
    private String issuer;
    private LocalDate issuedDate;




}
