package ru.penzgtu.bvito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.penzgtu.bvito.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {}