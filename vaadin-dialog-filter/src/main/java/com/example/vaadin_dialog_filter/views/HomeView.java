package com.example.vaadin_dialog_filter.views;

import java.util.Map;

import com.example.vaadin_dialog_filter.Settings;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("")
public class HomeView extends VerticalLayout {
    private final TextField description = new TextField("Description");

    public HomeView(Settings settings) {
        add(description);
        add(new Button("Open Dialog", e -> showDialog()));
        add(new Button("Settings Dialog", e -> {
            var dialog = new SetupDialog(settings);
            dialog.open();
        }));
    }

    private void showDialog() {
        var filter = new TextField();
        filter.setPlaceholder("Filter tasks...");
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.setClearButtonVisible(true);

        var data = java.util.List.of(
                Map.of("id", "1", "description", "Task 1"),
                Map.of("id", "2", "description", "Task 2"),
                Map.of("id", "3", "description", "Task 3"));

        Grid<Map<String, String>> lovGrid = new Grid<>();
        lovGrid.setItems(data);
        lovGrid.addColumn(item -> item.get("id")).setHeader("ID");
        lovGrid.addColumn(item -> item.get("description")).setHeader("Description");
        lovGrid.setSizeFull();

        filter.addValueChangeListener(e -> {
            lovGrid.setItems(
                    data.stream()
                            .filter(task -> task.get("description").toLowerCase().contains(e.getValue().toLowerCase()))
                            .toList());
        });

        var dialog = new Dialog();
        lovGrid.addItemClickListener(event -> {
            description.setValue(event.getItem().get("description"));
            dialog.close();
        });

        var layout = new VerticalLayout(filter, lovGrid);
        layout.setSizeFull();

        dialog.setHeaderTitle("Select Task");
        dialog.setWidth("600px");
        dialog.setHeight("400px");
        dialog.add(layout);
        dialog.open();

    }

}
