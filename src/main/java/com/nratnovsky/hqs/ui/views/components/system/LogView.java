package com.nratnovsky.hqs.ui.views.components.system;

import com.nratnovsky.hqs.models.SystemLogger;
import com.nratnovsky.hqs.services.SystemLoggerService;
import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Route(value = "logView", layout = MainLayout.class)
public class LogView extends VerticalLayout {

    private final SystemLoggerService loggerService;

    private final HorizontalLayout toolbar = new HorizontalLayout();

    private final Grid<SystemLogger> logTable = new Grid<>(SystemLogger.class);

    LogView(SystemLoggerService loggerService) {
        this.loggerService = loggerService;
        addClassName("list-view");
        setSizeFull();
        configureTable();
        add(logTable);
    }

    private void configureTable() {
        logTable.setSizeFull();
        logTable.setColumns("id", "date", "userName","logType", "message");
        logTable.getColumns().forEach(col -> col.setAutoWidth(true));
        logTable.setItems(loggerService.getAllLogs());

    }
}
