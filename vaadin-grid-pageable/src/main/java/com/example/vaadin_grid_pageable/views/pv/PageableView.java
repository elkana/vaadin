package com.example.vaadin_grid_pageable.views.pv;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.vaadin_grid_pageable.views.BaseView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public abstract class PageableView<T> extends BaseView {
    protected final Grid<T> grid;// = new Grid<>(T.class);
    protected final HorizontalLayout pagination = new HorizontalLayout();
    private final Span pageInfo = new Span();
    protected int currentPage = 0;
    protected long totalElements = 0;
    protected int pageSize = 10;

    protected abstract PaginatedResponse<T> requestData(Pageable pageRequest);

    public void refreshData() {
        PaginatedResponse<T> page = requestData(PageRequest.of(currentPage, pageSize));
        totalElements = page.getPage().getTotalElements();
        grid.setItems(page.getContent());
        updatePagination();
    }

    public PageableView(Class<T> clazz) {
        super();

        grid = new Grid<>(clazz);
        // configure grid
        grid.setSizeFull();
        grid.getColumns().forEach(c -> c.setAutoWidth(true));
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT, GridVariant.LUMO_COMPACT,
                GridVariant.LUMO_ROW_STRIPES, GridVariant.LUMO_COLUMN_BORDERS);

        // configure pagination
        var firstPage = new Button("<<", e -> goToPage(0));
        var previousPage = new Button("<", e -> goToPage(currentPage - 1));
        var nextPage = new Button(">", e -> goToPage(currentPage + 1));
        var lastPage = new Button(">>", e -> goToPage(getTotalPages() - 1));

        pagination.setJustifyContentMode(JustifyContentMode.CENTER);
        pagination.setWidthFull();
        pagination.add(firstPage, previousPage, pageInfo, nextPage, lastPage);
        pagination.setAlignItems(Alignment.CENTER);

        // TODO: dont forget to add pagination to the layout
    }

    private int getTotalPages() {
        return (int) Math.ceil((double) totalElements / pageSize);
    }

    private void goToPage(int page) {
        if (page >= 0 && page < getTotalPages()) {
            currentPage = page;
            grid.setPageSize(pageSize);
            refreshData();
        }
    }

    private void updatePagination() {
        int totalPages = getTotalPages();
        pageInfo.setText(String.format("Page %d of %d", currentPage + 1, totalPages));
    }
}
