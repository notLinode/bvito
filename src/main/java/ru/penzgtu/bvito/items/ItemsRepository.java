package ru.penzgtu.bvito.items;

import org.springframework.data.repository.CrudRepository;

public interface ItemsRepository extends CrudRepository<Item, Long> {}