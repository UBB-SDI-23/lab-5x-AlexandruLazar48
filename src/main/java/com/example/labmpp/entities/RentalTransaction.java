package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.transaction.Transactional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RentalTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_id")
    @JsonView({CustomJsonView.FullDataCustomer.class, CustomJsonView.FullDataRentalTransaction.class})
    Car car;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    @JsonView({CustomJsonView.FullDataCar.class, CustomJsonView.FullDataRentalTransaction.class})
    Customer customer;

    @Override
    public String toString() {
        return "RentalTransaction{}";
    }

    @JsonView(CustomJsonView.CoreData.class)
    Integer startTime;

    @JsonView(CustomJsonView.CoreData.class)
    Integer endTime;

    @JsonView(CustomJsonView.CoreData.class)
    Float value;
}
