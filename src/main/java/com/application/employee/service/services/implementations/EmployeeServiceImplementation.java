package com.application.employee.service.services.implementations;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.EmployeeRespository;
import com.application.employee.service.repositories.UserRepository;
import com.application.employee.service.services.EmployeeService;
import com.application.employee.service.user.Role;
import com.application.employee.service.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    @Autowired
    private EmployeeRespository employeeRespository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Employee saveEmployee(Employee employee) {
        String randomEmployeeID = UUID.randomUUID().toString();
        employee.setEmployeeID(randomEmployeeID);
        Employee savedEmployee;
        savedEmployee = employeeRespository.save(employee);

        User newUser = new User();
        newUser.setId(employee.getEmployeeID());
        newUser.setFirstname(employee.getFirstName());
        newUser.setLastname(employee.getLastName());
        newUser.setEmail(employee.getEmail());
        String hashedPassword = passwordEncoder.encode(employee.getPassword());
        newUser.setPassword(hashedPassword);
        newUser.setRole(Role.EMPLOYEE);
        userRepository.save(newUser);

        return savedEmployee;
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
    public Employee updateEmployee(String id, Employee employee) {
        Employee existingEmployee = employeeRespository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with given employeeID: " + id));

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailID(employee.getEmailID());
        existingEmployee.setVisaStatus(employee.getVisaStatus());
        existingEmployee.setDob(employee.getDob());
        existingEmployee.setPhoneNo(employee.getPhoneNo());
        existingEmployee.setClgOfGrad(employee.getClgOfGrad());
//        existingEmployee.setVisaStartDate(employee.getVisaStartDate());
//        existingEmployee.setVisaExpiryDate(employee.getVisaExpiryDate());
        existingEmployee.setOnBench(employee.getOnBench());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setPassword(employee.getPassword());

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
