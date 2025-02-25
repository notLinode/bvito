package ru.penzgtu.bvito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.penzgtu.bvito.model.Item;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<Page<Item>> getAllByListedBy_Id(Long customerId, Pageable pageable);

}