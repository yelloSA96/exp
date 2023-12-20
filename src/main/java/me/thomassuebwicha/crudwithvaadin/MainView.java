package me.thomassuebwicha.crudwithvaadin;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import org.jsoup.internal.StringUtil;
import org.springframework.util.StringUtils;

// Entry point for Vaadin's UI logic

/*
SNIPPET - HELLO WORLD EXAMPLE
 */

// Automatically picked up and shown as the root of your web application
//@Route
//public class MainView extends VerticalLayout {
//    public MainView() {
//        add(new Button("Click me", e -> Notification.show("Hello Spring+Vaadin user!")));
//    }
//}

/*
SNIPPET - List all data from JPA with or without filtering
 */
//@Route
//public class MainView extends VerticalLayout {
//    private final CustomerRepository repo;
//    final Grid<Customer> grid;
//
//    public MainView(CustomerRepository repo) {
//        this.repo = repo;
//        this.grid = new Grid<>(Customer.class);
//        add(grid);
//        // Non-filtering example
////        listCustomers();
//
//        // FILTERING EXAMPLE
//        // Hook a listern to the TextFiled and when user types, it will filter
//        TextField filter = new TextField();
//        filter.setPlaceholder("Filter by last name");
//        filter.setValueChangeMode(ValueChangeMode.EAGER);
//        filter.addValueChangeListener(e -> listCustomers(e.getValue()));
//        add(filter,grid);
//
//    }
//
//    private void listCustomers(String filterText) {
//        // FIRST EXAMPLE
//        // With lots of concurrent users, this would be expensive to execute
//        // Vaadin Grid lazy loads the data from the server to the browser
////        grid.setItems(repo.findAll());
//
//        // THe proceeding approach keeps the whole list of data in the server memory and could be saved by loading only topmost results by using paging or using lazy loading
////        grid.setItems(VaadinSpringDataHelpers.fromPagingRepository(repo));
//
//        if(StringUtils.hasText(filterText)) {
//            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
//        } else {
//            grid.setItems(repo.findAll());
//        }
//    }
//}

/*With Customer Editing Component*/
@Route
public class MainView extends VerticalLayout {
    private final CustomerRepository repo;

    private final CustomerEditor editor;

    final Grid<Customer> grid;

    final TextField filter;

    private final Button addNewBtn;

    public MainView(CustomerRepository repo, CustomerEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid<>(Customer.class);
        this.filter = new TextField();
        this.addNewBtn = new Button("New customer", VaadinIcon.PLUS.create());

        // build layout
        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
        add(actions, grid, editor);

        grid.setHeight("300px");
        grid.setColumns("id", "firstName", "lastName");
        grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

        filter.setPlaceholder("Filter by last Name");

        // Hook logic to components

        // Replace listing with filtered content when user changes filter
        filter.setValueChangeMode(ValueChangeMode.LAZY);
        filter.addValueChangeListener(e -> listCustomers(e.getValue()));

        // Connect selected Customer to editor or hid if none is selected
        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editCustomer(e.getValue());
        });

        //Instantiate and edit new Customer the new button is clicked
        addNewBtn.addClickListener(e -> editor.editCustomer(new Customer("asdf","asdf")));

        // Listen changes made by the editor, refresh data from backend
        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listCustomers(filter.getValue());
        });

        // Initialize listing
        listCustomers(null);

    }

    // tag::listCustomers[]
    void listCustomers(String filterText) {
        if (StringUtils.hasText(filterText)) {
            grid.setItems(repo.findByLastNameStartsWithIgnoreCase(filterText));
        } else {
            grid.setItems(repo.findAll());
        }
    }
    // end::listCustomers[]
}