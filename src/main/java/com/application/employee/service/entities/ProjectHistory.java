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
@Table(name = "project_history")
public class ProjectHistory {
    @Id
    @Column(name = "ID")
    private String projectId;
    @Column(name = "SUBVENDOR_ONE")
    private String subVendorOne;
    @Column(name = "SUBVENDOR_TWO")
    private String subVendorTwo;
    @Column(name = "PROJECT_ADDRESS")
    private String projectAddress;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate projectStartDate;
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    private LocalDate projectEndDate;
    @Column(name = "PROJECT_STATUS")
    private String projectStatus;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
