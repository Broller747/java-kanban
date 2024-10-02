import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {

    private HashMap<Integer, Task> Tasks = new HashMap<>();
    private HashMap<Integer, Epic> Epics = new HashMap<>();
    private HashMap<Integer, SubTask> SubTasks = new HashMap<>();
    public int nextId = 1;

    public void addTask(Task task) {

        task.setId(nextId);
        task.setStatus(TaskStatus.NEW);
        Tasks.put(task.getId(), task);
        nextId++;
    }


    public void addEpic(Epic epic) {
        epic.setId(nextId);
        Epics.put(epic.getId(), epic);
        epic.setStatus(TaskStatus.NEW);
        nextId++;
    }

    public void addSubTask(SubTask subTask) {
        subTask.setId(nextId);
        SubTasks.put(subTask.getId(), subTask);
        subTask.setStatus(TaskStatus.NEW);

        Epic epic = Epics.get(subTask.getEpicId());
        epic.getSubTasksId().add(subTask.getId());
        calculationStatus(epic);
        nextId++;
    }

    public void updateTask(Task task) {
        Tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {
        calculationStatus(epic);
        Epics.put(epic.getId(), epic);
    }

    public void updateSubTask(SubTask subTask) {
        SubTasks.put(subTask.getId(), subTask);
        calculationStatus(Epics.get(subTask.getEpicId()));
    }


    public HashMap<Integer, Task> printAllTasks() {
        return getTasks();
    }

    public HashMap<Integer, Epic> printAllEpics() {
        return getEpics();
    }

    public HashMap<Integer, SubTask> printAllSubTasks() {
        return getSubTasks();
    }

    public void deleteAllTasks() {
        Tasks.clear();
    }

    public void deleteAllEpics() {
        Epics.clear();
    }

    public void deleteAllSubTasks() {
        for (Integer id : Epics.keySet()) {
            Epic delete = Epics.get(id);
            delete.getSubTasksId().clear();
            delete.setStatus(TaskStatus.NEW);
        }
        SubTasks.clear();
    }

    public Task printToIdTask(Integer id) {
        if (Tasks.isEmpty()) {
            System.out.println("Список пуст");
            return null;
        } else if (Tasks.containsKey(id)) {
            return Tasks.get(id);
        } else {
            System.out.println("Такого элемента нет");
            return null;
        }
    }

    public Epic printToIdEpic(Integer id) {
        if (Epics.isEmpty()) {
            System.out.println("Список пуст");
            return null;
        } else if (Epics.containsKey(id)) {
            return Epics.get(id);
        } else {
            System.out.println("Такого элемента нет");
            return null;
        }
    }

    public Task printToIdSubTask(Integer id) {
        if (SubTasks.isEmpty()) {
            System.out.println("Список пуст");
            return null;
        } else if (SubTasks.containsKey(id)) {
            return SubTasks.get(id);
        } else {
            System.out.println("Такого элемента нет");
            return null;
        }
    }

    public ArrayList<SubTask> listAllEpicsSubTask(Integer EpicId) {
        ArrayList<SubTask> EpicsSubTask = new ArrayList<>();
        SubTask selectedSub;
        if (Epics.isEmpty()) {
            System.out.println("Список пуст");
            return null;
        } else if (Epics.containsKey(EpicId)) {
            for (int i = 0; i < Epics.get(EpicId).getSubTasksId().size(); i++) {
                selectedSub = SubTasks.get(Epics.get(EpicId).getSubTasksId().get(i));
                EpicsSubTask.add(selectedSub);
            }
            return EpicsSubTask;
        } else {
            System.out.println("Такого элемента нет");
            return null;
        }
    }

    public void deleteSelectedTask(int id) {
        if (Tasks.containsKey(id))
            Tasks.remove(id);
        else {
            System.out.println("Такой задачи нет");
        }
    }

    public void deleteSelectedEpic(int id) {
        if (Epics.containsKey(id))
            Epics.remove(id);
        else {
            System.out.println("Такой задачи нет");
        }
    }

    public void deleteSelectedSubTask(int id) {
        if (SubTasks.containsKey(id)) {
            int tempId = SubTasks.get(id).getEpicId();
            if (Epics.get(tempId).getSubTasksId().isEmpty()) {
                SubTasks.remove(id);
            } else {
                for (int i = 0; i < Epics.get(tempId).getSubTasksId().size(); i++) {
                    if (Epics.get(tempId).getSubTasksId().get(i) == id) {
                        Epics.get(tempId).getSubTasksId().remove(i);
                    }
                }
                SubTasks.remove(id);
                calculationStatus(Epics.get(tempId));
            }
        } else {
            System.out.println("Такой задачи нет");
        }
    }

    public void calculationStatus(Epic epic) {
        int counterNew = 0;
        int counterDone = 0;
        int counter = 0;
        for (Integer itemId : epic.getSubTasksId()) {
            SubTask anItem = SubTasks.get(itemId);
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

    public HashMap<Integer, Task> getTasks() {
        return Tasks;
    }

    public void setTasks(HashMap<Integer, Task> tasks) {
        Tasks = tasks;
    }

    public HashMap<Integer, Epic> getEpics() {
        return Epics;
    }

    public void setEpics(HashMap<Integer, Epic> epics) {
        Epics = epics;
    }

    public HashMap<Integer, SubTask> getSubTasks() {
        return SubTasks;
    }

    public void setSubTasks(HashMap<Integer, SubTask> subTasks) {
        SubTasks = subTasks;
    }
}