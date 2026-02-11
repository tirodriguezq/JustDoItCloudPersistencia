package teccr.justdoitcloud.repository;

import teccr.justdoitcloud.data.Task;

import java.util.List;

/**
 * Repository abstraction for task operations.
 */
public interface TaskRepository {

    /**
     * Return the list of tasks for a given username. If user doesn't exist, return empty list.
     */
    List<Task> findByUserName(String userName);

    /**
     * Add a task to the given user's task list. If user doesn't exist this operation may be a no-op.
     */
    void addTaskToUser(String userName, Task task);
}
