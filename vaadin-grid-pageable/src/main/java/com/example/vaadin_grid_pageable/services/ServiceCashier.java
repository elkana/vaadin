package com.example.vaadin_grid_pageable.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import com.example.vaadin_grid_pageable.dto.ArTrnInstallmentGttDto;

@Service
public class ServiceCashier {

    /**
     * Retrieves a paginated list of installments.
     *
     * @param pageable the pagination information
     * @return a paginated response containing installment data
     */
    public CollectionModel<ArTrnInstallmentGttDto> getInstallments(Pageable pageable) {
        List<ArTrnInstallmentGttDto> dummyData = Arrays.asList(
            new ArTrnInstallmentGttDto(1L, "INS001", new BigDecimal("1000.00")),
            new ArTrnInstallmentGttDto(2L, "INS002", new BigDecimal("1500.00")),
            new ArTrnInstallmentGttDto(3L, "INS003", new BigDecimal("2000.00"))
        );
        
        return CollectionModel.of(dummyData);
    }
}
