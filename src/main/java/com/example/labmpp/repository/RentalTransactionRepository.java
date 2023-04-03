package com.example.labmpp.repository;


import com.example.labmpp.entities.RentalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalTransactionRepository extends JpaRepository<RentalTransaction, Long> {
    Optional<RentalTransaction> findRentalTransactionById(Long id);
}
