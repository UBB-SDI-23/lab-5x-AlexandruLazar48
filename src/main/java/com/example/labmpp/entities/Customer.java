package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @JsonView(CustomJsonView.CoreData.class)
    private String name;

    @JsonView(CustomJsonView.CoreData.class)
    private String cnp;

    @JsonView(CustomJsonView.CoreData.class)
    private Integer age;

    @JsonView(CustomJsonView.CoreData.class)
    private Float budget;

    @JsonView(CustomJsonView.CoreData.class)
    private String description;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonView(CustomJsonView.FullDataCustomer.class)
    List<RentalTransaction> rentalTransactions;
}