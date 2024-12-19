package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class WeeklyStatistic extends Statistic {
    private final LocalDate startOfWeek;
    private final LocalDate endOfWeek;

    public WeeklyStatistic(List<Task> tasks) {
        super(filterTasksForWeek(tasks));
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        this.startOfWeek = LocalDate.now().with(weekFields.dayOfWeek(), 1); // Awal minggu (Senin)
        this.endOfWeek = startOfWeek.plusDays(6); // Akhir minggu (Minggu)
    }

    private static List<Task> filterTasksForWeek(List<Task> tasks) {
        LocalDate today = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate startOfWeek = today.with(weekFields.dayOfWeek(), 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        return tasks.stream()
                .filter(task -> !task.getDeadline().isBefore(startOfWeek) && !task.getDeadline().isAfter(endOfWeek))
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
        return "Weekly Statistic Report (" + startOfWeek + " to " + endOfWeek + "):\n" +
                "Total Tasks: " + totalTask + "\n" +
                "Completed Tasks: " + completedTask + "\n" +
                "On Progress Tasks: " + onProgressTask + "\n" +
                "Not Started Tasks: " + notStartedTask + "\n" +
                "Completion Rate: " + String.format("%.2f", calculateCompletionRate()) + "%";
    }

    public LocalDate getStartOfWeek() {
        return startOfWeek;
    }

    public LocalDate getEndOfWeek() {
        return endOfWeek;
    }
}
