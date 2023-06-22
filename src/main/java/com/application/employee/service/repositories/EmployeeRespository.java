package com.application.employee.service.repositories;

import com.application.employee.service.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee, String> {
}
