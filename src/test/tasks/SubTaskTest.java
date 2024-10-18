import org.junit.jupiter.api.Test;
import task.SubTask;

import static org.junit.jupiter.api.Assertions.assertEquals;


class SubTaskTest {
    @Test
    void checkEquals_subtaskShouldBeEqualsSubtask() {

        SubTask subtask1 = new SubTask(1, "subtask_1", "subtask_description_1");
        SubTask subtask2 = new SubTask(1, "subtask_12", "subtask_description_12");


        assertEquals(subtask1, subtask2);
    }

}