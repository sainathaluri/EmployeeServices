package com.application.employee.service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class PurchaseOrder {

    @Id
    @Column(name = "ID")
    private String orderId;

    @JsonFormat(pattern="MM-dd-yyyy")
    @Column(name = "DOJ")
    private LocalDate dateOfJoining;

    @JsonFormat(pattern="MM-dd-yyyy")
    @Column(name = "PED")
    private LocalDate projectEndDate;

    @Column(name = "BILLRATE")
    private int billRate;

    @Column(name = "ENDCLIENT")
    private String endClientName;

    @Column(name = "PHONENO")
    private String vendorPhoneNo;

    @Column(name = "emailId")
    private String vendorEmailId;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
