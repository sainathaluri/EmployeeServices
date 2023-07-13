package com.application.employee.service.controllers;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.entities.WithHoldTracking;
import com.application.employee.service.services.WithHoldTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/trackings")
@CrossOrigin(origins = "*")
public class WithHoldTrackingController {
    @Autowired
    private WithHoldTrackingService service;

    @GetMapping
    public ResponseEntity<List<WithHoldTracking>> getAllWithHoldTracking() {
        List<WithHoldTracking> trackingList = service.getAllWithHoldTracking();
        return ResponseEntity.ok(trackingList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WithHoldTracking> getWithHoldTrackingById(@PathVariable String id){
        WithHoldTracking tracking = service.getWithHoldTrackingById(id);
        return ResponseEntity.ok(tracking);
    }

    @PostMapping
    public ResponseEntity<WithHoldTracking> saveWithHoldTracking(@RequestBody WithHoldTracking withHoldTracking){
        WithHoldTracking tracking = service.saveWithHoldTracking(withHoldTracking);

        return ResponseEntity.status(HttpStatus.CREATED).body(tracking);
    }

}
