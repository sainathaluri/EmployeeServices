package com.application.employee.service.controllers;
import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
//    @GetMapping
//    public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrder() {
//        List<PurchaseOrder> orderList = purchaseOrderService.getAllOrders();
//        return ResponseEntity.ok(orderList);
//    }
    @GetMapping
    public ResponseEntity<Page<PurchaseOrder>> getAllPurchaseOrder(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<PurchaseOrder> orderPage = purchaseOrderService.findOrdersByEmployee(pageable);
        return ResponseEntity.ok(orderPage);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable String orderId) {
        purchaseOrderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
