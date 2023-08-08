package com.application.employee.service.controllers;

import com.application.employee.service.entities.VisaDetails;
import com.application.employee.service.services.VisaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visa-details")
@CrossOrigin(origins = "*")
public class VisaDetailsController {
    @Autowired
    private VisaDetailsService visaDetailsService;

    @GetMapping("/{visaDetailsId}")
    public ResponseEntity<VisaDetails> getDetailsById(@PathVariable String visaDetailsId){
        VisaDetails details = visaDetailsService.getVisaDetailsById(visaDetailsId);
        return ResponseEntity.ok(details);
    }
    @GetMapping
    public ResponseEntity<List<VisaDetails>> getAllVisaDetails() {
        List<VisaDetails> detailsList = visaDetailsService.getAllDetails();
        return ResponseEntity.ok(detailsList);
    }
    @DeleteMapping("/{visaDetailsId}")
    public ResponseEntity<Void> deleteProjectHistory(@PathVariable String visaDetailsId){
        visaDetailsService.getVisaDetailsById(visaDetailsId);
        return ResponseEntity.noContent().build();
    }
}
