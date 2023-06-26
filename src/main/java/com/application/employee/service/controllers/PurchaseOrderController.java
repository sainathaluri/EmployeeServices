package com.application.employee.service.controllers;
import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<PurchaseOrder> createPurchaseOrder(@RequestBody PurchaseOrder order){
        PurchaseOrder purchaseOrder = purchaseOrderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<PurchaseOrder> getPurchasedOrderById(@PathVariable String orderId){
        PurchaseOrder order = purchaseOrderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }
    @GetMapping
    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrder() {
        List<PurchaseOrder> orderList = purchaseOrderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }
}
