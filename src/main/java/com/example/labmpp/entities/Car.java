package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @JsonView(CustomJsonView.CoreData.class)
    private UUID code;

    @JsonView(CustomJsonView.CoreData.class)
    @NotNull
    private String make;

    @JsonView(CustomJsonView.CoreData.class)
    private String model;

    @JsonView(CustomJsonView.CoreData.class)
    private Float value;

    @JsonView(CustomJsonView.CoreData.class)
    private Integer horsepower;

    @JsonView(CustomJsonView.CoreData.class)
    @Min(1950)
    @Max(2023)
    private Integer yearOfFabrication;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_history_id", referencedColumnName = "id")
    @JsonView(CustomJsonView.FullDataCar.class)
    private ServiceHistory serviceHistory;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    @JsonView(CustomJsonView.FullDataCar.class)
    List<RentalTransaction> rentalTransactions;
}