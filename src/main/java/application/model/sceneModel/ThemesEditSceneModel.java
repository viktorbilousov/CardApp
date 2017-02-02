package application.model.sceneModel;

import application.controllers.ThemesEditSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.RootStageStageModel;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class ThemesEditSceneModel extends SceneModel {

    public ThemesEditSceneController myController = (ThemesEditSceneController) controller;
    public RootStageStageModel rootStageStageModel = (RootStageStageModel) parent;

    public ThemesEditSceneModel(BorderPane rootLayout, StageModel parent, URL FXMLLocation) {
        super(rootLayout, parent, FXMLLocation);
    }

    public void setThemesList(ArrayList<Theme> themesList){
        myController.setThemeArrayList(themesList);
    }
    public void backToViewThemes(){
        rootStageStageModel.showViewThemes();
    }

}
