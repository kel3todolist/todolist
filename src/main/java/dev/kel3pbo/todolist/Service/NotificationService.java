package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Notification;
import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
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

    public void createNotification(Task task, String message, LocalDate createdDate) {
        // Check if notification already exists for this task
        List<Notification> existingNotifications = notificationRepository.findAllByOrderByCreatedAtDesc();
        boolean notificationExists = existingNotifications.stream()
                .anyMatch(n -> n.getTask() != null && n.getTask().getId() == task.getId());

        if (!notificationExists) {
            Notification notification = new Notification(message, task);
            // Convert LocalDate to LocalDateTime dengan waktu 00:00
            notification.setCreatedAt(createdDate.atStartOfDay());
            notificationRepository.save(notification);
        }
    }

    public List<Notification> getUnreadNotifications() {
        generateNotifications();
        return notificationRepository.findByIsReadFalse();
    }

    public List<Notification> getAllNotifications() {
        generateNotifications();
        return notificationRepository.findAllByOrderByCreatedAtDesc();
    }

    private void generateNotifications() {
        List<Task> allTasks = taskService.getAllTasks();

        for (Task task : allTasks) {
            if (task.isNearDeadline() && !task.getStatus().getName().equals("COMPLETED")) {
                // Set tanggal 3 hari sebelum deadline
                LocalDate createdDate = task.getDeadline().minusDays(3);
                createNotification(task, task.sendNotification(), createdDate);
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