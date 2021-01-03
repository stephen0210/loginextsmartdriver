package com.example.loginext;

import com.example.loginext.dtos.BookingRequest;
import com.example.loginext.dtos.BookingResponse;
import com.example.loginext.dtos.TripCompletionRequest;
import org.junit.Assert;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class TestHelper {

    RestTemplate restTemplate = new RestTemplate();

    public void invokeAndAssert(URI uri, BookingRequest bookingRequest, String driverName) {
        ResponseEntity<BookingResponse> result = restTemplate.postForEntity(uri, new HttpEntity<>(bookingRequest), BookingResponse.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(driverName, result.getBody().getDriverId());
    }

    public void invokeAndAssertForComplete(URI uri, TripCompletionRequest tripCompletionRequest, String message) {
        ResponseEntity<String> result = restTemplate.postForEntity(uri, new HttpEntity<>(tripCompletionRequest), String.class);
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(message, "Trip Completed");
    }

    public void invokeAndAssertForFailure(URI uri, TripCompletionRequest tripCompletionRequest, String message) {
        ResponseEntity<String> result = restTemplate.postForEntity(uri, new HttpEntity<>(tripCompletionRequest), String.class);
    }

    public boolean getDriverId(Future<BookingResponse> t) {
        try {
            return t.get().getDriverId().contentEquals("STEPHEN");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return false;
    }
}
