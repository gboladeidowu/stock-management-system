package com.example.stockkeeping.dto;

public record IssuanceDTO(String itemCode,
                          Integer issuedQuantity,
                          String station) {
}
