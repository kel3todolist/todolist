package dev.kel3pbo.todolist.Model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="tasks")
public class Task implements Notifiable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String priority;
    private LocalDate deadline;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true) // nullable jika task bisa tanpa kategori
    private Category category;
    public Task( String title, String description, String priority, LocalDate deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = new Status();
    }

    public Task() {
        this.status = new Status();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus(String statusBaru) {
        status.setName(statusBaru);
    }

    public boolean isNearDeadline() {
        LocalDate today = LocalDate.now();
        long diffDays = ChronoUnit.DAYS.between(today, deadline);
        return diffDays <= 3 && diffDays >= 0;
    }

    @Override
    public String sendNotification() {
        return "Task " + title + " is near deadline!" + " (" + deadline + ")";
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    

}
