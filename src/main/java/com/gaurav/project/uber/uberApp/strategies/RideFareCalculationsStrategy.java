package com.gaurav.project.uber.uberApp.strategies;

import com.gaurav.project.uber.uberApp.entities.RideRequest;

public interface RideFareCalculationsStrategy {
    double RIDE_FARE_MULTIPLIER=10;
    double calculateFare(RideRequest rideRequest);

}
