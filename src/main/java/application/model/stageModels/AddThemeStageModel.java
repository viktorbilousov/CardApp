package application.model.stageModels;

import application.MainApp;
import application.controllers.StageAddThemeController;
import application.model.Model;
import application.model.StageModel;
import javafx.stage.Stage;

import java.net.URL;

public class AddThemeStageModel extends StageModel{

    StageAddThemeController myController = (StageAddThemeController)controller;

    public AddThemeStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
    }

    public void updateAddedModel(){
        parent.updateData();
    }

    public void setThemeNameField(String name){
        myController.setThemeFieldText(name);
    }

    public void setEditMode(boolean editMode){
        myController.setEditMode(editMode);
    }
 }
