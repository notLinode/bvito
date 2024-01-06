package ru.penzgtu.bvito.repository;

import org.springframework.data.repository.CrudRepository;
import ru.penzgtu.bvito.model.Item;

public interface ItemsRepository extends CrudRepository<Item, Long> {}