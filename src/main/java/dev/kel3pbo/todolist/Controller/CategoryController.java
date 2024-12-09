package dev.kel3pbo.todolist.Controller;

import dev.kel3pbo.todolist.Model.Category;
import dev.kel3pbo.todolist.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Menampilkan daftar kategori
    @GetMapping
    public String listAllCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category"; // Template untuk daftar kategori
    }

    // Menampilkan form untuk membuat kategori baru
    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "createcategory"; // Template untuk form membuat kategori
    }

    // Menangani pengiriman form untuk membuat kategori baru
    @PostMapping("/create")
    public String createCategory(@ModelAttribute("category") Category category) {
        categoryService.addCategory(category);
        return "redirect:/category"; // Redirect ke daftar kategori
    }

    // Menampilkan form edit kategori berdasarkan ID
    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "editcategory"; // Template untuk form edit kategori
        } else {
            return "redirect:/category"; // Redirect jika kategori tidak ditemukan
        }
    }

    // Menangani pengiriman form untuk mengupdate kategori
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute("category") Category updatedCategory) {
        categoryService.updateCategory(id, updatedCategory);
        return "redirect:/category"; // Redirect ke daftar kategori
    }

    // Menghapus kategori berdasarkan ID
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/category"; // Redirect ke daftar kategori
    }
}
