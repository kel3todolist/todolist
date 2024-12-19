package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Model.DailyStatistic;
import dev.kel3pbo.todolist.Model.MonthlyStatistic;
import dev.kel3pbo.todolist.Model.Statistic;
import dev.kel3pbo.todolist.Model.WeeklyStatistic;
import dev.kel3pbo.todolist.Service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Controller
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistic")
    public String getStatisticPage(@RequestParam(defaultValue = "daily") String type, Model model) {
        Statistic stat = statisticService.getStatistic(type);
        model.addAttribute("stat", stat);
        model.addAttribute("type", type);
        model.addAttribute("statTitle", capitalize(type) + " Statistics");
        return "statistic";
    }

    @GetMapping("/api/statistics")
    @ResponseBody
    public Map<String, Object> getStatisticData(@RequestParam(defaultValue = "daily") String type) {
        Statistic stat = statisticService.getStatistic(type);
        Map<String, Object> response = new HashMap<>();
        response.put("title", capitalize(type) + " Statistics");
        response.put("total", stat.getTotalTask());
        response.put("completed", stat.getCompletedTask());
        response.put("onProgress", stat.getOnProgressTask());
        response.put("notStarted", stat.getNotStartedTask());
        response.put("completionRate", stat.calculateCompletionRate());
        if (stat instanceof DailyStatistic) {
            DailyStatistic daily = (DailyStatistic) stat;
            response.put("dateRange", formatDate(daily.getDate()));
        } else if (stat instanceof WeeklyStatistic) {
            WeeklyStatistic weekly = (WeeklyStatistic) stat;
            response.put("dateRange", formatDate(weekly.getStartOfWeek()) + " - " + formatDate(weekly.getEndOfWeek()));
        } else if (stat instanceof MonthlyStatistic) {
            MonthlyStatistic monthly = (MonthlyStatistic) stat;
            response.put("dateRange", formatDate(monthly.getStartOfMonth()) + " - " + formatDate(monthly.getEndOfMonth()));
        }

        return response;
    }
    private String formatDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
    }
    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
