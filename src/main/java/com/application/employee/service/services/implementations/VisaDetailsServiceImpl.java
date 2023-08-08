package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.VisaDetails;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.VisaDetailsRepository;
import com.application.employee.service.services.VisaDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public class VisaDetailsServiceImpl implements VisaDetailsService {
    @Autowired
    private VisaDetailsRepository visaDetailsRepository;

    @Override
    public VisaDetails saveDetails(VisaDetails details) {
        String randomVisaID = UUID.randomUUID().toString();
        details.setVisaId(randomVisaID);
        return visaDetailsRepository.save(details);
    }
    @Override
    public List<VisaDetails> getAllDetails() {
        return visaDetailsRepository.findAll();
    }
    @Override
    public VisaDetails getVisaDetailsById(String id) {
        return visaDetailsRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Visa Details not found with given id" + id)
        );
    }

    @Override
    public VisaDetails updateVisaDetails(String id, VisaDetails updateVisaDetails) {
        VisaDetails existingDetails = getVisaDetailsById(id);
        existingDetails.setVisaType(updateVisaDetails.getVisaType());
        existingDetails.setVisaStartDate(updateVisaDetails.getVisaStartDate());
        existingDetails.setVisaExpiryDate(updateVisaDetails.getVisaExpiryDate());
        return visaDetailsRepository.save(existingDetails);
    }

    @Override
    public void deleteVisaDetails(String id) {
        VisaDetails details = getVisaDetailsById(id);
        visaDetailsRepository.delete(details);
    }
}
