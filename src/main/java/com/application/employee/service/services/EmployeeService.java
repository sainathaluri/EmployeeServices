package com.application.employee.service.services;

import com.application.employee.service.entities.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee e);

    List<Employee> getAllEmployee();

    Employee getEmployee(String id);

    Employee updateEmployee(String id, Employee e);

    void createProspectEmployee(Employee employee);

    void updateProspectEmployee(String id, Employee employee);

    void deleteEmployee(String id);

    Page<Employee> findEmployeeWithPagination(Pageable pageable);

    Page<Employee> findEmployeeWithPaginationAndSorting(Pageable pageable, String field);

}
