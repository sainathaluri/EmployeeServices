package com.application.employee.service.controllers;

import com.application.employee.service.entities.Employee;
import com.application.employee.service.entities.PurchaseOrder;
import com.application.employee.service.entities.WithHoldTracking;
import com.application.employee.service.exceptions.ResourceNotFoundException;
import com.application.employee.service.repositories.EmployeeRespository;
import com.application.employee.service.services.EmployeeService;
import com.application.employee.service.services.PurchaseOrderService;
import com.application.employee.service.services.WithHoldTrackingService;
import com.application.employee.service.services.implementations.PurchaseOrderImplementation;
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
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private WithHoldTrackingService withHoldTrackingService;

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employe) {
        Employee employee = employeeService.saveEmployee(employe);
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
    @PostMapping("/{employeeId}/orders")
    public PurchaseOrder createOrder(@PathVariable(value = "employeeId") String employeeId, @RequestBody PurchaseOrder order) {
        Employee employee = employeeService.getEmployee(employeeId);
        order.setEmployee(employee);
        return purchaseOrderService.saveOrder(order);
    }

    @GetMapping("/{employeeId}/orders")
    public ResponseEntity<List<PurchaseOrder>> getEmployeeOrders(@PathVariable(value = "employeeId") String employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<PurchaseOrder> orders = employee.getEmployeePurchaseOrder();
        return ResponseEntity.ok().body(orders);
    }

    @PostMapping("/{employeeId}/trackings")
    public WithHoldTracking createTracking(@PathVariable(value = "employeeId") String employeeId, @RequestBody WithHoldTracking track) {
        Employee employee = employeeService.getEmployee(employeeId);
        track.setEmployee(employee);
        return withHoldTrackingService.saveWithHoldTracking(track);
    }

    @GetMapping("/{employeeId}/trackings")
    public ResponseEntity<List<WithHoldTracking>> getEmployeeWithHold(@PathVariable(value = "employeeId") String employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<WithHoldTracking> tracking = employee.getEmployeeWithHoldTracking();
        return ResponseEntity.ok().body(tracking);
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
