package com.gaurav.project.uber.uberApp.controllers;

import com.gaurav.project.uber.uberApp.dto.*;
import com.gaurav.project.uber.uberApp.services.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/riders")
@RequiredArgsConstructor
@Secured("RIDER")


public class RiderController {

    private final RiderService riderService;
        @PostMapping("/requestRide")
        public ResponseEntity<RideRequestDto> requestRide(@RequestBody RideRequestDto rideRequestDto){
            return ResponseEntity.ok(riderService.requestRide(rideRequestDto));
        }

        @PostMapping("/cancelRide/{rideId}")
        public ResponseEntity<RideDto> cancelRide(@PathVariable Long rideId){
            return ResponseEntity.ok(riderService.cancelRide(rideId));
        }

        @PostMapping("/rateDriver")
        public ResponseEntity<DriverDto> rateDriver(@RequestBody RatingDto ratingDto){
            return ResponseEntity.ok(riderService.rateDriver(ratingDto.getRideId(), ratingDto.getRating()));

        }

        @GetMapping("/getMyProfile")
        public ResponseEntity<RiderDto> getMyProfile(){
            return ResponseEntity.ok(riderService.getMyProfile());
        }

        @GetMapping("/getMyRides")
    public ResponseEntity<Page<RideDto>> getAllMyRides(@RequestParam(defaultValue = "0") Integer pageOffset,
                                                       @RequestParam(defaultValue = "10",required = false) Integer pageSize){
            PageRequest pageRequest = PageRequest.of(pageOffset,pageSize);
            return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));

        }






}
