package application.model;

public interface Model {
    void init(String pathToFXML);
    void setDataToController(Object data);
    void updateData();
    void show();
    void close();
}
