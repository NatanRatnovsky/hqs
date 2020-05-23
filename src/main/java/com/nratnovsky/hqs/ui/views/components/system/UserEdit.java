package com.nratnovsky.hqs.ui.views.components.system;

import com.nratnovsky.hqs.models.User;
import com.nratnovsky.hqs.models.enums.Role;
import com.nratnovsky.hqs.services.UserService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;


@SpringComponent
@UIScope
public class UserEdit extends VerticalLayout implements KeyNotifier{

    private final UserService userService;

    private User user;
    private TextField username = new TextField("מספר אישי");
    private PasswordField password = new PasswordField("ליצור סיסמה");
    private CheckboxGroup<Role> roleCheckboxGroup = new CheckboxGroup<>();


    private final Button save = new Button("Save", VaadinIcon.CHECK.create());
    private final Button cancel = new Button("Cancel");
    private final Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    private final HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    private Binder<User> binder = new Binder<>(User.class);


    @Setter
    private ChangeHandler changeHandler;

    public interface ChangeHandler{
        void onChange();
    }

    @Autowired
    public UserEdit(UserService userService) {
        this.userService = userService;
        roleCheckboxGroup.setDataProvider(new ListDataProvider<>(Arrays.asList(Role.values())));
        roleCheckboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        binder.bind(roleCheckboxGroup, "roles");
        add(username, password,roleCheckboxGroup, actions);

        binder.bindInstanceFields(this);

        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> setVisible(false));
        setVisible(false);


    }

    private void save() {
        userService.saveUser(user);
        Notification.show("משתמש הוסף בהצלחה", 3000, Notification.Position.TOP_CENTER);
        changeHandler.onChange();
    }

    private void delete() {
        userService.deleteUser(user);
        Notification.show("משתמש נמחק בהצלחה", 3000, Notification.Position.TOP_CENTER);
        changeHandler.onChange();
    }

    public void editUser(User newUser) {
        if (newUser == null) {
            setVisible(false);
            return;
        }
        if (newUser.getId() != null) {
            user = userService.findById(newUser.getId()).orElse(newUser);
        } else {
            user = newUser;
        }

        binder.setBean(user);

        setVisible(true);

        username.focus();
    }

}
