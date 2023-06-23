package com.application.employee.service.controllers;

import com.application.employee.service.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchaseorder")
@CrossOrigin(origins = "*")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping
    public ResponseEntity<com.application.employee.service.entities.PurchaseOrder> createPurchaseOrder(@RequestBody com.application.employee.service.entities.PurchaseOrder order){
        com.application.employee.service.entities.PurchaseOrder purchaseOrder = purchaseOrderService.saveOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<com.application.employee.service.entities.PurchaseOrder> getPurchasedOrderById(@PathVariable String orderId){
        com.application.employee.service.entities.PurchaseOrder order = purchaseOrderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }
    @GetMapping
    public ResponseEntity<List<com.application.employee.service.entities.PurchaseOrder>> getAllPurchaseOrder() {
        List<com.application.employee.service.entities.PurchaseOrder> orderList = purchaseOrderService.getAllOrders();
        return ResponseEntity.ok(orderList);
    }
}
