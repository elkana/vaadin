package com.example.vaadin_dialog_filter.views;

import com.example.vaadin_dialog_filter.Settings;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class SetupDialog extends Dialog {
    private final Binder<Settings> binder;

    public SetupDialog(Settings settings) {
        this.binder = new Binder<>(Settings.class);
        
        setWidth("600px");
        
        var layout = new VerticalLayout(FlexComponent.Alignment.STRETCH);
        
        H3 title = new H3("Settings");
        layout.add(title);
        
        FormLayout form = new FormLayout();
        
        var adminApiUrl = new TextField("Admin API URL");
        var tableActionApiUrl = new TextField("Table Action API URL");
        var tokenId = new PasswordField("Token ID");
        
        form.add(adminApiUrl, tableActionApiUrl, tokenId);
        
        // Bind fields to settings
        binder.forField(adminApiUrl)
            .bind(Settings::getAdminApiUrl, Settings::setAdminApiUrl);
        binder.forField(tableActionApiUrl)
            .bind(Settings::getTableActionApiUrl, Settings::setTableActionApiUrl);
        binder.forField(tokenId)
            .bind(Settings::getTokenId, Settings::setTokenId);
        
        binder.readBean(settings);
        
        layout.add(form);
        
        // Buttons
        Button closeButton = new Button("Close", e -> close());
        Button saveButton = new Button("Save", e -> {
            try {
                binder.writeBean(settings);
                close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        
        var buttons = new HorizontalLayout(FlexComponent.JustifyContentMode.END, closeButton, saveButton);
        layout.add(buttons);
        
        add(layout);
    }
}
