package application;

import application.model.edit.stageModels.RootStageModel;
import cardSystem.CardSystem;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by BellPC on 20.01.2017.
 */
public class MainApp extends Application {

    private Stage primaryStage = null;
    private RootStageModel rootStageModel;

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
               MainApp.class.getResource("../fxml/edit/StageRoot.fxml")
       );
    }

}
