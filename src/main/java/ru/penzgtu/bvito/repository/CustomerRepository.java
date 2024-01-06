package ru.penzgtu.bvito.repository;

import org.springframework.data.repository.CrudRepository;
import ru.penzgtu.bvito.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> findByUsername(String username);

}