import manager.HistoryManager;
import manager.Managers;
import org.junit.jupiter.api.Test;
import task.Task;


import java.util.ArrayList;

import static manager.InMemoryHistoryManager.history;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class InMemoryHistoryManagerTest {
    HistoryManager historyManager = Managers.getDefaultHistory();
    @Test
    void add() {
        Task task = new Task("Уборка", "Убрать квартиру");
        historyManager.add(task);
        historyManager.getHistory();
        assertNotNull(history.size(), "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");

    }
}
