package application.model;

import application.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;

public class StageModel implements Model {

    protected Controller controller;
    protected Pane rootLayout;
    protected Stage primaryStage;
    protected Model parent;

    private boolean isRootStage = false;

    public StageModel(Stage primaryStage, Model parent, URL FXMLLocation) {
        this.primaryStage = primaryStage;
        this.parent = parent;
        if(parent == null)
            isRootStage = true;

        init(FXMLLocation);
        this.getPrimaryStage().getIcons().add(new Image("icon.png"));
    }

    public void init(URL pathToFXML){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(pathToFXML);
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


    protected void preShowInit(){};
    @Override
    public void show(){
        preShowInit();
        if(!primaryStage.isShowing())
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
    @Override
    public void updateDataParent() {
        parent.updateData();
    }

}
