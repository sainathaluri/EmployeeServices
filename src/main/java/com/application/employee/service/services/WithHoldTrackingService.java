package com.application.employee.service.services;
import com.application.employee.service.entities.WithHoldTracking;
import java.util.List;

public interface WithHoldTrackingService {
    List<WithHoldTracking> getAllWithHoldTracking();
    WithHoldTracking getWithHoldTrackingById(String id);
    WithHoldTracking saveWithHoldTracking(WithHoldTracking withHoldTracking);
}
