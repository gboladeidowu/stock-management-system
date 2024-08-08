package com.example.stockkeeping.controller;

import com.example.stockkeeping.dto.ItemDTO;
import com.example.stockkeeping.model.Item;
import com.example.stockkeeping.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String saveItem(@RequestBody ItemDTO itemDTO){
        itemService.saveItem(itemDTO);
        return "Item saved";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getAllItems(){
        return itemService.getAll();
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public List<Item> getByFilter(@RequestParam(name = "itemCode", required = false) String itemCode,
                                         @RequestParam(name = "itemName", required = false) String itemName,
                                         @RequestParam(name = "ItemDescription", required = false) String itemDescription){
        return itemService.getByParams(itemCode, itemName, itemDescription);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateItem(@PathVariable(name = "id") Integer id, @RequestBody ItemDTO itemDTO){
        itemService.updateItem(id, itemDTO);
        return "Update saved";
    }

    @DeleteMapping("/{itemCode}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteItem(@PathVariable(name = "itemCode") String itemCode){
        itemService.deleteItem(itemCode);
        return "Item deleted successfully";
    }
}
