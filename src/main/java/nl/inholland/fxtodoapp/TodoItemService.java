package nl.inholland.fxtodoapp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TodoItemService {

    private List<TodoItem> items;

    private TodoController controller;


    public TodoItemService(TodoController controller) {
        this.controller = controller;
        items = new ArrayList<>();
        items.add(new TodoItem("Test this app", false));
        items.add(new TodoItem("Just make sure it works", false));
    }

    public void save() {
        try (
                FileOutputStream fos = new FileOutputStream("items.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (TodoItem item : items) {
                oos.writeObject(item);
                controller.setMessage("File saved");
            }
        } catch (FileNotFoundException e) {
            controller.setMessage("Could not find output file");
        } catch (IOException e) {
            controller.setMessage("An unexpected error occurred writing to file");
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("items.dat"))) {
            while (true) {
                if (readItemsFromFile(ois)) break;
            }
        } catch (FileNotFoundException fileNotFoundException) {
            controller.setMessage("Input file not found");
        } catch (IOException ioe) {
            controller.setMessage("Unknown error");
        }
    }

    private boolean readItemsFromFile(ObjectInputStream ois) throws IOException {
        try {
            TodoItem item = (TodoItem) ois.readObject();
            items.add(item);
        } catch (ClassCastException | EOFException | ClassNotFoundException ex) {
            controller.setMessage(getMessageForException(ex));
            return true;
        }
        return false;
    }

    public List<TodoItem> getItems() {
        return items;
    }

    private String getMessageForException(Exception e) {
        if (e instanceof ClassCastException) {
            return "File does not contain Items";
        } else if (e instanceof ClassNotFoundException) {
            return "Type unknown";
        } else if (e instanceof EOFException) {
            return "File loaded";
        }
        return null;
    }
}
