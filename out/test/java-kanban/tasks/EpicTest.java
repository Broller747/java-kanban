import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.SubTask;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    TaskManager taskManager = Managers.getDefault();

    @Test
    void checkEquals_EpicShouldBeEqualsSubtask() {

        Epic epic1 = new Epic(1, "epic_1", "subtask_description_1");
        Epic epic2 = new Epic(1, "epic_12", "epic_description_12");


        assertEquals(epic1, epic2);
    }



}