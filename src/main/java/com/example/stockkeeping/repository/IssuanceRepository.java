package com.example.stockkeeping.repository;

import com.example.stockkeeping.model.Issuance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssuanceRepository extends JpaRepository<Issuance, Long> {
    // perform actions by code
    Issuance findByItemCode(String itemCode);
    boolean existsByItemCode(String itemCode);
    Iterable<Issuance> deleteByItemCode(String itemCode);

    // peform actions by date
    boolean existsByIssuedDate(LocalDate issuedDate);
    List<Issuance> findByIssuedDate(LocalDate issuedDate);



}
