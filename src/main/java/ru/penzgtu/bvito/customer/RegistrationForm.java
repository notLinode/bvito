package ru.penzgtu.bvito.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirm;
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