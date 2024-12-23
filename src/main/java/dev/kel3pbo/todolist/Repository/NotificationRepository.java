package dev.kel3pbo.todolist.Repository;

import dev.kel3pbo.todolist.Model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIsReadFalse();
    List<Notification> findAllByOrderByCreatedAtDesc();
}