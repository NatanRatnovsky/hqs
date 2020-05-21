package com.nratnovsky.hqs.ui.views.components.user;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.services.UserService;
import com.nratnovsky.hqs.ui.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
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

    private final TextField filter = new TextField("","סינון לפי מ.א.");

    private final Button addNewBtn = new Button("להוסיף משתמש");
    // build layout
    private final HorizontalLayout toolbar = new HorizontalLayout(filter, addNewBtn);

    private final UserEdit userEdit;

    public UserView(UserService userService, UserEdit editor) {
        this.userService = userService;
        this.userEdit = editor;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        add(toolbar,editor,grid);

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> updateList(e.getValue()));

        // Connect selected Customer to editor or hide if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> editor.editUser(e.getValue()));

        // Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editUser(new User()));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() ->{
            editor.setVisible(false);
            updateList(filter.getValue());
        });
        updateList("");
    }


    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();
        grid.setColumns("id", "username","roles");
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
