package com.example.stockkeeping.service;

import com.example.stockkeeping.model.Stock;
import com.example.stockkeeping.repository.StockRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class StockService {

    private final StockRepository stockRepository;

    // fetch a list of all items in stock from database
    public List<Stock> findAllStock(){
        return stockRepository.findAll();
    }

    // fetch stock by item code from database
    public Stock findItemByCode(String itemCode){
        if (!stockRepository.existsByItemCode(itemCode)) {
            throw new IllegalStateException("Product with code "+itemCode+" not found.");
        }
        return stockRepository.findByItemCode(itemCode);
    }

    // add a new item to the stock in database
    public Stock saveStock(Stock stock){
        if (!stockRepository.existsByItemCode(stock.getItemCode())){
            throw new IllegalStateException("Item with code " + stock.getItemCode() + " already exists, make an update instead.");
        }
        Stock newStock = stockRepository.save(stock);
        newStock.setItemCode(newStock.getItemCode().toLowerCase());
        newStock.setItemName(newStock.getItemName().toLowerCase());
        newStock.setRemainingQuantity(stock.getItemQuantity());
        stockRepository.save(newStock);
        return newStock;
    }

    // update previous stock from database
    public void updateStock(String itemCode, Stock stock){
        if (!stockRepository.existsByItemCode(itemCode)){
            throw new IllegalStateException("Item with code " + itemCode + " not found.");
        }
        stockRepository.deleteByItemCode(itemCode);
        stockRepository.save(stock);
    }

    // delete item by item code from database
    public Iterable<Stock> deleteStockByItemCode(String itemCode){
        if (!stockRepository.existsByItemCode(itemCode)){
            throw new IllegalStateException("Item with code " + itemCode + " not found.");
        }
        return stockRepository.deleteByItemCode(itemCode);

    }
    // delete all items in stock from database
    public void deleteStocks(){
        stockRepository.deleteAll();
    }

}
