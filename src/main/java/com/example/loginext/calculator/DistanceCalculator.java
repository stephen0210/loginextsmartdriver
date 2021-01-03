package com.example.loginext.calculator;

import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator implements IDistanceCalculator {

    @Override
    public double calculateDistance(double customerLatitude,
                                    double customerLongitude,
                                    double driverLatitude,
                                    double driverLongitude) {

        customerLongitude = Math.toRadians(customerLongitude);
        customerLatitude = Math.toRadians(customerLatitude);
        driverLatitude = Math.toRadians(driverLatitude);
        driverLongitude = Math.toRadians(driverLongitude);

        // Haversine formula
        double dlon = driverLongitude - customerLongitude;
        double dlat = driverLatitude - customerLatitude;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(customerLatitude) * Math.cos(driverLatitude)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        return (c * r);
    }
}
