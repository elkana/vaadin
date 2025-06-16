package com.example.vaadin_grid_pageable.views.pv;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponse<T> {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PageInfo {
        private int size;
        private int number;
        private int totalElements;
        private int totalPages;
    }
    private List<T> content;
    private PageInfo page;
}