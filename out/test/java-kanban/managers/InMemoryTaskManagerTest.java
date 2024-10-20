import manager.Managers;
import manager.TaskManager;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class InMemoryTaskManagerTest {

    TaskManager taskManager = Managers.getDefault();
    @Test
    void checkIntersection_shouldSaveTaskIfIntersection() {
        Task task1 = new Task("task_1", "task_description_1");
        Task task2 = new Task("task_2", "task_description_2");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        assertEquals(2, taskManager.getTasks().size());
    }

    @Test
    void getEpicSubtasksId_shouldReturnSubtaskIds() {

        Epic epic1 = new Epic("epic_1", "epic_description_1");
        Integer idEpic1 = taskManager.addEpic(epic1);
        SubTask subtask1 = new SubTask( "subtask_1", "subtask_description_1", idEpic1);
        Integer integer1 = taskManager.addNewSubtask(subtask1);
        SubTask subtask2 = new SubTask( "subtask_2", "subtask_description_1", idEpic1);
        Integer integer2 = taskManager.addNewSubtask(subtask2);
        SubTask subtask3 = new SubTask( "subtask_3", "subtask_description_1", idEpic1);
        Integer integer3 = taskManager.addNewSubtask(subtask3);
        ArrayList<Integer> checkIds = new ArrayList<>();
        checkIds.add( integer1);
        checkIds.add(integer2);
        checkIds.add(integer3);

        assertEquals(checkIds, epic1.getSubTasksId());
    }
    @Test
    void addAndFindTaskTest() {
        Task task = new Task(1, "Task", "Description", TaskStatus.NEW);
        Task task2 = new Task(1, "Task2", "Description2", TaskStatus.NEW);
        taskManager.addTask(task);
        taskManager.addTask(task2);

        Task findTask = taskManager.getTask(1);

        assertEquals(task.getId(), findTask.getId(), "Id должен совпадать");
        assertEquals(task.getName(), findTask.getName(), "Название должно совпадать");
        assertEquals(task.getDescription(), findTask.getDescription(), "Описание должно совпадать");
        assertNotNull(findTask, "Не должен быть Null");
        assertNotEquals(task2, findTask, "Не должно совпадать");
        assertEquals(task, findTask, "Должно совпадать");

    }

    @Test
    void managerGetDefaultTest() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Не должен быть Null");
    }

    @Test
    void managerAddTasksFindId() {
        TaskManager taskManager = Managers.getDefault();
        Task task1 = new Task("Task1", "Description1");
        int idTask = taskManager.addTask(task1);
        assertEquals(task1,taskManager.getTask(idTask),"Должны совпадать");

        Epic epic1 = new Epic("Epic1", "Description1");
        int idEpic = taskManager.addEpic(epic1);
        assertEquals(epic1,taskManager.getTask(idEpic),"Должны совпадать");

        SubTask subTask1 = new SubTask("Subtask1", "Description1", epic1.getId());
        int idSubTask = taskManager.addNewSubtask(subTask1);
        assertEquals(subTask1,taskManager.getSubTask(idSubTask),"Должны совпадать");
    }
    @Test
    void checkEqualsGenegateTaskAndPickTask() {

        Task task2 = new Task ("Task_2", "Description_2");
        int idTask = taskManager.addTask(task2);
        Task task1 = new Task(idTask, "Task_1", "Description_1");
        assertEquals(task1, task2,"Должно совпадать");
    }
}