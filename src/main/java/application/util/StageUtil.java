package application.util;


import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

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
    public static File FileChooser(Stage rootStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("XML Files", "*.xml"),
                new ExtensionFilter("All Files", "*.*"));
        return fileChooser.showOpenDialog(rootStage);
    }
    public static File FileOpener(Stage rootStage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("XML Files", "*.xml"),
                new ExtensionFilter("All Files", "*.*"));
        fileChooser.setInitialFileName("file.xml");
        return fileChooser.showSaveDialog(rootStage);

    }



}
