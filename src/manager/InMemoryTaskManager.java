package manager;

import task.Epic;
import task.Task;
import task.SubTask;
import task.TaskStatus;

import java.util.HashMap;
import java.util.ArrayList;

public class InMemoryTaskManager implements TaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();



    private int generatorId = 0;

    @Override
    public int addTask(Task task) {
        int id = ++generatorId;
        task.setId(generatorId);
        task.setStatus(TaskStatus.NEW);
        tasks.put(task.getId(), task);
        return id;
    }

    @Override
    public int addEpic(Epic epic) {
        int id = ++generatorId;
        epic.setId(generatorId);
        epics.put(epic.getId(), epic);
        epic.setStatus(TaskStatus.NEW);
        return id;
    }

    @Override
    public Integer addNewSubtask(SubTask subtask) {
        int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        int id = ++generatorId;
        subtask.setId(id);
        subtask.setStatus(TaskStatus.NEW);
        subTasks.put(id, subtask);
        epic.addSubtaskId(subtask.getId());
        actualizeEpicStatus(epicId);
        return id;
    }

    @Override
    public void updateTask(Task task) {
        int id = task.getId();
        Task savedTask = tasks.get(id);
        if (savedTask == null) {
            return;
        }
        tasks.put(id, task);
    }

    @Override
    public void updateEpic(Epic epic) {
        Epic savedEpic = epics.get(epic.getId());
        if (savedEpic == null) {
            return;
        }
        savedEpic.setName(epic.getName());
        savedEpic.setDescription(epic.getDescription());
    }

    @Override
    public void updateSubtask(SubTask subtask) {
        int id = subtask.getId();
        int epicId = subtask.getEpicId();
        SubTask savedSubtask = subTasks.get(id);
        if (savedSubtask == null) {
            return;
        }
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return;
        }
        subTasks.put(id, subtask);
        actualizeEpicStatus(epicId);
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        for (Integer id : epics.keySet()) {
            Epic delete = epics.get(id);
            delete.getSubTasksId().clear();
            delete.setStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    @Override
    public Task getTask(int id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    @Override
    public Epic getEpic(int id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTask(int id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public ArrayList<SubTask> getEpicSubtasks(int epicId) {
        ArrayList<SubTask> tasks = new ArrayList<>();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        for (int id : epic.getSubTasksId()) {
            tasks.add(subTasks.get(id));
        }
        return tasks;
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(id);
    }

    public void deleteEpic(int id) {
        final Epic epic = epics.remove(id);
        if (epic == null) {
            return;
        }
        for (Integer subtaskId : epic.getSubTasksId()) {
            subTasks.remove(subtaskId);
        }
    }

    @Override
    public void deleteSubtask(int id) {
        SubTask subtask = subTasks.remove(id);
        if (subtask == null) {
            return;
        }
        Epic epic = epics.get(subtask.getEpicId());
        epic.removeSubtask(id);
        actualizeEpicStatus(epic.getId());
    }

    @Override
    public ArrayList<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public ArrayList<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public ArrayList<SubTask> getSubTasks() {
        return new ArrayList<>(subTasks.values());
    }

    private void actualizeEpicStatus(int id) {
        int counterNew = 0;
        int counterDone = 0;
        int counter = 0;
        Epic epic;
        epic = epics.get(id);
        for (Integer itemId : epic.getSubTasksId()) {
            SubTask anItem = subTasks.get(itemId);
            counter++;
            if (anItem.getStatus() == TaskStatus.NEW) {
                counterNew += 1;
            } else if (anItem.getStatus() == TaskStatus.DONE) {
                counterDone += 1;
            }
        }
        if (counterNew == counter) {
            epic.setStatus(TaskStatus.NEW);
        } else if (counterDone == counter) {
            epic.setStatus(TaskStatus.DONE);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }




}
