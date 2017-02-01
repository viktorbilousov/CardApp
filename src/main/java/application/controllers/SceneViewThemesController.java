package application.controllers;

import application.cardSystemProperty.ThemeProperty;
import application.model.Model;
import application.model.sceneModel.ViewThemesSceneModel;
import cardSystem.Theme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;

public class SceneViewThemesController implements Controller {

    private ObservableList<ThemeProperty> observableList = FXCollections.observableArrayList();
    private ArrayList<Theme> themeArrayList;
    private ViewThemesSceneModel myModel;

    @FXML
    TableView<ThemeProperty> themeTableView;
    @FXML
    TableColumn<ThemeProperty, String> c1;


    public ArrayList<Theme> getThemeArrayList() {
        return themeArrayList;
    }

    public void setThemeArrayList(ArrayList<Theme> themeArrayList) {
        this.themeArrayList = themeArrayList;
    }

    @FXML
    public void initialize() {
        c1.setCellValueFactory(cellData -> cellData.getValue().themeNameProperty());
    }

    @Override
    public void setMyModel(Model model) {
        this.myModel = (ViewThemesSceneModel) model;
    }


    public void updateElementsData() {
        observableList.clear();
        for(Theme theme : themeArrayList){
            observableList.add(new ThemeProperty(theme));
        }
       themeTableView.setItems(observableList);
    }
    @FXML
    public void newButton(){
        myModel.showAddThemeStage();
    }

    @FXML
    private void editButton(){
        if(themeTableView.getSelectionModel().getSelectedItem() == null){
            myModel.openErrorSelectWindow();
            return;
        }
        int index  =  themeTableView.getSelectionModel().getSelectedIndex();
        myModel.showEditTheme(themeArrayList.get(index));
    }

    @FXML
    public void openButton(){
        if(themeTableView.getSelectionModel().getSelectedItem() == null){
            myModel.openErrorSelectWindow();
            return;
        }
        int index = themeTableView.getSelectionModel().getFocusedIndex();
        myModel.showQuestionScene(index);
    }
    @FXML void deleteButton(){
        if(themeTableView.getSelectionModel().getSelectedItem() == null){
            myModel.openErrorSelectWindow();
            return;
        }
        int index =  themeTableView.getSelectionModel().getFocusedIndex();
        this.themeArrayList.remove(index);
        this.updateElementsData();
    }
}

