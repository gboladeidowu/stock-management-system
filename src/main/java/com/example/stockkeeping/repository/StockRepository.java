package com.example.stockkeeping.repository;

import com.example.stockkeeping.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {
    // perform actions by code
    Stock findByItemCode(String itemCode);
    boolean existsByItemCode(String itemCode);
    Iterable<Stock> deleteByItemCode(String itemCode);

}
