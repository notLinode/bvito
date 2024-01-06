package ru.penzgtu.bvito.customer;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByUsername(String username);

}