package com.example.vaadin_grid_pageable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.component.page.AppShellConfigurator;

@SpringBootApplication
@Theme("my-theme")
public class VaadinGridPageableApplication implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(VaadinGridPageableApplication.class, args);
    }
}
