package com.example.labmpp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "service_history")
@NoArgsConstructor
@AllArgsConstructor
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Boolean isAllowedToRun;
    private Float yearlyTax;
    private Float insuranceTax;
    private Boolean hasStockPowertrain;
    private Boolean hasStockBody;

    @OneToOne(mappedBy = "serviceHistory")
    @JsonIgnore
    private Car car;

    @OneToMany(mappedBy = "serviceHistory", cascade = CascadeType.ALL)
    private List<Service> serviceList;
}
