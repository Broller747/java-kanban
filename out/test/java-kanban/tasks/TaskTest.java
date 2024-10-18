import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task.Task;


class TaskTest {


    @Test
    void checkEquals_taskShouldBeEqualsTask() {

        Task task1 = new Task(1, "task_1", "description_1");
        Task task2 = new Task(1, "task_12", "description_12");


        Assertions.assertEquals(task1, task2);
    }



}