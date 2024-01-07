package ru.penzgtu.bvito.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private final String fullName;
    private final String oblast;
    private final String city;
    private final String street;
    private final String phoneNumber;

}