package com.application.employee.service.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "withhold-tracking")
public class WithHoldTracking {
    @Id
    @Column(name = "ID")
    private String trackingId;

    @Column(name = "month")
    private String month;

    @Column(name = "actualhours")
    private BigDecimal actualHours;

    @Column(name = "actualrate")
    private BigDecimal actualRate;

    @Column(name = "actualAmt")
    private BigDecimal actualAmt;

    @Column(name = "hours")
    private BigDecimal paidHours;

    @Column(name = "paidRate")
    private BigDecimal paidRate;

    @Column(name = "paidAmt")
    private BigDecimal paidAmt;

    @Column(name = "balance")
    private BigDecimal balance;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee employee;

}
