package com.application.employee.service.services;

import com.application.employee.service.entities.ProjectHistory;
import com.application.employee.service.entities.VisaDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VisaDetailsService {
    VisaDetails saveDetails(VisaDetails details);
    List<VisaDetails> getAllDetails();
    VisaDetails getVisaDetailsById(String id);
    VisaDetails updateVisaDetails(String id, VisaDetails updateVisaDetails);
    void deleteVisaDetails(String id);
    Page<VisaDetails> findVisaDetailsByEmployee(Pageable pageable);
}
