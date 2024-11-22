/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.kel3pbo.todolist.Model;
/**
 *
 * @author zoyadzaka
 */
public class DailyStatistic extends Statistik {
    
    @Override
    public double calculateCompletionRate(){
        return (double) completedTask / totalTask * 100;
    }
    
    @Override
    public String generateReport(){
    
    }
}
