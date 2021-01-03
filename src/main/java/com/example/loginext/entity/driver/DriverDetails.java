package com.example.loginext.entity.driver;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "DRIVER_DETAILS")
@Data
public class DriverDetails{

	@Id
	@Column(name = "DRIVER_ID")
	private String driverId;

	@Column(name = "CUST_ID")
	private String customerId;

	@Column(name = "DRIVER_LAT")
	private double driverLatitude;

	@Column(name = "DRIVER_LONG")
	private double driverLongitude;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	private AvailabilityStatus availabilityStatus;

	@Column(name = "version")
	@Version
	private Long version;

}
