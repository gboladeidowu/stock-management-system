package com.example.stockkeeping.controller;

import com.example.stockkeeping.dto.IssuanceDTO;
import com.example.stockkeeping.model.Issuance;
import com.example.stockkeeping.service.IssuanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/issuance")
public class IssuanceController {

    private final IssuanceService issuanceService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String issue(@RequestBody IssuanceDTO issuanceDTO){
        issuanceService.save(issuanceDTO);
        return "Issuance record saved";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Issuance> findAll(){
        return issuanceService.findAll();
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<Issuance> findByParam(@RequestParam(required = false) LocalDate issuedDate,
                                      @RequestParam(required = false) String station,
                                      @RequestParam(required = false) String itemCode){
        return issuanceService.findByParam(issuedDate, station, itemCode);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateIssuanceRecord(@PathVariable(name = "id") Long id, @RequestBody IssuanceDTO issuanceDTO){
        issuanceService.updateIssuanceRecord(id, issuanceDTO);
        return "Update saved.";
    }
}
