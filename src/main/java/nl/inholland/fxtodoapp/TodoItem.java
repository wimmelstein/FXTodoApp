package nl.inholland.fxtodoapp;

// TODO: Make class serializable
public class TodoItem {

    private String description;
    private boolean completed;

    public TodoItem(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
