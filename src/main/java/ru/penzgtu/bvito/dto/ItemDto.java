package ru.penzgtu.bvito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ItemDto {

    private long id;
    private Date createdAt;
    private String name;
    private String description;
    private BigDecimal price;
    private List<String> tags;

}