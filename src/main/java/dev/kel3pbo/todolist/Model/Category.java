package dev.kel3pbo.todolist.Model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();


    // Constructor
    public Category(String name) {
        setName(name);
        this.tasks = new ArrayList<>();
    }

    public Category() {

    }

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
        task.setCategory(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setCategory(null);
    }

    // Get the list of tasks in the category
    public List<Task> getTasks() {
        return tasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

