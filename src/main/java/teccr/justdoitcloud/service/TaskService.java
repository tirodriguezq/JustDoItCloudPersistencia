package teccr.justdoitcloud.service;

import org.springframework.stereotype.Service;
import teccr.justdoitcloud.data.Task;
import teccr.justdoitcloud.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasksForUser(String userName) {
        return taskRepository.findByUserName(userName);
    }

    public void addTaskToUser(String userName, Task task) {
        taskRepository.addTaskToUser(userName, task);
    }
}
