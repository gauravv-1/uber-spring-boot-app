package com.gaurav.project.uber.uberApp.services.impl;

import com.gaurav.project.uber.uberApp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSMRImpl implements DistanceService {

    private static final String OSMR_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    @Override
    public double calculateDistance(Point src, Point dest) {

        try{
            String uri = src.getX()+","+src.getY()+";"+dest.getX()+","+dest.getY();

            OSMSRResponseDto responseDto =  RestClient.builder()
                    .baseUrl(OSMR_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve()
                    .body(OSMSRResponseDto.class);

            return responseDto.getRoutes().get(0).getDistance()/1000.0;

        }

        catch (Exception e){

            throw new RuntimeException("Error getting data from OSRM "+e.getMessage());

        }


    }
}

@Data
class OSMSRResponseDto{

    private List<OSMRRoute> routes;

}

@Data
class OSMRRoute{
    private Double distance;
}
