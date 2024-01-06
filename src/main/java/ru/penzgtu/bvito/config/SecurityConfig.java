package ru.penzgtu.bvito.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.penzgtu.bvito.customer.Customer;
import ru.penzgtu.bvito.customer.CustomerRepository;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(CustomerRepository customerRepo) {
        return username -> {
            Customer customer = customerRepo.findByUsername(username);
            if (customer != null) {
                return customer;
            }

            throw new UsernameNotFoundException("Пользователь \"" + username + "\" не найден");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/items/add").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(cfg -> cfg
                        .loginPage("/profile/login")
                        .defaultSuccessUrl("/")
                )
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

}