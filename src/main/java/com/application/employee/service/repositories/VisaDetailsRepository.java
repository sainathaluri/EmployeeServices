package com.application.employee.service.repositories;

import com.application.employee.service.entities.VisaDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisaDetailsRepository extends JpaRepository<VisaDetails, String> {
}
