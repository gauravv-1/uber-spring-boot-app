package com.gaurav.project.uber.uberApp.dto;

import com.gaurav.project.uber.uberApp.entities.enums.PaymentMethod;
import com.gaurav.project.uber.uberApp.entities.enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideRequestDto {

    private Long id;


    private PointDto pickupLocation;

    private PointDto dropoutLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestedTime;

    private RiderDto rider;
    private Double fare;
    private RideRequestStatus rideRequestStatus;
}
