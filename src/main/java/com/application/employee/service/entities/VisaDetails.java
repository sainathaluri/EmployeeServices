package com.application.employee.service.entities;

import com.application.employee.service.deserializer.CustomLocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@Table(name = "visa_details")
public class VisaDetails {
    @Id
    private String visaId;

    @Column(name = "VISA_TYPE")
    private String visaType;
  
    @Column(name = "visaStartDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate visaStartDate;
  
    @Column(name = "visaExpiryDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate  visaExpiryDate;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
