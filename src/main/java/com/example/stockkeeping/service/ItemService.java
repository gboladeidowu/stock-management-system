package com.example.stockkeeping.service;

import com.example.stockkeeping.dto.ItemDTO;
import com.example.stockkeeping.model.Item;
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
public class ItemService {

    private final ItemRepository itemRepository;

    public void saveItem(ItemDTO itemDTO){
        Optional<Item> savedItem = itemRepository.findByItemCode(itemDTO.itemCode());

        if(savedItem.isEmpty() || LocalDate.now().isAfter(savedItem.get().getDate())) {
                Item newItem = Item.builder()
                        .itemCode(itemDTO.itemCode())
                        .itemName(itemDTO.itemName())
                        .itemDescription(itemDTO.itemDescription())
                        .itemQuantity(itemDTO.itemQuantity())
                        .date(LocalDate.now())
                        .build();
                itemRepository.save(newItem);
        }
        else throw new IllegalStateException (String.format("item with code: '%s' already saved today. Update record instead.", itemDTO.itemCode()));
    }

    // get all items in stock
    public List<Item> getAll() {
       return itemRepository.findAll();
    }


    //filter items by parameter
    public List<Item> getByParams(String itemCode, String itemName, String itemDescription) {
        List<Item> items = itemRepository.findItemByItemCodeContainingIgnoreCaseOrItemNameContainingIgnoreCaseOrItemDescriptionContainingIgnoreCase(itemCode, itemName, itemDescription);
        if (items.isEmpty()) {
            throw new IllegalStateException("Item not found");
        }
        return items;
    }

    // update item
    public void updateItem(Integer id, ItemDTO itemDTO){
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            item.get().setItemCode(itemDTO.itemCode());
            item.get().setItemName(itemDTO.itemName());
            item.get().setItemDescription(itemDTO.itemDescription());
            item.get().setItemQuantity(itemDTO.itemQuantity());

            itemRepository.save(item.get());
        }
       else throw new IllegalStateException(String.format("Item with code: '%s' not found",item.get().getItemCode()));
    }

    public void deleteItem(String itemCode) {
        Optional<Item> item = itemRepository.findByItemCode(itemCode);
        if (item.isPresent()){
            itemRepository.deleteByItemCode(itemCode);
        }
        else throw new IllegalStateException("Item with code: " + itemCode + " not found");
    }
}
