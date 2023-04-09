package com.example.labmpp.utils.dtos;

import com.example.labmpp.entities.Car;
import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortedCarsDto {
    @JsonView(CustomJsonView.CoreData.class)
    List<Car> cars;
    @JsonView(CustomJsonView.CoreData.class)
    Float average;
}
