package ru.penzgtu.bvito.items;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class ItemTag {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;

}