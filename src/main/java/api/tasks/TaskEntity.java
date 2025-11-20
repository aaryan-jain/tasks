package api.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
public class TaskEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String title;

    private String description;

    private Status status;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public TaskEntity(String title, String description, Status status) {
        this.title = title;
        this.status = status;
        this.description = description;
    }

    public TaskEntity() {

    }

    public void updateValues(TaskDto t) {
        this.description = t.description;
        this.title = t.title;
        this.status = t.status;
    }

}
