package application.controllers;

import application.model.Model;
import javafx.application.Application;

public interface Controller {
    void initialize();
    void updateElementsData();
    void setInputData(Object data);
    void setMyModel(Model model);
    Object getInputData();
}
