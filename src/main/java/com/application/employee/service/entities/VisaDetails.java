package com.application.employee.service.entities;

import com.application.employee.service.deserializer.CustomLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "visa_details")
public class VisaDetails {
    @Id
    @Column(name = "ID")
    private String visaId;
    @Column(name = "visa_type")
    private String visaType;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "visa_start_date")
    private LocalDate visaStartDate;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @Column(name = "visa_expiry_date")
    private LocalDate  visaExpiryDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
