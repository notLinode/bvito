package ru.penzgtu.bvito.dto;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.penzgtu.bvito.model.Customer;

@Data
public class CustomerDto {

    private String username;
    private String password;
    private String fullName;
    private String oblast;
    private String city;
    private String street;
    private String phone;

    public Customer toCustomer(PasswordEncoder passwordEncoder) {
        return new Customer(username, passwordEncoder.encode(password), fullName, oblast, city, street, phone);
    }

}