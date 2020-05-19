package com.nratnovsky.hqs.ui.views.parts;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.services.UserService;
import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "test", layout = MainLayout.class)
public class TestView extends HorizontalLayout {

    private final UserService userService;

    Grid<User> grid = new Grid<>(User.class);

    public TestView(UserService userService) {
        this.userService = userService;
        this.grid = new Grid<>(User.class);
        add(grid);
        listUsers();
    }

    private void listUsers() {
        userService.findAll();
    }
}
