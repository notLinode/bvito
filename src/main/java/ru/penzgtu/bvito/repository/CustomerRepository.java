package ru.penzgtu.bvito.repository;

import org.springframework.data.repository.CrudRepository;
import ru.penzgtu.bvito.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {}