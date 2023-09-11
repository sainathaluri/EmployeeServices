package com.application.employee.service.services.implementations;
import com.application.employee.service.entities.WithHoldTracking;
import com.application.employee.service.repositories.WithHoldTrackingRepository;
import com.application.employee.service.services.WithHoldTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        BigDecimal actualHours = withHoldTracking.getActualHours();
        BigDecimal actualRate = withHoldTracking.getActualRate();
        BigDecimal paidHours = withHoldTracking.getPaidHours();
        BigDecimal paidRate = withHoldTracking.getPaidRate();

        if (actualHours != null && actualRate != null) {
            BigDecimal actualAmount = actualHours.multiply(actualRate);
            withHoldTracking.setActualAmt(actualAmount);
            if (!withHoldTracking.getActualAmt().equals(actualAmount)) {
                throw new IllegalArgumentException("Actual amount is not consistent.");
            }
        }

        if (paidHours != null && paidRate != null) {
            BigDecimal paidAmount = paidHours.multiply(paidRate);
            withHoldTracking.setPaidAmt(paidAmount);
            if (!withHoldTracking.getPaidAmt().equals(paidAmount)) {
                throw new IllegalArgumentException("Paid amount is not consistent.");
            }
        }

        if (withHoldTracking.getActualAmt() != null && withHoldTracking.getPaidAmt() != null) {
            BigDecimal balance = withHoldTracking.getActualAmt().subtract(withHoldTracking.getPaidAmt());
            withHoldTracking.setBalance(balance);
            if (!withHoldTracking.getBalance().equals(balance)) {
                throw new IllegalArgumentException("Balance amount is not consistent.");
            }
        }

        return repository.save(withHoldTracking);
    }


    @Override
    public WithHoldTracking updateWithHoldTracking(String trackingId, WithHoldTracking updatedTracking) {
        WithHoldTracking existingTracking = getWithHoldTrackingById(trackingId);

        existingTracking.setMonth(updatedTracking.getMonth());
        existingTracking.setYear(updatedTracking.getYear());
        existingTracking.setProjectName(updatedTracking.getProjectName());
        existingTracking.setActualHours(updatedTracking.getActualHours());
        existingTracking.setActualRate(updatedTracking.getActualRate());
        existingTracking.setActualAmt(updatedTracking.getActualAmt());
        existingTracking.setPaidHours(updatedTracking.getPaidHours());
        existingTracking.setPaidRate(updatedTracking.getPaidRate());
        existingTracking.setPaidAmt(updatedTracking.getPaidAmt());
        existingTracking.setBalance(updatedTracking.getBalance());
        existingTracking.setExcelData(updatedTracking.getExcelData());

        return repository.save(existingTracking);
    }

    @Override
    public void deleteTracking(String id) {
        WithHoldTracking tracking = getWithHoldTrackingById(id);
        repository.delete(tracking);
    }

    @Override
    public Page<WithHoldTracking> findEmployeeWithPagination(Pageable pageable) {
        return repository.findAll(pageable);
    }

}
