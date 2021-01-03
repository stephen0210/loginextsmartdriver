package com.example.loginext.calculator;

public interface IDistanceCalculator {
    double calculateDistance(double customerLatitude,
                             double customerLongitude,
                             double driverLatitude,
                             double driverLongitude);
}
