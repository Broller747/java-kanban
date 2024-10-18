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
    @Test
    void getEpicSubtasksId_shouldReturnSubtaskIds() {

        Epic epic = new Epic(1, "epic_1", "epic_description_1");
        SubTask subtask1 = new  SubTask(2, "subtask_1", "subtask_description_1");
        SubTask subtask2 = new  SubTask(3, "subtask_2", "subtask_description_2");
        SubTask subtask3 = new  SubTask(4, "subtask_3", "subtask_description_3");
        ArrayList<Integer> checkIds = new ArrayList<>();
        checkIds.add(2);
        checkIds.add(3);
        checkIds.add(4);


        taskManager.addNewSubtask(subtask1);
        taskManager.addNewSubtask(subtask2);
        taskManager.addNewSubtask(subtask3);
      //  taskManager.addNewSubtask(epic);

        assertEquals(checkIds, epic.getSubTasksId());
    }
}