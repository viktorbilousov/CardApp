package application.util;


import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

public final class StageUtil {

    public static ExtensionFilter XMLFiler =  new ExtensionFilter("XML Files", "*.xml");
    public static ExtensionFilter XLSXFiler =  new ExtensionFilter("XLSX Files", "*.xlsx");

    public static void showAlertMessage(String title, String header, String context, Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(primaryStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(context);
        alert.showAndWait();

    }

    public static Stage makeNewStage(String title, Stage primaryStage) {
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage);
        return stage;
    }

    public static File FileChooser(Stage rootStage, ExtensionFilter filter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(filter);
        /*
                new ExtensionFilter("XML Files", "*.xml"),
                new ExtensionFilter("All Files", "*.*")*/
        return fileChooser.showOpenDialog(rootStage);
    }

    public static File FileOpener(Stage rootStage, ExtensionFilter filter) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Resource File");
        fileChooser.getExtensionFilters().addAll(filter);
        fileChooser.setInitialFileName("file");
        return fileChooser.showSaveDialog(rootStage);

    }

    public static void showAboutMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Card App v1.0");
        alert.setContentText("Made by Viktor Belousov\nEmail: 17123vitek@gmail.com");
        alert.showAndWait();
    }
    public static void showExceptionDialog(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Exception Dialog");
        alert.setHeaderText("An error occurred:");

        String content = "Error: ";
        if (null != e) {
            content += e.toString() + "\n\n";
        }

        alert.setContentText(content);

        Exception ex = new Exception(e);

        //Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);

        String exceptionText = sw.toString();

        //Set up TextArea
        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);


        textArea.setPrefHeight(600);
        textArea.setPrefWidth(800);


        //Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(textArea);


        alert.showAndWait();
    }

}
