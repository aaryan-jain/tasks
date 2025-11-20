package api.tasks;

import Utilities.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity createTask(TaskDto information) {
        return taskService.saveTask(information);
    }

    @GetMapping()
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<TaskEntity> updateTaskById(@PathVariable Integer id, @RequestBody TaskDto information) {
        return taskService.updateTaskById(id, information);
    }

    @DeleteMapping("{id")
    public ResponseEntity deleteTaskById(@PathVariable Integer id) {
        return taskService.deleteTaskById(id);
    }
}
