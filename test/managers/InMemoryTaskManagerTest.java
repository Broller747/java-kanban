import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryTaskManagerTest {
    TaskManager taskManager = Managers.getDefault();
    @Test
    void checkIntersection_shouldSaveTaskIfIntersection() {
        // prepare
        Task task1 = new Task("task_1", "task_description_1");
        Task task2 = new Task("task_2", "task_description_2");

        // do
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        // check
        assertEquals(2, taskManager.getTasks().size());
    }

}