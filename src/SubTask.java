public class SubTask extends Task {


    private int epicId;

    int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "epicId: " + epicId + " статус: " + getStatus() +
                '}';
    }
}
