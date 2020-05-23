package com.nratnovsky.hqs.ui.views.components.hr;

import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "solderView", layout = MainLayout.class)
public class SolderView extends VerticalLayout {

    private final HorizontalLayout layout;
    private Div navigation;
    private Div content;

    public SolderView() {
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        layout = new HorizontalLayout();
        layout.setHeight("100px");
        layout.getStyle().set("flex-grow", "1");
        layout.setSpacing(false);
        createTextLayout();

        add(layout);
    }

    private void createTextLayout() {
        navigation = new Div();
        navigation.setClassName("navigation");
        navigation.setWidth("16em");
        navigation.getElement().getStyle().set("flex-shrink", "0");
        navigation.setText("This is the navigation area. My width is 25% of the ApplicationLayout.");

        content = new Div();
        content.setHeightFull();
        content.getStyle().set("display", "flex");
        content.setText("This is the content area");
        content.setClassName("content");
        content.getStyle().set("alignContent", "start");

        layout.add(navigation, content);
        layout.expand(content);
        layout.setDefaultVerticalComponentAlignment(Alignment.STRETCH);
    }
}
