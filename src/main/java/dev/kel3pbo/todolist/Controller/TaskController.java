package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Menampilkan form untuk membuat task
    @GetMapping("/new")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task()); // Mengirimkan objek kosong untuk diisi di form
        return "create-task"; // Nama template untuk form HTML
    }

    // Menangani pengiriman form untuk membuat task
    @PostMapping
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task); // Simpan task ke database
        return "redirect:/tasks"; // Redirect ke halaman daftar task
    }

    // Menampilkan daftar semua task
    @GetMapping
    public String listAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "task-list"; // Template untuk menampilkan semua task
    }
}

