package com.nratnovsky.hqs.ui.views.components.hr;

import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "addSolder", layout = SolderView.class, absolute = true)
public class AddSolderView extends VerticalLayout {
    private final HorizontalLayout layout;
    private final Div div = new Div();
    public AddSolderView() {
        layout = new HorizontalLayout();
        div.add("ADD SOLDER");
        layout.add(div);
    }
}
