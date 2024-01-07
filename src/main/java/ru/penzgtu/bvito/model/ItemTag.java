package ru.penzgtu.bvito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String name;

}