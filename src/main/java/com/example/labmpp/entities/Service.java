package com.example.labmpp.entities;

import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "service")
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(CustomJsonView.id.class)
    private Long id;

    @JsonView(CustomJsonView.CoreData.class)
    private Date date;

    @JsonView(CustomJsonView.CoreData.class)
    private Boolean isPassed;

    @JsonView(CustomJsonView.CoreData.class)
    private String notes;

    @JsonView(CustomJsonView.CoreData.class)
    private Float cost;

    @JsonView(CustomJsonView.CoreData.class)
    private String serviceName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "service_history_id", nullable = false)
    @JsonView(CustomJsonView.FullDataService.class)
    private ServiceHistory serviceHistory;
}
