package com.application.employee.service.entities;

import com.application.employee.service.deserializer.CustomLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Table(name = "purchase_orders")
public class PurchaseOrder {

    @Id
    @Column(name = "ID")
    private String orderId;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "DOJ")
    private LocalDate dateOfJoining;

    @JsonSerialize(using = CustomLocalDateSerializer.class)
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
