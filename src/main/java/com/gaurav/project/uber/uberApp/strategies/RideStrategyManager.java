package com.gaurav.project.uber.uberApp.strategies;

import com.gaurav.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.gaurav.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.gaurav.project.uber.uberApp.strategies.impl.RideFareDefaultFareCalculationsStrategy;
import com.gaurav.project.uber.uberApp.strategies.impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    private final RideFareDefaultFareCalculationsStrategy defaultFareCalculationsStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){
        if(riderRating>=4.8){

            return highestRatedDriverStrategy;

        }

        else {
            return nearestDriverStrategy;
        }

    }

    public RideFareCalculationsStrategy rideFareCalculationsStrategy(){
        //6pm-9pm
        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);



        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;
        } else {
            return defaultFareCalculationsStrategy;
        }

    }

}
