public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        System.out.println("____________________");
        System.out.println("Создание задач");
        Task task = new Task();
        task.setDescription("Убрать квартиру");
        task.setName("Уборка");
        taskManager.addTask(task);
        System.out.println(taskManager.printToIdTask(1));

        Task task1 = new Task();
        task1.setDescription("Полить цветы");
        task1.setName("Цветы");
        taskManager.addTask(task1);
        System.out.println(taskManager.printToIdTask(2));

        System.out.println("____________________");
        System.out.println("Изменение задачи");
        Task task2 = new Task();
        task2.setId(task.getId());
        task2.setDescription("Приготовить еду");
        task2.setName("Готовка");
        task2.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(task2);
        System.out.println(taskManager.printToIdTask(1));
        System.out.println("____________________");

        System.out.println("Список задач");
        System.out.println(taskManager.printAllTasks());

        System.out.println("____________________");
        System.out.println("Создание эпиков");
        Epic epic = new Epic();
        epic.setId(task.getId());
        epic.setDescription("............................");
        epic.setName("Ремонт");
        taskManager.addEpic(epic);
        System.out.println(taskManager.printToIdEpic(3));

        Epic epic2 = new Epic();
        taskManager.addEpic(epic2);
        System.out.println(taskManager.printToIdEpic(4));
        System.out.println("____________________");

        System.out.println("Список эпиков");
        System.out.println(taskManager.printAllEpics());
        System.out.println("____________________");

        System.out.println("Создание Подзадач");
        SubTask subTask1 = new SubTask();
        subTask1.setEpicId(epic.getId());
        taskManager.addSubTask(subTask1);
        System.out.println(taskManager.printToIdSubTask(5));

        SubTask subTask2 = new SubTask();
        subTask2.setEpicId(epic.getId());
        taskManager.addSubTask(subTask2);
        System.out.println(taskManager.printToIdSubTask(6));

        SubTask subTask3 = new SubTask();
        subTask3.setEpicId(epic2.getId());
        taskManager.addSubTask(subTask3);
        System.out.println(taskManager.printToIdSubTask(7));
        System.out.println("____________________");

        System.out.println("Список подзадач");
        System.out.println(taskManager.printAllSubTasks());
        System.out.println("____________________");

        System.out.println("Изменение подзадачи");
        SubTask subTask4 = new SubTask();
        subTask4.setId(subTask2.getId());
        subTask4.setDescription("Приготовить еду");
        subTask4.setName("Готовка");
        subTask4.setStatus(TaskStatus.IN_PROGRESS);
        subTask4.setEpicId(3);
        taskManager.updateSubTask(subTask4);
        System.out.println(taskManager.printToIdSubTask(6));
        System.out.println("____________________");

        System.out.println("Подзадачи по эпику");
        System.out.println(taskManager.listAllEpicsSubTask(3));
        System.out.println("____________________");


        System.out.println("Подзадача по выбору");
        System.out.println(taskManager.printToIdSubTask(5));
        System.out.println("____________________");
        System.out.println("Эпик по выбору");
        System.out.println(taskManager.printToIdEpic(3));
        System.out.println("____________________");

        System.out.println("Изменение эпика");
        Epic epic3 = new Epic();
        epic3.setId(epic2.getId());
        epic3.setDescription("Приготовить еду");
        epic3.setName("Готовка");
        taskManager.updateEpic(epic3);
        System.out.println(taskManager.printToIdEpic(4));
        System.out.println("____________________");

        taskManager.deleteSelectedTask(1);
        System.out.println("Удаление задачи");
        System.out.println(taskManager.printToIdTask(1));
        System.out.println("____________________");

        System.out.println("Удаление подзадачи");
        taskManager.deleteSelectedSubTask(5);
        System.out.println(taskManager.printToIdSubTask(5));
        System.out.println("____________________");

        System.out.println("Удаление эпика");
        taskManager.deleteSelectedEpic(4);
        System.out.println(taskManager.printToIdEpic(4));
        System.out.println("____________________");

        System.out.println("Удаление всех задач");
        System.out.println(taskManager.printAllTasks());
        taskManager.deleteAllTasks();
        System.out.println(taskManager.printAllTasks());
        System.out.println("____________________");

        System.out.println("Удаление всех подзадач");
        System.out.println(taskManager.printAllSubTasks());
        taskManager.deleteAllSubTasks();
        System.out.println(taskManager.printAllSubTasks());
        System.out.println("____________________");

        System.out.println("Удаление всех эпиков");
        System.out.println(taskManager.printAllEpics());
        taskManager.deleteAllEpics();
        System.out.println(taskManager.printAllEpics());
        System.out.println("____________________");

    }
}
