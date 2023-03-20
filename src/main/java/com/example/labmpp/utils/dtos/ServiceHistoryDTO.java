package com.example.labmpp.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistoryDTO {
    private long id;
    private boolean isAllowedToRun;
    private float yearlyTax;
    private float insuranceTax;
    private boolean hasStockPowertrain;
    private boolean hasStockBody;
    private long carId;
}
