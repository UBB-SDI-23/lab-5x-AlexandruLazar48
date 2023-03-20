package com.example.labmpp.repository;

import com.example.labmpp.entities.ServiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceHistoryRepository extends JpaRepository<ServiceHistory, Long> {
    Optional<ServiceHistory> findServiceHistoryByCarId(Long id);
}
