package application.model.edit.sceneModel;

import application.controllers.edit.ThemesEditSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.edit.stageModels.RootStageModel;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class ThemesEditSceneModel extends SceneModel {

    public ThemesEditSceneController myController = (ThemesEditSceneController) controller;
    public RootStageModel rootStageModel = (RootStageModel) parent;

    public ThemesEditSceneModel(BorderPane rootLayout, StageModel parent, URL FXMLLocation) {
        super(rootLayout, parent, FXMLLocation);
    }

    public void setThemesList(ArrayList<Theme> themesList){
        myController.setThemeArrayList(themesList);
    }
    public void backToViewThemes(){
        rootStageModel.showViewThemes();
    }

}
