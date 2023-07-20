package com.application.employee.service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class PurchaseOrder {
    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "orderId='" + orderId + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                ", projectEndDate=" + projectEndDate +
                ", billRate=" + billRate +
                ", endClientName='" + endClientName + '\'' +
                ", vendorPhoneNo='" + vendorPhoneNo + '\'' +
                ", vendorEmailId='" + vendorEmailId + '\'' +
                ", employee=" + employee +
                '}';
    }

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
