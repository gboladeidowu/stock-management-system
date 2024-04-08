package com.example.stockkeeping.service;

import com.example.stockkeeping.entity.Stock;
import com.example.stockkeeping.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StockService {


    private final StockRepository stockRepository;


    // fetch a list of all items in stock
    public List<Stock> findAllStock(){
        return stockRepository.findAll();
    }

    // fetch stock by item code
    public Optional<Stock> findItemCode(String itemCode){
        if (!stockRepository.existsById(itemCode)){
           throw new IllegalStateException("Product with code "+itemCode+" not found.");
        }
        return stockRepository.findById(itemCode);
    }

    // add a new item to the stock list
    public void saveStock(Stock stock){
        if (stockRepository.existsById(stock.getItemCode())){
            throw new IllegalStateException("Item with code " + stock.getItemCode() + " already exists, make an update instead.");
        }
        stockRepository.save(stock);
    }
}
