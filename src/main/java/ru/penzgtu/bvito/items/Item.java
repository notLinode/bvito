package ru.penzgtu.bvito.items;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
public class Item {

    @Id
    @GeneratedValue
    private long id;

    private Date createdAt;

    @NotBlank(message = "Укажите название.")
    @Size(min = 5, max = 40, message = "Название объявления должно быть в пределах от 5 до 40 символов.")
    private String name;

    @Size(max = 2000, message = "Максимальная объём описания: 2000 символов.")
    private String description;

    @NotNull(message = "Укажите цену.")
    @Min(value = 0, message = "Укажите цену.")
    private BigDecimal price;

    @ManyToMany
    private List<ItemTag> tags;

}