package application.model;

import application.MainApp;
import application.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneModel implements Model {

    private Controller controller;
    private BorderPane rootLayout;
    private AnchorPane thisLayout;
    private String pathToFXML;
    private Object dataToController;
    private Application mainApp;


    public SceneModel(BorderPane rootLayout, Application mainApp, String pathToFXML) {
        this.rootLayout = rootLayout;
        this.pathToFXML = pathToFXML;
        this.mainApp = mainApp;
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void setDataToController(Object dataToController) {
        this.dataToController = dataToController;
    }
    public void updateData(){
        if(controller == null){
            System.out.println("error: controller == null in " + getClass().getSimpleName());
            return;
        }
        controller.setInputData(dataToController);
        controller.updateElementsData();
    }

    public void init(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(pathToFXML));
            thisLayout =  loader.load();
            // Даём контроллеру доступ к главному прилодению.
            controller = loader.getController();
            controller.setInputData(dataToController);
            //todo не перенести ли setInputData в setDataToController ?
            controller.setMainApp(mainApp);
            controller.updateElementsData();

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void show(){
        rootLayout.setCenter(thisLayout);
    }


    @Override
    public void close() {
        thisLayout.setVisible(false);
    }
}

