package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Notification;
import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final TaskService taskService;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository, TaskService taskService) {
        this.notificationRepository = notificationRepository;
        this.taskService = taskService;
    }

    public void createNotification(Task task, String message, LocalDateTime createdAt) {
        // Check if notification already exists for this task
        List<Notification> existingNotifications = notificationRepository.findAllByOrderByCreatedAtDesc();
        boolean notificationExists = existingNotifications.stream()
                .anyMatch(n -> n.getTask() != null && n.getTask().getId() == task.getId());

        if (!notificationExists) {
            Notification notification = new Notification(message, task);
            notification.setCreatedAt(createdAt); // Set the calculated creation time
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getUnreadNotifications() {
        generateNotifications(); // Generate notifications before returning
        return notificationRepository.findByIsReadFalse();
    }

    public List<Notification> getAllNotifications() {
        generateNotifications(); // Generate notifications before returning
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    private void generateNotifications() {
        List<Task> allTasks = taskService.getAllTasks();

        for (Task task : allTasks) {
            if (task.isNearDeadline() && !task.getStatus().getName().equals("COMPLETED")) {
                // Calculate creation time as 3 days before deadline at 9:00 AM
                LocalDateTime createdAt = task.getDeadline()
                        .minusDays(3)
                        .atTime(LocalTime.of(9, 0)); // Set to 9:00 AM

                createNotification(task, task.sendNotification(), createdAt);
            }
        }
    }

    public void markAsRead(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
    }

    public void markAllAsRead() {
        List<Notification> unreadNotifications = getUnreadNotifications();
        unreadNotifications.forEach(notification -> notification.setRead(true));
        notificationRepository.saveAll(unreadNotifications);
    }
}