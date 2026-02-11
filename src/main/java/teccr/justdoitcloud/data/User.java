package teccr.justdoitcloud.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class User {
    private final String userName;
    private final String name;
    private final String email;
    private final Type type;
    private List<Task> tasks;

    public enum Type {
        ADMIN,
        REGULAR
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
