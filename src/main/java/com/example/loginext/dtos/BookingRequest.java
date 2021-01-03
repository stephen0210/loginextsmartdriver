package com.example.loginext.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    @NotEmpty(message = "Customer name cannot be null")
    private String customerId;
    @NotNull(message = "Latitude cannot be null")
    private Double latitude;
    @NotNull(message = "message = Longitude cannot be null")
    private Double longitude;
}
