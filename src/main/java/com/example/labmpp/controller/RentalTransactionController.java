package com.example.labmpp.controller;

import com.example.labmpp.entities.RentalTransaction;
import com.example.labmpp.services.RentalTransactionService;
import com.example.labmpp.utils.dtos.CustomView.CustomJsonView;
import com.example.labmpp.utils.dtos.RentalTransactionRequest;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/rent")
public class RentalTransactionController {

    private RentalTransactionService rentalTransactionService;

    public RentalTransactionController(RentalTransactionService rentalTransactionService) {
        this.rentalTransactionService = rentalTransactionService;
    }

    @GetMapping("")
    @JsonView(CustomJsonView.CoreData.class)
    public ResponseEntity<List<RentalTransaction>> getAllRentalTransactions() {
        List<RentalTransaction> rentalTransactions = rentalTransactionService.findAll();
        return new ResponseEntity<>(rentalTransactions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(CustomJsonView.FullDataRentalTransaction.class)
    public ResponseEntity<RentalTransaction> getRentalTransactionById(@PathVariable("id") Long id) {
        RentalTransaction rentalTransaction = rentalTransactionService.findRentalTransactionById(id);
        return new ResponseEntity<>(rentalTransaction, HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(CustomJsonView.FullDataRentalTransaction.class)
    public ResponseEntity<RentalTransaction> addRentalTransaction(@RequestBody RentalTransactionRequest rentalTransaction) throws Exception{
        RentalTransaction newRentalTransaction = rentalTransactionService.addRentalTransaction(rentalTransaction);
        return new ResponseEntity<>(newRentalTransaction, HttpStatus.CREATED);
    }

    @PutMapping("")
    @JsonView(CustomJsonView.FullDataRentalTransaction.class)
    public ResponseEntity<RentalTransaction> updateRentalTransaction(@RequestBody RentalTransaction rentalTransaction) {
        RentalTransaction updatedRentalTransaction = rentalTransactionService.updateRentalTransaction(rentalTransaction);
        return new ResponseEntity<>(updatedRentalTransaction, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @JsonView(CustomJsonView.FullDataRentalTransaction.class)
    public ResponseEntity<?> deleteRentalTransaction(@PathVariable("id") Long id) {
        rentalTransactionService.deleteRentalTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
