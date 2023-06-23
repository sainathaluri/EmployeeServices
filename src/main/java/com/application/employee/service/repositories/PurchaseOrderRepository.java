package com.application.employee.service.repositories;

import com.application.employee.service.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,String> {

}
