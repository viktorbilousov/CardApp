package application.model.stageModels;


import application.MainApp;
import application.model.StageModel;
import application.model.sceneModel.QuestionsEditSceneModel;
import application.model.sceneModel.QuestionsViewSceneModel;
import application.model.sceneModel.ThemesEditSceneModel;
import application.model.sceneModel.ThemesViewSceneModel;
import cardSystem.CardSystem;
import cardSystem.Question;
import cardSystem.Theme;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class RootStageStageModel extends StageModel {

    private QuestionsViewSceneModel viewQuestionScene;
    private QuestionsEditSceneModel editQuestionScene;
    private ThemesViewSceneModel viewThemesScene;
    private ThemesEditSceneModel editThemesScene;
    private CardSystem system;


    public RootStageStageModel(Stage primaryStage, StageModel parent, URL FXMLLocation, CardSystem system) {
        super(primaryStage, parent, FXMLLocation);
        this.system = system;
        init();
        showViewThemes();
    }

    public QuestionsViewSceneModel getViewQuestionScene() {
        return viewQuestionScene;
    }

    public ThemesViewSceneModel getViewThemesScene() {
        return viewThemesScene;
    }

    public CardSystem getCardSystem() {
        return system;
    }

    public void init(){
        viewQuestionScene = new QuestionsViewSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneQuestionsView.fxml")
        );
        viewThemesScene = new ThemesViewSceneModel(
                (BorderPane)getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneThemesView.fxml")
        );
        viewThemesScene.setThemeList(system.getThemeList());

        editThemesScene = new ThemesEditSceneModel(
                (BorderPane) getRootLayout(),
                this,
                MainApp.class.getResource("../fxml/SceneThemesEdit.fxml")
        );
        editThemesScene.setThemesList(system.getThemeList());

        editQuestionScene = new QuestionsEditSceneModel(
                (BorderPane)rootLayout,
                this,
                MainApp.class.getResource("../fxml/SceneQuestionsEdit.fxml")
        );


    }

    public void showViewQuestionOfTheme(Theme theme){
        viewQuestionScene.setTheme(theme);
        viewQuestionScene.show();
    }
    public void showViewQuestion(){
        viewQuestionScene.updateData();
        viewQuestionScene.show();
    }

    public void showEditThemesScene(){
        editThemesScene.updateData();
        editThemesScene.show();
    }

    public void showViewThemes(){
        viewThemesScene.updateData();
        viewThemesScene.show();
    }
    public void showEditQuestionScene(Theme theme){
        editQuestionScene.setQuestionList(theme.getQuestionsList());
        editQuestionScene.updateData();
        editQuestionScene.show();

    }



}
