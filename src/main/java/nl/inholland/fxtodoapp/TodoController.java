package nl.inholland.fxtodoapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class TodoController implements Initializable {

    @FXML
    private TextField descriptionTextield;
    @FXML
    private TableView<TodoItem> tableView;
    private TodoItemService todoItemService;
    private ObservableList<TodoItem> todoItems;
    @FXML
    private Label message;

    public void onAddButtonClick() {
        if (!descriptionTextield.getText().isEmpty()) {
            TodoItem item = new TodoItem(descriptionTextield.getText(), false);
            todoItems.add(item);
            descriptionTextield.setText("");
        }
    }

    public void setMessage(String input) {
        message.setText(input);
    }

    public void onDeleteButtonClick() {
        ObservableList<TodoItem> selectedItems = tableView.getSelectionModel().getSelectedItems();
        todoItems.removeAll(selectedItems);
    }

    public void onLoadMenuItemClick() {
        todoItemService.load();
        todoItems = FXCollections.observableList(todoItemService.getItems());
        tableView.setItems(todoItems);
    }

    public void onSaveMenuItemClick() {
        todoItemService.save();
    }

    public void onCloseMenuItemClick() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        todoItemService = new TodoItemService(this);
        todoItems = FXCollections.observableList(todoItemService.getItems());

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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