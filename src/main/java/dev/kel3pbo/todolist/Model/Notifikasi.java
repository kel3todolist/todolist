import java.util.Date;

// Interface untuk fitur notifikasi
public interface NotificationService {
    void sendNotification();  // Mengirim notifikasi
    void manageNotification();  // Mengelola notifikasi
}

// Kelas Notifikasi (Implementasi)
public class Notification implements NotificationService {
    private Date time;          // Waktu notifikasi
    private String message;     // Pesan notifikasi
    private Task task;          // Tugas terkait notifikasi

    // Constructor
    public Notification(Date time, String message, Task task) {
        this.time = time;
        this.message = message;
        this.task = task;
    }

    // Getter dan Setter
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    // Implementasi metode dari interface
    @Override
    public void sendNotification() {
        System.out.println("Notifikasi terkirim!");
        System.out.println("Waktu: " + time);
        System.out.println("Pesan: " + message);
        System.out.println("Tugas: " + task.getTaskName());
    }

    @Override
    public void manageNotification() {
        System.out.println("Notifikasi dikelola untuk tugas: " + task.getTaskName());
    }
}

// Kelas Task untuk mewakili tugas dalam to-do list
class Task {
    private String taskName; // Nama tugas

    // Constructor
    public Task(String taskName) {
        this.taskName = taskName;
    }

    // Getter dan Setter
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}

// Contoh Pengujian
public class NotificationTest {
    public static void main(String[] args) {
        // Membuat tugas baru
        Task task = new Task("Menyelesaikan laporan");

        // Membuat notifikasi
        Notification notification = new Notification(new Date(), "Jangan lupa menyelesaikan laporan sebelum deadline!", task);

        // Mengirim dan mengelola notifikasi
        notification.sendNotification();
        notification.manageNotification();
    }
}
