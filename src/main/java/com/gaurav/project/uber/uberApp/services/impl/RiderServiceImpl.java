package com.gaurav.project.uber.uberApp.services.impl;

import com.gaurav.project.uber.uberApp.dto.DriverDto;
import com.gaurav.project.uber.uberApp.dto.RideDto;
import com.gaurav.project.uber.uberApp.dto.RideRequestDto;
import com.gaurav.project.uber.uberApp.dto.RiderDto;
import com.gaurav.project.uber.uberApp.entities.*;
import com.gaurav.project.uber.uberApp.entities.enums.RideRequestStatus;
import com.gaurav.project.uber.uberApp.entities.enums.RideStatus;
import com.gaurav.project.uber.uberApp.exceptions.ResourceNotFoundException;
import com.gaurav.project.uber.uberApp.repositories.RideRequestRepository;
import com.gaurav.project.uber.uberApp.repositories.RiderRepository;
import com.gaurav.project.uber.uberApp.services.DriverService;
import com.gaurav.project.uber.uberApp.services.RatingService;
import com.gaurav.project.uber.uberApp.services.RideService;
import com.gaurav.project.uber.uberApp.services.RiderService;
import com.gaurav.project.uber.uberApp.strategies.RideStrategyManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j

public class RiderServiceImpl implements RiderService {

    private final ModelMapper modelMapper;
    private final RideStrategyManager rideStrategyManager;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;

    @Override
    @Transactional
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {

        Rider  rider = getCurrentRider();

        RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);

//        log.info(rideRequest.toString());
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare  = rideStrategyManager.rideFareCalculationsStrategy().calculateFare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest saveRideRequest = rideRequestRepository.save(rideRequest);

        List<Driver> drivers =  rideStrategyManager
                .driverMatchingStrategy(rider.getRating())
                .findMatchingDriver(rideRequest);
        //TODO: send notifications to all drivers about thid ride



        return modelMapper.map(saveRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider = getCurrentRider();
        Ride ride = rideService.getRideById(rideId);
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride "+rideId);
        }

        Ride savedRide= rideService.updateRideStatus(ride, RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(savedRide,RideDto.class);
    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride = rideService.getRideById(rideId);
        Rider rider = getCurrentRider();

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not the owner of the ride");
        }

        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride status is not END hence cannot rate "+ride.getRideStatus());

        }

        return ratingService.rateDriver(ride,rating);

    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider = getCurrentRider();

        return modelMapper.map(currentRider, RiderDto.class);
    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {

        Rider currentRider = getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest)
                .map(
                        ride -> modelMapper.map(ride,RideDto.class)
                );
    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepository.save(rider);

    }

    @Override
    public Rider getCurrentRider() {
//        TODO IMPL SPRING SECURITY
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return riderRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException("Rider Not Associated with user with id " + user.getId()));
    }
}