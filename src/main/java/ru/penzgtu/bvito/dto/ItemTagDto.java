package ru.penzgtu.bvito.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ItemTagDto {

    private long id;

    @NotBlank
    private String name;

}