package ru.penzgtu.bvito.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;
import ru.penzgtu.bvito.service.ItemService;

@RestController
@RequestMapping("/api")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/item")
    public ResponseEntity<ItemResponse> getItems(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(itemService.getAllItems(pageNum, pageSize));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDto> itemDetail(@PathVariable long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @GetMapping("/item/listed-by/{id}")
    public ResponseEntity<ItemResponse> itemsByOneCustomer(
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable long id) {
        return ResponseEntity.ok(itemService.getAllItemsByCustomerId(pageNum, pageSize, id));
    }

    @PostMapping("/item/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ItemDto> createItem(@Valid @RequestBody ItemDto itemDto) {
        return new ResponseEntity<>(itemService.createItem(itemDto), HttpStatus.CREATED);
    }

    @PutMapping("/item/{id}/update")
    public ResponseEntity<ItemDto> updateItem(@Valid @RequestBody ItemDto itemDto, @PathVariable long id) {
        return ResponseEntity.ok(itemService.updateItem(itemDto, id));
    }

    @DeleteMapping("/item/{id}/delete")
    public ResponseEntity<String> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item delete");
    }

}