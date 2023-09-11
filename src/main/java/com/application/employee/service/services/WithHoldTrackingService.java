package com.application.employee.service.services;
import com.application.employee.service.entities.Employee;
import com.application.employee.service.entities.WithHoldTracking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WithHoldTrackingService {
    List<WithHoldTracking> getAllWithHoldTracking();
    WithHoldTracking getWithHoldTrackingById(String id);
    WithHoldTracking saveWithHoldTracking(WithHoldTracking withHoldTracking);
    WithHoldTracking updateWithHoldTracking(String trackingId, WithHoldTracking updatedTracking);
    void deleteTracking(String id);

    Page<WithHoldTracking> findEmployeeWithPagination(Pageable pageable);
}
