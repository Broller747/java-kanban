package manager;

import task.Task;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    InMemoryHistoryManager inMemoryHistoryManager= new InMemoryHistoryManager();
    private final Map<Integer,Node> history =new HashMap<>();
    private static Node head;
    private Node tail;
    private int lastId=0;


    private  void  linkLast(Task task){
if (history.isEmpty()){
    head = new Node(null, history.get(lastId).getElement(),null);
    history.put(task.getId(),head);
} else{
    tail = new Node(history.get(lastId), task, null);
    history.get(lastId).next= tail;
    history.put(task.getId(), tail);
}
lastId=task.getId();
    }

    public  List<Task> getTasks(){
        if (head==null){
            System.out.println("История пуста");
            return null;
        } else {
            List<Task> ArrayHistory = new ArrayList<>();
            Node temp =head.next;
            ArrayHistory.add( head.element);
            ArrayHistory.add( temp.element);
            while (temp.next!=null){
                temp=temp.next;
                ArrayHistory.add(temp.element);
            }
            return ArrayHistory;
            }

        }


    private void removeNode(Node delNode){
        Node selectPrev= delNode.prev;
        Node selectNext= delNode.next;
if (delNode.prev==null&& delNode.next==null){

} else if (delNode.prev==null) {
    selectNext.prev=null;
    head=selectNext;
} else if (delNode ==null) {
    selectPrev.next=null;
    tail=selectPrev;
} else {
    selectPrev.next=selectNext;
    selectNext.prev=selectPrev;
}
    }

    @Override
    public List<Task> getHistory() {
        return  inMemoryHistoryManager.getTasks();
    }
    @Override
    public void remove(int id){
        if (history.containsKey(id)){
            Node removeNode = history.remove(id);
            removeNode(removeNode);
        } else {
            System.out.println("Id не найден.");
        }
    }

    @Override
    public void add(Task task) {
        inMemoryHistoryManager.linkLast(task);
    }
    private  static class Node<T> {
        private Node next;
        private final Task  element;
        private Node prev;


        public Node(Node next, Task taskNode, Node prev) {
            this.next = next;
            this.prev = prev;
            this.element = taskNode;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Task getElement() {
            return element;
        }
    }
}