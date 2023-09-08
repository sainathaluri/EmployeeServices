package com.application.employee.service.services;
import com.application.employee.service.entities.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder saveOrder(PurchaseOrder e);

    List<PurchaseOrder> getAllOrders();

    PurchaseOrder getOrder(String id);
    PurchaseOrder updateOrder(String id, PurchaseOrder updatedOrder);
    void deleteOrder(String id);
    Page<PurchaseOrder> findOrdersByEmployee(Pageable pageable);
}
