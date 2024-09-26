package com.gaurav.project.uber.uberApp.repositories;

import com.gaurav.project.uber.uberApp.entities.Payment;
import com.gaurav.project.uber.uberApp.entities.Ride;
import com.gaurav.project.uber.uberApp.entities.WalletTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long> {
    Optional<Payment> findByRide(Ride ride);
}
