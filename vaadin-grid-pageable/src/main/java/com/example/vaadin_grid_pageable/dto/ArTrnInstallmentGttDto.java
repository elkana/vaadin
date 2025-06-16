package com.example.vaadin_grid_pageable.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArTrnInstallmentGttDto {
    private Long id;
    private String label;
    private BigDecimal receivedAmount;

}
