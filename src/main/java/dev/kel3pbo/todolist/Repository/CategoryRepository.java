package dev.kel3pbo.todolist.Repository;

import dev.kel3pbo.todolist.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByName(String name);

}