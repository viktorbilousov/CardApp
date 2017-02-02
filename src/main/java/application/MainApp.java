package application;

import application.model.stageModels.RootStageStageModel;
import cardSystem.CardSystem;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Created by BellPC on 20.01.2017.
 */
public class MainApp extends Application {

    private CardSystem cardSystem;

    private Stage primaryStage = null;
    private RootStageStageModel rootStageStageModel;


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
       rootStageStageModel.show();
    }
    private void initialize() {
        rootStageStageModel = new RootStageStageModel(
                primaryStage,
                null,
                MainApp.class.getResource("../fxml/StageRoot.fxml"),
                cardSystem
        );
    }

}
