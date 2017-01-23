package application.model.sceneModel;

import application.MainApp;
import application.model.SceneModel;
import application.model.stageModels.AddThemeStageModel;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

public class ViewThemesSceneModel extends SceneModel{
    public ViewThemesSceneModel(BorderPane rootLayout, MainApp mainApp, String pathToFXML) {
        super(rootLayout, mainApp, pathToFXML);
    }

    public void showQuestionScene(int idTheme){
        mainApp.showViewQuestionOfTheme(idTheme);
    }
    public void showAddThemeStage(){
        AddThemeStageModel addTheme = mainApp.getAddThemeStageModel();
        addTheme.setEditMode(false);
        mainApp.getAddThemeStageModel().setDataToController(controller.getInputData());
        mainApp.getAddThemeStageModel().show();
    }

    public void showEditTheme(Theme t) {
        AddThemeStageModel addTheme = mainApp.getAddThemeStageModel();
        addTheme.setThemeNameField(t.getThemeName());
        addTheme.setEditMode(true);
        addTheme.setDataToController(t);
        addTheme.show();
    }

    public void openErrorSelectWindow(){
        mainApp.showAlertMessage(
                "No Selection",
                "No Theme Selected",
                "Please select a Theme in the table.");
    }
}
