package teccr.justdoitcloud.service;

import org.springframework.stereotype.Service;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.data.User;
import teccr.justdoitcloud.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForUser(User user) {
        return taskRepository.findByUserId(user.getId());
    }

    public void addTaskToUser(User user, Task task) {
        task.setUserId(user.getId());
        taskRepository.save(task);
    }

    public void advanceTaskStatus(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task no existe con id: " + taskId));

        Task.Status nextStatus = getNextStatus(task.getStatus());

        Task updatedTask = new Task(
                task.getId(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getDeadline(),
                nextStatus
        );
        updatedTask.setUserId(task.getUserId());

        taskRepository.save(updatedTask);
    }

    private Task.Status getNextStatus(Task.Status current) {
        if (current == null) return Task.Status.PENDING;

        switch (current) {
            case PENDING:
                return Task.Status.INPROGRESS;
            case INPROGRESS:
                return Task.Status.DONE;
            case DONE:
            default:
                return Task.Status.DONE;
        }
    }
}