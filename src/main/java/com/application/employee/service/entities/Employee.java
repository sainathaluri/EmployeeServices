//package com.application.employee.service.entities;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "employees")
//public class Employee {
//
//    @Id
//    @Column(name = "ID")
//    private String employeeID;
//
//    @Column(name = "FIRSTNAME")
//    private String firstName;
//
//    @Column(name = "LASTNAME")
//    private String lastName;
//
//    @Column(name = "EMAILID")
//    private String emailID;
//
//    @Column(name = "VISA_STATUS")
//    private String visaStatus;
//
//    @Column(name = "DATE_OF_BIRTH")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate dob;
//
//    @Column(name = "COLLEGE_OF_GRADUATION")
//    private String clgOfGrad;
//
//    @Column(name = "VISA_START_DATE")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate visaStartDate;
//
//    @Column(name = "VISA_EXPIRY_DATE")
//    @JsonFormat(pattern = "yyyy-MM-dd")
//    private LocalDate visaExpiryDate;
//
//    @Column(name = "ON_BENCH")
//    private String onBench;
//
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<PurchaseOrder> employeePurchaseOrder;
//
//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    private List<WithHoldTracking> employeeWithHoldTracking;
//
//}

//package com.application.employee.service.entities;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "employees")
//public class Employee {
//
//    @Id
//    @Column(name = "ID")
//    private String employeeID;
//
//    @Column(name = "FIRSTNAME")
//    private String firstName;
//
//    @Column(name = "LASTNAME")
//    private String lastName;
//
//    @Column(name = "EMAIL ID")
//    private String emailID;
//
//    @Column(name = "VISA STATUS")
//    private String visaStatus;
//
//    @Column(name = "DATE OF BIRTH")
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate dob;
//
//    @Column(name = "COLLEGE OF GRADUATION")
//    private String clgOfGrad;
//
//    @Column(name = "VISA START DATE")
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate visaStartDate;
//
//    @Column(name = "VISA EXPIRY DATE")
//    @JsonFormat(pattern="yyyy-MM-dd")
//    private LocalDate visaExpiryDate;
//
//    @Column(name = "ON BENCH")
//    private boolean onBench;
//
//
//    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
//    private List<PurchaseOrder> employeePurchaseOrder;
//
//    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
//    private List<WithHoldTracking> employeeWithHoldTracking;
//
//}
package com.application.employee.service.entities;
import com.application.employee.service.deserializer.CustomLocalDateSerializer;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
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

    @Column(name = "EMAILID")
    private String emailID;

//    @Column(name = "VISA_STATUS")
//    private String visaStatus;

    @Column(name = "COLLEGE_OF_GRADUATION")
    private String clgOfGrad;

    @Column(name = "PHONE_NO")
    private String phoneNo;

    @Column(name = "dob")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate dob;

//    @Column(name = "visaStartDate")
//    @JsonSerialize(using = CustomLocalDateSerializer.class)
//    private LocalDate visaStartDate;
//
//    @Column(name = "visaExpiryDate")
//    @JsonSerialize(using = CustomLocalDateSerializer.class)
//    private LocalDate visaExpiryDate;
    //change it to emp status
    @Column(name = "ON_BENCH")
    private String onBench;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<PurchaseOrder> employeePurchaseOrder;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<WithHoldTracking> employeeWithHoldTracking;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ProjectHistory> employeeProjectHistory;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<VisaDetails> employeeVisaDetails;
}
