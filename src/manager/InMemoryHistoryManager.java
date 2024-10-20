package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public static final int MAX_SIZE = 10;
    private final List<Task> history = new ArrayList<>();

    @Override
    public ArrayList<Task> getHistory() {
        return (ArrayList<Task>) history;
    }

    @Override
    public void add(Task task) {
        if (history.size() >= MAX_SIZE) {
            history.remove(0);
        }
        history.add(task);
    }
}