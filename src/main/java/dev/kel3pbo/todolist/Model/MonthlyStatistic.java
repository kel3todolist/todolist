package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlyStatistic extends Statistic {
    private final int month;
    private final int year;

    public MonthlyStatistic(List<Task> tasks) {
        super(tasks);
        LocalDate today = LocalDate.now();
        this.month = today.getMonthValue();
        this.year = today.getYear();
    }

    private static List<Task> filterTasksForMonth(List<Task> tasks, int month, int year) {
        return tasks.stream()
                .filter(task -> task.getDeadline().getMonthValue() == month && task.getDeadline().getYear() == year)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateCompletionRate() {
        return (double) completedTask / totalTask * 100;
    }

    @Override
    public String generateReport() {
        return "Monthly Statistic Report for " + Month.of(month) + " " + year + ":\n" +
                "Total Tasks: " + totalTask + "\n" +
                "Completed Tasks: " + completedTask + "\n" +
                "On Progress Tasks: " + onProgressTask + "\n" +
                "Not Started Tasks: " + notStartedTask + "\n" +
                "Completion Rate: " + String.format("%.2f", calculateCompletionRate()) + "%";
    }

}
