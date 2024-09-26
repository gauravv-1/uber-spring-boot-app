package com.gaurav.project.uber.uberApp.strategies.impl;

import com.gaurav.project.uber.uberApp.entities.Driver;
import com.gaurav.project.uber.uberApp.entities.RideRequest;
import com.gaurav.project.uber.uberApp.repositories.DriverRepository;
import com.gaurav.project.uber.uberApp.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverMatchingNearestDriverStrategy implements DriverMatchingStrategy {

    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepository.findTenNearestDriver(rideRequest.getPickupLocation());

    }
}
