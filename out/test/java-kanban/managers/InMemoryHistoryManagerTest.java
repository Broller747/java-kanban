import manager.InMemoryHistoryManager;
import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.TaskStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {
    private TaskManager taskManager;
    InMemoryHistoryManager historyManager;

    @BeforeEach
    void setUp() {
        taskManager = Managers.getDefault();
        historyManager = new InMemoryHistoryManager();
    }


    @Test
    void add() {
        Task task = new Task("Уборка", "Убрать квартиру");
        historyManager.add(task);
      List<Task> history= historyManager.getHistory();
        assertNotNull(history.size(), "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");

    }

    @Test
    void add_shouldSavePreviousVersionOfTask() {

        Task task5 = new Task("Task1", "Description1");
        int idTask = taskManager.addTask(task5);
        historyManager.add(task5);
        assertEquals(TaskStatus.NEW, task5.getStatus(), "Должны совпадать");
        Task task3 = new Task(idTask,"Task1", "Description1",TaskStatus.DONE);
        taskManager.updateTask(task3);
        assertEquals(TaskStatus.NEW, historyManager.getHistory().get(0).getStatus(), "Должны совпадать");
    }


}
