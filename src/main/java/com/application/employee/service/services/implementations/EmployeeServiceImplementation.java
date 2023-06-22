package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.EmployeeRespository;
import com.application.employee.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;

    @Override
    public Employee saveEmployee(Employee e) {
        String randomEmployeeID = UUID.randomUUID().toString();
        e.setEmployeeID(randomEmployeeID);
        return employeeRespository.save(e);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRespository.findAll();
    }

    @Override
    public Employee getEmployee(String id) {
        return employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given employeeID: " + id));
    }

    @Override
    public Page<Employee> findEmployeeWithPagination(int offset, int pageSize) {
        Page<Employee> employee = employeeRespository.findAll(PageRequest.of(offset, pageSize));
        return employee;
    }

    @Override
    public Page<Employee> findEmployeeWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Employee> employee = employeeRespository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return employee;
    }


}
