package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;

public interface TaskManager {

    int addTask(Task task);

    int addEpic(Epic epic);

    Integer addNewSubtask(SubTask subtask);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(SubTask subtask);

    void deleteAllTasks();

    void deleteAllSubTasks();

    void deleteAllEpics();

    Task getTask(int id);

    Epic getEpic(int id);

    SubTask getSubTask(int id);

    ArrayList<SubTask> getEpicSubtasks(int epicId);

    void deleteTask(int id);

    void deleteEpic(int id);

    void deleteSubtask(int id);

    ArrayList<Task> getTasks();

    ArrayList<Epic> getEpics();

    ArrayList<SubTask> getSubTasks();

}
