package com.application.employee.service.services.implementations;
import com.application.employee.service.entities.WithHoldTracking;
import com.application.employee.service.repositories.WithHoldTrackingRepository;
import com.application.employee.service.services.WithHoldTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        BigDecimal actualAmount = withHoldTracking.getActualHours().multiply(withHoldTracking.getActualRate());
        withHoldTracking.setActualAmt(actualAmount);

        BigDecimal paidAmount = withHoldTracking.getPaidHours().multiply(withHoldTracking.getPaidRate());
        withHoldTracking.setPaidAmt(paidAmount);

        BigDecimal balance = withHoldTracking.getActualAmt().subtract(withHoldTracking.getPaidAmt());
        withHoldTracking.setBalance(balance);
        return repository.save(withHoldTracking);
    }

}
