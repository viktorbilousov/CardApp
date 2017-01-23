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

    protected Controller controller;
    protected BorderPane rootLayout;
    protected AnchorPane thisLayout;
    protected MainApp mainApp;

    public SceneModel(BorderPane rootLayout, MainApp mainApp, String pathToFXML) {
        this.rootLayout = rootLayout;
        this.mainApp = mainApp;
        init(pathToFXML);
    }

    public BorderPane getRootLayout() {
        return rootLayout;
    }

    public void init(String pathToFXML){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(pathToFXML));
            thisLayout =  loader.load();
            // Даём контроллеру доступ к главному прилодению.
            controller = loader.getController();
            controller.setMyModel(this);

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public void setDataToController(Object dataToController) {
        if(controller == null){
            System.out.println("error: controller == null in " + getClass().getSimpleName());
            return;
        }
        controller.setInputData(dataToController);
        controller.updateElementsData();
    }
    @Override
    public void updateData(){
        if(controller == null){
            System.out.println("error: controller == null in " + getClass().getSimpleName());
            return;
        }
        controller.updateElementsData();
    }

    protected void preShowInit(){};
    @Override
    public void show(){
        preShowInit();
        rootLayout.setCenter(thisLayout);
    }
    @Override
    public void close() {
        thisLayout.setVisible(false);
    }
}

