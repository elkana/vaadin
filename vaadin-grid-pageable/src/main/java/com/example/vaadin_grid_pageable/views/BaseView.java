package com.example.vaadin_grid_pageable.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class BaseView extends VerticalLayout {
    public BaseView() {
        setSizeFull();
        addClassName("base-view");
        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
    }
}
