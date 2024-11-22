package dev.kel3pbo.todolist.Model;
import java.util.ArrayList;
import java.util.List;

public class Category {
    private String name;
    private List<Task> tasks;

    // Constructor
    public Category(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Add a task to the category
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove a task from the category
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    // Get the list of tasks in the category
    public List<Task> getTasks() {
        return tasks;
    }
}

