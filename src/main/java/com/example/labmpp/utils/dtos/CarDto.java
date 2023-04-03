package com.example.labmpp.utils.dtos;

import com.example.labmpp.entities.ServiceHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    long id;
    UUID code;
    String make;
    String model;
    float value;
    int horsepower;
    int yearOfFabrication;
    ServiceHistory serviceHistory;
}
