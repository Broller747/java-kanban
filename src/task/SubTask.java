package task;

public class SubTask extends Task {


    private int epicId;

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    public SubTask(int id, String name, String description) {
        super(id, name, description);
    }


    public SubTask(String name, String description, Integer epicId) {
        super(name, description);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" + "id: " + getId() + ", Название задачи: " + getName() + ", Описание задачи: "
                + getDescription() + ", epicId: " + epicId + " статус: " + getStatus() +
                '}';
    }
}
