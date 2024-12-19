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


    public Statistic(List<Task> filteredTasks) {
        this.totalTask = filteredTasks.size();
        this.completedTask = 0;
        this.onProgressTask = 0;
        this.notStartedTask = 0;

        // Hitung jumlah tugas berdasarkan status
        for (Task task : filteredTasks) {
            switch (task.getStatus().getName()) {
                case "COMPLETED":
                    completedTask++;
                    break;
                case "ON_PROGRESS":
                    onProgressTask++;
                    break;
                case "NOT_STARTED":
                    notStartedTask++;
                    break;
            }
        }
    }
    
    public abstract double calculateCompletionRate();
    public abstract String generateReport();


    public int getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(int completedTask) {
        this.completedTask = completedTask;
    }

    public int getTotalTask() {
        return totalTask;
    }

    public void setTotalTask(int totalTask) {
        this.totalTask = totalTask;
    }

    public int getOnProgressTask() {
        return onProgressTask;
    }

    public void setOnProgressTask(int onProgressTask) {
        this.onProgressTask = onProgressTask;
    }

    public int getNotStartedTask() {
        return notStartedTask;
    }

    public void setNotStartedTask(int notStartedTask) {
        this.notStartedTask = notStartedTask;
    }

}
