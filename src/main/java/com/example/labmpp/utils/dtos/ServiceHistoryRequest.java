package com.example.labmpp.utils.dtos;

import com.example.labmpp.entities.ServiceHistory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistoryRequest {
    private ServiceHistory serviceHistory;
    private Long carId;
}