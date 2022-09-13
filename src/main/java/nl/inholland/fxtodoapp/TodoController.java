package nl.inholland.fxtodoapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML
    private TextField descriptionTextield;
    @FXML
    private TableView tableView;
    private TodoItemService todoItemService;
    private ObservableList<TodoItem> todoItems;

    public void onAddButtonClick(ActionEvent event) {
        if (!descriptionTextield.getText().isEmpty()) {
            TodoItem item = new TodoItem(descriptionTextield.getText(), false);
            todoItems.add(item);
            descriptionTextield.setText("");
        }
    }

    public void onLoadMenuItemClick(ActionEvent event) {
        todoItemService.load();
        todoItems = FXCollections.observableList(todoItemService.getItems());
        tableView.setItems(todoItems);
    }

    public void onSaveMenuItemClick(ActionEvent event) {
        todoItemService.save();
    }

    public void onCloseMenuItemClick(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Load in the todo items
        todoItemService = new TodoItemService();
        todoItems = FXCollections.observableList(todoItemService.getItems());

        // Configure the TableView
        tableView.setItems(todoItems);

        // the RowFactory setter allows us to set up our own logic for every table row
        tableView.setRowFactory(tv -> { // functional programming, wait for Java Advanced
            TableRow<TodoItem> row = new TableRow<>();
            // We add a custom response to the click event of every row
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) { // double click
                    TodoItem item = row.getItem();
                    item.setCompleted(!item.isCompleted()); // toggle true/false
                    tableView.refresh(); // the table needs to be refreshed
                    descriptionTextield.requestFocus(); // focus the cursor back on the input field
                }
            });
            return row;
        });
    }
}