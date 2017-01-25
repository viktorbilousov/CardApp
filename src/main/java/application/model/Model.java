package application.model;

import java.net.URL;

public interface Model {
    void init(URL pathToFXML);
    void setDataToController(Object data);
    void updateData();
    void show();
    void close();
}
