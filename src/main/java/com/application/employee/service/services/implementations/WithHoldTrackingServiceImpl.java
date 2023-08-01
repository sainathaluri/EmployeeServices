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
        if(!(withHoldTracking.getActualAmt() == actualAmount)){
            throw new IllegalArgumentException("Actual amount is not consistent.");
        }

        BigDecimal paidAmount = withHoldTracking.getPaidHours().multiply(withHoldTracking.getPaidRate());
        withHoldTracking.setPaidAmt(paidAmount);
        if(!(withHoldTracking.getPaidAmt() == paidAmount)){
            throw new IllegalArgumentException("Paid amount is not consistent.");
        }

        BigDecimal balance = withHoldTracking.getActualAmt().subtract(withHoldTracking.getPaidAmt());
        withHoldTracking.setBalance(balance);
        if(!(withHoldTracking.getBalance() == balance)){
            throw new IllegalArgumentException("Balance amount is not consistent.");
        }
        return repository.save(withHoldTracking);
    }

    @Override
    public WithHoldTracking updateWithHoldTracking(String trackingId, WithHoldTracking updatedTracking) {
        WithHoldTracking existingTracking = getWithHoldTrackingById(trackingId);

        existingTracking.setMonth(updatedTracking.getMonth());
        existingTracking.setYear(updatedTracking.getYear());
        existingTracking.setActualHours(updatedTracking.getActualHours());
        existingTracking.setActualRate(updatedTracking.getActualRate());
        existingTracking.setActualAmt(updatedTracking.getActualAmt());
        existingTracking.setPaidHours(updatedTracking.getPaidHours());
        existingTracking.setPaidRate(updatedTracking.getPaidRate());
        existingTracking.setPaidAmt(updatedTracking.getPaidAmt());
        existingTracking.setBalance(updatedTracking.getBalance());

        return repository.save(existingTracking);
    }

}
