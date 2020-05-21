package com.nratnovsky.hqs.ui.views;

import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(layout = MainLayout.class)
public class MainView extends HorizontalLayout {


    public MainView() {
        H1 logoText = new H1("Headquarters System");
        logoText.addClassName("main-h1-logo");
        add(logoText);
    }
}
