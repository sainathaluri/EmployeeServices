package com.application.employee.service.controllers;
import com.application.employee.service.entities.*;
import com.application.employee.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private WithHoldTrackingService withHoldTrackingService;
    @Autowired
    private ProjectHistoryService projectHistoryService;
    @Autowired
    private VisaDetailsService visaDetailsService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employe) {
        Employee employee = employeeService.saveEmployee(employe);
        return ResponseEntity.status(HttpStatus.CREATED).body("Employee created successfully");
    }
    @GetMapping("/{employeeID}")
    public ResponseEntity<Employee> getEmployeeByID(@PathVariable String employeeID) {
        Employee employee = employeeService.getEmployee(employeeID);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> getAllEmployee(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> employees = employeeService.findEmployeeWithPagination(pageable);
        return ResponseEntity.ok(employees);
    }
    @PutMapping("/{employeeID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateEmployee(@PathVariable String employeeID, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeID, employee);
        return ResponseEntity.ok("Employee updated successfully");
    }

    @PostMapping("prospect/{employeeID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createProspectEmployee(@RequestBody Employee employee) {
        employeeService.createProspectEmployee(employee);
        return ResponseEntity.ok("Prospect added successfully");
    }

    @PutMapping("prospect/{employeeID}")
    @PreAuthorize("hasRole('PROSPECT')")
    public ResponseEntity<String> updateProspectEmployee(@PathVariable String employeeID, @RequestBody Employee employee) {
        employeeService.updateProspectEmployee(employeeID,employee);
        return ResponseEntity.ok("Prospect added successfully");
    }

    @DeleteMapping("/{employeeID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeID) {
        employeeService.deleteEmployee(employeeID);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PostMapping("/{employeeId}/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public PurchaseOrder createOrder(@PathVariable(value = "employeeId") String employeeId, @RequestBody PurchaseOrder order) {
        Employee employee = employeeService.getEmployee(employeeId);
        order.setEmployee(employee);
        return purchaseOrderService.saveOrder(order);
    }
    @PutMapping("/orders/{orderID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PurchaseOrder> updateOrder(
            @PathVariable("orderID") String orderID,
            @RequestBody PurchaseOrder updatedOrder
    ) {
        PurchaseOrder existingOrder = purchaseOrderService.getOrder(orderID);
        updatedOrder.setOrderId(orderID);
        PurchaseOrder updatedPurchaseOrder = purchaseOrderService.updateOrder(orderID, updatedOrder);
        return ResponseEntity.ok(updatedPurchaseOrder);
    }
//    @GetMapping("/{employeeId}/orders")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<PurchaseOrder>> getEmployeeOrders(@PathVariable(value = "employeeId") String employeeId) {
//        Employee employee = employeeService.getEmployee(employeeId);
//        List<PurchaseOrder> orders = employee.getEmployeePurchaseOrder();
//        return ResponseEntity.ok().body(orders);
//    }
@GetMapping("/{employeeId}/orders")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Page<PurchaseOrder>> getEmployeeOrders(
        @PathVariable(value = "employeeId") String employeeId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Employee employee = employeeService.getEmployee(employeeId);

    Pageable pageable = PageRequest.of(page, size);
    Page<PurchaseOrder> paginatedOrders = purchaseOrderService.findOrdersByEmployee(pageable);

    return ResponseEntity.ok(paginatedOrders);
}

    @PostMapping("/{employeeId}/trackings")
    @PreAuthorize("hasRole('ADMIN')")
    public WithHoldTracking createTracking(@PathVariable(value = "employeeId") String employeeId, @RequestBody WithHoldTracking track) {
        Employee employee = employeeService.getEmployee(employeeId);
        track.setEmployee(employee);
        return withHoldTrackingService.saveWithHoldTracking(track);
    }
    @PutMapping("/trackings/{trackingID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WithHoldTracking> updateWithHoldTracking(
            @PathVariable("trackingID") String trackingID,
            @RequestBody WithHoldTracking updatedTracking
    ) {
        WithHoldTracking existingTracking = withHoldTrackingService.getWithHoldTrackingById(trackingID);
        updatedTracking.setTrackingId(trackingID);
        WithHoldTracking updatedWithHoldTracking = withHoldTrackingService.updateWithHoldTracking(trackingID, updatedTracking);
        return ResponseEntity.ok(updatedWithHoldTracking);
    }
    @GetMapping("/{employeeId}/trackings")
    public ResponseEntity<List<WithHoldTracking>> getEmployeeWithHold(@PathVariable(value = "employeeId") String employeeId) {
        Employee employee = employeeService.getEmployee(employeeId);
        List<WithHoldTracking> tracking = employee.getEmployeeWithHoldTracking();
        return ResponseEntity.ok().body(tracking);
    }
    @PostMapping("/{employeeId}/projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ProjectHistory createHistory(@PathVariable(value = "employeeId") String employeeId, @RequestBody ProjectHistory history){
        Employee employee = employeeService.getEmployee(employeeId);
        history.setEmployee(employee);
        return projectHistoryService.saveProjectHistory(history);
    }
    @PutMapping("/projects/{projectID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectHistory> updateHistory(
            @PathVariable("projectID") String projectID,
            @RequestBody ProjectHistory updateHistory
    ) {
        ProjectHistory existingProjectHistory = projectHistoryService.getProjectHistoryById(projectID);
        updateHistory.setProjectId(projectID);
        ProjectHistory updateProjectHistory = projectHistoryService.updateProjectHistory(projectID,updateHistory);
        return ResponseEntity.ok(updateProjectHistory);
    }
//    @GetMapping("/{employeeId}/projects")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<ProjectHistory>> getEmployeeProjectHistory(@PathVariable(value = "employeeId") String employeeId){
//        Employee employee = employeeService.getEmployee(employeeId);
//        List<ProjectHistory> historyList = employee.getEmployeeProjectHistory();
//        return ResponseEntity.ok().body(historyList);
//    }
@GetMapping("/{employeeId}/projects")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Page<ProjectHistory>> getEmployeeProjectHistory(
        @PathVariable(value = "employeeId") String employeeId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
) {
    Employee employee = employeeService.getEmployee(employeeId);

    Pageable pageable = PageRequest.of(page, size);
    Page<ProjectHistory> paginatedHistory = projectHistoryService.findProjectHistoryByEmployee(pageable);

    return ResponseEntity.ok(paginatedHistory);
}


    @PostMapping("/{employeeId}/visa-details")
    @PreAuthorize("hasRole('ADMIN')")
    public VisaDetails createDetails(@PathVariable(value = "employeeId") String employeeId, @RequestBody VisaDetails details){
        Employee employee = employeeService.getEmployee(employeeId);
        details.setEmployee(employee);
        return visaDetailsService.saveDetails(details);
    }
    @PutMapping("/visa-details/{visaID}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VisaDetails> updateVisaDetails(
            @PathVariable("visaID") String visaID,
            @RequestBody VisaDetails updateVisaDetails
    ){
        VisaDetails existingVisaDetails = visaDetailsService.getVisaDetailsById(visaID);
        updateVisaDetails.setVisaId(visaID);
        VisaDetails updateDetails = visaDetailsService.updateVisaDetails(visaID,updateVisaDetails);
        return ResponseEntity.ok(updateDetails);
    }
//    @GetMapping("/{employeeId}/visa-details")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<List<VisaDetails>> getEmployeeVisaDetails(@PathVariable(value = "employeeId") String employeeId){
//        Employee employee = employeeService.getEmployee(employeeId);
//        List<VisaDetails> detailsList = employee.getEmployeeVisaDetails();
//        return ResponseEntity.ok().body(detailsList);
//    }
    @GetMapping("/{employeeId}/visa-details")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<VisaDetails>> getEmployeeVisaDetails(
            @PathVariable(value = "employeeId") String employeeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Employee employee = employeeService.getEmployee(employeeId);

        Pageable pageable = PageRequest.of(page, size);
        Page<VisaDetails> paginatedDetails = visaDetailsService.findVisaDetailsByEmployee( pageable);

        return ResponseEntity.ok(paginatedDetails);
    }

}


//    @PutMapping("/{employeeID}/orders/{orderID}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<PurchaseOrder> updateOrder(
//            @PathVariable("employeeID") String employeeID,
//            @PathVariable("orderID") String orderID,
//            @RequestBody PurchaseOrder updatedOrder
//    ) {
//        Employee employee = employeeService.getEmployee(employeeID);
//        PurchaseOrder existingOrder = purchaseOrderService.getOrder(orderID);
//
//        if (existingOrder.getEmployee().getEmployeeID().equals(employeeID)) {
//            updatedOrder.setOrderId(orderID);
//            updatedOrder.setEmployee(employee);
//            PurchaseOrder updatedPurchaseOrder = purchaseOrderService.updateOrder(orderID,updatedOrder);
//            return ResponseEntity.ok(updatedPurchaseOrder);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }


//        @GetMapping
//    public ResponseEntity<List<Employee>> getAllEmployee() {
//        List<Employee> employeeList = employeeService.getAllEmployee();
//        return ResponseEntity.ok(employeeList);
//    }
//    @GetMapping("/pagination/{offset}/{pageSize}")
//    public ResponseEntity<List<Employee>> getEmployeeByPagination(@PathVariable int offset, @PathVariable int pageSize){
//        List<Employee> employeeWithPagination = employeeService.findEmployeeWithPagination(offset, pageSize).getContent();
//        return ResponseEntity.ok(employeeWithPagination);
//    }
//    @GetMapping("/pagination/{offset}/{pageSize}")
//    public ResponseEntity<Page<Employee>> getEmployeeByPagination(@PathVariable int offset, @PathVariable int pageSize){
//        Page<Employee> employeeWithPagination = employeeService.findEmployeeWithPagination(offset, pageSize);
//        return ResponseEntity.ok(employeeWithPagination);
//    }
//    @GetMapping("/paginationandsort/{offset}/{pageSize}/{field}")
//    public ResponseEntity<Page<Employee>> getEmployeeByPaginationAndSorting(@PathVariable int offset,
//                                                                            @PathVariable int pageSize,
//                                                                            @PathVariable String field){
//        Page<Employee> employeeWithPagination = employeeService.findEmployeeWithPaginationAndSorting(offset, pageSize, field);
//        return ResponseEntity.ok(employeeWithPagination);
//    }

