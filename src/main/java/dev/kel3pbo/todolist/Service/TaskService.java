package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Menambahkan task baru
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    // Mengupdate task yang sudah ada
    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setDeadline(updatedTask.getDeadline());
            task.updateStatus(updatedTask.getStatus().getName());
            return taskRepository.save(task);
        } else {
            throw new RuntimeException("Task dengan ID " + id + " tidak ditemukan.");
        }
    }

    // Menghapus task berdasarkan ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    // Mendapatkan task berdasarkan ID
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // Mendapatkan semua task
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Mendapatkan task berdasarkan deadline hari ini
    public List<Task> getTasksForToday() {
        return taskRepository.findByDeadline(LocalDate.now());
    }

    // Mendapatkan task berdasarkan status
    public List<Task> getTasksByStatus(String status) {
        return taskRepository.findByStatus_Name(status);
    }

    // Mendapatkan task berdasarkan prioritas
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }
}
