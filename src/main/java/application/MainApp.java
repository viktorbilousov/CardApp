package application;

import application.model.SceneModel;
import application.model.StageModel;
import application.model.sceneModel.ViewQuestionSceneModel;
import application.model.sceneModel.ViewThemesSceneModel;
import application.model.stageModels.AddQuestionStageModel;
import application.model.stageModels.AddThemeStageModel;
import cardSystem.CardSystem;
import cardSystem.Question;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Created by BellPC on 20.01.2017.
 */
public class MainApp extends Application {

    private CardSystem cardSystem;

    private Stage primaryStage = null;
    private StageModel rootStageModel;
    private AddQuestionStageModel addQuestionStageModel;
    private AddThemeStageModel addThemeStageModel;

    private ViewThemesSceneModel viewThemesModel;
    private ViewQuestionSceneModel viewQuestionModel;

    public MainApp(CardSystem cardSystem) {
        this.cardSystem = cardSystem;
    }

    //region getters

    public CardSystem getCardSystem() {
        return cardSystem;
    }

    public StageModel getRootStageModel() {
        return rootStageModel;
    }

    public AddQuestionStageModel getAddQuestionStageModel() {
        return addQuestionStageModel;
    }

    public ViewThemesSceneModel getViewThemesModel() {
        return viewThemesModel;
    }

    public ViewQuestionSceneModel getViewQuestionModel() {
        return viewQuestionModel;
    }

    public AddThemeStageModel getAddThemeStageModel() {
        return addThemeStageModel;
    }

    //endregion

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Card System");
        initialize();
        rootStageModel.show();
  //      viewQuestionModel.updateData();
 //       viewQuestionModel.show();
        viewThemesModel.show();
    }
    private void initialize(){
        rootStageModel = new StageModel( primaryStage, this ,"../fxml/StageRoot.fxml");
        viewQuestionModel = new ViewQuestionSceneModel((BorderPane) rootStageModel.getRootLayout(), this, "../fxml/SceneViewQuestions.fxml");

        viewThemesModel = new ViewThemesSceneModel((BorderPane) rootStageModel.getRootLayout(), this ,"../fxml/SceneViewThemes.fxml");
        viewThemesModel.setDataToController(cardSystem.getThemeList());
        initAddQuestionStage();
        initAddThemeQuestion();
    }

    private void initAddQuestionStage(){

        addQuestionStageModel = new AddQuestionStageModel(
                makeNewStage("Question"),
                this,
                "../fxml/StageAddQuestion.fxml" );
    }

    private void initAddThemeQuestion(){
        addThemeStageModel = new AddThemeStageModel(
                makeNewStage("Theme"),
                this,
                "../fxml/StageAddTheme.fxml" );
    }

    public void showAddQuestionStage(){
        addQuestionStageModel.show();
    }

    public void showViewThemes(){
        viewThemesModel.show();
    }

    public void showViewQuestionOfTheme(int idTheme){
       viewQuestionModel.viewTheme(idTheme);
       viewQuestionModel.show();
    }

    public void showAlertMessage(String title, String header, String context){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(primaryStage);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(context);
            alert.showAndWait();

    }

    private Stage makeNewStage(String title){
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        return stage;
    }

}
