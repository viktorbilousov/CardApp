package application.model.sceneModel;

import application.MainApp;
import application.controllers.SceneViewThemesController;
import application.model.SceneModel;
import application.model.StageModel;
import application.model.stageModels.AddThemeStageModel;
import application.model.stageModels.RootStageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ArrayList;

public class ViewThemesSceneModel extends SceneModel{

    private RootStageModel rootModel = (RootStageModel) this.parent;
    private AddThemeStageModel addThemeModel;
    private SceneViewThemesController myController = (SceneViewThemesController) controller;

    public ViewThemesSceneModel(BorderPane rootLayout, StageModel parentModel, URL FXMLLocation) {
        super(rootLayout, parentModel, FXMLLocation);
        init();
    }

    public void init(){
        addThemeModel = new AddThemeStageModel(
                StageUtil.makeNewStage("Theme", rootModel.getPrimaryStage()),
                this,
                MainApp.class.getResource("../fxml/StageAddTheme.fxml")
        );
    }

    public void setThemeList(ArrayList<Theme> themes){
        myController.setThemeArrayList(themes);
        updateData();
    }

    public void showQuestionScene(int idTheme){
        rootModel.showViewQuestionOfTheme(idTheme);
    }
    public void showAddThemeStage(){
        addThemeModel.setEditMode(false);
        addThemeModel.setThemesList(myController.getThemeArrayList());
        addThemeModel.show();
    }

    public void showEditTheme(Theme t) {
        addThemeModel.setThemeNameField(t.getThemeName());
        addThemeModel.setEditMode(true);
        addThemeModel.setEditTheme(t);
        addThemeModel.show();
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
