package ru.penzgtu.bvito.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.penzgtu.bvito.model.Customer;

@Data
public class CustomerDto {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String fullName;
    @NotBlank
    private String oblast;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String phone;

    public Customer toCustomer(PasswordEncoder encoder) {
        return new Customer(username, encoder.encode(password), fullName, oblast, city, street, phone);
    }

}