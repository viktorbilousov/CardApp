package application.util;


import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class StageUtil {
    public static void showAlertMessage(String title, String header, String context, Stage primaryStage){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();

    }
    public static Stage makeNewStage(String title, Stage primaryStage){
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        return stage;
    }



}
