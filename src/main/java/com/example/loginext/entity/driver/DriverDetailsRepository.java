package com.example.loginext.entity.driver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverDetailsRepository extends JpaRepository<DriverDetails,String> {

    List<DriverDetails> findAllByAvailabilityStatus(AvailabilityStatus availabilityStatus);

    @Override
    Page<DriverDetails> findAll(Pageable pageable);

    Optional<DriverDetails> findByDriverIdAndAvailabilityStatus(String name, AvailabilityStatus availabilityStatus);
}
