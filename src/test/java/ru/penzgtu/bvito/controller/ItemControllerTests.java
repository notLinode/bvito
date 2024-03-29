package ru.penzgtu.bvito.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;
import ru.penzgtu.bvito.model.Item;
import ru.penzgtu.bvito.model.ItemTag;
import ru.penzgtu.bvito.service.ItemService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService service;

    @Autowired
    private ObjectMapper objectMapper;
    private Item item;
    private ItemDto itemDto;

    @BeforeEach()
    public void init() {
        item = Item.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .tags(List.of(new ItemTag("test tag"), new ItemTag("tag #2")))
                .build();
        itemDto = ItemDto.builder()
                .createdAt(new Date())
                .name("Test listing")
                .description("")
                .price(BigDecimal.valueOf(42))
                .tags(List.of("test tag", "tag #2"))
                .build();
    }

    @Test public void ItemController_GetItems_ReturnResponseDto() throws Exception {
        ItemResponse responseDto = ItemResponse.builder()
                .content(List.of(itemDto))
                .pageNum(1)
                .pageSize(10)
                .totalElements(1L)
                .totalPages(1)
                .last(true)
                .build();

        BDDMockito.when(service.getAllItems(1, 10))
                .thenReturn(responseDto);

        ResultActions response = mockMvc.perform(
                get("/api/item")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNum","1")
                        .param("pageSize", "10")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));
    }

    @Test
    public void ItemController_ItemDetail_ReturnItemDto() throws Exception {
        long itemId = 1L;
        BDDMockito.when(service.getItemById(itemId)).thenReturn(itemDto);

        ResultActions response = mockMvc.perform(
                get("/api/item/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(itemDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(itemDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(itemDto.getPrice().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", CoreMatchers.is(itemDto.getTags())));
    }

    @Test
    public void ItemController_ItemsByOneCustomer_ReturnCreated() throws Exception {
        long customerId = 1L;

        ItemResponse responseDto = ItemResponse.builder()
                .content(List.of(itemDto))
                .pageNum(1)
                .pageSize(10)
                .totalElements(1L)
                .totalPages(1)
                .last(true)
                .build();

        BDDMockito.when(service.getAllItemsByCustomerId(1, 10, customerId))
                .thenReturn(responseDto);

        ResultActions response = mockMvc.perform(
                get("/api/item/listed-by/" + customerId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("pageNum","1")
                        .param("pageSize", "10")
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content.size()", CoreMatchers.is(responseDto.getContent().size())));
    }

    @Test
    public void ItemController_CreateItem_ReturnCreated() throws Exception {
        BDDMockito.given(service.createItem(ArgumentMatchers.any()))
                .willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        ResultActions response = mockMvc.perform(
                post("/api/item/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(itemDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(itemDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(itemDto.getPrice().intValue()))) // JsonSmartJsonProvider настолько умный, что при десериализации BigDecimal даёт переменной тип int
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", CoreMatchers.is(itemDto.getTags())));
    }

    @Test
    public void ItemController_UpdateItem_ReturnItemDto() throws Exception {
        long itemId = 1L;

        BDDMockito.when(service.updateItem(itemDto, itemId)).thenReturn(itemDto);

        ResultActions response = mockMvc.perform(
                put("/api/item/" + itemId + "/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(itemDto.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", CoreMatchers.is(itemDto.getDescription())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", CoreMatchers.is(itemDto.getPrice().intValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.tags", CoreMatchers.is(itemDto.getTags())));
    }

    // лень тестить deleteItem()

}