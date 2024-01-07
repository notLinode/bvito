package ru.penzgtu.bvito.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;
import ru.penzgtu.bvito.model.Item;
import ru.penzgtu.bvito.repository.ItemRepository;
import ru.penzgtu.bvito.service.impl.ItemServiceImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTests {

    @Mock
    private ItemRepository repo;

    @InjectMocks
    private ItemServiceImpl service;

    @Test
    public void ItemService_CreateItem_ReturnItemDto() {
        Item item = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();
        ItemDto itemDto = ItemDto.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();

        Mockito.when(repo.save(Mockito.any(Item.class))).thenReturn(item);

        ItemDto savedItem = service.createItem(itemDto);

        Assertions.assertThat(savedItem).isNotNull();
    }

    @Test
    public void ItemService_GetAllItems_ReturnItemResponse() {
        Page<Item> items = Mockito.mock(Page.class);

        Mockito.when(repo.findAll(Mockito.any(Pageable.class))).thenReturn(items);

        ItemResponse itemResponse = service.getAllItems(1, 10);

        Assertions.assertThat(itemResponse).isNotNull();
    }

    @Test
    public void ItemService_GetItemById_ReturnItemDto() {
        long itemId = 1L;
        Item item = Item.builder()
                .id(itemId)
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();

        Mockito.when(repo.findById(itemId)).thenReturn(Optional.ofNullable(item));

        ItemDto returnedItem = service.getItemById(itemId);

        Assertions.assertThat(returnedItem).isNotNull();
    }

    @Test
    public void ItemService_GetAllItemsByCustomerId_ReturnItemResponse() {
        Page<Item> items = Mockito.mock(Page.class);

        Mockito.when(repo.getAllByListedBy_Id(Mockito.any(Long.class), Mockito.any(Pageable.class))).thenReturn(items);

        ItemResponse itemResponse = service.getAllItemsByCustomerId(1, 10, 1L);

        Assertions.assertThat(itemResponse).isNotNull();
    }

    @Test
    public void ItemService_UpdateItem_ReturnItemDto() {
        long itemId = 1L;
        Item item = Item.builder()
                .id(itemId)
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();
        ItemDto itemDto = ItemDto.builder()
                .id(itemId)
                .createdAt(new Date())
                .name("Updated listing")
                .description("Updated")
                .price(BigDecimal.valueOf(42))
                .build();

        Mockito.when(repo.findById(itemId)).thenReturn(Optional.ofNullable(item));
        Mockito.when(repo.save(Mockito.any(Item.class))).thenReturn(item);

        ItemDto updatedItem = service.updateItem(itemDto, itemId);

        Assertions.assertThat(updatedItem).isNotNull();
    }

    @Test
    public void ItemService_DeleteItem_ReturnVoid() {
        long itemId = 1L;
        Item item = Item.builder()
                .id(itemId)
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .build();

        Mockito.when(repo.findById(itemId)).thenReturn(Optional.ofNullable(item));
        Mockito.doNothing().when(repo).delete(item);

        assertAll(() -> service.deleteItem(itemId));
    }

}