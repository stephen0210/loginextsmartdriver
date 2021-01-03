package com.example.loginext.dtos;

import com.example.loginext.entity.driver.DriverDetails;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DriverDetailsResponse {

    private List<DriverDetails> driverDetails;
    private int pageSize;
    private long totalElements;

    public DriverDetailsResponse(List<DriverDetails> driverDetails, int pageSize, long totalElements) {
        this.driverDetails = driverDetails;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }
}
