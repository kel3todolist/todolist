package dev.kel3pbo.todolist.repository;

import dev.kel3pbo.todolist.Model.Status;
import dev.kel3pbo.todolist.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDeadline(LocalDate deadline);
    List<Task> findByStatus(Status status);
    List<Task> findByPriority(String priority);
}
