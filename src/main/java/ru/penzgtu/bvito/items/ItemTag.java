package ru.penzgtu.bvito.items;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force = true)
public class ItemTag {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    private String name;

    public ItemTag(String name) {
        this.name = name;
    }

}