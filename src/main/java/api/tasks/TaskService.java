package api.tasks;

import Utilities.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    ResponseEntity saveTask(TaskDto t) {
        try {
            // validate task info
            validateTaskInfo(t);
            TaskEntity te = t.getTaskEntityObj();
            te = taskRepository.save(te);
            return new ResponseEntity<TaskEntity>(te, "Task created with id - " + te.getId(), 201);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
            return new ResponseEntity<String>(null, msg, 500);
        }
    }

    private void validateTaskInfo(TaskDto t) throws Exception {
        if(t.description == null || t.status == null || t.title == null) {
            throw new Exception("Missing values in task");
        }
    }

    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        try {
            List<TaskEntity> teListToSend = new ArrayList<>();
            Iterable<TaskEntity> teList = taskRepository.findAll();
            teList.forEach(teListToSend::add);

            return new ResponseEntity<List<TaskEntity>>(teListToSend, "Found all tasks", 200);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
            return new ResponseEntity<List<TaskEntity>>(emptyList(), msg, 500);
        }
    }


    public ResponseEntity<TaskEntity> getTaskById(Integer id) {
        try {
            Optional<TaskEntity> opt= taskRepository.findById(id);
            TaskEntity te = opt.get();
            return new ResponseEntity<>(te, "Task with id - " + id, 200);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
            return new ResponseEntity<TaskEntity>(null, msg, 500);
        }
    }

    public ResponseEntity<TaskEntity> updateTaskById(Integer id, TaskDto t) {
        try {
            TaskEntity taskEntityResponseEntity = this.getTaskById(id).responseData;
            taskEntityResponseEntity.updateValues(t);
            taskRepository.save(taskEntityResponseEntity);
            return new ResponseEntity<>(taskEntityResponseEntity, "Task with id - " + id, 200);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
            return new ResponseEntity<TaskEntity>(null, msg, 500);
        }
    }

    public ResponseEntity deleteTaskById(Integer id) {
        try {
            TaskEntity te = taskRepository.findById(id).get();
            taskRepository.delete(te);
            return new ResponseEntity<>(te, "Deleted Task with id - " + id, 200);
        } catch (Exception e) {
            String msg = e.getMessage() != null ? e.getMessage() : "Internal Server Error";
            return new ResponseEntity<String>(null, msg, 500);
        }
    }
}
