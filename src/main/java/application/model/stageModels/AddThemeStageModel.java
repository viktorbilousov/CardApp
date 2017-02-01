package application.model.stageModels;

import application.controllers.ThemeAddStageController;
import application.model.Model;
import application.model.StageModel;
import cardSystem.Theme;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class AddThemeStageModel extends StageModel{

    private ThemeAddStageController myController = (ThemeAddStageController)controller;


    public AddThemeStageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        super(primaryStage, parent, FXMLLocation);
        init();
    }

    public void updateAddedModel(){
        parent.updateData();
    }

    private void init(){

    }

    public void setThemeNameField(String name){
        myController.setThemeFieldText(name);
    }

    public void setEditMode(boolean editMode){
        myController.setEditMode(editMode);
    }

    public void setThemesList(ArrayList<Theme> themeList){
        myController.setThemeList(themeList);
        updateData();
    }

    public void setEditTheme(Theme theme){
        myController.setEditTheme(theme);
    }



 }
