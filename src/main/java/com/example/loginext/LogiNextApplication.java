package com.example.loginext;

import com.example.loginext.calculator.DistanceCalculator;
import com.example.loginext.entity.driver.DriverDetails;
import com.example.loginext.entity.order.OrderDetails;
import com.example.loginext.service.CabBookingApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackageClasses = {CabBookingApplication.class, DistanceCalculator.class})
@EntityScan(basePackageClasses = { OrderDetails.class, DriverDetails.class})
@EnableJpaRepositories
@SpringBootApplication()
public class LogiNextApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogiNextApplication.class, args);
	}

}
