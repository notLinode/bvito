package ru.penzgtu.bvito.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.penzgtu.bvito.dto.ItemDto;
import ru.penzgtu.bvito.dto.ItemResponse;
import ru.penzgtu.bvito.model.Item;
import ru.penzgtu.bvito.model.ItemTag;
import ru.penzgtu.bvito.repository.ItemRepository;
import ru.penzgtu.bvito.repository.ItemTagRepository;
import ru.penzgtu.bvito.service.ItemService;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepo;
    private final ItemTagRepository tagRepo;

    public ItemServiceImpl(ItemRepository itemRepo, ItemTagRepository tagRepo) {
        this.itemRepo = itemRepo;
        this.tagRepo = tagRepo;
    }

    @Override
    public ItemDto createItem(ItemDto itemDto) {
        Item item = Item.builder()
                .createdAt(itemDto.getCreatedAt())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .tags(itemDto.getTags().stream().map(this::findOrCreateItemTag).toList())
                .build();

        Item savedItem = itemRepo.save(item);

        return mapToDto(savedItem);
    }

    @Override
    public ItemResponse getAllItems(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Item> items = itemRepo.findAll(pageable);
        List<Item> itemList = items.getContent();
        List<ItemDto> content = itemList.stream().map(this::mapToDto).toList();

        return ItemResponse.builder()
                .content(content)
                .pageNum(items.getNumber())
                .pageSize(items.getSize())
                .totalElements(items.getTotalElements())
                .totalPages(items.getTotalPages())
                .last(items.isLast())
                .build();
    }

    @Override
    public ItemDto getItemById(long id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item could not be found"));
        return mapToDto(item);
    }

    @Override
    public ItemResponse getAllItemsByCustomerId(int pageNum, int pageSize, Long customerId) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Item> items = itemRepo.getAllByListedBy_Id(customerId, pageable);
        List<Item> itemList = items.getContent();
        List<ItemDto> content = itemList.stream().map(this::mapToDto).toList();

        return ItemResponse.builder()
                .content(content)
                .pageNum(items.getNumber())
                .pageSize(items.getSize())
                .totalElements(items.getTotalElements())
                .totalPages(items.getTotalPages())
                .last(items.isLast())
                .build();
    }

    @Override
    public ItemDto updateItem(ItemDto newItemDto, long id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Item could not be found"));

        item.setCreatedAt(newItemDto.getCreatedAt());
        item.setName(newItemDto.getName());
        item.setDescription(newItemDto.getDescription());
        item.setPrice(newItemDto.getPrice());
        item.setTags(newItemDto.getTags().stream().map(this::findOrCreateItemTag).toList());

        Item updatedItem = itemRepo.save(item);

        return mapToDto(updatedItem);
    }

    @Override
    public void deleteItem(long id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Pokemon could not be found"));
        itemRepo.delete(item);
    }

    private ItemDto mapToDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .createdAt(item.getCreatedAt())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .tags(item.getTags().stream().map(ItemTag::getName).toList())
                .build();
    }

    private Item mapToEntity(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .createdAt(itemDto.getCreatedAt())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .price(itemDto.getPrice())
                .tags(itemDto.getTags().stream().map(this::findOrCreateItemTag).toList())
                .build();
    }

    private ItemTag findOrCreateItemTag(String tag) {
        return tagRepo.findById(tag).isPresent() ? tagRepo.findById(tag).get()
                : tagRepo.save(new ItemTag(tag));
    }

}