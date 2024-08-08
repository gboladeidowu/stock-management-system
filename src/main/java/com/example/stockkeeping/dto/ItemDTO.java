package com.example.stockkeeping.dto;

public record ItemDTO(String itemCode,
                      String itemName,
                      String itemDescription,
                      Integer itemQuantity) {
}
