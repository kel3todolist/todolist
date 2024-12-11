package dev.kel3pbo.todolist.Repository;

import dev.kel3pbo.todolist.Model.Status;
import dev.kel3pbo.todolist.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByDeadline(LocalDate deadline);
    List<Task> findByStatus_Name(String statusName);
    List<Task> findByPriority(String priority);
    List<Task> findByCategoryIsNull();
}
