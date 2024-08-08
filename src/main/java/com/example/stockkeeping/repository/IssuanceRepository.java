package com.example.stockkeeping.repository;

import com.example.stockkeeping.model.Issuance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssuanceRepository extends JpaRepository<Issuance, Long> {

    List<Issuance> findIssuanceByIssuedDateOrStationContainingIgnoreCaseOrItemCodeContainingIgnoreCase(LocalDate issuedDate, String station, String itemCode);
}
