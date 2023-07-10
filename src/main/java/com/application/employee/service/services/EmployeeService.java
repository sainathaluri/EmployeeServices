package com.application.employee.service.services;

import com.application.employee.service.entities.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(Employee e);

    List<Employee> getAllEmployee();

    Employee getEmployee(String id);

    Employee updateEmployee(String id, Employee e);

    void deleteEmployee(String id);

    Page<Employee> findEmployeeWithPagination(int offset, int pageSize);

    Page<Employee> findEmployeeWithPaginationAndSorting(int offset, int pageSize, String field);
}
