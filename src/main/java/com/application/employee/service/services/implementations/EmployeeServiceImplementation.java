package com.application.employee.service.services.implementations;

import com.application.employee.service.auth.AuthenticationService;
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
    @Autowired
    private AuthenticationService authenticationService;

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
        newUser.setEmail(employee.getEmailID());
        newUser.setRole(Role.EMPLOYEE);
        newUser.setPassword(passwordEncoder.encode(employee.getPassword()));
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
        User existingUser = userRepository.findById(id);
        if(existingUser == null ){
            existingUser = new User();
            existingUser.setId(id);
        }
        existingUser.setEmail(employee.getEmailID());
        existingUser.setRole(Role.EMPLOYEE);
        existingUser.setFirstname(employee.getFirstName());
        existingUser.setLastname(employee.getLastName());
        userRepository.save(existingUser);
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmailID(employee.getEmailID());
        existingEmployee.setDob(employee.getDob());
        existingEmployee.setPhoneNo(employee.getPhoneNo());
        existingEmployee.setClgOfGrad(employee.getClgOfGrad());
//        existingEmployee.setVisaStartDate(employee.getVisaStartDate());
//        existingEmployee.setVisaExpiryDate(employee.getVisaExpiryDate());
        existingEmployee.setOnBench(employee.getOnBench());
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


}
