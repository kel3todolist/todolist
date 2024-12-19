package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Model.Statistic;
import dev.kel3pbo.todolist.Model.DailyStatistic;
import dev.kel3pbo.todolist.Model.WeeklyStatistic;
import dev.kel3pbo.todolist.Model.MonthlyStatistic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {
    private final TaskService taskService;

    public StatisticService(TaskService taskService) {
        this.taskService = taskService;
    }

    public Statistic getStatistic(String type) {
        List<Task> tasks = taskService.getAllTasks();
        switch (type.toLowerCase()) {
            case "daily":
                return new DailyStatistic(tasks);
            case "weekly":
                return new WeeklyStatistic(tasks);
            case "monthly":
                return new MonthlyStatistic(tasks);
            default:
                throw new IllegalArgumentException("Invalid statistic type: " + type);
        }
    }
}
