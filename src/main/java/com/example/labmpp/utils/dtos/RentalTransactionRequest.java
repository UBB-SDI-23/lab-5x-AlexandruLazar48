package com.example.labmpp.utils.dtos;

import com.example.labmpp.entities.RentalTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalTransactionRequest {
    RentalTransaction rentalTransaction;
    Long carId;
    Long customerId;
}
