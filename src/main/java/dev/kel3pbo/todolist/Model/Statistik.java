/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.kel3pbo.todolist.Model;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author zoyadzaka
 */
public abstract class Statistik {
    private int totalTask;
    private int completedTask;
    private int onProgressTask;
    private int notStartedTask;
    private List<Task> tasks;
    
    public Statistik(List<Task> tasks){
        this.tasks = new ArrayList<>();
    };
    
    public abstract double calculateCompletionRate();
    public abstract String generateReport();
            
   
          
}
