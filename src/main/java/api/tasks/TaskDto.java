package api.tasks;

import lombok.Data;

@Data
public class TaskDto {

     String title;
     String description;
     Status status;

    public TaskEntity getTaskEntityObj() {
        return new TaskEntity(title, description, status);
    }
}
