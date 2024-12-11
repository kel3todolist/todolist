package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Category;
import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.CategoryRepository;
import dev.kel3pbo.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TaskRepository taskRepository;

    // Mendapatkan semua kategori
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Mendapatkan kategori berdasarkan ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // Membuat kategori baru
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    // Menambahkan task ke kategori
    public Category addTaskToCategory(Long categoryId, Long taskId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        category.addTask(task);
        return categoryRepository.save(category);
    }

    // Menghapus task dari kategori
    public Category removeTaskFromCategory(Long categoryId, Task task) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        category.removeTask(task);
        return categoryRepository.save(category);
    }

    // Menghapus kategori berdasarkan ID
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }

    public void updateCategory(Long id, Category updatedCategory) {
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existingCategory.setName(updatedCategory.getName());

        categoryRepository.save(existingCategory);
    }
}
