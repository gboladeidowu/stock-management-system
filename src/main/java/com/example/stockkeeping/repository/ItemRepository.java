package com.example.stockkeeping.repository;

import com.example.stockkeeping.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

   Optional<Item> findByItemCode(String itemCode);
   void deleteByItemCode(String itemCode);
   List<Item> findItemByItemCodeContainingIgnoreCaseOrItemNameContainingIgnoreCaseOrItemDescriptionContainingIgnoreCase(String itemCode, String itemName, String itemDescription);

}


