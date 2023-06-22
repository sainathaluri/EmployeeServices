package com.application.employee.service.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
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
}
