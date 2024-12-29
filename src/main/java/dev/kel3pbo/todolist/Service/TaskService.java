package dev.kel3pbo.todolist.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;

import java.util.List;
import java.util.Optional;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.TaskRepository;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Menambahkan task baru
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    // Ambil semua task berdasarkan deadline ascending
    public List<Task> getAllTasksSortedByDeadline() {
        return taskRepository.findAllByOrderByDeadlineAsc();
    }
    public List<Task> getAllTasksSortedByDeadlineDesc() {
        return taskRepository.findAllByOrderByDeadlineDesc();
    }

    // Mengupdate task yang sudah ada
    public void updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setDeadline(updatedTask.getDeadline());
            taskRepository.save(task);
        } else {
            throw new RuntimeException("Task dengan ID " + id + " tidak ditemukan.");
        }
    }
    public void updateStatus(Long id, String statusName) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.updateStatus(statusName); // Update status di model
            taskRepository.save(task);    // Simpan perubahan ke database
        }
    }


    // Menghapus task berdasarkan ID
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void removeTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setCategory(null); // Update status di model
            taskRepository.save(task);    // Simpan perubahan ke database
        }
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

    public List<Task> getTasksWithoutCategory() {
        return taskRepository.findByCategoryIsNull();
    }
    private static final Comparator<Task> PRIORITY_COMPARATOR = (task1, task2) -> {
        // Define priority order: High -> Medium -> Low
        Map<String, Integer> priorityOrder = Map.of(
                "High", 1,
                "Medium", 2,
                "Low", 3
        );

        return priorityOrder.get(task1.getPriority()) - priorityOrder.get(task2.getPriority());
    };

    public Map<LocalDate, List<Task>> getTasksGroupedByDate(boolean showCompleted) {
        List<Task> tasks;
        if (showCompleted) {
            tasks = getAllTasksSortedByDeadline();
        } else {
            tasks = getNonCompletedTasks().stream()
                    .sorted(Comparator.comparing(Task::getDeadline))
                    .collect(Collectors.toList());
        }

        return tasks.stream()
                .collect(Collectors.groupingBy(
                        Task::getDeadline,
                        LinkedHashMap::new,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(PRIORITY_COMPARATOR)
                                        .collect(Collectors.toList())
                        )
                ));
    }
    // Add new method to get non-completed tasks
    public List<Task> getNonCompletedTasks() {
        return taskRepository.findByStatus_NameNot("COMPLETED");
    }


    // Add new method to get completed tasks
    public List<Task> getCompletedTasks() {
        return taskRepository.findByStatus_Name("COMPLETED");
    }

    // Mendapatkan task berdasarkan prioritas
    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.findByPriority(priority);
    }
}
