import manager.*;
import task.Epic;
import task.SubTask;
import task.Task;
import task.TaskStatus;

public class Main {

    public static void main(String[] args) {

           TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        System.out.println("____________________");
        System.out.println("Создание задач");
        Task task = new Task("Уборка", "Убрать квартиру");
        int idTask = taskManager.addTask(task);
        System.out.println(taskManager.getTask(idTask));

        Task task1 = new Task("Цветы", "Полить цветы");
        int idTask2 = taskManager.addTask(task1);
        System.out.println(taskManager.getTask(idTask2));


        System.out.println("____________________");
        System.out.println("Изменение задачи");
        Task task2 = new Task(task.getId(), "Готовка", "Приготовить еду", TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task2);
        System.out.println(taskManager.getTask(idTask));
        System.out.println("____________________");

        System.out.println("Список задач");
        System.out.println(taskManager.getTasks());

        System.out.println("____________________");
        System.out.println("Создание эпиков");
        Epic epic = new Epic("Ремонт", "............................");
        epic.setId(task.getId());
        epic.setDescription("............................");
        epic.setName("Ремонт");
        int idEpic = taskManager.addEpic(epic);
        System.out.println(taskManager.getEpic(idEpic));

        Epic epic2 = new Epic("Эпик 2", "Описание 2");
        int idEpic2 = taskManager.addEpic(epic2);
        System.out.println(taskManager.getEpic(idEpic2));
        System.out.println("____________________");

        System.out.println("Список эпиков");
        System.out.println(taskManager.getEpics());
        System.out.println("____________________");

        System.out.println("Создание Подзадач");
        SubTask subTask1 = new SubTask("Подзадача 1", "Описание 1", (epic.getId()));
        int idSubTask = taskManager.addNewSubtask(subTask1);
        System.out.println(taskManager.getSubTask(idSubTask));

        SubTask subTask2 = new SubTask("Подзадача 2", "Описание 2", (epic.getId()));
        int idSubTask2 = taskManager.addNewSubtask(subTask2);
        System.out.println(taskManager.getSubTask(idSubTask));

        SubTask subTask3 = new SubTask("Подзадача 3", "Описание 3", (epic2.getId()));
        int idSubTask3 = taskManager.addNewSubtask(subTask3);
        System.out.println(taskManager.getSubTask(idSubTask3));
        System.out.println("____________________");

        System.out.println("Список подзадач");
        System.out.println(taskManager.getSubTasks());
        System.out.println("____________________");

        System.out.println("Изменение подзадачи");
        SubTask subTask4 = new SubTask(subTask2.getId(), "Готовка", "Приготовить еду"
        );
        subTask4.setEpicId(idEpic);
        taskManager.updateSubtask(subTask4);
        System.out.println(taskManager.getSubTask(idSubTask2));
        System.out.println("____________________");

        System.out.println("Подзадачи по эпику");
        System.out.println(taskManager.getEpicSubtasks(idEpic));
        System.out.println("____________________");


        System.out.println("Подзадача по выбору");
        System.out.println(taskManager.getSubTask(idSubTask));
        System.out.println("____________________");
        System.out.println("Эпик по выбору");
        System.out.println(taskManager.getEpic(idEpic));
        System.out.println("____________________");

        System.out.println("Изменение эпика");
        Epic epic3 = new Epic(idEpic2, "Готовка", "Приготовить еду");
        taskManager.updateEpic(epic3);
        System.out.println(taskManager.getEpic(idEpic2));
        System.out.println("____________________");

        System.out.println("История последних 10 просмотров");
        for (Task i:  historyManager.getHistory()) {
            System.out.println(i);
        }
        System.out.println("____________________");

        taskManager.deleteTask(idTask);
        System.out.println("Удаление задачи");
        System.out.println(taskManager.getTask(idTask));
        System.out.println("____________________");

        System.out.println("Удаление подзадачи");
        taskManager.deleteSubtask(idSubTask);
        System.out.println(taskManager.getSubTask(idSubTask));
        System.out.println("____________________");

        System.out.println("Удаление эпика");
        taskManager.deleteEpic(idEpic2);
        System.out.println(taskManager.getEpic(idEpic2));
        System.out.println("____________________");

        System.out.println("Удаление всех задач");
        System.out.println(taskManager.getTasks());
        taskManager.deleteAllTasks();
        System.out.println(taskManager.getTasks());
        System.out.println("____________________");

        System.out.println("Удаление всех подзадач");
        System.out.println(taskManager.getSubTasks());
        taskManager.deleteAllSubTasks();
        System.out.println(taskManager.getSubTasks());
        System.out.println("____________________");

        System.out.println("Удаление всех эпиков");
        System.out.println(taskManager.getEpics());
        taskManager.deleteAllEpics();
        System.out.println(taskManager.getEpics());
        System.out.println("____________________");

    }
}
