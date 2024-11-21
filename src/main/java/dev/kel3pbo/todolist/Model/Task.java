package dev.kel3pbo.todolist.Model;
import java.util.ArrayList;

public class Task {
    public String taskName;
    public String taskDescription;
    public String taskDeadline;

    public String settaskName() {
        this.taskName = taskName
    }

    public String setttaskDeadline() {
        this.taskDeadline = taskDeadline
    }

    public String settaskDescription() {
        this.taskDescription = taskDescription
    }

    public String gettaskName() {
        return taskName;
    }

    public String getttaskDeadline() {
        return taskDeadline;
    }

    public String gettaskDescription() {
        return taskDescription
    }
}

public class TaskList {
    private String namaTask;
    private ArrayList<task> nama;

    public TaskList(String name){
        this.name = name;
    }

    public String getNama(){
        return name;
    }
}
