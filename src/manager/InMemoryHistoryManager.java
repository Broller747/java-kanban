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


    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    @Override
    public void remove(int id) {

        final Node node = history.remove(id);
        if (node == null) {
            return;
        }
        removeNode(node);

    }

    @Override
    public void add(Task task) {

        if (task == null) {
            return;
        }
        final int id = task.getId();
        remove(id);
        linkLast(task);
        history.put(id, tail);
    }

    private void linkLast(Task task) {
        final Node node = new Node(tail, task, null);
        if (head == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node node = head;
        for (int i = 0; i < history.size(); i++) {
            tasks.add(node.element);
            node = node.next;
        }
        return tasks;
    }


    private void removeNode(Node delNode) {
        Node selectPrev = delNode.prev;
        Node selectNext = delNode.next;
        if (delNode.prev == null && delNode.next == null) {
            System.out.println("Удаление последнего элемента");
        } else if (delNode.prev == null) {
            selectNext.prev = null;
            head = selectNext;
        } else {
            selectPrev.next = selectNext;
            selectNext.prev = selectPrev;
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