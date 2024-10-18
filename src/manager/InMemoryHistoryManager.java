package manager;

import task.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    public static ArrayList<Task> history = new ArrayList<>();

    @Override
    public ArrayList<Task> getHistory() {
        return history;
    }

    @Override
    public void add(Task task) {
        if (history.size() > 9) {
            history.remove(0);
        }
        history.add(task);
    }
}