package manager;

import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, Node> history = new HashMap<>();
    private Node head;
    private Node tail;
    private int lastId = 0;


    private void linkLast(Task task) {
        if (head == null) {
            head = new Node(null, task, null);
            history.put(task.getId(), head);
        } else {
            tail = new Node(history.get(lastId), task, null);
            history.put(task.getId(), tail);
            Node tempNode = history.get(lastId);

            tempNode.next = tail;

        }
        lastId = task.getId();
    }

    private List<Task> getTasks() {
        List<Task> arrayHistory = new ArrayList<>();
        if (head == null) {
            System.out.println("История пуста");
            return arrayHistory;
        } else if (tail == null) {
            arrayHistory.add(head.element);
            return arrayHistory;
        } else {
            for (Node tempTask : history.values()) {
                arrayHistory.add(tempTask.element);
            }

            return arrayHistory;
        }

    }


    private void removeNode(Node delNode) {
        Node selectPrev = delNode.prev;
        Node selectNext = delNode.next;
        if (delNode.prev == null && delNode.next == null) {
            System.out.println("Удаление последнего элемента");
        } else if (delNode.prev == null) {
            selectNext.prev = null;
            head = selectNext;
        } else if (delNode == null) {
            selectPrev.next = null;
            tail = selectPrev;
        } else {
            selectPrev.next = selectNext;
            selectNext.prev = selectPrev;
        }
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {
        if (history.containsKey(id)) {
            Node removeNode = history.remove(id);
            removeNode(removeNode);
        } else {
            System.out.println("Id не найден.");
        }
    }

    @Override
    public void add(Task task) {
        if (task == null) {
            System.out.println("Пустая задача.");
        } else {
            Task copy = task;
            if (history.containsKey(task.getId())) {
                remove(task.getId());
                linkLast(copy);
            } else {
                linkLast(task);

            }
        }
    }

    private static class Node {
        private Node next;
        private final Task element;
        private Node prev;


        public Node(Node next, Task taskNode, Node prev) {
            this.next = next;
            this.prev = prev;
            this.element = taskNode;
        }

    }
}