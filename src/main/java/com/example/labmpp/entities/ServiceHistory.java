package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @JsonView(CustomJsonView.CoreData.class)
    private Boolean isAllowedToRun;

    @JsonView(CustomJsonView.CoreData.class)
    private Float yearlyTax;

    @JsonView(CustomJsonView.CoreData.class)
    private Float insuranceTax;

    @JsonView(CustomJsonView.CoreData.class)
    private Boolean hasStockPowertrain;

    @JsonView(CustomJsonView.CoreData.class)
    private Boolean hasStockBody;

    @OneToOne(mappedBy = "serviceHistory")
    @JsonView(CustomJsonView.FullDataServiceHistory.class)
    private Car car;

    @OneToMany(mappedBy = "serviceHistory", cascade = CascadeType.ALL)
    @JsonView(CustomJsonView.FullDataServiceHistory.class)
    private List<Service> serviceList;
}
