package com.example.loginext.service;

import com.example.loginext.calculator.IDistanceCalculator;
import com.example.loginext.dtos.BookingRequest;
import com.example.loginext.dtos.TripCompletionRequest;
import com.example.loginext.entity.driver.AvailabilityStatus;
import com.example.loginext.entity.driver.DriverDetails;
import com.example.loginext.entity.driver.DriverDetailsRepository;
import com.example.loginext.entity.order.OrderDetails;
import com.example.loginext.entity.order.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Component
public class CabBookingService {

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private IDistanceCalculator IDistanceCalculator;

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public DriverDetails getBestDriver(BookingRequest bookingRequest) {
        List<DriverDetails> availableDrivers = driverDetailsRepository.findAllByAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        double min = Double.MAX_VALUE;
        DriverDetails bestDriver = null;
        for (DriverDetails driverDetails : availableDrivers) {
            double customerLong = bookingRequest.getLongitude();
            double customerLat = bookingRequest.getLatitude();
            double driverLong = driverDetails.getDriverLongitude();
            double driverLat = driverDetails.getDriverLatitude();
            double distance = IDistanceCalculator.calculateDistance(customerLat,
                    customerLong,
                    driverLat,
                    driverLong);
            if (distance < min) {
                min = distance;
                bestDriver = driverDetails;
            }
        }
        return bestDriver;
    }

    @Transactional
    public void bookCab(BookingRequest bookingRequest, DriverDetails driverDetails) {
        driverDetails.setCustomerId(bookingRequest.getCustomerId());
        driverDetails.setAvailabilityStatus(AvailabilityStatus.BUSY);
        driverDetailsRepository.save(driverDetails);
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCustomerLatitude(bookingRequest.getLatitude());
        orderDetails.setCustomerLongitude(bookingRequest.getLongitude());
        orderDetails.setCustomerName(bookingRequest.getCustomerId());
        orderDetailsRepository.save(orderDetails);
    }

    @Transactional
    public void completeTrip(@Valid TripCompletionRequest tripCompletionRequest) {
        Optional<DriverDetails> driverDetailsOpt = driverDetailsRepository.findByDriverIdAndAvailabilityStatus(tripCompletionRequest.getDriverId(),AvailabilityStatus.BUSY);
        if (driverDetailsOpt.isPresent()) {
            DriverDetails driverDetails = driverDetailsOpt.get();
            driverDetails.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            driverDetails.setCustomerId(null);
            driverDetailsRepository.save(driverDetails);
        }else {
            throw new RuntimeException("The driver has already completed his trip");
        }
    }

}
