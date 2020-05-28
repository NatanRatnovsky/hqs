package com.nratnovsky.hqs.ui.views.components.system;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.services.UserService;
import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;


@Route(value = "userList", layout = MainLayout.class)
public class UserView extends VerticalLayout {


    private final UserService userService;


    private Grid<User> grid = new Grid<>(User.class);

    private final TextField filter = new TextField("", "סינון לפי מ.א.");

    private final Button addNewBtn = new Button("להוסיף משתמש");

    private final Checkbox checkbox = new Checkbox();
    // build layout
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);

    private final UserEdit userEdit;

    private final Button logButton = new Button();

    public UserView(UserService userService, UserEdit editor) {
        this.userService = userService;
        this.userEdit = editor;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(logButton, toolbar, editor, grid);

        logButton.setText("פעילות במערכת");
        logButton.addClickListener(e -> UI.getCurrent().navigate(LogView.class));

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateList(e.getValue()));
        filter.setClassName("user-view-filter");

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> editor.editUser(e.getValue()));

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editUser(new User()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            updateList(filter.getValue());
        });
        updateList("");
    }


    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();
        grid.setColumns("id", "username", "roles", "active");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

    }

    private void updateList(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(userService.findAll());
        } else {
            grid.setItems(userService.findByUsername(filterText));
        }

    }
}
