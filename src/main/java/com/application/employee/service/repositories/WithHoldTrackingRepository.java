package com.application.employee.service.repositories;
import com.application.employee.service.entities.WithHoldTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WithHoldTrackingRepository extends JpaRepository<WithHoldTracking,String> {
}
