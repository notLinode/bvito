//package ru.penzgtu.bvito.repository;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.penzgtu.bvito.model.Customer;
//
//import java.util.Optional;
//
//@DataJpaTest
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//public class CustomerRepositoryTests {
//
//    @Autowired
//    private UserRepository repo;
//
//    @Test
//    public void CustomerRepository_Save_ReturnCustomer() {
//        Customer customer = new Customer("root", "alpine", "1", "2", "3", "4", "5");
//
//        Customer savedCustomer = repo.save(customer);
//
//        Assertions.assertThat(savedCustomer.getId()).isGreaterThan(0);
//        Assertions.assertThat(savedCustomer.getUsername()).isEqualTo("root");
//    }
//
//    @Test
//    public void CustomerRepository_FindByUsername_ReturnCustomerIsPresent() {
//        Customer customer = new Customer("root", "alpine", "1", "2", "3", "4", "5");
//
//        repo.save(customer);
//
//        Optional<Customer> optionalCustomer = repo.findByUsername("root");
//
//        Assertions.assertThat(optionalCustomer).isPresent();
//    }
//
//    @Test
//    public void CustomerRepository_FindByUsername_ReturnCustomerIsEmptyWhenNotPresent() {
//        Customer customer = new Customer("root", "alpine", "1", "2", "3", "4", "5");
//
//        repo.save(customer);
//
//        Optional<Customer> optionalCustomer = repo.findByUsername("admin");
//
//        Assertions.assertThat(optionalCustomer).isEmpty();
//    }
//
//    @Test
//    public void CustomerRepository_Delete_ReturnCustomerIsEmpty() {
//        Customer customer = new Customer("root", "alpine", "1", "2", "3", "4", "5");
//
//        repo.save(customer);
//
//        repo.delete(customer);
//        Optional<Customer> returnedCustomer = repo.findById(customer.getId());
//
//        Assertions.assertThat(returnedCustomer).isEmpty();
//    }
//
//}