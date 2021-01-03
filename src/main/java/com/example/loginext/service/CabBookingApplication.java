package com.example.loginext.service;

import com.example.loginext.dtos.BookingRequest;
import com.example.loginext.dtos.BookingResponse;
import com.example.loginext.dtos.DriverDetailsResponse;
import com.example.loginext.dtos.TripCompletionRequest;
import com.example.loginext.entity.driver.DriverDetails;
import com.example.loginext.entity.driver.DriverDetailsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class CabBookingApplication {

    @Autowired
    CabBookingService cabBookingService;

    @Autowired
    DriverDetailsRepository driverDetailsRepository;

    @PostMapping("/bookcab")
    public BookingResponse bookCab(@Valid @RequestBody BookingRequest bookingRequest) {
        DriverDetails bestDriver = cabBookingService.getBestDriver(bookingRequest);
        if (Optional.ofNullable(bestDriver).isPresent()) {
            cabBookingService.bookCab(bookingRequest, bestDriver);
        }
        return new BookingResponse(bestDriver != null ? bestDriver.getDriverId() : "No cab available");
    }

    @PostMapping("/completetrip")
    public String bookCab(@Valid @RequestBody TripCompletionRequest tripCompletionRequest) {
        cabBookingService.completeTrip(tripCompletionRequest);
        return "Trip Completed";
    }

    @GetMapping("/fetchdriverdetails")
    public DriverDetailsResponse getDriverDetails(@RequestParam(name = "page", defaultValue = "0") int page,
                                                  @RequestParam(name = "size", defaultValue = "2") int size) throws JsonProcessingException {
        Pageable pageable = PageRequest.of(page, size);
        Page<DriverDetails> driverDetails = driverDetailsRepository.findAll(pageable);
        new ObjectMapper().writeValueAsString(driverDetails.toList().get(0));
        return new DriverDetailsResponse(driverDetails.toList(), driverDetails.getTotalPages(), driverDetails.getTotalElements());
    }

}
