package com.example.stockkeeping.controller;

import com.example.stockkeeping.model.Issuance;
import com.example.stockkeeping.service.IssuanceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@RestController
@RequestMapping("api/record")
public class IssuanceController {

    private final IssuanceService issuanceService;

    // display all issuance records
    @GetMapping
    public List<Issuance> getAllRecords() {
        return issuanceService.findAll();
    }

    // display record by item code
    @GetMapping("/code/{itemCode}")
    public Issuance findByItemCode(@PathVariable String itemCode){
        return issuanceService.findByItemCode(itemCode);
    }

    // display record by date
    @GetMapping("/date/{date}")
    public List<Issuance> findByIssuedDate(@PathVariable LocalDate date){
        return issuanceService.findByIssuedDate(date);
    }

    // create new record
    @PostMapping("/new")
    public void newIssuanceRecord(@RequestBody Issuance issuance){
        issuanceService.save(issuance);
    }

    // update an issuance record by item code
    @PutMapping("/update/{itemCode}")
    public void updateIssuanceRecord(@PathVariable String itemCode, @RequestBody Issuance issuance){
        issuanceService.updateIssuanceByItemCode(itemCode, issuance);
    }

    // delete issuance record by item code
    @DeleteMapping("/delete/{itemCode}")
    public void deleteIssuanceRecord(@PathVariable String itemCode){
        issuanceService.deleteIssuanceByItemCode(itemCode);
    }
}
