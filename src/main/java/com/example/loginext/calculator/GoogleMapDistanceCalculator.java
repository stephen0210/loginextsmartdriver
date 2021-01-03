package com.example.loginext.calculator;
/**
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.DistanceMatrixRow;
**/
import java.io.IOException;

public class GoogleMapDistanceCalculator implements IDistanceCalculator {
    @Override
    public double calculateDistance(double customerLatitude, double customerLongitude, double driverLatitude, double driverLongitude) {
        /**
        GeoApiContext ctx = new GeoApiContext.Builder().apiKey("AIzaSyBDy7aET1IyYBe097kF8Hgg-maelONqWLw").build();
        String[] destinationAddress = {"40.7127837,-74.0059413", "33.9533487,-117.3961564", "38.6270025,-90.19940419999999"};
        String[] originAddress = {"12.8445,80.1523"};
        DistanceMatrixApiRequest distanceMatrix = DistanceMatrixApi.getDistanceMatrix(ctx, originAddress, destinationAddress);
        DistanceMatrix matrix = null;
        try {
            matrix = distanceMatrix.destinations(destinationAddress).origins(originAddress).await();
        } catch (ApiException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        DistanceMatrixRow[] rows = matrix.rows;
         **/
        return 0;
    }
}
