package application.model.sceneModel;

import application.MainApp;
import application.model.Model;
import application.model.SceneModel;
import application.model.stageModels.AddThemeStageModel;
import application.model.stageModels.RootStageModel;
import application.util.StageUtil;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;

import java.net.URL;

public class ViewThemesSceneModel extends SceneModel{

    private RootStageModel rootModel = (RootStageModel) this.parentModel;
    private AddThemeStageModel addThemeModel;

    public ViewThemesSceneModel(BorderPane rootLayout, Model parentModel, URL FXMLLocation) {
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


    public void showQuestionScene(int idTheme){
        rootModel.showViewQuestionOfTheme(idTheme);
    }
    public void showAddThemeStage(){
        addThemeModel.setEditMode(false);
        addThemeModel.setDataToController(controller.getInputData());
        addThemeModel.show();
    }

    public void showEditTheme(Theme t) {
        addThemeModel.setThemeNameField(t.getThemeName());
        addThemeModel.setEditMode(true);
        addThemeModel.setDataToController(t);
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
