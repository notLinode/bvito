package ru.penzgtu.bvito.service;

import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;
import ru.penzgtu.bvito.model.Customer;

public interface ItemService {

    ItemDto createItem(ItemDto itemDto);
    ItemResponse getAllItems(int pageNum, int pageSize);
    ItemDto getItemById(long id);
    ItemResponse getAllItemsByCustomerId(int pageNum, int pageSize, Long customerId);
    ItemDto updateItem(ItemDto itemDto, long id);
    void deleteItem(long id);

}