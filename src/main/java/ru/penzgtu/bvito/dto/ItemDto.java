package ru.penzgtu.bvito.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ItemDto {

    private long id;
    private Date createdAt;
    private String name;
    private String description;
    private BigDecimal price;

}