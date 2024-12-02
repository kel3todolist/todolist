package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Locale;

public class WeeklyStatistic extends Statistic {
    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;

    public WeeklyStatistic(List<Task> tasks) {
        super(tasks);
        // Menentukan rentang minggu ini
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        this.startOfWeek = today.with(weekFields.dayOfWeek(), 1); // Senin minggu ini
        this.endOfWeek = startOfWeek.plusDays(6); // Minggu minggu ini
    }

    private static List<Task> filterTasksForWeek(List<Task> tasks, LocalDate startOfWeek, LocalDate endOfWeek) {
        return tasks.stream()
                .filter(task -> !task.getDeadline().isBefore(startOfWeek) && !task.getDeadline().isAfter(endOfWeek))
                .collect(Collectors.toList());
    }

    @Override
    public double calculateCompletionRate() {
        return (double) completedTask / totalTask * 100;
    }

    @Override
    public String generateReport() {
        return "Weekly Statistic Report from " + startOfWeek + " to " + endOfWeek + ":\n" +
                "Total Tasks: " + totalTask + "\n" +
                "Completed Tasks: " + completedTask + "\n" +
                "On Progress Tasks: " + onProgressTask + "\n" +
                "Not Started Tasks: " + notStartedTask + "\n" +
                "Completion Rate: " + String.format("%.2f", calculateCompletionRate()) + "%";
    }

}
