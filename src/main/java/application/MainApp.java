package application;

import application.model.SceneModel;
import application.model.StageModel;
import application.model.sceneModel.ViewQuestionSceneModel;
import application.model.sceneModel.ViewThemesSceneModel;
import application.model.stageModels.AddQuestionStageModel;
import application.model.stageModels.AddThemeStageModel;
import application.model.stageModels.RootStageModel;
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
    private RootStageModel rootStageModel;


    public MainApp(CardSystem cardSystem) {
        this.cardSystem = cardSystem;
    }

    public CardSystem getCardSystem() {
        return cardSystem;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Card System");
        initialize();
       rootStageModel.show();
    }
    private void initialize() {
        rootStageModel = new RootStageModel(
                primaryStage,
                null,
                MainApp.class.getResource("../fxml/StageRoot.fxml"),
                cardSystem
        );
    }

}
