package dev.kel3pbo.todolist.repository;

import dev.kel3pbo.todolist.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name); // Temukan kategori berdasarkan nama
}
