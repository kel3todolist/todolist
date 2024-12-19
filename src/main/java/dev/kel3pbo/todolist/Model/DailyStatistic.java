package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DailyStatistic extends Statistic {
    private final LocalDate date;

    public DailyStatistic(List<Task> tasks) {
        super(filterTasksForToday(tasks));
        this.date = LocalDate.now();
    }

    private static List<Task> filterTasksForToday(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        return tasks.stream()
                .filter(task -> task.getDeadline().equals(today)) // Filter tugas untuk hari ini
                .collect(Collectors.toList());
    }

    @Override
    public double calculateCompletionRate() {
        if (totalTask == 0) {
            return 0.0; // Tidak ada tugas untuk dihitung
        }
        return (double) completedTask / totalTask * 100;
    }

    @Override
    public String generateReport() {
        return "Daily Statistic Report for " + date + ":\n" +
                "Total Tasks: " + totalTask + "\n" +
                "Completed Tasks: " + completedTask + "\n" +
                "On Progress Tasks: " + onProgressTask + "\n" +
                "Not Started Tasks: " + notStartedTask + "\n" +
                "Completion Rate: " + String.format("%.2f", calculateCompletionRate()) + "%";
    }

    public LocalDate getDate() {
        return date;
    }
}
