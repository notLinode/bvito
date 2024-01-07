package ru.penzgtu.bvito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ItemResponse {

    private List<ItemDto> content;
    private int pageNum;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;

}