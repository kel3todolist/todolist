package dev.kel3pbo.todolist.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class DashboardController {
    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard"; // Mengarahkan ke template HTML langsung
    }

    @GetMapping("/createtask")
    public String showCreateTask() {
        return "createtask"; // Mengarahkan ke template createtask.html
    }

    @GetMapping("/category")
    public String showCategory() {
        return "category"; // Mengarahkan ke template category.html
    }

    @GetMapping("/statistic")
    public String showStatistic() {
        return "statistic"; // Mengarahkan ke template statistic.html
    }

    @GetMapping("/notification")
    public String showNotification() {
        return "notification"; // Mengarahkan ke template notification.html
    }
}


