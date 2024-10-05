package task;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subTasksId = new ArrayList<>();

    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
    }

    public void setSubTasksId(ArrayList<Integer> subTasksId) {
        this.subTasksId = subTasksId;
    }

    public void addSubtaskId(int id) {
        subTasksId.add(id);
    }

    public void removeSubtask(Integer id) {
        subTasksId.remove(id);
    }

    public Epic(String name, String description) {
        super(name, description);

    }

    public Epic(int id, String name, String description) {
        super(id, name, description);

    }

    public Epic(int id) {
        super(id);
    }


    @Override
    public String toString() {
        return "Epic{" + "id: " + getId() + ", название: " + getName() + ", описание " + getDescription() +
                ", Статус: " + getStatus() + "subTasksId= " + subTasksId +
                '}';
    }
}


