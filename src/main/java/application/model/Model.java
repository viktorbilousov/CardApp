package application.model;

public interface Model {
    void init();
    void setDataToController(Object data);
    void updateData();
    void show();
    void close();
}
