package com.example.stockkeeping.service;


import com.example.stockkeeping.entity.Issuance;
import com.example.stockkeeping.entity.Stock;
import com.example.stockkeeping.repository.IssuanceRepository;
import com.example.stockkeeping.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class IssuanceService {

    private final IssuanceRepository issuanceRepository;
    private final StockRepository stockRepository;

    // fetch all items issued out
    public List<Issuance> findAll(){
        return issuanceRepository.findAll();
    }

    // fetch items issued out by item code
    public Issuance findByItemCode (String itemCode){
        if (issuanceRepository.existsByItemCode(itemCode)){
            throw new IllegalStateException("No record with item code " + itemCode + " found");
        }
        return issuanceRepository.findByItemCode(itemCode);
    }

    // fetch items issued out by date
    public List<Issuance> findByIssuedDate(LocalDate date){
        if (!issuanceRepository.existsByIssuedDate(date)){
            throw new IllegalStateException("No record with date " + date + " found");
        }
        return issuanceRepository.findByIssuedDate(date);
    }

    // create a new issuance record
    public void save(Issuance issuance){
        Issuance issued = issuanceRepository.save(issuance);
        issued.setItemCode(issuance.getItemCode().toLowerCase());
        issued.setItemName(issuance.getItemName().toLowerCase());
        issued.setStation(issuance.getStation().toLowerCase());
        issued.setIssuer(issuance.getIssuer().toLowerCase());
        issuanceRepository.save(issued);

        // update remaining quantity in stock
        Stock stock = stockRepository.findByItemCode(issued.getItemCode());
        int diff = stock.getRemainingQuantity() - issuance.getIssuedQuantity();
        stock.setRemainingQuantity(diff);
        stockRepository.save(stock);
    }

    // update an issued record
    public void updateIssuanceByItemCode(String itemCode, Issuance issuance){
        if (!issuanceRepository.existsByItemCode(itemCode)){
            throw new IllegalStateException("No record with item code " + itemCode + " found");
        }
        // update remaining quantity in stock
        Issuance issued = issuanceRepository.findByItemCode(itemCode);
        Stock stock = stockRepository.findByItemCode(itemCode);
        int diff = issued.getIssuedQuantity() - issuance.getIssuedQuantity();
        int updateStock = stock.getRemainingQuantity() + diff;
        stock.setRemainingQuantity(updateStock);
        stockRepository.save(stock);

        issuanceRepository.deleteByItemCode(itemCode);
        issuanceRepository.save(issuance);
    }

    // delete an issuance record by item code
    public void deleteIssuanceByItemCode(String itemCode){
        if (!issuanceRepository.existsByItemCode(itemCode)){
            throw new IllegalStateException("No record with item code " + itemCode + " found");
        }
        issuanceRepository.deleteByItemCode(itemCode);
    }

}
