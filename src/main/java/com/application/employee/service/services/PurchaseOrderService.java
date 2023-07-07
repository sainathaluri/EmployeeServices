package com.application.employee.service.services;
import com.application.employee.service.entities.PurchaseOrder;
import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder saveOrder(PurchaseOrder e);

    List<PurchaseOrder> getAllOrders();

    PurchaseOrder getOrder(String id);
}
