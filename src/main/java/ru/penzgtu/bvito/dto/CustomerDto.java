package ru.penzgtu.bvito.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private String username;
    private String password;
    private String fullName;
    private String oblast;
    private String city;
    private String street;
    private String phone;

}