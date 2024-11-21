package dev.kel3pbo.todolist.Model;
import java.util.Date;
public class Status {
    private String name;
    private Date lastUpdated;

    public Status(String name) {
        this.name = name;
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
}
