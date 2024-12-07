package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // Menampilkan form untuk membuat task
    @GetMapping("/createtask")
    public String showCreateTaskForm(Model model) {
        model.addAttribute("task", new Task()); // Mengirimkan objek kosong untuk diisi di form
        return "createtask"; // Nama template untuk form HTML
    }

    // Menangani pengiriman form untuk membuat task
    @PostMapping
    public String createTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task); // Simpan task ke database
        return "redirect:/"; // Redirect ke halaman daftar task
    }

    // Menampilkan daftar semua task
    @GetMapping
    public String listAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "dashboard"; // Template untuk menampilkan semua task
    }

    @GetMapping("/category")
    public String showCategory() {
        return "category"; // Mengarahkan ke template category.html
    }

    @GetMapping("/statistic")
    public String showStatistic() {
        return "statistic"; // Mengarahkan ke template statistic.html
    }

    @GetMapping("/notification")
    public String showNotification() {
        return "notification"; // Mengarahkan ke template notification.html
    }
    
    // Menghapus task berdasarkan ID
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return "redirect:/"; // Redirect ke dashboard setelah delete
    }
}
