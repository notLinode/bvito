package ru.penzgtu.bvito.controller;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.penzgtu.bvito.dto.CustomerDto;
import ru.penzgtu.bvito.repository.CustomerRepository;

@Controller
@RequestMapping("/profile")
public class CustomerController {

    CustomerRepository customerRepo;
    PasswordEncoder passwordEncoder;

    public CustomerController(CustomerRepository customerRepo, PasswordEncoder passwordEncoder) {
        this.customerRepo = customerRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "registration";
    }

    @GetMapping("/login")
    public String getLoginForm() {
        return "login";
    }

    @PostMapping("/register")
    public String processRegistration(@Valid CustomerDto form, Errors errors) {
        if (errors.hasErrors()) {
            return "registration";
        }

        customerRepo.save(form.toCustomer(passwordEncoder));

        return "redirect:/profile/login";
    }

}