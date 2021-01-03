package com.example.loginext.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingResponse {

    private String driverId;

    public BookingResponse(String driverId) {
        this.driverId = driverId;
    }
}
