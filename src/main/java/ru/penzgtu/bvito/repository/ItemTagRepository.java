package ru.penzgtu.bvito.repository;

import org.springframework.data.repository.CrudRepository;
import ru.penzgtu.bvito.model.ItemTag;

public interface ItemTagRepository extends CrudRepository<ItemTag, String> {}