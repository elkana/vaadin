package com.example.vaadin_grid_pageable.views;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;

import com.example.vaadin_grid_pageable.dto.ArTrnInstallmentGttDto;
import com.example.vaadin_grid_pageable.services.ServiceCashier;
import com.example.vaadin_grid_pageable.views.pvho.PageableHateOasView;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends Main {

    public HomeView(ServiceCashier serviceCashier) {
        add(new ArInstallmentView(serviceCashier));
    }
}

class ArInstallmentView extends PageableHateOasView<ArTrnInstallmentGttDto> {

    private ServiceCashier serviceCashier;

    public ArInstallmentView(ServiceCashier serviceCashie) {
        super(ArTrnInstallmentGttDto.class);
        grid.setAllRowsVisible(true);

        this.serviceCashier = serviceCashie;
        add(getContent(), pagination);
        refreshData();
    }

    private HorizontalLayout getContent() {
        var content = new HorizontalLayout(grid);
        content.setFlexGrow(2, grid);
        content.setSizeFull();
        return content;
    }

    @Override
    protected CollectionModel<ArTrnInstallmentGttDto> requestData(Pageable pageRequest) {
        return serviceCashier.getInstallments(pageRequest);
    }

}
