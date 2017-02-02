package application.model.sceneModel;

import application.controllers.ThemesViewSceneController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.RootStageStageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class ThemesViewSceneModel extends SceneModel{

    private RootStageStageModel rootModel = (RootStageStageModel) this.parent;
    private ThemesViewSceneController myController = (ThemesViewSceneController) controller;

    public ThemesViewSceneModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
        init();
    }

    public void init(){
    }

    public void setThemeList(ArrayList<Theme> themes){
        myController.setThemeArrayList(themes);
        updateData();
    }

    public void showQuestionScene(Theme theme){
        rootModel.showViewQuestionOfTheme(theme);
    }


    public void showEditTheme() {
       rootModel.showEditThemesScene();
    }


    public void openErrorSelectWindow(){
        StageUtil.showAlertMessage(
                "No Selection",
                "No Theme Selected",
                "Please select a Theme in the table.",
                this.rootModel.getPrimaryStage()
        );
    };

}
