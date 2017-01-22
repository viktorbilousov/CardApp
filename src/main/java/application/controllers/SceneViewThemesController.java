package application.controllers;

import application.MainApp;
import application.cardSystemProperty.ThemeProperty;
import cardSystem.Theme;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.*;

public class SceneViewThemesController implements Controller {

    private ObservableList<ThemeProperty> observableList = FXCollections.observableArrayList();
    private ArrayList<Theme> themeArrayList;
    private MainApp mainApp;

    @FXML
    TableView<ThemeProperty> themeTableView;
    @FXML
    TableColumn<ThemeProperty, String> c1;

    public void setInputData(Object data) {
        if(!(data instanceof ArrayList)){
            System.out.println("error input data in " + getClass().getSimpleName());
            return;
        }
        themeArrayList = (ArrayList<Theme>)data;
    }

    @FXML
    public void initialize() {
        c1.setCellValueFactory(cellData -> cellData.getValue().themeNameProperty());
    }

    @Override
    public void setMainApp(Application mainApp) {
        this.mainApp = (MainApp)mainApp;
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
        System.out.println("new event");
    }
    @FXML
    public void editButton(){
        // TODO: 23.01.2017 null select event!
        int index = themeTableView.getSelectionModel().getFocusedIndex();
        mainApp.showViewQuestionOfTheme(index);
    }
    @FXML void deleteButton(){
        System.out.println("delete event");
    }
}

