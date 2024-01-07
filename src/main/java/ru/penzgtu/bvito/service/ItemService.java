package ru.penzgtu.bvito.service;

import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;

public interface ItemService {

    ItemDto createItem(ItemDto itemDto);
    ItemResponse getAllItems(int pageNum, int pageSize);
    ItemDto getItemById(long id);
    ItemDto updateItem(ItemDto itemDto, long id);
    void deleteItem(long id);

}