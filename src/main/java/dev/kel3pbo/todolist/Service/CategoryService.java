package dev.kel3pbo.todolist.Service;

import dev.kel3pbo.todolist.Model.Category;
import dev.kel3pbo.todolist.Model.Task;
import dev.kel3pbo.todolist.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Mendapatkan semua kategori
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Mendapatkan kategori berdasarkan ID
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    // Membuat kategori baru
    public Category createCategory(String name) {
        Category category = new Category(name);
        return categoryRepository.save(category);
    }

    // Menambahkan task ke kategori
    public Category addTaskToCategory(Long categoryId, Task task) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
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
}
