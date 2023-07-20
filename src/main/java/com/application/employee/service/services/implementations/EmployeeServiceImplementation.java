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
    public Employee updateEmployee(String id, Employee e) {
        Employee existingEmployee = employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given employeeID: " + id));

        existingEmployee.setFirstName(e.getFirstName());
        existingEmployee.setLastName(e.getLastName());
        existingEmployee.setEmailID(e.getEmailID());
        existingEmployee.setVisaStatus(e.getVisaStatus());
        existingEmployee.setDob(e.getDob());
        existingEmployee.setClgOfGrad(e.getClgOfGrad());
        existingEmployee.setVisaStartDate(e.getVisaStartDate());
        existingEmployee.setVisaExpiryDate(e.getVisaExpiryDate());
        existingEmployee.setOnBench(e.getOnBench());

        return employeeRespository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee(String id) {
        Employee existingEmployee = employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given employeeID: " + id));

        employeeRespository.delete(existingEmployee);
    }

    @Override
    public Page<Employee> findEmployeeWithPagination(Pageable pageable) {
        return employeeRespository.findAll(pageable);
    }

    @Override
    public Page<Employee> findEmployeeWithPaginationAndSorting(Pageable pageable, String field) {
        Pageable pageableWithSort = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(field));
        return employeeRespository.findAll(pageableWithSort);
    }
//    @Override
//    public Page<Employee> findEmployeeWithPagination(int offset, int pageSize) {
//        Page<Employee> employee = employeeRespository.findAll(PageRequest.of(offset, pageSize));
//        return employee;
//    }
//
//    @Override
//    public Page<Employee> findEmployeeWithPaginationAndSorting(int offset, int pageSize, String field) {
//        Page<Employee> employee = employeeRespository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
//        return employee;
//    }

}
