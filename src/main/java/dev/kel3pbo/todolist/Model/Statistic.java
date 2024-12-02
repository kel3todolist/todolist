/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.kel3pbo.todolist.Model;

import java.util.List;
/**
 *
 * @author zoyadzaka
 */
public abstract class Statistic {
    int totalTask;
    int completedTask;
    int onProgressTask;
    int notStartedTask;
    private List<Task> tasks;
    
    public Statistic(List<Task> tasks){
        this.totalTask = tasks.size();
        this.completedTask = 0;
        this.onProgressTask = 0;
        this.notStartedTask = 0;

        // Hitung jumlah task berdasarkan status
        for (Task task : tasks) {
            switch (task.getStatus().getName()) {
                case "Completed":
                    completedTask++;
                    break;
                case "On Progress":
                    onProgressTask++;
                    break;
                case "Not Started":
                    notStartedTask++;
                    break;
            }
        }
    };
    
    public abstract double calculateCompletionRate();
    public abstract String generateReport();
            
   
          
}
