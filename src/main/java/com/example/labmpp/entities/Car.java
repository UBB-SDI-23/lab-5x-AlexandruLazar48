package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @JsonView(CustomJsonView.CoreData.class)
    private UUID code;

    @JsonView(CustomJsonView.CoreData.class)
    private String make;

    @JsonView(CustomJsonView.CoreData.class)
    private String model;

    @JsonView(CustomJsonView.CoreData.class)
    private Float value;

    @JsonView(CustomJsonView.CoreData.class)
    private Integer horsepower;

    @JsonView(CustomJsonView.CoreData.class)
    private Integer yearOfFabrication;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_history_id", referencedColumnName = "id")
    @JsonView(CustomJsonView.FullDataCar.class)
    private ServiceHistory serviceHistory;
}