package application.model.stageModels;


import application.MainApp;
import application.model.StageModel;
import application.model.sceneModel.ViewQuestionSceneModel;
import application.model.sceneModel.ViewThemesSceneModel;
import cardSystem.CardSystem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class RootStageModel extends StageModel {

    private ViewQuestionSceneModel  viewQuestionScene;
    private ViewThemesSceneModel    viewThemesScene;
    private CardSystem system;


    public RootStageModel(Stage primaryStage, StageModel parent, URL FXMLLocation, CardSystem system) {
        super(primaryStage, parent, FXMLLocation);
        this.system = system;
        init();
        showViewThemes();
    }

    public ViewQuestionSceneModel getViewQuestionScene() {
        return viewQuestionScene;
    }

    public ViewThemesSceneModel getViewThemesScene() {
        return viewThemesScene;
    }

    public CardSystem getCardSystem() {
        return system;
    }

    public void init(){
        viewQuestionScene = new ViewQuestionSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneViewQuestions.fxml")
        );
        viewThemesScene = new ViewThemesSceneModel(
                (BorderPane)getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneViewThemes.fxml")
        );
        viewThemesScene.setThemeList(system.getThemeList());
    }

    public void showViewQuestionOfTheme(int idTheme){
        viewQuestionScene.viewThemesQuestion(idTheme);
        viewQuestionScene.show();
    }

    public void showViewThemes(){
        viewThemesScene.show();
    }



}
