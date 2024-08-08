package com.example.stockkeeping.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    private Integer id;

    @NotEmpty
    String itemCode;

    @NotEmpty
    String itemName;

    @NotBlank
    String itemDescription;

    Integer quantity;
}
