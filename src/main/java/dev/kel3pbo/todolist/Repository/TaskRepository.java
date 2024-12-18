package dev.kel3pbo.todolist.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.kel3pbo.todolist.Model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDeadline(LocalDate deadline);
    List<Task> findByStatus_Name(String statusName);
    List<Task> findByPriority(String priority);
    List<Task> findByCategoryIsNull();
    List<Task> findByDescriptionOrderByDescription(String description);   
    List<Task> findAllByOrderByDeadlineAsc();
    List<Task> findAllByOrderByDeadlineDesc();
}
