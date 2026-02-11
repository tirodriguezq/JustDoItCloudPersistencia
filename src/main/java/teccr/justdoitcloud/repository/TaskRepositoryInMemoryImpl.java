package teccr.justdoitcloud.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import teccr.justdoitcloud.data.Task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple in-memory implementation that stores tasks keyed by username.
 * For initial purposes it delegates to the seeded users in UserRepositoryImpl when possible.
 */
@Repository
public class TaskRepositoryInMemoryImpl implements TaskRepository {

    private final List<Task> defaultTasks = new ArrayList<>();

    @PostConstruct
    public void init() {
        // Initialize default tasks list
        Task task = new Task("Comprar Leche", LocalDateTime.now(), null, Task.Status.DONE);
        defaultTasks.add(task);
        task = new Task("Reparacion de sistema de frenos del carro", LocalDateTime.now(),
                LocalDateTime.now().plusDays(3).toLocalDate(), Task.Status.INPROGRESS);
        defaultTasks.add(task);
    }

    private final Map<String, List<Task>> tasksByUser = new HashMap<>();

    @Override
    public List<Task> findByUserName(String userName) {
        // Ensure the map contains an entry for the user: if absent, insert a new list
        // seeded with copies of defaultTasks so subsequent modifications persist in the map.
        return tasksByUser.computeIfAbsent(userName, k -> new ArrayList<>(defaultTasks));
    }

    @Override
    public void addTaskToUser(String userName, Task task) {
        tasksByUser.computeIfAbsent(userName, k -> new ArrayList<>()).add(task);
    }
}
