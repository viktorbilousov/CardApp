package application.model.stageModels;

import application.MainApp;
import application.controllers.StageAddThemeController;
import application.model.StageModel;
import javafx.stage.Stage;

public class AddThemeStageModel extends StageModel{

    StageAddThemeController myController = (StageAddThemeController)controller;

    public AddThemeStageModel(Stage primaryStage, MainApp mainApp, String pathToFXML) {
        super(primaryStage, mainApp, pathToFXML);
    }

    public void updateAddedModel(){
        mainApp.getViewThemesModel().updateData();
    }

    public void setThemeNameField(String name){
        myController.setThemeFieldText(name);
    }

    public void setEditMode(boolean editMode){
        myController.setEditMode(editMode);
    }
 }
