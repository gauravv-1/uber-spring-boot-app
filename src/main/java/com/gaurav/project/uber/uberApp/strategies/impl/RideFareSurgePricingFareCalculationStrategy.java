package com.gaurav.project.uber.uberApp.strategies.impl;

import com.gaurav.project.uber.uberApp.entities.RideRequest;
import com.gaurav.project.uber.uberApp.services.DistanceService;
import com.gaurav.project.uber.uberApp.strategies.RideFareCalculationsStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationsStrategy  {

    private final DistanceService distanceService;
    private static final double SURGE_FACTOR = 2;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        Double distance = distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropoutLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
