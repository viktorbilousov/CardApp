package application;

import application.model.SceneModel;
import application.model.StageModel;
import cardSystem.CardSystem;
import cardSystem.Question;
import javafx.application.Application;
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
    private StageModel addQuestionStageModel;

    private SceneModel viewThemesModel;
    private SceneModel viewQuestionModel;

    public MainApp(CardSystem cardSystem) {
        this.cardSystem = cardSystem;
    }

    //region getters
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public StageModel getAddQuestionStageModel() {
        return addQuestionStageModel;
    }

    public SceneModel getViewThemesModel() {
        return viewThemesModel;
    }

    public SceneModel getViewQuestionModel() {
        return viewQuestionModel;
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
        rootStageModel.init();

        viewQuestionModel = new SceneModel((BorderPane) rootStageModel.getRootLayout(), this, "../fxml/SceneViewQuestions.fxml");
        viewQuestionModel.init();

        viewThemesModel = new SceneModel((BorderPane) rootStageModel.getRootLayout(), this ,"../fxml/SceneViewThemes.fxml");
        viewThemesModel.setDataToController(cardSystem.getThemeList());
        viewThemesModel.init();

        initAddQuestionStage();
    }

    private void initAddQuestionStage(){
        Stage stage = new Stage();
        stage.setTitle("New Question");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        addQuestionStageModel = new StageModel(stage, this, "../fxml/StageAddQuestion.fxml" );
        addQuestionStageModel.init();
    }
    public void showAddQuestionStage(){
        addQuestionStageModel.show();
    }

    public void showViewThemes(){
        viewThemesModel.show();
    }
    public void showViewQuestionOfTheme(int idTheme){
        ArrayList<Question> list = (ArrayList<Question>) cardSystem.getThemeList().get(idTheme).getQuestionsList();

        viewQuestionModel.setDataToController(cardSystem.getThemeList()
                .get(idTheme).getQuestionsList());

        viewQuestionModel.updateData();
        viewQuestionModel.show();
    }




}
