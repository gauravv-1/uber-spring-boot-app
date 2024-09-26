package com.gaurav.project.uber.uberApp.dto;

import com.gaurav.project.uber.uberApp.entities.enums.PaymentMethod;
import com.gaurav.project.uber.uberApp.entities.enums.RideStatus;
import lombok.Data;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
public class RideDto {
    private Long id;


    private PointDto pickupLocation;

    private PointDto dropoutLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;

    private DriverDto driver;
    private PaymentMethod paymentMethod;


    private RideStatus rideStatus;

    private String otp;

    private Double fare;
    private  LocalDateTime startedAt;
    private LocalDateTime endedAt;
}
