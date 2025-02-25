package ru.penzgtu.bvito.repository;

import org.springframework.data.repository.CrudRepository;
import ru.penzgtu.bvito.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

//    List<Customer> findByGroup(String group);

}