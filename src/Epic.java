import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Integer> subTasksId = new ArrayList<>();

    public ArrayList<Integer> getSubTasksId() {
        return subTasksId;
    }

    public void setSubTasksId(ArrayList<Integer> subTasksId) {
        this.subTasksId = subTasksId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subTasksId= " + subTasksId + "Статус: " + getStatus() +
                '}';
    }
}


