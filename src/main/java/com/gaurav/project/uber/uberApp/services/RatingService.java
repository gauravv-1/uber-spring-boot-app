package com.gaurav.project.uber.uberApp.services;

import com.gaurav.project.uber.uberApp.dto.DriverDto;
import com.gaurav.project.uber.uberApp.dto.RiderDto;
import com.gaurav.project.uber.uberApp.entities.Driver;
import com.gaurav.project.uber.uberApp.entities.Ride;
import com.gaurav.project.uber.uberApp.entities.Rider;
import org.springframework.stereotype.Service;


public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);
}
