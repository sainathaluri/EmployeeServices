package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.PurchaseOrderRepository;
import com.application.employee.service.services.EmployeeService;
import com.application.employee.service.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class PurchaseOrderImplementation implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private EmployeeService employeeService;

    @Override
    public PurchaseOrder saveOrder(PurchaseOrder order) {
        String randomOrderID = UUID.randomUUID().toString();
        order.setOrderId(randomOrderID);
        return purchaseOrderRepository.save(order);
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

    @Override
    public PurchaseOrder updateOrder(String id, PurchaseOrder updatedOrder) {
        PurchaseOrder existingOrder = getOrder(id);

        existingOrder.setDateOfJoining(updatedOrder.getDateOfJoining());
        existingOrder.setProjectEndDate(updatedOrder.getProjectEndDate());
        existingOrder.setBillRate(updatedOrder.getBillRate());
        existingOrder.setEndClientName(updatedOrder.getEndClientName());
        existingOrder.setVendorPhoneNo(updatedOrder.getVendorPhoneNo());
        existingOrder.setVendorEmailId(updatedOrder.getVendorEmailId());

        return purchaseOrderRepository.save(existingOrder);
    }

    @Override
    public void deleteOrder(String id) {
        PurchaseOrder order = getOrder(id);
        purchaseOrderRepository.delete(order);
    }

    @Override
    public Page<PurchaseOrder> findOrdersByEmployee(Pageable pageable) {
            return purchaseOrderRepository.findAll(pageable);
    }
}
