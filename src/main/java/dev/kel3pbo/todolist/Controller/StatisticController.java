package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Model.Statistic;
import dev.kel3pbo.todolist.Service.StatisticService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return response;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}
