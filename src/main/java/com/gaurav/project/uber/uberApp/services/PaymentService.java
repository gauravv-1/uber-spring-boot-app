package com.gaurav.project.uber.uberApp.services;

import com.gaurav.project.uber.uberApp.entities.Payment;
import com.gaurav.project.uber.uberApp.entities.Ride;
import com.gaurav.project.uber.uberApp.entities.enums.PaymentStatus;

public interface PaymentService {

    void processPayment(Ride ride);
    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus paymentStatus);
}
