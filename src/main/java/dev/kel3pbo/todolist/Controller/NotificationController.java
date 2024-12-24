package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    public String showNotifications(Model model) {
        model.addAttribute("notifications", notificationService.getAllNotifications());
        model.addAttribute("unreadCount", notificationService.getUnreadCount());
        return "notification";
    }

    @PostMapping("/mark-read/{id}")
    public String markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "redirect:/notification";
    }

    @PostMapping("/mark-all-read")
    public String markAllAsRead() {
        notificationService.markAllAsRead();
        return "redirect:/notification";
    }
}