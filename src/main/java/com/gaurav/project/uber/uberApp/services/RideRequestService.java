package com.gaurav.project.uber.uberApp.services;

import com.gaurav.project.uber.uberApp.entities.RideRequest;

public interface RideRequestService {
    RideRequest findRideRequestById(Long rideRequestId);

    void update (RideRequest rideRequest);
}
