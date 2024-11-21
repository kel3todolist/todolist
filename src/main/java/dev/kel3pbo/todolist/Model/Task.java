package dev.kel3pbo.todolist.Model;

import java.util.Date;

public class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private Date deadline;
    private Status status;

    public Task( String title, String description, String priority, Date deadline) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.deadline = deadline;
        this.status = new Status("Not Started");
    }
    public int getId() {
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus(String statusBaru) {
        status.setName(statusBaru);

    }

    public boolean isNearDeadline() {
        Date today = new Date();
        long diff = deadline.getTime() - today.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return diffDays <= 3;
    }
}
