package com.nratnovsky.hqs.ui.views.components.hr;

import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.Objects;


@Route(value = "solderView", layout = MainLayout.class)
public class SolderView extends Composite<Div> implements RouterLayout {

    private final Div content = new Div();

    private final VerticalLayout menuBar = new VerticalLayout(
            new RouterLink("להוסיף חייל", AddSolderView.class)
    );

    private final HorizontalLayout root = new HorizontalLayout(menuBar, content);

    public SolderView() {
        getContent().add(root);
    }

    @Override
    public void showRouterLayoutContent(HasElement hasElement) {
        Objects.requireNonNull(hasElement);
        Objects.requireNonNull(hasElement.getElement());
        content.removeAll();
        content.getElement().appendChild(hasElement.getElement());
    }
}
