package application;

import application.model.edit.stageModels.RootStageModel;
import application.util.StageUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage = null;
    private RootStageModel rootStageModel;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("start");
        try {
            this.primaryStage = primaryStage;
            primaryStage.setTitle("Card System");
         //   Platform.setImplicitExit(false);
            initialize();
            rootStageModel.show();
        }catch (Exception e){
            StageUtil.showExceptionDialog(e);
        }
        System.out.println("ok start");
    }

    private void initialize() {
        System.out.println("start init");
        rootStageModel = new RootStageModel(
               primaryStage,
               null,
               MainApp.class.getResource("/fxml/edit/StageRoot.fxml")
       );
        System.out.println("end init");

    }

    public static void main(String[] args) {
        try {
            launch(args);
        }catch (Exception e){
            System.out.println(e);
            StageUtil.showExceptionDialog(e);

        }
    }

}
