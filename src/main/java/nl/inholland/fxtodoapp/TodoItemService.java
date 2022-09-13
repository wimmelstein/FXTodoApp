package nl.inholland.fxtodoapp;

import java.util.ArrayList;
import java.util.List;

public class TodoItemService {

    private List<TodoItem> items;

    public TodoItemService() {
        items = new ArrayList<>();
        items.add(new TodoItem("Test this app", false));
        items.add(new TodoItem("Just make sure it works", false));
    }

    public void save() {
        // TODO: Save items to disk
    }

    public void load() {
        // TODO: Load items from disk
    }

    public List<TodoItem> getItems() {
        return items;
    }
}
