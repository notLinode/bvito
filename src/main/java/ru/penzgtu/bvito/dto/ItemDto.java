package ru.penzgtu.bvito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    @Builder.Default
    private List<String> tags = new ArrayList<>();

}