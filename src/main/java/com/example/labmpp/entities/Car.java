package com.example.labmpp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Locale;
import java.util.UUID;

@Data
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private UUID code;
    private String make;
    private String model;
    private Float value;
    private Integer horsepower;
    private Integer yearOfFabrication;
}