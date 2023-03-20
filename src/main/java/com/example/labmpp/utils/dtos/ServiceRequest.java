package com.example.labmpp.utils.dtos;

import com.example.labmpp.entities.Service;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRequest {
    public Service service;
    public Long serviceHistoryId;
}
