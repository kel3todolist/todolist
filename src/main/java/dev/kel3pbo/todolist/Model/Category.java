package dev.kel3pbo.todolist.Model;
import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<Task> taks;

    public Category(String name){
        this.name = name;
    }

    public String getNama(){
        return name;
    }
}
