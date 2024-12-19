package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlyStatistic extends Statistic {
    private final LocalDate startOfMonth;
    private final LocalDate endOfMonth;

    public MonthlyStatistic(List<Task> tasks) {
        super(filterTasksForMonth(tasks));
        LocalDate today = LocalDate.now();
        this.startOfMonth = today.withDayOfMonth(1); // Awal bulan
        this.endOfMonth = today.withDayOfMonth(today.lengthOfMonth()); // Akhir bulan
    }

    private static List<Task> filterTasksForMonth(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        LocalDate startOfMonth = today.withDayOfMonth(1);
        LocalDate endOfMonth = today.withDayOfMonth(today.lengthOfMonth());
        return tasks.stream()
                .filter(task -> !task.getDeadline().isBefore(startOfMonth) && !task.getDeadline().isAfter(endOfMonth))
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
        return "Monthly Statistic Report (" + startOfMonth + " to " + endOfMonth + "):\n" +
                "Total Tasks: " + totalTask + "\n" +
                "Completed Tasks: " + completedTask + "\n" +
                "On Progress Tasks: " + onProgressTask + "\n" +
                "Not Started Tasks: " + notStartedTask + "\n" +
                "Completion Rate: " + String.format("%.2f", calculateCompletionRate()) + "%";
    }

    public LocalDate getStartOfMonth() {
        return startOfMonth;
    }

    public LocalDate getEndOfMonth() {
        return endOfMonth;
    }
}
