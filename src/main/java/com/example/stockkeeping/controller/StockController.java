package com.example.stockkeeping.controller;

import com.example.stockkeeping.entity.Stock;
import com.example.stockkeeping.service.StockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/stocks")
public class StockController {

    private final StockService stockService;

    // display a list of all items in stock
    @GetMapping
    public List<Stock> getStocks(){
        return stockService.findAllStock();
    }

    // display item by item code
    @GetMapping("/{itemCode}")
    public Optional<Stock> getByItemCode(@PathVariable String itemCode){
        return stockService.findItemCode(itemCode);
    }

    // add a new item to the stock
    @PostMapping("/new")
    public void newStock(@Valid @RequestBody Stock stock){
        stockService.saveStock(stock);
    }
}
