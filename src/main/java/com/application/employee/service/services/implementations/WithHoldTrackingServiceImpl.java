package com.application.employee.service.services.implementations;
import com.application.employee.service.entities.WithHoldTracking;
import com.application.employee.service.repositories.WithHoldTrackingRepository;
import com.application.employee.service.services.WithHoldTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class WithHoldTrackingServiceImpl implements WithHoldTrackingService {
    @Autowired
    private WithHoldTrackingRepository repository;

    @Override
    public List<WithHoldTracking> getAllWithHoldTracking() {
        return repository.findAll();
    }

    @Override
    public WithHoldTracking getWithHoldTrackingById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public WithHoldTracking saveWithHoldTracking(WithHoldTracking withHoldTracking) {
        String randomTrackingId = UUID.randomUUID().toString();
        withHoldTracking.setTrackingId(randomTrackingId);
        return repository.save(withHoldTracking);
    }

}
