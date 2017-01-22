package application.controllers;

import javafx.application.Application;

public interface Controller {
    void initialize();
    void updateElementsData();
    void setInputData(Object data);
    void setMainApp(Application mainApp);
}
