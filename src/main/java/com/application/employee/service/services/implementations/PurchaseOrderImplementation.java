package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.PurchaseOrderRepository;
import com.application.employee.service.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PurchaseOrderImplementation implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public PurchaseOrder saveOrder(PurchaseOrder e) {
        String randomOrderId = UUID.randomUUID().toString();
        e.setOrderId(randomOrderId);
        return purchaseOrderRepository.save(e);
    }

    @Override
    public List<PurchaseOrder> getAllOrders() {
        return purchaseOrderRepository.findAll();
    }

    @Override
    public PurchaseOrder getOrder(String id) {
        return purchaseOrderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Order not found with given orderID: " + id));
    }
}
