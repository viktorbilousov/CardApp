package application.model;

import application.MainApp;
import application.controllers.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StageModel implements Model {

    protected Controller controller;
    protected Pane rootLayout;
    protected Stage primaryStage;
    protected MainApp mainApp;

    public StageModel(Stage primaryStage, MainApp mainApp, String pathToFXML) {
        this.primaryStage = primaryStage;
        this.mainApp = mainApp;
        init(pathToFXML);
    }

    public void init(String pathToFXML){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(pathToFXML));
            rootLayout =  loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            controller = loader.getController();
            controller.setMyModel(this);

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }catch (Exception e){
            if(controller == null){
                System.out.println("error: controller == null in " + getClass().getSimpleName());
                return;
            }
            System.out.println(e);
        }
    }

    public Pane getRootLayout() {
        return rootLayout;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }


    @Override
    public void setDataToController(Object dataToController) {
        if(controller == null){
            System.out.println("error: controller == null in " + getClass().getSimpleName());
            return;
        }
        controller.setInputData(dataToController);
    }
    protected void preShowInit(){};
    @Override
    public void show(){
        preShowInit();
        primaryStage.show();
    }
    public void showAndWait(){
        primaryStage.showAndWait();
    }
    @Override
    public void close(){
        primaryStage.close();
    }
    @Override
    public void updateData() {
        if(controller == null){
            System.out.println("error: controller == null in " + getClass().getSimpleName());
            return;
        }
        controller.updateElementsData();
    }

}
