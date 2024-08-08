package com.example.stockkeeping.service;

import com.example.stockkeeping.dto.IssuanceDTO;
import com.example.stockkeeping.model.Issuance;
import com.example.stockkeeping.model.Item;
import com.example.stockkeeping.repository.IssuanceRepository;
import com.example.stockkeeping.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class IssuanceService {

    private final IssuanceRepository issuanceRepository;
    private final ItemRepository itemRepository;

    public void save(IssuanceDTO issuanceDTO) {
        Optional<Item> item = itemRepository.findByItemCode(issuanceDTO.itemCode());
        if (item.isPresent()) {
            Issuance newIssuance = Issuance.builder()
                    .itemCode(issuanceDTO.itemCode())
                    .itemName(item.get().getItemName())
                    .itemDescription(item.get().getItemDescription())
                    .issuedQuantity(issuanceDTO.issuedQuantity())
                    .issuer("Admin")
                    .station(issuanceDTO.station())
                    .issuedDate(LocalDate.now()).build();

            issuanceRepository.save(newIssuance);
        }
        else throw new IllegalStateException("Item with code: " + issuanceDTO.itemCode() + " not found");
    }

    //get all issuance record
    public List<Issuance> findAll(){
        return issuanceRepository.findAll();
    }

    //filter issuance record
    public List<Issuance> findByParam(LocalDate issuedDate, String station, String itemCode){
       List<Issuance> issuanceRecord = issuanceRepository.findIssuanceByIssuedDateOrStationContainingIgnoreCaseOrItemCodeContainingIgnoreCase(issuedDate, station, itemCode);
       if (issuanceRecord.isEmpty()){
           throw new IllegalStateException("No issuance record found");
       }
       return issuanceRecord;
    }

    //update issuance record
    public void updateIssuanceRecord(Long id, IssuanceDTO issuanceDTO){
        Optional<Issuance> issuanceRecord = issuanceRepository.findById(id);
        Optional<Item> itemRecord = itemRepository.findByItemCode(issuanceDTO.itemCode());
        if (issuanceRecord.isPresent() && itemRecord.isPresent()){
            issuanceRecord.get().setItemCode(issuanceDTO.itemCode());
            issuanceRecord.get().setItemName(itemRecord.get().getItemName());
            issuanceRecord.get().setItemDescription(itemRecord.get().getItemDescription());
            issuanceRecord.get().setIssuedQuantity(issuanceDTO.issuedQuantity());
            issuanceRecord.get().setStation(issuanceDTO.station());

            issuanceRepository.save(issuanceRecord.get());
        }
    }
}
