package dev.kel3pbo.todolist.Model;
import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name ="status")
public class Status {
    private String name;
    private Date lastUpdated;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Status(String name) {
        this.name = name;
        this.lastUpdated = new Date();
    }

    public Status() {
        this.name = "Not Started";
        this.lastUpdated = new Date();
    }

    public void setName(String name) {
        this.name = name;
        this.lastUpdated = new Date();
    }
    public String getName() {
        return name;
    }
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public Long getId() {
        return id;
    }
}
