package com.nratnovsky.hqs.ui.views.components.hr;

import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.util.ArrayList;
import java.util.List;


@Route(value = "solderView", layout = MainLayout.class)
public class SolderView extends VerticalLayout {

    private final HorizontalLayout layout;
    private Div navigation;
    private Div content;
    private final Tabs menu;

    public SolderView() {
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        menu = createMenuTabs();


        layout = new HorizontalLayout();
        layout.setHeight("100px");
        layout.getStyle().set("flex-grow", "1");
        layout.setSpacing(false);
        createTextLayout();

        add(layout);
        this.navigation.add(menu);
    }

    private Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(getAvailableTabs());
        return tabs;
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    private void createTextLayout() {
        navigation = new Div();
        navigation.setClassName("navigation");
        navigation.setWidth("16em");
        navigation.getElement().getStyle().set("flex-shrink", "0");


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


    private static Tab createTab(String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), title));
    }

    private static <T extends HasComponents> T populateLink(T a, String title) {
        a.add(title);
        return a;
    }


    private Tab[] getAvailableTabs() {
        final List<Tab> tabs = new ArrayList<>(1);
        tabs.add(createTab("add solder", AddSolderView.class));

        return tabs.toArray(new Tab[tabs.size()]);
    }


}
