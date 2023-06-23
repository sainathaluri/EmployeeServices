package com.application.employee.service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "ID")
    private String employeeID;

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "EMAIL ID")
    private String emailID;

    @Column(name = "VISA STATUS")
    private String visaStatus;

    @Column(name = "DATE OF BIRTH")
    @JsonFormat(pattern="MM-dd-yyyy")
    private LocalDate dob;

    @Column(name = "COLLEGE OF GRADUATION")
    private String clgOfGrad;

    @Column(name = "VISA START DATE")
    @JsonFormat(pattern="MM-dd-yyyy")
    private LocalDate visaStartDate;

    @Column(name = "VISA EXPIRY DATE")
    @JsonFormat(pattern="MM-dd-yyyy")
    private LocalDate visaExpiryDate;

    @Column(name = "ON BENCH")
    private boolean onBench;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "employee_id",referencedColumnName = "ID")
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<EmployeePO> employeePurchaseOrder;
}
