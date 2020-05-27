package com.nratnovsky.hqs.ui;

import com.nratnovsky.hqs.ui.views.MainView;
import com.nratnovsky.hqs.ui.views.components.hr.SolderView;
import com.nratnovsky.hqs.ui.views.components.system.UserView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinServlet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;


@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {


    private final Tabs menu ;
    private final HorizontalLayout layout = new HorizontalLayout();
    private final Span authUser = new Span();
    private final Div userDiv = new Div();

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();

    public MainLayout() {
        this.setDrawerOpened(false);
        menu = createMenuTabs();

        this.addToNavbar(true, menu);
        userDiv.setClassName("user-div");
        layout.setHeight("80px");
        layout.getStyle().set("flex-grow", "1");
        layout.setSpacing(false);
        layout.add(userDiv);
        authUser();
    }



    private void authUser() {
        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);
        header.getStyle().set("margin-right", "0");
        header.add(new Icon(VaadinIcon.USER));
        authUser.add(currentPrincipalName);
        authUser.getStyle().set("font-size", "18px");
        header.add(authUser);
        addToNavbar(header);
    }

    private static Tabs createMenuTabs() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        tabs.add(getAvailableTabs());
        return tabs;
    }

    private static Tab createTab(Component content) {
        final Tab tab = new Tab();
        tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        tab.add(content);
        return tab;
    }

    private static Tab createTab(VaadinIcon icon, String title, Class<? extends Component> viewClass) {
        return createTab(populateLink(new RouterLink(null, viewClass), icon, title));
    }

    private static <T extends HasComponents> T populateLink(T a, VaadinIcon icon, String title) {
        a.add(icon.create());
        a.add(title);
        return a;
    }

    private static Tab[] getAvailableTabs() {
        final List<Tab> tabs = new ArrayList<>(5);
        tabs.add(createTab(VaadinIcon.HOME, "ראשי", MainView.class));
        tabs.add(createTab(VaadinIcon.CLIPBOARD_USER, "שלישות",
                SolderView.class));
        tabs.add(createTab(VaadinIcon.ROCKET, "נשקיה",
                UserView.class));
        tabs.add(createTab(VaadinIcon.COG_O, "מערכת",
                UserView.class));

        final String contextPath = VaadinServlet.getCurrent().getServletContext().getContextPath();
        final Tab logoutTab = createTab(createLogoutLink(contextPath));
        tabs.add(logoutTab);

        return tabs.toArray(new Tab[tabs.size()]);
    }

    private static Anchor createLogoutLink(String contextPath) {
        final Anchor a = populateLink(new Anchor(), VaadinIcon.ARROW_LEFT, "להתנתק");
        a.setHref(contextPath + "/logout");
        return a;
    }


}