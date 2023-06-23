package com.application.employee.service.controllers;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee e) {
        Employee employee = employeeService.saveEmployee(e);
        return ResponseEntity.status(HttpStatus.CREATED).body(employee);
    }
    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable String employeeID) {
        Employee employee = employeeService.getEmployee(employeeID);
        return ResponseEntity.ok(employee);
    }
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employeeList = employeeService.getAllEmployee();
        return ResponseEntity.ok(employeeList);
    }
    @GetMapping("/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<Employee>> getEmployeeByPagination(@PathVariable int offset, @PathVariable int pageSize){
        Page<Employee> employeeWithPagination = employeeService.findEmployeeWithPagination(offset, pageSize);
        return ResponseEntity.ok(employeeWithPagination);
    }
    @GetMapping("/paginationandsort/{offset}/{pageSize}/{field}")
    public ResponseEntity<Page<Employee>> getEmployeeByPaginationAndSorting(@PathVariable int offset,
                                                                            @PathVariable int pageSize,
                                                                            @PathVariable String field){
        Page<Employee> employeeWithPagination = employeeService.findEmployeeWithPaginationAndSorting(offset, pageSize, field);
        return ResponseEntity.ok(employeeWithPagination);
    }

}
