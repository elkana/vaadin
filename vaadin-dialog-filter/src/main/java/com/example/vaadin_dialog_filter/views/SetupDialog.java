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
    private final Settings settings;
    private final Binder<Settings> binder;

    public SetupDialog(Settings settings) {
        this.settings = settings;
        this.binder = new Binder<>(Settings.class);
        
        setWidth("600px");
        
        VerticalLayout layout = new VerticalLayout();
        layout.setAlignItems(FlexComponent.Alignment.STRETCH);
        
        H3 title = new H3("JamAIBase Settings");
        layout.add(title);
        
        FormLayout form = new FormLayout();
        
        TextField adminApiUrl = new TextField("Admin API URL");
        TextField tableActionApiUrl = new TextField("Table Action API URL");
        TextField llmApiUrl = new TextField("LLM API URL");
        TextField organizationId = new TextField("Organization ID");
        PasswordField tokenId = new PasswordField("Token ID");
        
        form.add(adminApiUrl, tableActionApiUrl, llmApiUrl, organizationId, tokenId);
        
        // Bind fields to settings
        binder.forField(adminApiUrl)
            .bind(Settings::getAdminApiUrl, Settings::setAdminApiUrl);
        binder.forField(tableActionApiUrl)
            .bind(Settings::getTableActionApiUrl, Settings::setTableActionApiUrl);
        binder.forField(llmApiUrl)
            .bind(Settings::getLlmApiUrl, Settings::setLlmApiUrl);
        binder.forField(organizationId)
            .bind(Settings::getOrganizationId, Settings::setOrganizationId);
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
        
        HorizontalLayout buttons = new HorizontalLayout(closeButton, saveButton);
        buttons.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        layout.add(buttons);
        
        add(layout);
    }
}
